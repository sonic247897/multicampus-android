package exam.day03.view.advancedview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// 클릭리스너 - 버튼 클릭 이벤트 처리
// OnSeekBarChangeListener - seekbar를 드래그 할 때 발생하는 이벤트 처리
public class SeekBarActivity extends AppCompatActivity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    // SeekBar: 유저가 수치를 선택할 수 있다.
    // 뷰의 주소 값을 담을 참조변수
    SeekBar seek1, seek2;
    TextView text1, text2;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbar_main);

        seek1 = (SeekBar)findViewById(R.id.seekBar);
        seek2 = (SeekBar)findViewById(R.id.seekBar2);
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

        btn1 = findViewById(R.id.seekBtn1);
        btn2 = findViewById(R.id.seekBtn2);
        btn3 = findViewById(R.id.seekBtn3);
        btn4 = findViewById(R.id.seekBtn4);

        // 리스너 연결 - 이벤트 연결(위젯이 이벤트에 반응할 수 있도록 연결)
        btn1.setOnClickListener(this); // 액티비티가 Listener를 implements하고 있으므로
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        seek1.setOnSeekBarChangeListener(this);
        seek2.setOnSeekBarChangeListener(this);

        //SeekBarListener listener = new SeekBarListener();
       
    }

    // 버튼을 클릭할 때 자동으로 후촐되는 메소드
    // 모든 버튼이 클릭될 때 이 메소드가 실행되므로 구분해주기 위해 매개변수로 View를 받는다.
    //  - switch나 if문 사용
    // (매개변수로 전달되는 View가 이벤트를 발생시키는 소스객체)
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.seekBtn1:
                // 값을 증가시키기
                seek1.incrementProgressBy(1);
                seek2.incrementProgressBy(1);
                break;
            case R.id.seekBtn2:
                // 값을 감소시키기
                seek1.incrementProgressBy(-1);
                seek2.incrementProgressBy(-1);
                break;
            case R.id.seekBtn3:
                // 값을 설정하기
                seek1.setProgress(5);
                seek2.setProgress(7);
                break;
            case R.id.seekBtn4:
                // 값을 가져오기
                text1.setText(seek1.getProgress()+"");
                // setText, getText: 타입이 안맞으면 오류
                //  -> int에서 String으로
                text2.setText(seek2.getProgress()+"");
                break;
        }

    }

    // SeekBar의 값이 변경되었을 때 호출되는 메소드
    // fromUser: 내가 코드로 변경했는지(False), 사용자가 드래그로 변경했는지(True) 구분
    // progress: 변경된 값
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId(); //어떤 seekBar인지 알아냄
        // 버튼을 클릭하면 첫번째와 두번째 seekBar가 차례로 바뀌므로
        // 화면에는 "두 번째 seekbar: ~"만 뜬다.
        switch (id){
            case R.id.seekBar:
                text1.setText("첫 번째 seekbar:"+progress);
                break;
            case R.id.seekBar2:
                text1.setText("두 번째 seekbar:"+progress);
                break;
        }
        if(fromUser){
            text2.setText("사용자가 변경");
        }else{
            text2.setText("코드로 변경");
        }
    }

    // 값을 변경하기 위해서 seekbar에 터치를 시작할 때
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        int id = seekBar.getId();

        // R.id.~의 리턴값이 int이기 때문에 switch문의 id변수도 int
        switch (id){
            case R.id.seekBar:
                text1.setText("첫 번째 SeekBar를 터치 시작");
                break;
            case R.id.seekBar2:
                text1.setText("두 번째 SeekBar를 터치 시작");
                break;
        }
    }
    // 값의 변경을 끝내고 seekbar에 터치를 끝낼 때
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // 내부에 값을 전송하거나 db를 바꾸고 싶을 때 사용
        int id = seekBar.getId();

        switch (id){
            case R.id.seekBar:
                text1.setText("첫 번째 SeekBar를 터치 종료");
                break;
            case R.id.seekBar2:
                text1.setText("두 번째 SeekBar를 터치 종료");
                break;
        }
    }
}









