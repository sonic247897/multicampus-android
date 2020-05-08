package multi.android.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import multi.android.network.R;

public class MainActivity extends AppCompatActivity {
    TextView clientInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientInfo = findViewById(R.id.textInfo);
    }
    public void btn_connect(View v){
       new NetworkClient().start();
    }

    // swing에서 작업했던 네트워크 연결 코드를 전부 스레드로 빼서 작업하면
    // 동일하게 작동한다.
    // 화면을 물고 있기 때문에 스레드로 만든다.
    class NetworkClient extends Thread{
        @Override
        public void run() {
            Socket socket;
            InputStream is = null;
            DataInputStream dis = null;
            OutputStream os = null;
            DataOutputStream dos = null;
            try {
                // 서버 접속
                socket = new Socket("70.12.116.71", 12345);
                System.out.println("서버접속완료..."+socket);

                is = socket.getInputStream();
                dis = new DataInputStream(is);

                os = socket.getOutputStream();
                dos = new DataOutputStream(os);

                // 1. 클라이언트 <- 서버 (서버가 보내는 데이터를 읽기 - 2번 연속해서 읽기)
                final String data = dis.readUTF();
                Log.d("mynetwork","서버가 전송하는 메시지1: "+data);
                final int intdata = dis.readInt();
                Log.d("mynetwork","서버가 전송하는 메시지2: "+intdata);

                // 2. 클라이언트 -> 서버
                dos.writeUTF("안녕하세요 서버님 클라이언트입니다.");


                // => UI 그려주는 작업이 더 많아지면 AsyncTask-doInBackground에서 작업해야 한다.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clientInfo.append(data+"\n");
                        clientInfo.append(intdata+"\n");
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
  
    
}
