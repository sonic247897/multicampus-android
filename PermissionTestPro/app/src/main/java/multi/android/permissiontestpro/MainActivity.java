package multi.android.permissiontestpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    /* 퍼미션이 변경되었으면 기존에 있던 permission을 물고 있을 수 있기 때문에
        앱을 지우고 다시 설치해야 한다. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
