package multi.android.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    int progressVal;
    // android.os import
    Handler handler1;
    Handler handler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);

        // worker thread의 요청을 처리할 handler객체를 정의
        // Handler의 하위 객체를 익명으로 정의하고 생성
        handler1 = new Handler(){
            // sendMessage() 안에서 handleMessage() 호출
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d("mythread", "handleMessage 요청");
                textView.setText("progressbar 진행률: "+progressVal+"%");
                progressBar.incrementProgressBy(1);
            }
        };

        handler2 = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                // Log.d("mythread", msg.what+"");
                if(msg.what == 1){
                    int val = msg.arg1;
                    textView.setText("progressbar 진행률2: "+val+"%");
                    progressBar.incrementProgressBy(1);
                }
            }
        };

    }

    /*
    btnNoThread가 화면을 물고 있어서 UI 스레드(동일 쓰레드)는 아무 작업을 할 수 없다.
    100%가 되었을 때 실행완료 되면 UI에 프로그레스 바 100% 찍힘
    [화면을 변경하는 작업을 다른 메소드에서 처리]
    긴 시간 동안 실행하며 view를 변경하려고 하는 경우,
    실행되는 동안 다른 작업을 할 수 없다. 실행이 되는 동안 사용자의 이벤트가
    발생하고 이벤트에 5초 동안 반응하지 않으면 안드로이드 OS는
    어플리케이션을 강제 종료한다.
        => ANR(Application Not Responding)
          스마트폰: 전화가 우선순위가 제일 높기 때문에 인터럽트가 계속 들어오면 프로세스를 종료해버린다.
    오랫동안 처리해야 하는 작업을 UI 쓰레드에 정의하면 안 된다.
                                ---------
                                   ^
                                   |__ 별도의 작업 스레드를 정의하고 실행

    액티비티를 실행하면 기본적으로 메인스레드와 UI스레드가 돈다. -화면 만들고 이벤트 리스닝 계속 함
    */

    // cf) 웹: 알아서 동시접속 됨. 무결성 보장
    public void btnNoThread(View view){
        for(progressVal=1; progressVal<=100; ++progressVal){
            progressBar.setProgress(progressVal);
            SystemClock.sleep(1000); // 1초 동안 쉬게 (1초 동안 멈춰있는 효과)
        }
    }

    // 개발자가 만든 스레드 안에서 UI를 변경
    // - 잠정적인 문제점을 갖고 있는 방법(UI의 변경은 UI스레드에서만 작업)
    // 버전10: 진행률과 프로그레스바 모두 동작/ 버전7: 튕김 => 8레벨 이상에서부터 가능!!
    public void useThread(View view){
        // 프로그레스바에 진행상태가 출력되도록 설정
        // 프로그레스바의 progress가 변경되는 것을 스레드로 만들어서 실행
        // 개발자가 만든 스레드 - worker thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(progressVal=1; progressVal<=100; ++progressVal){
                    progressBar.setProgress(progressVal);
                    textView.setText("progressbar 진행률: "+progressVal+"%");
                    SystemClock.sleep(1000); // 1초 동안 쉬게 (1초 동안 멈춰있는 효과)
                }
            }
        }).start();
    }
    // 작업스레드가 핸들러에게 View에 대한 변경을 요청한다.(작업스레드에서 값을 핸들러에게 넘기기)
    // 핸들러는 작업스레드로부터 받은 요청정보를 꺼내서 뷰를 변경한다.
    public void useHandler(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(progressVal=1; progressVal<=100; ++progressVal){
                    // handler가 갖고 있는 Message객체를 매개변수로 전달하며 작업을 의뢰 - 100번 요청
                    handler1.sendMessage(handler1.obtainMessage());
                    SystemClock.sleep(100); // 0.1초 동안 쉬게 (0.1초 동안 멈춰있는 효과)
                }
            }
        }).start();
    }

    // 멤버변수가 아닌 변수를 제어해야 할 때(progressVal 사용 X)
    //  - 핸들러에게 작업을 의뢰할 때 Message객체를 생성해서 전달
    public void useMessageHandler(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=100; ++i){
                    // 변경할 뷰의 정보나 Handler에게 전달한 데이터를 Message객체로 생성한다.
                    // (ex. DB에서 불러온 ArrayList<DTO> 등)
                    Message msg = new Message();
                    // 그냥 숫자 - handler에게 작업을 의뢰한 스레드를 구분하기 위한 코드
                    // (안 그러면 스레드마다 handler 새로 만들어야 함)
                    msg.what = 1;
                    msg.arg1 = i; // 전달할 데이터
                    // Message객체(내가 만든 msg)를 전달하며 핸들러에게 작업을 의뢰
                    handler2.sendMessage(msg);
                    SystemClock.sleep(100); // 0.1초 동안 쉬게 (0.1초 동안 멈춰있는 효과)
                }
            }
        }).start();
    }
}
