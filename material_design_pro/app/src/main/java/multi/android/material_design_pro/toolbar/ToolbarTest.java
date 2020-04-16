package multi.android.material_design_pro.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import multi.android.material_design_pro.R;

/*
 Toolbar는 find해서 사용해야 하며
 직접 만든 디자인을 연결할 수 있다.
 */
public class ToolbarTest extends AppCompatActivity {
    TextView result;
    // androidx꺼 사용
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_test);
        result = findViewById(R.id.result);
        toolbar = findViewById(R.id.toolbar);

        // 액션바 대신 툴바를 사용하겠다고 정의
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 툴바의 아이콘, 메뉴가 선택될 때 - 손으로 터치
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 리소스 아이디 값이 하나씩 증가 ex) 2131230877, 2131230878, ..
        Log.d("menu", item.getItemId()+"");
        int id = item.getItemId();
        String msg = "";
        switch (id) {
            case R.id.option_1:
                msg = "첫 번째 메뉴 선택";
                break;
            case R.id.option_2:
                msg = "공유";
                break;
        }
        result.setText(msg);
        return super.onOptionsItemSelected(item);
    }
}
