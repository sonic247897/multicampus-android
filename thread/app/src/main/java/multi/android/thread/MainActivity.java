package multi.android.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    int progressVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.text1);
    }

    // 화면에 대해 리스닝 하는 작업을 해야하는데 sleep때문에
    // 바뀌는 작업 못하고 계속 리스닝만 해서 변화x (다른 버튼도 못만짐. 계속 만지면 꺼진다)
    // 스마트폰: 전화가 우선순위가 제일 높기 때문에 인터럽트가 계속 들어오면 프로세스를 종료해버린다.
    //  (실행흐름: UI를 만들고 리스닝을 하면서 기다림)
    // 웹: 알아서 동시접속 됨. 무결성 보장
    public void btnNoThread(View view){
        for(progressVal=1; progressVal<100; ++progressVal){
            progressBar.setProgress(progressVal);
            SystemClock.sleep(1000); // 1초 동안 쉬게 (1초 동안 멈춰있는 효과)
        }
    }
}
