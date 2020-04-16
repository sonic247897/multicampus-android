package multi.android.material_design_pro.tab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import multi.android.material_design_pro.R;
import multi.android.material_design_pro.exam.ListTestFragment;
import multi.android.material_design_pro.exam.ViewFragment1;
import multi.android.material_design_pro.exam.ViewFragment3;

public class TabTest extends AppCompatActivity {
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();

    // 화면 하나당 프래그먼트 1개씩 필요
    ViewFragment1 viewFragment1;
    ListTestFragment viewFragment2;
    ViewFragment3 viewFragment3;

    TabLayout tabLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test);
        viewFragment1 = new ViewFragment1();
        viewFragment2 = new ListTestFragment();
        viewFragment3 = new ViewFragment3();

        tabLayout = findViewById(R.id.mytab);
        bottomNavigationView = findViewById(R.id.bottomNavi);

        // 탭 추가(xml이 아니라 코드로 생성하는 방법도 있음)
        tabLayout.addTab(tabLayout.newTab().setText("설정"));

        // 처음 실행할 때 보여줄 프레그먼트 지정(메소드 체이닝)
        getSupportFragmentManager().beginTransaction().
                replace(R.id.contents_container, viewFragment1).commit();
        // 탭에 이벤트 연결하기(deprecated된 이벤트는 set이 아니라 add로 시작하는 새 메소드로 만들어놨다)
        // set: 1차 버전, add: 2차 버전
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 탭이 선택될 때 호출되는 메소드
            // 필요시 DB, 네트워크 연결
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 자바에서는 특이한 경우 빼고는 index가 0번부터 시작한다.
                int position = tab.getPosition(); // 탭의 순서를 받아오기(0부터 시작)
                Log.d("tab", position+"");
                Fragment fragment = null;
                if(position==0){
                    fragment = viewFragment1;
                }else if(position ==1){
                    fragment = viewFragment2;
                }else{
                    fragment = viewFragment3;
                }
                // 탭을 선택할 때 지정된 프레그먼트 객체가 show되도록 연결
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.contents_container, fragment).commit();
            }
            // DB, 네트워크 연결 끊음
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            // 이미 연결되어 있는 것이 있으므로 다시 연결하지 말고 가져와서 씀
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // bottomNavigationView 이벤트 연결
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.bottom_item2){
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.contents_container, viewFragment2).commit();
                }
                return false;
            }
        });

    }
}
