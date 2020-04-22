package multi.android.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AsyncTaskTest extends AppCompatActivity {
    TextView view1;
    TextView view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
        view1 = findViewById(R.id.txtView1);
        view2 = findViewById(R.id.txtView2);

        AsyncTaskExam asyncTaskExam = new AsyncTaskExam();
        // 스레드 시작 start()와 같음
        // 가변 매개변수 -> doInBackground()를 호출할 때 전달
        // 매개변수의 타입: AsyncTask의 첫번째 generic변수(전달해 줄 것이 없으면 Void 써주면 됨)
        asyncTaskExam.execute(10,20);

    }
    public void btn_click(View view){
        // 자바: 1960.1.1부터 계산된 시간
        long now_time = System.currentTimeMillis();
		view1.setText(now_time+"");
    }

    // 안드로이드의 AsyncTask 상속 -> 스레드 정의와 같음
    // AsyncTask클래스를 상속하여 작업할 클래스를 정의
    // (타입 없으면 Void)
    class AsyncTaskExam extends AsyncTask<Integer, Long, String>{
        // 초기화(UI 스레드)
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("myasync", "onPreExecute 호출.. 작업시작...");
        }
        // 스레드 시작(worker 스레드)
        @Override
        protected String doInBackground(Integer... integers) {
            // 가변은 배열로 관리!
            int num1 = integers[0];
            int num2 = integers[1];
            for(int i=1; i<=10; ++i){
                SystemClock.sleep(1000);
                Log.d("myasync", "i="+i+", num1="+num1+", num2="+num2);
                long now_time = System.currentTimeMillis();
                // 가변형 매개변수 - 내부에서 onProgressUpdate 호출할 때 전달
                publishProgress(now_time);
            }
            return "모든 처리 작업이 완료";
        }
        // 중간에 UI 변경(UI 스레드)
        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            // doInBackground에서 발생하는 값을 이용해서 화면을 변경하고 싶은 경우
            view2.setText("Async테스트: "+values[0]);
        }
        // 중간에 취소되면 처리
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
        // 종료하고 반납(UI 스레드)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            view1.setText("반환값: "+s);
        }
    }
}
