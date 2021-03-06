package multi.android.material_design_pro.appbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.StackView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import multi.android.material_design_pro.R;

public class AppbarTest extends AppCompatActivity {
    // androidx.appcompat
    Toolbar toolbar;
    ImageView app_bar_image;
    CollapsingToolbarLayout toolbarLayout;
    FloatingActionButton fab;
    ListView listView;
    ArrayList<String> datalist = new ArrayList<String>();
    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_test);
        toolbar = findViewById(R.id.toolbar);
        app_bar_image = findViewById(R.id.app_bar_image);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        bottomAppBar = findViewById(R.id.bottom_bar);
        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.mylistview);


        // 앱바 이미지 변경
        app_bar_image.setImageResource(R.drawable.lee);

        // 1. Appbar에 텍스트 추가, 변경
        toolbar.setTitle("툴바입니다.");
        toolbarLayout.setCollapsedTitleTextColor(Color.CYAN);
        toolbarLayout.setExpandedTitleColor(Color.WHITE);

        toolbarLayout.setCollapsedTitleGravity(Gravity.CENTER); // 접혀질 때
        // 상수이므로 +로 연결해줄 수 있다!
        toolbarLayout.setExpandedTitleGravity(Gravity.RIGHT+Gravity.TOP); // 펼쳐질 때

        // Fab: Floating action button 에 대한 설정을 바텀앱바에서 할 수 있다!
        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        bottomAppBar.setFabCradleRoundedCornerRadius(100); // 동그랗게 굴려줌
        bottomAppBar.setFabCradleMargin(20); // 바텀앱바와 fab 사이의 마진

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                datalist);
        listView.setAdapter(adapter);

        // FloatingActionButton을 눌렀을 때 대화상자가 뜨고 입력한 데이터가
        // 리스트뷰에 추가되도록 구현 (대화상자의 레이아웃 필요)
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(AppbarTest.this);
                // AlertDialog의 타이틀을 정의
                builder.setTitle("데이터 입력");

                // AlertDialog에 보여질 화면을 inflate
                // (layout inflater - xml을 펼쳐놓음)
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.input, null);
                // AlertDialog에 추가할 버튼을 정의
                // 주기능
                builder.setPositiveButton("확인", new DialogListener());
                // 부기능
                builder.setNegativeButton("취소", null);
                // AlertDialog에 화면 설정
                builder.setView(dialogView);
                builder.show();
            }
        });

    }
    // 이너클래스
    // (+나중에 로컬DB에 저장하는 방법)
    class DialogListener implements DialogInterface.OnClickListener{
        // dialog: 어떤 다이얼로그를 클릭했는지 전달됨
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialog에서 입력하는 내용을 ListView에 추가하기
            AlertDialog inputAlert = (AlertDialog) dialog;
            EditText input = inputAlert.findViewById(R.id.input);
            String data = input.getText().toString();
            datalist.add(data);

            // *내 리스트뷰에 설정된 어댑터를 가져온다.
            // ArrayList에 데이터를 추가한 후 adapter가 갖고 있는 데이터를 업데이트
            //  => Adapter에게 데이터가 변경되었음을 알려주는 작업
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }

}
