package multi.android.thread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TintTypedArray;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Logo extends AppCompatActivity {
    Handler handler;
    // 5초 후에 처리해야 하는 작업을 스레드로 정의(post - Runnable타입을 매개변수로 받음)
    // 익명 이너 클래스 이용(우항)
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Logo.this, HandlerExam2.class);
            startActivity(intent);
            finish();
            // 메인 액티비티로 전환될 때 애니메이션효과를 추가
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        handler = new Handler();
        // 5초 후에 실행
        handler.postDelayed(runnable, 5000);
    }
}
