package multi.android.material_design_pro2.cardview.exam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import multi.android.material_design_pro2.R;

public class CardImageRecyclerTest extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<CardItem> imglist = new ArrayList<CardItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 리싸이클러 뷰밖에 없으니까 그대로 써도 됨
        setContentView(R.layout.activity_circle_image_recycler_test);
        recyclerView = findViewById(R.id.recycler_view);

        // 데이터 생성 - R.drawable에는 화면 디자인을 위한 정적인 이미지만 저장할 수 있다.
        imglist.add(new CardItem(R.drawable.gong, "공유 도깨비"));
        imglist.add(new CardItem(R.drawable.jang, "모델"));
        imglist.add(new CardItem(R.drawable.jung, "정우성 비트"));
        imglist.add(new CardItem(R.drawable.lee, "이민호 신의"));
        imglist.add(new CardItem(R.drawable.so, "이 안에 너 있다"));

        // 어댑터 생성
        RecycleCardAdapter adapter = new RecycleCardAdapter(this,
                                                    imglist, R.layout.card_item);

        // 레이아웃 매니저 연결
        /*GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);*/
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        // 어댑터 연결
        recyclerView.setAdapter(adapter);

    }
}
