package multi.android.material_design_pro.tab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.ArrayList;

import multi.android.material_design_pro.R;

public class TabTest2 extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    // 프래그먼트를 담을 ArrayList
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    // 탭 문자열을 담을 ArrayList
    ArrayList<String> tabdatalist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test2);
        tabLayout = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);

        // 선택했을 때 색 다름
        tabLayout.setTabTextColors(Color.CYAN, Color.WHITE);
        for (int i = 1; i <= 10; ++i) {
            ChildFragment fragment = new ChildFragment();
            fragment.setTitle("작업중인 프래그먼트: " + i);
            fragmentArrayList.add(fragment);
            tabdatalist.add("탭 " + i);
            // 동적으로 탭 만들기(코드로 생성) -> 탭과 프래그먼트가 연결이 안된다!
            //tabLayout.addTab(tabLayout.newTab().setText("탭 " + i));
        }
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),
                                        fragmentArrayList.size());
        pager.setAdapter(adapter);

        // TabLayout과 ViewPager를 연결 - ViewPager의 getPageTitle 메소드를 호출해서
        // 탭의 문자열을 셋팅 (개수가 같아야 함)
        tabLayout.setupWithViewPager(pager);
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
            return tabdatalist.get(position);
        }
    }

}