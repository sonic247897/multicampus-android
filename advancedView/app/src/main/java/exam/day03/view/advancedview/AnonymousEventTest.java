package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// 이벤트 처리할 객체가 한 개인 경우 익명이너클래스를 이용해서 처리
public class AnonymousEventTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_test);
        Button btn = findViewById(R.id.mybtn);
        // 이벤트 핸들러(onClickListener)를 안에 만듬
        // View.OnClickListener: 인터페이스이므로 new로 생성할 수 없다.
        // <익명이너클래스>
        // new 인터페이스명() => 지정한 인터페이스의 하위객체를 만들어서
        // setOnClickListener의 매개변수로 전달하겠다는 의미
        // (인터페이스를 만들겠다는 의미 X)
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnonymousEventTest.this, "익명이너클래스", Toast.LENGTH_LONG).show();
            }
        });
    }
}
