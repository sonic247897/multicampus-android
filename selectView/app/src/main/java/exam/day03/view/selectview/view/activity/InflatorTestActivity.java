package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import exam.day03.view.selectview.R;

public class InflatorTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflator_test);
        // 정해져 있는 뷰를 부분 수정하기
        // fragment, 리싸이클러 뷰, adapter 수정 시 사용
        Button btn = findViewById(R.id.btnAdd);
        final LinearLayout container = findViewById(R.id.container);
        // 레이아웃
        btn.setOnClickListener(new View.OnClickListener() {
            // 객체 하나만 만들어지므로 계속 클릭해도 하나만 붙음
            @Override
            public void onClick(View v) {
                // 안드로이드 OS내부에서 제공해주는 시스템 서비스를 찾아와서 작업
                // Java API Framework단에서 작동하는 서비스.
                // context가 정보를 갖고 있다. (상수로 각 서비스를 정의)
                // @캐스팅 필수! : 여러 서비스를 받아오기 때문에
                LayoutInflater inflater
                        = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // 지정한 뷰를 parent view에 붙일 것이기 때문에 true
                inflater.inflate(R.layout.include_view, container, true);
            }
        });
    }
}
