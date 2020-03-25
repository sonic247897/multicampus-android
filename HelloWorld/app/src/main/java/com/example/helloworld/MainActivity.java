package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

// Activity의 LifeCycle
// 자동으로 화면이 한장 만들어짐
// 상속받음 = 스펙이 정해져 있다(LifeCycle을 내부에서 관리해준다)
public class MainActivity extends AppCompatActivity {
    // 반드시 호출되어야 하는 메소드
    // Activity가 생성될 때 자동으로 호출 - (액티비티가 실행: 1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sysout으로 출력하면 logcat에서 찾기 힘들다
        // System.out.println("onCreate호출~~~");
        Log.d("test","onCreate() 호출"); // tag: 이름표(filtering)
        // R=resource. layout.activity_main=layout 안에 있는 xml파일 이름
        setContentView(R.layout.activity_main);
    }
    // onCreate 다음으로 호출되는 메소드
    // - (액티비티가 실행: 2, 단, pause상태에서 빠져나올 때는 onCreate가 아니라 onStart부터 호출)
    // 일시정지 상태에서 빠져나올 때 호출
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test", "onStart() 호출");
    }
    // onStart 다음으로 호출되는 메소드(액티비티가 실행: 3)
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test", "onResume() 호출");
    }
    // 일시정지 상태로 바뀔 때 호출되는 메소드
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("test", "onPause() 호출");
    }
    // 일시정지나 종료 상태로 바뀔 때 onPause 다음으로 호출되는 메소드
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("test", "onStop() 호출");
    }
    // 앱이 종료될 때 호출되는 메소드
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test", "onDestroy() 호출");
    }


    // 버튼을 클릭했을때 실행할 메소드를 정의
    // 메소드의 매개변수에 실행할 버튼을 정의
    // Button의 상위인 View타입으로 정의
    public void myclickMethod(View v) {
        // 안드로이드의 메시지
        // 자동생성되는 문자열 = 정의된 매개변수에 내용을 넘김
        Toast.makeText(this, "확인버튼이 눌려졌습니다.", Toast.LENGTH_LONG).show();
    }

}
