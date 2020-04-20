package multi.android.fishing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    // 처음부터 지도에 포인트 다 띄우면 로딩 오래 걸리고 지저분하니까 지역 다 선택한 후에 거기 줌해서
    // 보여주기
    Spinner dropdown;
    Spinner dropdown2;
    String[] items = new String[]{"시도 전체", "서울특별시", "부산광역시", "대구광역시", "인천광역시"};
    String[] items2 = new String[]{"시군구 전체"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropdown = findViewById(R.id.spinner2);
        dropdown2 = findViewById(R.id.spinner3);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
    }
}
