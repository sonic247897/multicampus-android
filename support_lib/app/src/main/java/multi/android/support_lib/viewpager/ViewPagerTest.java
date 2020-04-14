package multi.android.support_lib.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import multi.android.support_lib.R;
/*
 화면 전환을 위해서 ViewPager를 사용하는 경우 (ListView와 동일)
 1. ViewPager에 담을 데이터 - View, Fragment
 2. 무조건 Adapter를 커스터마이징 해야 한다.
 3. ViewPager에 Adapter 연결하기
 */

public class ViewPagerTest extends AppCompatActivity {
    // 1. ViewPager에 표시할 뷰를 저장할 List
    ArrayList<View> viewlist = new ArrayList<View>();
    ViewPager mainPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_test);
        mainPager = findViewById(R.id.mainPager);

        // ViewPager에 담을 View객체 생성
        // getSystemService()로도 가져올 수 있는데 getLayoutInflater()라는 메소드도 존재한다.
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.view1, null);
        View view2 = inflater.inflate(R.layout.view2, null);
        View view3 = inflater.inflate(R.layout.view3, null);

        viewlist.add(view1);
        viewlist.add(view2);
        viewlist.add(view3);

        // 3. ViewPager에 어댑터 연결
        MainPagerAdapter adapter = new MainPagerAdapter();
        mainPager.setAdapter(adapter);
    }

    // 2. Adapter 커스터마이징
    class MainPagerAdapter extends PagerAdapter{
        // ViewPager를 통해 보여줄 뷰의 개수 리턴
        @Override
        public int getCount() {
            return viewlist.size();
        }
        // ViewPager에 보여줄 뷰를 등록
        //  - ListView의 getView와 같은 역할
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            // addView는 뷰 밖에 못 담는다(프레그먼트는 뷰가 아님)
            // [규칙] 뷰페이저에 담은 뷰를 리턴해줘야 한다.
            //  ->뷰페이저에 등록한 뷰를 확인한 후 맞으면 pass하는 역할을 하기 때문!
            mainPager.addView(viewlist.get(position));
            return viewlist.get(position);
        }
        // 매개변수로 전달된 뷰들끼리 비교
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            // 주소비교
            // view(생성해서 뷰페이지에 넣은 뷰)와 object(리턴한 뷰)가 같은지 확인
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            mainPager.removeView((View)object);
        }
    }
}
