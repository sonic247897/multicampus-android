package multi.android.material_design_pro2.recycler.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

import multi.android.material_design_pro2.R;

public class CircleImageRecyclerTest extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<CircleItem> imglist = new ArrayList<CircleItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_recycler_test);
        recyclerView = findViewById(R.id.recycler_view);

        // 데이터 생성 - R.drawable에는 화면 디자인을 위한 정적인 이미지만 저장할 수 있다.
        imglist.add(new CircleItem(R.drawable.gong));
        imglist.add(new CircleItem(R.drawable.jang));
        imglist.add(new CircleItem(R.drawable.jung));
        imglist.add(new CircleItem(R.drawable.lee));
        imglist.add(new CircleItem(R.drawable.so));

        // 어댑터 생성
        RecyclerCircleAdapter adapter = new RecyclerCircleAdapter(this,
                                                    imglist, R.layout.circle_item);

        // 레이아웃 매니저 연결
        /*GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);*/
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        // 어댑터 연결
        recyclerView.setAdapter(adapter);

    }
}
