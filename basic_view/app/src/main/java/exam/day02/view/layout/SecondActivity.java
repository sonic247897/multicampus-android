package exam.day02.view.layout;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// 액티비티가 실행될 때 TextView의 문자열을 변경
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 뷰를 메모리에 로딩한 후에 뷰에 접근할 수 있다
        setContentView(R.layout.activity_second);
        TextView tv = findViewById(R.id.second_txtView);
        tv.setText("안녕~~~~");
    }
}
