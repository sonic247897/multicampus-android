package multi.android.network.tcp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import multi.android.network.R;

public class ChatClientActivity extends AppCompatActivity {
    ListView msg_listview ;
    ListView user_listview ;
    EditText msg_edit;
    String nickname;
    Socket socket;
    /* ArrayList<ChatMessage> msg;*/
    // 벡터 안 써도 되나??
    ArrayList<String> msglist;
    InputStreamReader isr;
    BufferedReader br;

    OutputStream os;
    PrintWriter pw;
    Vector<String> userlist = new Vector<String>();
    StringTokenizer token;
    // 어댑터 2개
    ArrayAdapter  msgadapter;
    ArrayAdapter  useradapter;
    AsyncTaskExam asyncTaskExam;

    Handler writeHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_client);
        msg_listview = findViewById(R.id.chat_list);
        user_listview = findViewById(R.id.user_list);
        msg_edit = findViewById(R.id.msg_edit);
       /* msg = new ArrayList<ChatMessage>();*/
        msglist = new ArrayList<String>();
        msgadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, msglist);
        useradapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userlist);
        msg_listview.setAdapter(msgadapter);
        user_listview.setAdapter(useradapter);
        asyncTaskExam = new AsyncTaskExam();
    }
	//닉네임 입력 버튼을 누르면 호출되는 메소드
    public void nickname_input(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("데이터입력");
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.nick_name_view,null);
        builder.setPositiveButton("확인",new DialogListener());
        builder.setNegativeButton("취소",null);
        builder.setView(dialogView);
        builder.show();
    }


	//서버접속버튼을 누르면 호출되는 메소드
    public void server_connect(View view){
        msglist.add("서버와 연결되었습니다.");
        msg_listview.setAdapter(msgadapter);
        asyncTaskExam.execute(10,20);
    }
    public void btn_send(View view){
        // 네트워크 작업은 스레드에서 동작해야 한다
      sendMessage("chatting/"+msg_edit.getText().toString().trim()
              +"/"+nickname);
      msg_edit.setText("");
    }
    public void sendMessage(final String message) {
        //메시지를 서버에 전송할 수 있도록 작성하세요
        Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                pw.println(message); // print() 쓰면 안 가므로 println() 써야한다!
            }
        });
        sendThread.start();

    }

	//nickname을 다이얼로그를 통해서 입력받도록 구현한 리스너
    class DialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            AlertDialog inputAlert = (AlertDialog)dialog;
            EditText edit = inputAlert.findViewById(R.id.nickname);
            // 앞뒤 공백 자름
            nickname = edit.getText().toString().trim();
            if(userlist.size()==0){
                userlist.add(nickname);
                user_listview.setAdapter(useradapter);
            }else{ // 계속 입력하면 첫번째 원소 지우고 다시 add
                userlist.remove(0);
                userlist.add(nickname);
                user_listview.setAdapter(useradapter);
            }
            Toast.makeText(ChatClientActivity.this, "서버접속 버튼을 누르세요", Toast.LENGTH_LONG).show();
        }
    }



    //서버로부터 데이터 읽기
    class AsyncTaskExam extends AsyncTask<Integer,String,String> {
        @Override
        protected String doInBackground(Integer... integers) {
            //서버와 접속하여 서버가 보내오는 메시지를 읽을 수 있도록 작성하세요
            try {
                // 서버에 접속
                socket = new Socket("192.168.168.104", 12345);

                if(socket != null) {
                    // 서버와 통신할 수 있는 io 만들기
                    ioWork();
                }

                // 서버한테 nickname 보내기
                sendMsg(nickname);
                // 내 벡터에 저장 -> 앞에서 함
                // userlist.add(nickname);
                // 서버가 보내오는 메시지를 받는 스레드
                Thread receiveThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true) {
                            String msg = "";
                            try {
                                msg = br.readLine();
                                System.out.println("서버가 전달한 메시지>>"+msg);
                                filteringMsg(msg);
                            } catch (IOException e) {
                                // 서버쪽에서 연결이 끊어지는 경우 사용자는 자원을 반납 ==========
                                // - null체크 필요
                                msglist.add("서버와 연결이 끊어졌습니다.");
                                publishProgress("disconnect");
                                try {
                                    if(isr != null) isr.close();
                                    if(br != null) br.close();
                                    if(os != null) os.close();
                                    if(pw != null) pw.close();
                                    if(socket != null) socket.close();
                                } catch (IOException e1) {
                                }
                                break; // 오류 발생하면 반복문 빠져나가도록 설정
                            }
                        }
                    }
                });
                receiveThread.start();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        public void ioWork(){
            try {
                isr = new InputStreamReader(socket.getInputStream());
                br = new BufferedReader(isr);

                os = socket.getOutputStream();
                pw = new PrintWriter(os,true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void sendMsg(String msg){
            System.out.println("클라이언트가 서버에게 메시지 전송:"+msg);
            pw.println(msg);
        }

        // 서버가 보내오는 메시지를 분석하는 메소드
        private void filteringMsg(String msg){
            token = new StringTokenizer(msg,"/");
            String protocol = token.nextToken();
            String message = token.nextToken();
            System.out.println("프로토콜:"+protocol+",메시지:"+message);
            if(protocol.equals("new")){
                userlist.add(message);
                //내용을 추가하세요.
                msglist.add("****************"+message+"님이 입장하셨습니다.***************");
                publishProgress(protocol);
            }else if(protocol.equals("old")){
                userlist.add(message);
                //내용을 추가하세요.
                publishProgress(protocol);
            }else if(protocol.equals("chatting")){
                String nickname = token.nextToken();
                //내용을 추가하세요.
                msglist.add(nickname+">>"+message);
                publishProgress(protocol);
            }else if(protocol.equals("out")){
                userlist.remove(message);
                msglist.add("**************"+message+"님이 퇴장하셨습니다.******************");
                //내용을 추가하세요.
                publishProgress(protocol);
            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
           //코드를 추가하세요
            switch (values[0]){
                case "new":
                case "out":
                    user_listview.setAdapter(useradapter);
                    msg_listview.setAdapter(msgadapter);
                    break;
                case "old":
                    user_listview.setAdapter(useradapter);
                    break;
                case "chatting":
                case "disconnect":
                    msg_listview.setAdapter(msgadapter);
                    break;
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if(socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
