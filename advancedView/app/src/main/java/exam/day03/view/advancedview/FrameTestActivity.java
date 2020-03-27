package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FrameTestActivity extends AppCompatActivity {
    LinearLayout linear1;
    LinearLayout linear2;
    LinearLayout linear3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_test);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
    }
    // 버튼마다 메소드를 만들어주기 힘들기 때문에 이 방법을 사용한다.
    // => 리스너를 이용해 소스코드에서 이벤트를 처리해주는 방법이 아니라,
    //   attribute창의(xml속성) onClick에 메소드를 연결해주는 방법 사용!
    // Button이 클릭될 때 호출되는 메소드 = View.OnClickListener의
    // public void onClick(View v) 메소드와 동일한 역할
    public void myclick(View v){
        // if문으로 각각 작업하면 된다!
        if(v.getId() == R.id.button){
            linear1.setVisibility(View.VISIBLE);
            linear2.setVisibility(View.INVISIBLE);
            linear3.setVisibility(View.INVISIBLE);
        }else if(v.getId() == R.id.button2){
            linear1.setVisibility(View.INVISIBLE);
            linear2.setVisibility(View.VISIBLE);
            linear3.setVisibility(View.INVISIBLE);
        }else if(v.getId() == R.id.button3) {
            linear1.setVisibility(View.INVISIBLE);
            linear2.setVisibility(View.INVISIBLE);
            linear3.setVisibility(View.VISIBLE);
        }
    }

}
