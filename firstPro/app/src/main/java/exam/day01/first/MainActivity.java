package exam.day01.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // long short long
    public void confirm(View v){
        Toast.makeText(this, "확인 버튼 누름", Toast.LENGTH_LONG).show();
    }

    public void cancel(View v){
        Toast.makeText(this, "취소 버튼 누름", Toast.LENGTH_SHORT).show();
    }

    public void delete(View v){
        Toast.makeText(this, "삭제 버튼 누름", Toast.LENGTH_LONG).show();
    }

}
