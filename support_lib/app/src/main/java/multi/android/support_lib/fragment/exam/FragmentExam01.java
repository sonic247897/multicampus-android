package multi.android.support_lib.fragment.exam;

// support library: 외부에서 추가해야 하는 라이브러리 -> appcompat으로 이름 바뀜
// androidx
// android
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import multi.android.support_lib.R;

public class FragmentExam01 extends AppCompatActivity {
    // 화면 하나당 프래그먼트 1개씩 필요
    ViewFragment1 viewFragment1;
    ViewFragment2 viewFragment2;
    ViewFragment3 viewFragment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // layout_weight: (여백이 있다면 사이즈 재조절)
        // 둘 다 wrap_content일 때: weight가 있든 없든 화면을 나눠가짐
        // 둘 다 match_parent일 때: weight를 안 준 쪽이 화면을 다 차지한다.
        //      => weight속성이 있으면 이미 화면이 꽉차서 여백이 없으므로 화면에 나오지 X
        setContentView(R.layout.linear02);

        viewFragment1 = new ViewFragment1();
        viewFragment2 = new ViewFragment2();
        viewFragment3 = new ViewFragment3();

    }

    // @@이벤트에 붙이려면 반드시 뷰 객체를 받아야 한다!!
    public void btn_click(View view){
        setFragment(view.getTag().toString());
    }

    public void setFragment(String idx){
        Log.d("fragment", idx);
        // 프래그먼트를 관리하는 객체
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 프래그먼트의 변화를 관리하는 객체
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (idx){
            case "0":
                // 프래그먼트 영역을 교체 - 어느 영역에 넣을것인지: LinearLayout를 담고 있는 곳 =container
                transaction.replace(R.id.container, viewFragment1);
                break;
            case "1":
                transaction.replace(R.id.container, viewFragment2);
                break;
            case "2":
                transaction.replace(R.id.container, viewFragment3);
                break;
        }
        // 적용
        transaction.commit();
    }
}
