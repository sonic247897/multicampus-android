package multi.android.fishing_app;

// support library: 외부에서 추가해야 하는 라이브러리 -> appcompat으로 이름 바뀜
// androidx
// android

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import multi.android.fishing_app.ListTestFragment;
import multi.android.fishing_app.R;
import multi.android.fishing_app.ViewFragment1;


public class Main2Activity extends AppCompatActivity {
    // 1. ViewPager에 표시할 프래그먼트를 저장할 List
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    ViewPager fragment_viewPager;
    TabLayout tabLayout;
    // 탭 문자열을 담을 ArrayList
    String[] tab_txt = {"날씨", "어종"};

    // 화면 하나당 프래그먼트 1개씩 필요
    ViewFragment1 viewFragment1;
    ListTestFragment viewFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_exam);
        fragment_viewPager = findViewById(R.id.fragment_viewPager);
        viewFragment1 = new ViewFragment1();
        viewFragment2 = new ListTestFragment();
        tabLayout = findViewById(R.id.examtab);
        TabItem tab1 = findViewById(R.id.tab_item1);
        TabItem tab2 = findViewById(R.id.tab_item2);

        fragmentArrayList.add(viewFragment1);
        fragmentArrayList.add(viewFragment2);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),
                fragmentArrayList.size());
        fragment_viewPager.setAdapter(adapter);

        // TabLayout과 ViewPager를 연결 - ViewPager의 getPageTitle 메소드를 호출해서
        // 탭의 문자열을 셋팅 (개수가 같아야 함)
        tabLayout.setupWithViewPager(fragment_viewPager);

    }


    class PagerAdapter extends FragmentStatePagerAdapter{

        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
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

        // 뷰페이저와 탭을 연결하기 위해서 탭에 출력될 문자열을 만들어내는 메소드
        // 이게 없으면 탭은 만들어졌는데 탭에 연결된 문자열이 안보인다.
        // setupWithViewPager메소드 내부에서 탭의 문자열을 출력하기 위해서 호출한다.
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tab_txt[position];
        }
    }

}
