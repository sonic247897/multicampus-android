package multi.android.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExamFirstActivity extends AppCompatActivity {
    // android.R 리소스파일처럼 sequence 정의
    public static final int DATA_INTENT = 1001;
    public static final int OBJECT_INTENT = 1002;
    EditText name;
    EditText tel;
    Button btn;
    Button btn2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstexam);

        name = findViewById(R.id.EditText01);
        tel = findViewById(R.id.EditText02);
        btn = findViewById(R.id.Button01);
        btn2 = findViewById(R.id.Button02);
        result = findViewById(R.id.first_return);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 명시적 인텐트 - 같은 앱 내에서 실행(클래스명 명확하게 정의). 거의 못 쓴다
                // 암시적 인텐트 - 다른 앱과 데이터 공유할 때. 다른 사람이 만든 앱의 액티비티 클래스명 모름!
                Intent intent = new Intent(ExamFirstActivity.this, ExamSecondActivity.class);
                // 기본형+string만 공유할 수 있으므로 editable을 리턴하므로 toString()
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("tel", tel.getText().toString());

                startActivityForResult(intent, DATA_INTENT);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인텐트를 밖에 정의하고 버튼1,2에서 공유해도 된다.
                //액티비티를 호출하면서 인텐트에 객체를 공유
                Intent intent = new Intent(ExamFirstActivity.this, ExamSecondActivity.class);
                User dto = new User(name.getText().toString(), tel.getText().toString());
                intent.putExtra("dto", dto);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==DATA_INTENT){
            if(resultCode==RESULT_OK){
                boolean state = intent.getBooleanExtra("isChecked", false);
                if(state){
                    result.setText("우수회원설정");
                }else{
                    result.setText("일반회원설정");
                }
            }
        }
    }
}
