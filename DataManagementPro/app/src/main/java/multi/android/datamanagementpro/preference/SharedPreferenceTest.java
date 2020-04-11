package multi.android.datamanagementpro.preference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import multi.android.datamanagementpro.R;

// 앱을 지우고 다시 설치하면 지워진다
public class SharedPreferenceTest extends AppCompatActivity {
    EditText first_edit, second_edit;
    CheckBox noti;
    Switch siren;
    Button save;
    SharedPreferences setting;
    // setting에 저장하고 쓰는 역할
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference_test);
  
        first_edit = (EditText) findViewById(R.id.first_edit);
        second_edit = (EditText) findViewById(R.id.secod_edit);
        noti = (CheckBox) findViewById(R.id.check1);
        siren = (Switch) findViewById(R.id.check2);
        save = findViewById(R.id.save);

        // 설정정보를 저장할 수 있도록 지원되는 객체 - SharedPreferences
        // setting = getPreferences(Context.MODE_PRIVATE);
        //  -> 이렇게 가져오면 설정정보를 내 앱 안에 있는 다른 액티비티와 공유하지 못한다.
        // (설정정보는 여러 액티비티(화면)와 공유해야 한다)
        // 설정정보는 xml파일로 저장 - 액티비티명.xml

        // Context.MODE_PRIVATE은 다른 앱과 공유가 안 된다.
        setting = getSharedPreferences("setting", Context.MODE_PRIVATE);
        editor = setting.edit(); // 설정 정보 기록에 필요한 객체

        // 설정정보가 저장된 xml에서 값을 읽어와서 초기화 (정보를 setting이 갖고 있다)
        first_edit.setText(setting.getString("first", "")); // xml: name-value 쌍으로 저장
        second_edit.setText(setting.getString("second", ""));
        noti.setChecked(setting.getBoolean("noti", false));
        siren.setChecked(setting.getBoolean("siren", false));

        // 저장하기 버튼을 누르면 설정정보를 저장
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // map구조 저장 - put
                editor.putString("first", first_edit.getText().toString());
                editor.putString("second", second_edit.getText().toString());
                editor.putBoolean("noti", noti.isChecked());
                editor.putBoolean("siren", siren.isChecked());
                editor.commit(); //저장

                // clear메소드를 이용해서 지우는 것은 전체삭제
                // remove는 editor 안의 일부를 지우는 작업
            }
        });
	}
}
