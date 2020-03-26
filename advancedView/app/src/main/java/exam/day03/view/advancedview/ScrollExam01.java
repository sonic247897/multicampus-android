package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ScrollExam01 extends AppCompatActivity {
    ImageView img03;
    ImageView img04;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_exam01);
        img03 = findViewById(R.id.img03);
        img04 = findViewById(R.id.img04);
    }
    // 버튼이 클릭될 때 호출되는 메소드
    // View를 받아와야 한다
    // ▲ 버튼
    public void myclick1(View v){
        imgExchange1();
    }

    // ▲ 버튼을 선택할 때마다 이미지가 위에 위치하여 보이도록 구현
    public void imgExchange1(){
        if(index==1) {
            // 0번에 해당하는 이미지를 화면에 보이도록 설정
            img03.setVisibility(View.VISIBLE);
            // 1번은 화면에 보이지 않도록 설정
            img04.setVisibility(View.INVISIBLE);
            index = 0;
        }
        Log.d("value", "현재 index값===> " + index);
    }

    // ▼ 버튼
    public void myclick2(View v){
            imgExchange2();
    }

    // ▼ 버튼을 선택할 때마다 이미지가 아래에 위치하여 보이도록 구현
    public void imgExchange2(){
        if(index==0) {
            // 0번에 해당하는 이미지를 화면에 보이지 않도록 설정
            img03.setVisibility(View.INVISIBLE);
            // 1번은 화면에 보이도록 설정
            img04.setVisibility(View.VISIBLE);
            index =1;
        }
        Log.d("value", "현재 index값===> " + index);
    }

}
