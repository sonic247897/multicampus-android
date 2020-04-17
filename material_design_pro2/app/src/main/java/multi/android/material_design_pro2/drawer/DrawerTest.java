package multi.android.material_design_pro2.drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import multi.android.material_design_pro2.R;

public class DrawerTest extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.main_drawer);
        // 액션바에 버튼 설정 - 버튼을 선택하면 NavigationView가 display
        //                   버튼을 다시 선택하면 NavigationView가 화면에서 사라지도록 설정
        // 뷰 내부에서 작업할 때는 context가 무조건 필요하다
        // int로 받으므로 resource로 저장된 문자열 리소스를 불러와야 한다.
        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                                R.string.open_str, R.string.close_str);
        // 기본으로 설정해주는 버튼 추가
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // support 라이브러리
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.item1){
                    Toast.makeText(DrawerTest.this,
                            "내가 본 레시피", Toast.LENGTH_LONG).show();
                    // 액티비티 띄우려면 Intent 이용해주면 된다.
                }
                return false;
            }
        });
    }

    // drawerLayout은 onOptionsItemSelected 메소드를 반드시 구현해야 사용할 수 있다.
    // 액션바든 툴바든 어떤 메뉴 아이템을 선택하면 동작
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "onOptionsItemSelected", Toast.LENGTH_LONG).show();
        if(toggle.onOptionsItemSelected(item)){
            Toast.makeText(this, "if", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
