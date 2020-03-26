package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    /* 멤버변수로 선언해서 다른 함수에서도 접근할 수 있게 함 */
    ImageView img01;
    ImageView img02;
    int index; //멤버변수는 초기화 됨(0)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img01 = findViewById(R.id.img01);
        img02 = findViewById(R.id.img02);
    }
    // 버튼이 클릭될 때 호출되는 메소드
    // View를 받아와야 한다
    public void myclick(View v){
        index = 1-index;
        imageChange();
    }

    // 버튼을 선택할 때마다 이미지가 교체되어 보이도록 구현
    public void imageChange(){
        if(index==0){
            // 0번에 해당하는 이미지를 화면에 보이도록 설정
            img01.setVisibility(View.VISIBLE);
            // 1번은 화면에 보이지 않도록 설정
            img02.setVisibility(View.INVISIBLE);
            Log.d("value", "현재 index값===> "+index);
        }else if(index==1){
            img01.setVisibility(View.INVISIBLE);
            img02.setVisibility(View.VISIBLE);
            Log.d("value", "현재 index값===> "+index);
        }
    }
}
