package multi.android.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HandlerExam2 extends AppCompatActivity {
    int num;
    Handler handler;
    TextView numView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_exam);
        numView = findViewById(R.id.numTxt);
        // 2. UI스레드에서 Handler객체를 생성(구현 - 하위객체)
        //            onCreate메소드 내부에서 처리
        handler = new Handler();
    }

    public void btn_click(View view){
        // 버튼을 누르면 스레드를 start
        new NumThread().start();
    }

    // TextView의 값을 지속적으로 변경하는 스레드(실제 UI를 변경하는 스레드) - UI 스레드
    class UIUpdateThread implements Runnable{
        @Override
        public void run() {
            numView.setText(num+"");
        }
    }

    // 1. 동시 실행흐름을 처리할 내용을 스레드 객체로 구현
    // (지속해서 값을 만들고 요청하는 스레드) - worker 스레드
    class NumThread extends Thread{
        public void run(){
            for(int i=1; i<=10; ++i){
                num = i;
                // 핸들러에게 UI를 변경하는 스레드를 전달하며 실행해달라고 요청
                //  => 오래 걸리는 작업을 주면 안된다!
                handler.post(new UIUpdateThread());
                SystemClock.sleep(100);
            }
        }
    }

}
