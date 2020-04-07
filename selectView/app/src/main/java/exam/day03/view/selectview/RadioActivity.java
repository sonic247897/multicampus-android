package exam.day03.view.selectview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import exam.day03.view.selectview.R;

public class RadioActivity extends AppCompatActivity {
    // 뷰의 주소값을 담을 참조변수
    RadioButton radio3, radio4;
    RadioGroup group1, group2;
    TextView text1, text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        // 뷰의 주소 값을 얻어온다.
        radio3 = (RadioButton)findViewById(R.id.radioButton3);
        radio4 = (RadioButton)findViewById(R.id.radioButton4);
        group1 = (RadioGroup)findViewById(R.id.group1);
        group2 = (RadioGroup)findViewById(R.id.group2);
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

        // 라디오 그룹에 리스너를 설정한다.
        RadioListener listener = new RadioListener();
        group1.setOnCheckedChangeListener(listener);
        group2.setOnCheckedChangeListener(listener);
    }

    public void radioCheck(View v){
        radio3.setChecked(true);
        radio4.setChecked(true);
    }

    public void getCheckStatus(View v){
        // 라디오 그룹 내에서 선택되어 있는 라디오버튼을 반환하는 메소드가 이미 있다.
        int id1 = group1.getCheckedRadioButtonId();
        int id2 = group2.getCheckedRadioButtonId();
        text1.setText(id1+"radio 버튼이 선택");
        text2.setText(id2+"radio 버튼이 선택");
    }
    // RadioGroup.OnCheckedChangeListener - 라디오 그룹(그룹+버튼)
    // CompoundButton.OnCheckedChangeListener - 체크박스, 스위치(버튼)
    class RadioListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // 그룹 ID, 버튼 ID - 제어용
            Log.d("radio", group.getId()+",,,,,,,"+checkedId);
            switch (group.getId()){
                case R.id.group1:
                    switch (checkedId){
                        case R.id.radioButton:
                            Log.d("radio", "1번 그룹의 1-1버튼");
                    }
                    break;
                case R.id.group2:

            }
        }
    }

}










