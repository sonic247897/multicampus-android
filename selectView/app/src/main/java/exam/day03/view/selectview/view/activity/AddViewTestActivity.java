package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import exam.day03.view.selectview.R;

public class AddViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_view_test);
        // 처음부터 화면 만들기
        final LinearLayout layout = new LinearLayout(this); //대부분 context가 들어간다
        layout.setOrientation(LinearLayout.VERTICAL);

        /* 인플레이터 사용하지 않고 만들기 */
        // 1. Layout만들기 - width, height 지정
        // LayoutParams: 이너클래스 - 속성을 정의할 수 있게 한다
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT); //width, height
        // Layout에 추가할 view를 생성 - 상위 뷰의 크기 정보를 갖고 있는
        // LayoutParams를 설정
        Button btn = new Button(this);
        // setText(charSequence)
        // 안드로이드에서는 문자열을 charSequence로 다루고 있는데,
        // String도 charSequence를 상속하므로 String을 써도 된다.
        btn.setText("코드로 만들어진 버튼");
        btn.setLayoutParams(params); //상위에 종속적이므로 상위 params를 받는다.

        //Layout에 뷰를 추가
        layout.addView(btn);

        // 2. Activity에 레이아웃 붙이기
        setContentView(layout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // context: 안드로이드 OS 내부에서 필요한 공통의 요소들
                // activity, view 만드는 작업 등을 함
                //  => this가 가리키는 객체가 익명 이너클래스 내부이기 때문에 this 사용 X
                //      익명 이너클래스는 context가 될 수 없다.
                Button btn2 = new Button(AddViewTestActivity.this);
                btn2.setText("이벤트로 만들어진 객체");
                layout.addView(btn2);
            }
        });

    }

}
