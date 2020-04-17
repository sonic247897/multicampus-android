package multi.android.material_design_pro2.recycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import multi.android.material_design_pro2.R;

// @어댑터 커스터마이징 할 때의 구조는 항상 이와 같다! - ListView에서 getView에서 했던 작업을 메소드별로 나누어 놨다.
// Adapter<VH> : 어댑터 안에 뷰홀더 타입
// RecyclerView에서 사용하는 Adapter를 커스터마이징
// Adapter 안에 ViewHolder 포함 - 정의해야 한다.(ListView 사용할 때와 동일한 역할=
//                              row를 구성하는 리소스를 findViewById로 가져오는 작업)
//  => Inner Class로 정의!
public class SimpleItemAdapter
        extends RecyclerView.Adapter<SimpleItemAdapter.ViewHolder> {
    Context context;
    // row를 구성하는 layout(리소스는 int형으로 관리)
    int row_res_id;
    List<SimpleItem> data; // RecyclerView에 출력될 전체 데이터

    public SimpleItemAdapter(Context context, int row_res_id, List<SimpleItem> data) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
    }

    // xml로부터 뷰(한 row에 대한 뷰)를 만들어서 ViewHolder를 넘기는 작업
    // ViewHolder: View를 구성하는 구성요소의 리소스를 가져오는 작업을 하는 객체
    // 1. onCreateViewHolder에서 row에 대한 뷰를 inflate해서 생성
    // 2. ViewHolder객체를 만들어서 1번에서 생성한 뷰를 넘긴다.
    // 3. ViewHolder객체 안에서 onCreateViewHolder메소드에서 리턴받은 객체에서
    //   데이터를 연결할 뷰를 찾아온다.
    // 4. onBindViewHolder메소드에서 ViewHolder가 갖고 있는 구성요소에 데이터를 연결하기
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 액티비티가 아니므로 from 메소드 사용
        //  - Context로부터 LayoutInflater를 받는다.
        View view = LayoutInflater.from(context).inflate(row_res_id, null);
        // 화면을 만들어서 뷰홀더 생성자에 뷰를 넘김 => 뷰홀더가 findViewById 해야함
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        // getView와 똑같음(순서 출력)
        Log.d("recycler", "onBindViewHolder: "+position);
        // ViewHolder가 찾아놓은 TextView를 꺼내고
        TextView row_text_view = holder.txtView;
        // 꺼낸 TextView에 데이터 연결
        row_text_view.setText(data.get(position).getData());
        // TextView에 클릭 이벤트 연결
        row_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "데이터 연결 완료 "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // RecyclerView에 출력할 데이터의 개수 리턴
    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView; // simple_item.xml

        // 매개변수로 전달된 View: convertView같은 거(onCreateViewHolder메소드에서 받아옴)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.itemview);
        }
    }

}
