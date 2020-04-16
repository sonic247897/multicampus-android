package multi.android.support_lib.viewpager.exam;

// support library: 외부에서 추가해야 하는 라이브러리 -> appcompat으로 이름 바뀜
// androidx
// android

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import multi.android.support_lib.R;

public class FragmentExam01 extends AppCompatActivity {
    // 1. ViewPager에 표시할 프래그먼트를 저장할 List
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    ViewPager fragment_viewPager;

    // 화면 하나당 프래그먼트 1개씩 필요
    ViewFragment1 viewFragment1;
    ListTestFragment viewFragment2;
    ViewFragment3 viewFragment3;
    // Map 자체도 fragment인데 또 fragment로 만들어서 사용했다.(앞으로 다른방법 쓸것임)
    MapFragment viewFragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_exam);
        fragment_viewPager = findViewById(R.id.fragment_viewPager);
        viewFragment1 = new ViewFragment1();
        viewFragment2 = new ListTestFragment();
        viewFragment3 = new ViewFragment3();
        viewFragment4 = new MapFragment();

        fragmentArrayList.add(viewFragment1);
        fragmentArrayList.add(viewFragment2);
        fragmentArrayList.add(viewFragment3);
        fragmentArrayList.add(viewFragment4);

        // 3. ViewPager에 어댑터 연결
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragmentArrayList.size());
        fragment_viewPager.setAdapter(adapter);

        // 최신꺼라 set아니고 add 사용
        fragment_viewPager.addOnPageChangeListener(new PageListener());
    }

    public void btn_click(View view){
        // getTag()는 Object를 반환하므로 toString으로 변환해야한다.
        // fragment 뷰페이저에서 프래그먼트는 0부터 라벨링 되므로
        // 버튼의 tag도 그에 맞춰 0부터 줬다.(getTag에 추가로 연산할 필요 X)
        fragment_viewPager.setCurrentItem(Integer.parseInt(view.getTag().toString()));
    }

    // 2. Adapter 커스터마이징(이너클래스)
    class FragAdapter extends FragmentStatePagerAdapter {
        // behavior: 몇 번 동작해야하는지
        public FragAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }
    }

    // 이너클래스
    class PageListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        // 페이지가 변경되었을 때 - 이동할까 하다가 말아버리는 상황 있어서 복잡함
        @Override
        public void onPageSelected(int position) {
            Toast.makeText(FragmentExam01.this, "페이지가 전환",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
