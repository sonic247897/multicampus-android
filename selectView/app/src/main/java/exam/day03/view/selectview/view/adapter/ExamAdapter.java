package exam.day03.view.selectview.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exam.day03.view.selectview.view.activity.SelectView_ExamActivity;

public class ExamAdapter extends ArrayAdapter<ActorItem> {
    private Context context;
    private int resId;
    private ArrayList<ActorItem> datalist;

    // row마다 사용자가 설정한 값을 position과 함께 저장
    // 해당 position에 대한 설정 값을 같이 출력
    // 저장하는 시점은 사용자가 설정을 끝낸 시점 - focus를 잃어버리는 시점
    // (하나밖에 없으면 <Integer, String>으로 저장해도 된다)
    //  => isChecked 저장: Boolean
    HashMap<Integer, Boolean> saveData = new HashMap<Integer, Boolean>();
    public ExamAdapter(Context context, int resId, ArrayList<ActorItem> datalist) {
        super(context, resId, datalist);
        this.context = context;
        this.resId = resId;
        this.datalist = datalist;
    }

    // 리스트의 한 항목을 만들 때 호출되는 메소드 - 리스트 항목이 100개면 100번 호출
    // position => 리스트 순서
    // convertView => 한 항목에 대한 뷰
    // 움직일 때마다 getView가 계속 호출된다 -> 뷰를 계속 만듬(성능저하)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 뷰를 생성 - 매개변수로 전달되는 convertView를 재사용
        ViewHolder holder = null;
        if(convertView==null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);

            // ========== 뷰를 만드는 최초작업이므로 뷰를 찾아서 가져오기 ===========
            holder = new ViewHolder(convertView);
            // 홀더를 저장 (코드로도 레이아웃에 tag등을 set할 수 있다)
            convertView.setTag(holder);
        }else{
            // ============= 최초 작업이 아니라 뷰를 재사용하는 중이라면 ================
            holder = (ViewHolder)convertView.getTag();
        }

        // ArrayList에서 리턴된 리스트 항목의 번호와 동일한 데이터를 구하기
        ActorItem actor = datalist.get(position);
        if(actor != null){
            //위에서 생성한 뷰의 각 요소에 데이터를 연결
            // inflate로 convertView에 뷰를 다 붙였으므로 찾을 수 있다
            // (findByView도 리소스를 많이 쓰기 때문에 한번만 사용하고 저장된 것 불러오기)
            ImageView imageView = holder.Img;
            TextView nameView = holder.nameView;
            TextView dateView = holder.dateView;
            TextView msgView = holder.msg;
            final CheckBox checkView = holder.check;

            imageView.setImageResource(actor.Img);
            nameView.setText(actor.name);
            dateView.setText(actor.date);
            msgView.setText(actor.msg);
            // 뷰를 만들때 저장된 내용이 있는지 체크해서 값을 출력하기
            Boolean state = saveData.get(position);
            if(state == null){ //저장된 객체가 없으면
                checkView.setChecked(false); // 체크x
            }else{ //저장된 객체가 있으면 객체에서 data를 추출해서 출력
                checkView.setChecked(state);
            }

            /* 버튼, 체크박스 이벤트 등  - 순차 프로그램이 아니라 이벤트에 의해 발생하는 비동기 동작!*/
            // Checkbox가 focus를 잃어버리는 시점에 입력한 데이터를 저장하거나
            // setOnCheckedChangeListener를 쓰면
            checkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean data = checkView.isChecked();
                    saveData.put(position, data);
                }
            });
        }

        return convertView; //생성한 뷰를 리턴해야 함
    }
}
