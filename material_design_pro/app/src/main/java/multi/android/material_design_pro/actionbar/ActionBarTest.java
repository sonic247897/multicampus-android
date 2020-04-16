package multi.android.material_design_pro.actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import multi.android.material_design_pro.R;

public class ActionBarTest extends AppCompatActivity {
    TextView result;
    TextView result2;
    ListView listView;
    String[] datalist = {"java", "servlet", "jsp", "html", "android", "spring",
                        "spark", "sqoop", "spark", "sqoop", "spark", "sqoop"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_test);
        result = findViewById(R.id.result);
        result2 = findViewById(R.id.result2);
        listView = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                    android.R.layout.simple_list_item_1,
                                    android.R.id.text1,
                                    datalist);
        listView.setAdapter(adapter); // adapter가 데이터 형식에 맞게 화면 만들어 놓고
        // 이제부터 여러가지 설정

        // 리스트뷰가 검색이 가능하도록 설정(set) - 리스트 뷰에 있는 텍스트를 검색하게 할 수 있다.
        listView.setTextFilterEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);

        // 검색뷰가 셋팅되어 있는 메뉴아이템을 추출
        MenuItem search_item = menu.findItem(R.id.search);
        // 액션뷰로 설정되어 있는 뷰를 추출한다.
        // androidx의 SearchView (어떤 뷰를 설정할지 모르므로 캐스팅도 필요)
        SearchView searchView = (SearchView) search_item.getActionView();
        // 안내문구를 등록
        searchView.setQueryHint("검색어를 입력하세요");

        // 이벤트 연결하기
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 키패드의 엔터키(화면의 돋보기 모양 아이콘)를 누르면 호출되는 메소드
            @Override
            public boolean onQueryTextSubmit(String query) {
                result.setText(query);
                return true; // 동작하게 하려면 true를 리턴
            }
            // searchView의 텍스트가 변경될 때 호출 - keyUp과 같다.
            @Override
            public boolean onQueryTextChange(String newText) {
                result2.setText(newText);
                // 전달되는 입력하는 문자열을 이용하여 리스트뷰에서 필터링
                listView.setFilterText(newText);
                if(newText.length()==0){
                    // 텍스트 필터링 한 내용을 클리어 - 전체 목록이 펼쳐짐
                    listView.clearTextFilter();
                }
                return true; //true를 리턴
            }
        });

        // search item에 이벤트를 연결 - 펼쳐질 때
        search_item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            // 메뉴가 펼쳐질 때
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                result.setText("메뉴가 펼쳐짐");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                result.setText("메뉴가 접혀짐");
                return true;
            }
        });
        return true;
    }

    // 액션바의 아이콘, 메뉴가 선택될 때 - 손으로 터치
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 리소스 아이디 값이 하나씩 증가 ex) 2131230877, 2131230878, ..
        Log.d("menu", item.getItemId()+"");
        int id = item.getItemId();
        String msg = "";
        switch (id) {
            case R.id.search:
                msg = "첫 번째 메뉴 선택";
                break;
            case R.id.option_1:
                msg = "즐겨찾기";
                break;
            case R.id.option_2:
                msg = "공유";
                break;
            case R.id.option_3:
                msg = "설정";
                break;
        }
        result2.setText(msg);
        return super.onOptionsItemSelected(item);
    }
}
