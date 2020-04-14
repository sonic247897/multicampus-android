package multi.android.support_lib.viewpager.exam2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

import multi.android.support_lib.R;

public class FragmentExam02 extends AppCompatActivity {
    // 1. ViewPager에 표시할 프래그먼트를 저장할 List
    ArrayList<Fragment> fragmentlist = new ArrayList<Fragment>();
    ViewPager examViewpager;

    // 화면 하나당 프래그먼트 1개씩 필요



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_exam02);
    }
}
