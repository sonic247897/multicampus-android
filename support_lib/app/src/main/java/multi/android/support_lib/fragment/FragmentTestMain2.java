package multi.android.support_lib.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import multi.android.support_lib.R;

public class FragmentTestMain2 extends AppCompatActivity {
    //화면에 연결할 프레그먼트 객체를 생성한다.
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test_main2);
        // FrameLayout으로 추가될 영역만 xml에 설정하고 코드로 생성해서 붙인다.
        /*FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();*/
        Log.d("lifecycle", "Activity --> onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "Activity --> onStart");
    }

    // =========Fragment Activity=========
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "Activity --> onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "Activity --> onPause");
    }
    // =========Fragment Activity=========

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "Activity --> onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "Activity --> onDestroy");
    }


    // 메인에 대한 디자인
    // 1. add메소드를 이용하면 프레그먼트 객체를 생성한다.
    //  => 똑같은 객체는 한 개만 생성할 수 있다.(객체 생성 - "클래스 자체를 메모리에 올리는 것"
    //  과는 다른 개념! 프레그먼트 추가 객체를 생성했다는 것)
    //  => LinearLayout에 추가하면 레이아웃의 특성 상 영역 바깥에 추가되어 안보임
    //      (전부 match_parent로 설정했기 때문에)
    //  => FrameLayout에 추가하면 여러 프레그먼트가 겹쳐서 보임
    // 2. replace는 있으면 있는 객체를 연결, 없으면 새로 생성해서 연결
    public void first_click(View view){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        // add는 똑같은 객체 하나밖에 못만드므로 버튼 두번 누르면 에러난다.
        // 프레그먼트는 액티비티에 종속적이므로 (시스템에서 관리되는 독립적인 컴포넌트)
        // 메모리도 적게 차지하는데, 액티비티가 종료되면 같이 종료된다.
        //transaction.add(R.id.container, firstFragment);
        transaction.replace(R.id.container, firstFragment);
        // 프레그먼트는 액티비티의 lifecycle에 종속적이나 액티비티처럼 동작할 수 있도록
        // backstack에 등록할 수 있다. => 데이터 유지시키거나 마지막으로 되돌아가야 할 때 사용!
        //transaction.addToBackStack("first");
        transaction.commit();
    }
    public void second_click(View view){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //transaction.add(R.id.container, secondFragment);
        transaction.replace(R.id.container, secondFragment);
        //transaction.addToBackStack("second");
        transaction.commit();
    }

}
