package exam.day03.view.selectview;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity {
    // 뷰의 주소값을 담을 참조변수
    TextView text1;
    CheckBox[] checkArr = new CheckBox[3];
    Switch myswitch ;
    Button showStatus;
    Button setCheckBtn;
    Button clearCheckBtn;
    Button reverseCheckStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);
        // 뷰의 주소 값을 가지고 온다. - 26버전부터는 캐스팅 클래스를 정의하지 않아도 된다.
        // @위젯 - 이벤트를 발생시키는 소스 객체(findViewById)
        text1 = findViewById(R.id.checkTxt);
        checkArr[0] = findViewById(R.id.check1);
        checkArr[1] = findViewById(R.id.check2);
        checkArr[2] = findViewById(R.id.check3);
        showStatus = findViewById(R.id.btnCheck1);
        setCheckBtn = findViewById(R.id.btnCheck2);
        clearCheckBtn = findViewById(R.id.btnCheck3);
        reverseCheckStats = findViewById(R.id.btnCheck4);
        myswitch = findViewById(R.id.switch1);

        // @소스 객체에 알맞은 리스너를 붙여줘야 함(set~Listener)
        CheckBoxListener listener = new CheckBoxListener();
        // 체크박스에 리스너를 설정한다.
        for(int i=0; i<checkArr.length; ++i){
            checkArr[i].setOnCheckedChangeListener(listener);
        }

        myswitch.setOnCheckedChangeListener(listener);
        showStatus.setOnClickListener(listener);
        setCheckBtn.setOnClickListener(listener);
        clearCheckBtn.setOnClickListener(listener);
        reverseCheckStats.setOnClickListener(listener);
    }
    // 체크박스들의 상태를 TextView에 출력하기
    public void getCheckStatus(){
        text1.setText(""); // 매번 호출될 때마다 누적이 안되도록
        for(int i=0; i<checkArr.length; ++i){
            if(checkArr[i].isChecked()){
                //text1.append((i+1)+"번 체크박스가 체크가 설정됨\n");
                String tag = (String)checkArr[i].getTag();
                text1.append(tag+"번 체크박스가 체크가 설정됨\n");
            }
        }
    }
    // 모든 체크박스의 상태를 체크 상태로 설정 - 매개변수를 이용해서 설정 및 해제
    public void setCheckVal(boolean chkVal){
        for(int i=0; i<checkArr.length; ++i){
            checkArr[i].setChecked(chkVal);
        }
    }
    // 체크박스가 선택되어 있으면 해제, 해제되어 있으면 선택
    public void toggle(){
        for(int i=0; i<checkArr.length; ++i){
            checkArr[i].toggle();
        }
    }

    // @리스너 - 이벤트 처리하는 부분 (Activity 클래스 안에 이너클래스로 생성)
    // OnCheckedChangeListener - 체크박스가 선택되거나 해제될 때 호출되는 이벤트
    // OnClickListener - 버튼 클릭
    class CheckBoxListener
            implements CompoundButton.OnCheckedChangeListener, View.OnClickListener{
        // 안드로이드는 보통 if-else문이 3단계 depth까지도 간다.
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnCheck1:
                    setCheckVal(true);
                    break;
                case R.id.btnCheck2:
                    getCheckStatus();
                    break;
                case R.id.btnCheck3:
                    setCheckVal(false);
                    break;
                case R.id.btnCheck4:
                    toggle();

            }
        }
        // 체크박스의 상태가 변경될때 호출되는 메소드
        // Switch와 CheckBox 모두 CompoundButton을 상속한다.
        // 체크박스와 스위치가 선택되면 Toast로 "XXX체크 박스 선택",
        // 해제되면 "XXX체크 박스 해제"
        // 스위치도 체크 해제에 따라 토스트 출력

        // 체크박스가 너무 많아서 if-else문을 3개 이상 넣어야 할 때는 함수를 만들어서 코드를 줄인다.
        // 체크박스 순서, 텍스트 뷰(여러개일 경우), 체크 상태
        public void display(int index, TextView txtView, boolean checkState){
            if(checkState){
                txtView.setText(index+"번째 체크박스가 선택");
            }else{
                txtView.setText(index+"번째 체크박스가 해제");
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // Log.d("onCheckedChanged", buttonView.toString()+"::::"+isChecked);
            // 자바의 instanceof 연산자
            if(buttonView instanceof CheckBox){
                // 체크되면 TextView에 체크 메시지가 출력
                // getTag()는 Object를 반환하므로 형변환 해줘야 한다.
                display(Integer.parseInt(buttonView.getTag()+""), text1, isChecked);
            }else{
                // @이벤트 처리를 할 때 ID체크는 항상 해줘야 한다!
                if(buttonView.getId()== R.id.switch1){
                    String msg = "";
                    if(buttonView.isChecked()){
                        msg = "활성";
                    }else{
                        msg = "비활성";
                    }
                    // context에는 Activity 클래스를 명시해줘야 한다. (AppCompatActivity가 context를 갖고있다)
                    // context: 전반적인 내부 설정 정보를 갖고 메시지를 주고받을 수 있게 서포트 해주는 객체
                    // Activity클래스일 때만 this, 현재 이너클래스 안이기 때문에 못 씀
                    Toast.makeText(CheckActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}
