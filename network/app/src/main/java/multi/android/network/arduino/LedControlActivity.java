package multi.android.network.arduino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import multi.android.network.R;

public class LedControlActivity extends AppCompatActivity {
    AsyncTaskExam asyncTaskExam;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    Socket socket;
    OutputStream os;
    PrintWriter pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_control);
        asyncTaskExam = new AsyncTaskExam();
        // 10,20 안씀 - 나중에 수정
        asyncTaskExam.execute(10,20);
    }

    // 안드로이드는 서버와 연결, 데이터 받기, 보내기 모두 스레드로 만들어줘야 한다.
    public void send_msg(final View view){
       new Thread(new Runnable() {
           String message = "";
           @Override
           public void run() {
               if(view.getId() == R.id.btn_led_on){
                   message = "led_on";
               }else{
                   message = "led_off";
               }
               // 서버로 전송
               pw.println(message);
               pw.flush();
           }
       }).start();
    }

    class AsyncTaskExam extends AsyncTask<Integer,String,String> {
        @Override
        protected String doInBackground(Integer... integers) {
            try {
                // 라떼판다의 IP
                socket = new Socket("70.12.228.29", 12345);
                if (socket != null) {
                    ioWork();
                }
                // 서버에서 전달되는 메시지를 익는 스레드(지금은 사용x)
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String msg;
                            try {
                                msg = br.readLine();
                                Log.d("chat", "서버로 부터 수신된 메시지>>"
                                        + msg);
                            } catch (IOException e) {

                            }
                        }
                    }
                });
                t1.start();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
         void ioWork(){
            try {
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                os = socket.getOutputStream();
                // 서버 전송용
                pw = new PrintWriter(os,true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // 액티비티가 꺼졌을 때도 데이터를 받고 싶다면 다른 방법을 생각해봐야 한다.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
