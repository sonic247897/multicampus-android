package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {
    // 네트워크나 서비스의 진행상황 표현
    ProgressBar progressBar;
    Button btn1;
    Button btn2;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        // findViewById는 최신버전에서 캐스팅코드를 집어넣지 않아도 자동으로 캐스팅해준다.
        //progressBar = (ProgressBar)findViewById(R.id.progress2);
        progressBar = findViewById(R.id.progress3);
        btn1 = findViewById(R.id.progressBtn1);
        btn2 = findViewById(R.id.progressBtn2);
        btn3 = findViewById(R.id.progressBtn3);
        // 버그때문에 listner 사용
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1_click();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2_click();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3_click();
            }
        });
    }
    // 클릭은 코드가 길어지므로 편하게 만드는 방법을 제공해준다. (디자인 창에 onClick attribute 있음)
    // 인식을 못하는 버그가 발생할 수 있음
    public void btn1_click(){ //5증가
        progressBar.incrementProgressBy(5);
    }
    public void btn2_click(){ //5감소
        progressBar.incrementProgressBy(-5);
    }
    public void btn3_click(){ //값 셋팅
        progressBar.setProgress(80);
    }
}
