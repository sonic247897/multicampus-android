package multi.android.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExamSecondActivity extends AppCompatActivity {
    TextView result;
    Button ok;
    CheckBox memberState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_secondview);

        result = findViewById(R.id.exam_result_txt);
        ok = findViewById(R.id.exam_close);
        memberState = findViewById(R.id.member_state);

        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if(name==null){
            // name이 없다는 것은 Parcelable객체로 전달됐다는 뜻이므로
            User dto = intent.getParcelableExtra("dto");
            result.setText(dto.name+", "+dto.getTelNum());
        }else{
            String tel = intent.getStringExtra("tel");
            result.setText("입력한 name: "+ name+", 입력한 전화번호: "+ tel);
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("isChecked", memberState.isChecked());
                setResult(RESULT_OK, intent);
                finish();
            }
        });



    }
}
