package multi.android.material_design_pro2.recycler.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import multi.android.material_design_pro2.R;

public class RecyclerCircleAdapter extends RecyclerView.Adapter<RecyclerCircleAdapter.ViewHolder> {
    Context context;
    ArrayList<CircleItem> imglist;
    int row_res_id;

    public RecyclerCircleAdapter(Context context, ArrayList<CircleItem> imglist, int row_res_id) {
        this.context = context;
        this.imglist = imglist;
        this.row_res_id = row_res_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(row_res_id, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CircleImageView imageView = holder.img;
        // 게터메소드 사용 (또는 변수에 직접 접근해도 됨)
        imageView.setImageResource(imglist.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return imglist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }

}
