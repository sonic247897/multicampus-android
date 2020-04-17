package multi.android.material_design_pro2.cardview.exam;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import multi.android.material_design_pro2.R;

public class RecycleCardAdapter extends RecyclerView.Adapter<RecycleCardAdapter.ViewHolder> {
    Context context;
    ArrayList<CardItem> cardlist;
    int row_res_id;

    public RecycleCardAdapter(Context context, ArrayList<CardItem> imglist, int row_res_id) {
        this.context = context;
        this.cardlist = imglist;
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
        ImageView imageView = holder.img;
        TextView txtView = holder.txt;
        // 게터메소드 사용 (또는 변수에 직접 접근해도 됨)
        imageView.setImageResource(cardlist.get(position).getImg());
        txtView.setText(cardlist.get(position).getTxt());
    }

    @Override
    public int getItemCount() {
        return cardlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            txt = itemView.findViewById(R.id.textView);
        }
    }

}
