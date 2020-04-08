package exam.day03.view.selectview.exam;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import exam.day03.view.selectview.R;

public class ExamAdapter extends ArrayAdapter<ActorItem> {
    private Context context;
    private int resId;//view에 대한 리소스
    private ArrayList<ActorItem> data;//핸들링할 데이터
    private HashMap<Integer,MyMemento> userStateValue = new HashMap<Integer,MyMemento>();
    private int value;

    public ExamAdapter(Context context,
                     int textViewResourceId,	ArrayList<ActorItem> objects) {

        super(context, textViewResourceId, objects);
        // 생성자는 한 번만 호출
        Log.d("constructor","생성자호출");
        this.context = context;
        this.resId = textViewResourceId;
        this.data = objects;
        // 14개 데이터
        Log.d("constructor",data.size()+"");
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // 최초 작업일 때 - 리스트 뷰는 기본으로 10개 row뷰 생성(한 화면 구성)
        if(convertView==null){
            value++;
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId,null);
            ViewHolder itemView = new ViewHolder(convertView);

            // 객체를 저장하거나 이름을 저장
            convertView.setTag(itemView);
            convertView.setTag(R.string.app_name,value); // 이름을 줄 때는 리소스 형태로 정의해야 한다
            // if문이 몇번이나 실행되는지?
            // 최초 작업==>차범근======>convertView::::::
            // android.widget.LinearLayout{39b4557 V.E...... ......I. 0,0-0,0}::::2
            // 원소 상관없이 최초 10개 생성(리스트 뷰의 특성 - 10개 row 만들고 재활용함)
            Log.d("firstjob", "최초 작업==>"+data.get(position).name
                    +"======>convertView::::::"+convertView+"::::"+value);
        }
        // 최초 작업 아닐 때 여기서부터 실행
        ViewHolder itemView = (ViewHolder)convertView.getTag();
        ActorItem actorItem = data.get(position);

        if(actorItem!=null) {
            ImageView myimg = itemView.myimg;
            final TextView nameView = itemView.nameView;
            TextView dateView = itemView.dateView;
            TextView info = itemView.info;
            final CheckBox chkVal = itemView.chkVal;
            myimg.setImageResource(actorItem.myImg);
            nameView.setText(actorItem.name);
            dateView.setText(actorItem.date);
            info.setText(actorItem.info);
            MyMemento state = userStateValue.get(position);
            if(state ==null){
                //한 번도 저장한 적이 없는 경우
                // 재사용하여 다른뷰로 초기화된 2번 뷰가 false로 넘어감
                Log.d("check","널"+position);
                chkVal.setChecked(false);
            }else{
                Log.d("check","널아님"+position);
                chkVal.setChecked(state.check);
            }
            // convertView 재사용
            //  => 위의 화면에서 쓰던 뷰(39b4557)를, 다음화면으로 넘어가면 밑의 새로운 멤버가 뷰를 재사용해서 쓴다!
            // 최초 작업==>차범근======>convertView::::::android.widget.LinearLayout{39b4557 V.E...... ......I. 0,0-0,0}::::2
            //D/firstjob: ActorItem{myImg=2131165281, name='차범근', date='2020/04/06', info='아들~~'}*********2*********android.widget.LinearLayout{39b4557 V.E...... ......I. 0,0-0,0}
            //D/getview: ActorItem{myImg=2131165286, name='이민호', date='2020/04/06', info='멋져'}*********2*********android.widget.LinearLayout{39b4557 V.E...... ......ID 0,-195-1080,15}
            //D/getview: ActorItem{myImg=2131165286, name='이민호', date='2020/04/06', info='멋져'}*********2*********android.widget.LinearLayout{39b4557 V.E...... ......ID 0,-208-1080,2}
            Log.d("getview",actorItem.toString()+"*********"
                    +convertView.getTag(R.string.app_name)+"*********"
                    +convertView);
            // @체크박스는 onClick으로 주는게 권고사항!!
            chkVal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d("onCheckedChanged",isChecked+":"+position);
                    boolean data = chkVal.isChecked();
                    MyMemento state = new MyMemento();
                    state.check = data;
                    // 차범근 2,1(체크됨)
                    // - 화면이 넘어가서 차범근 뷰가 사라지면 position이 다른 뷰가 2번을 쓴다.
                    userStateValue.put(position,state);
                }
            });
        }
        return  convertView;
    }

    class MyMemento{
        boolean check;
    }

    class ViewHolder{
        ImageView myimg;
        TextView nameView;
        TextView dateView;
        TextView info;
        CheckBox chkVal;
        ViewHolder(View parentView){
            Log.d("viewholder","ViewHolder호출");
            this.myimg = parentView.findViewById(R.id.myImg);
            this.nameView = parentView.findViewById(R.id.name);
            this.dateView = parentView.findViewById(R.id.date);
            this.info = parentView.findViewById(R.id.resultinfo);
            this.chkVal = parentView.findViewById(R.id.exam_chk);
        }
    }
}

