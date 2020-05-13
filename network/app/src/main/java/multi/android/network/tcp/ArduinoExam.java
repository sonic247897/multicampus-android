package multi.android.network.tcp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import multi.android.network.R;

public class ArduinoExam extends AppCompatActivity {
    Button onBtn;
    Button offBtn;

    InputStream is;
    OutputStream os;
    Socket socket;
    AsyncTaskExam asyncTaskExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_exam);
        onBtn = findViewById(R.id.onBtn);
        offBtn = findViewById(R.id.offBtn);

        asyncTaskExam = new AsyncTaskExam();
    }

    public void LEDon(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    os.write(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void LEDoff(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    os.write(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    class AsyncTaskExam extends AsyncTask<Integer,String,String> {
        // 서버 접속
        @Override
        protected String doInBackground(Integer... integers) {
            try {
                socket = new Socket("70.12.116.55", 12345);
                if(socket!=null){
                    ioWork();
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  "";
        }

        public void ioWork() {
            try {
                is = socket.getInputStream();
                os = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if(is != null) is.close();
            if(os != null) os.close();
            if(socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
