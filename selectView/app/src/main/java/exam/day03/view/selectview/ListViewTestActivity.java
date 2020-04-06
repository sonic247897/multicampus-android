package exam.day03.view.selectview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewTestActivity extends AppCompatActivity {
    // 1. ListView에 출력할 데이터
    String[] datalist = {"java", "oracle", "HTML5", "CSS", "javascript", "servlet",
                    "jsp", "spring", "hadoop","flume","sqoop","hive","R", "android"};
    ListView listview;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_test);
        listview = findViewById(R.id.listview1);
        txt = findViewById(R.id.listTxt);
        /*
        리스트뷰에 출력할 데이터?
        어떤 디자인으로 출력? */
        // 2. Adapter 객체를 선택해서 생성
        // resource - 한 row에 대한 디자인 리소스
        // 내가 만든 리소스 - R.~ 접근, 안드로이드 제공 리소스 - android.R~ (자동으로 스크롤)
        ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, datalist);
        // 3. ListView에서 어댑터가 작업할 수 있도록 ListView에 어댑터 셋팅
        listview.setAdapter(adapter);

        //이벤트 연결
        MyListener listener = new MyListener();
        listview.setOnItemClickListener(listener);
    }

    class MyListener implements AdapterView.OnItemClickListener{
        // view: 목록, position: 몇 번째 리스트인지, id: 그 아이디
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            txt.setText(datalist[position]);
        }
    }
}
