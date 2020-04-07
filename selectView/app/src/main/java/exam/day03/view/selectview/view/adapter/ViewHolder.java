package exam.day03.view.selectview.view.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import exam.day03.view.selectview.R;

// 모든 뷰에 대해서 findViewById를 최소화하기 위해 정의
public class ViewHolder {
    ImageView Img;
    TextView nameView;
    TextView dateView;
    TextView msg;
    CheckBox check;

    //객체가 생성될 때 targetView(parentView)-convertView를 전달받는다.
    public ViewHolder(View parentView) {
        this.Img = parentView.findViewById(R.id.examImg);
        this.nameView = parentView.findViewById(R.id.txtExam1);
        this.dateView = parentView.findViewById(R.id.txtExam2);;
        this.msg = parentView.findViewById(R.id.txtExam3);;
        this.check = parentView.findViewById(R.id.chkExam);;
    }
}
