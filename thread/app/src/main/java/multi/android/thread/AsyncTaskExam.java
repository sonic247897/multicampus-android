package multi.android.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskExam extends AppCompatActivity {
    Button btn1;
    Button btn2;
    TextView textView;
    ProgressBar progressBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_exam);

        btn1 = findViewById(R.id.button3);
        btn2 = findViewById(R.id.button4);
        textView = findViewById(R.id.textView2);
        progressBar = findViewById(R.id.progressBar2);
        imageView = findViewById(R.id.imageView2);

        btn2.setEnabled(false);

    }

    public void btn_click(View view){
        BtnAsyncTask btnAsyncTask = new BtnAsyncTask();
        btnAsyncTask.execute();
    }

    public void btn_click2(View view){

    }

    // 안드로이드의 AsyncTask 상속 -> 스레드 정의와 같음
    // AsyncTask클래스를 상속하여 작업할 클래스를 정의
    // (타입 없으면 Void)
    class BtnAsyncTask extends AsyncTask<Void, Integer, String> {
        int sum =0;

        @Override
        protected void onPreExecute() {
            // 실행 중에는 다시 못누르게 함
            btn1.setEnabled(false);
            btn2.setEnabled(true); //취소 버튼
        }

        @Override
        protected String doInBackground(Void... voids) {
            for(int i=1; i<=50; ++i){
                if(isCancelled() == true){
                    break;
                }
                sum+=i;
                SystemClock.sleep(100);
                publishProgress(i);
            }
            return sum+"";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int num = values[0];
            textView.setText(num+"");
            progressBar.setProgress(num);
            if(num%2 ==0){
                imageView.setImageResource(R.drawable.d1);
            }else{
                imageView.setImageResource(R.drawable.d2);
            }
        }

        // 종료하고 반납(UI 스레드)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            btn1.setEnabled(true);
            btn2.setEnabled(false); //취소버튼 비활성
            textView.setText("Result: "+s);
        }
    }
}
