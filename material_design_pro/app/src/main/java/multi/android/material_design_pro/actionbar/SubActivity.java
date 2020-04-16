package multi.android.material_design_pro.actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import multi.android.material_design_pro.R;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 안드로이드에서 제공하는 뒤로가기 버튼(홈 버튼: 화살표, id가 home)을 액션바에 추가하기
        // 액션바를 얻어오기
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // 안드로이드에서 제공: android. 추가
        if(id == android.R.id.home){
            finish(); //액티비티 종료
        }
        return super.onOptionsItemSelected(item);
    }
}
