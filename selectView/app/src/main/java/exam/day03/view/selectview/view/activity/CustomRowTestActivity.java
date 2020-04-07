package exam.day03.view.selectview.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import exam.day03.view.selectview.R;

public class CustomRowTestActivity extends AppCompatActivity {
    // 1. ListView에 출력할 데이터 - 커스텀row로 리스트뷰를 구성하는 경우
    //                        데이터를 ArrayList로 정의해야 한다(ArrayAdapter 생성자가 그거밖에 없음)
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
        // txtcust1 한 개에만 변화를 줄 수 있다.(이름) => 이미지, 내용, 날짜 등 추가로 변화 주고싶음
        // cf) 자바 - dto객체, 배열, 클래스 리턴하여 여러개 리턴(한개만 리턴할 수 있으므로)

        // 3. ListView에서 어댑터가 작업할 수 있도록 ListView에 어댑터 셋팅
        listview.setAdapter(adapter);

        //이벤트 연결
        MyListener listener = new MyListener();
        listview.setOnItemClickListener(listener);

        // 문제는 개별 이벤트가 붙지 않는다는 것! -> cutrow에 있는 각 button을 찾아와야 하는데 문제점
        Button custBtn = findViewById(R.id.btncust);
        // NULL pointer exception: findViewById로 못 받아온다. ArrayAdapter 내부에서 작업했기 때문에
        // 내가 건드릴 수 없음 -> 내가 어댑터 만들어야 한다!
        custBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "반응 있나?");
            }
        });

    }

    class MyListener implements AdapterView.OnItemClickListener{
        // view: 목록, position: 몇 번째 리스트인지, id: 그 아이디
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            txt.setText(datalist.get(position));
        }
    }
}
