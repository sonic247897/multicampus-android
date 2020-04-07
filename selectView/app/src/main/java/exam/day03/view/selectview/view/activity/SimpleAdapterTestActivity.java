package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

// 이전버전에서 사용한 방법: 액티비티 하나가 List가 되어버려서 뷰가 필요 없다.
public class SimpleAdapterTestActivity extends ListActivity {
    // 두 줄 텍스트로 리스트뷰를 구성하기 (설명 - 부연설명)
    ArrayList<HashMap<String, String>> listdata = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_simple_adapter_test);
        // 리스트를 구성할 샘플 데이터 준비
        HashMap<String,String> item = new HashMap<String,String>();
        item.put("name", "김현정");
        item.put("telNum", "010-1111-2222");
        listdata.add(item);

        item = new HashMap<String, String>();
        item.put("name", "김고기");
        item.put("telNum", "010-1111-2222");
        listdata.add(item);

        item = new HashMap<String, String>();
        item.put("name", "김헬스");
        item.put("telNum", "010-1111-2222");
        listdata.add(item);

        item = new HashMap<String, String>();
        item.put("name", "야호호");
        item.put("telNum", "010-1111-2222");
        listdata.add(item);

        item = new HashMap<String, String>();
        item.put("name", "ㅇㅅㅇ");
        item.put("telNum", "010-1111-2222");
        listdata.add(item);

        // 두 줄 처리해주는 어댑터 - 자동으로 리스트를 구성해줌
        SimpleAdapter adapter = new SimpleAdapter(this,
                    listdata, // HashMap으로 구성된 데이터가 저장된 리스트
                    android.R.layout.simple_list_item_2, // row의 디자인(2줄짜리)
                    new String[]{"name","telNum"}, //HashMap에 저장된 키 리스트
                    //위에서 정의한 맵 데이터를 어떤 view에 출력할 것인지
                    //키의 순서와 동일한 리소스 아이디 순서대로 매핑
                    new int[]{android.R.id.text1, android.R.id.text2}
                );
        // ListActivity를 상속했기 때문에 자체적으로 함수가 존재한다.
        setListAdapter(adapter);

    }
}
