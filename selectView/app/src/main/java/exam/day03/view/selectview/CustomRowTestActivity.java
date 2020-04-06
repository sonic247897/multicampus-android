package exam.day03.view.selectview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CustomRowTestActivity extends AppCompatActivity {
    // 1. ListView에 출력할 데이터 - 커스텀row로 리스트뷰를 구성하는 경우
    //                              데이터를 ArrayList로 정의해야 한다(생성자가 그거밖에 없음)
    ArrayList<String> datalist = new ArrayList<String>();
    ListView listview;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_test);
        listview = findViewById(R.id.listview1);
        txt = findViewById(R.id.listTxt);
        datalist.add("김현정");
        datalist.add("김김이");
        datalist.add("김현");
        datalist.add("앵무새");
        datalist.add("과자줘");
        /*
        리스트뷰에 출력할 데이터?
        어떤 디자인으로 출력? */
        // 2. Adapter 객체를 선택해서 생성
        // 커스텀 디자인을 row로 사용할 것이므로 어떤 뷰에 데이터를 연결할 것인지 설정
        ArrayAdapter adapter = new ArrayAdapter(this,
                        R.layout.custrow, R.id.txtcust1, datalist);
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
            txt.setText(datalist.get(position));
        }
    }
    // 문제는 개별 이벤트가 붙지 않는다는 것!
}
