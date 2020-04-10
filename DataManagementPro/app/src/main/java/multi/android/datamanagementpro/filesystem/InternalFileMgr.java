package multi.android.datamanagementpro.filesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import multi.android.datamanagementpro.R;

// 내부저장소 - 이 앱만 사용하므로 권한 체크 할 필요 X
// 외부저장소 - 다른 것들을 건드리지 않도록 권한 체크 필요
public class InternalFileMgr extends AppCompatActivity {
    TextView internalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internalTxt = findViewById(R.id.fileTxt);
    }
    // 파일 I/O - 자바에서 썼던 input/output stream
    public void saveInternalFile(View v){
        // 내부저장소는 데이터를 저장하거나 읽어올 때 스트림을 직접 생성하지 않는다.
        //      => data
        // openFileOutput을 이용
        // 매개변수 name은 파일명
        // mode => MODE_APPEND : 기존 파일에 내용을 추가
        //         MODE_PRIVATE : 기존 파일을 덮어쓰겠다는 의미
        FileOutputStream fos = null; //자바 기능
        DataOutputStream dos = null;
        try {
            fos = openFileOutput("myfile.txt", MODE_PRIVATE); // new FileOutputStream으로 생성 x
            // DataOutputStream으로 한번 덧대도 된다(안해도 됨)
            dos = new DataOutputStream(fos);
            dos.writeUTF("테스트 중..."); // String쓰기
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dos != null){
                    // 하나만 close시켜도 둘 다 close된다.
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openInternalFile(View v){
        FileInputStream fis = null; //자바 기능
        DataInputStream dis = null;
        try {
            fis = openFileInput("myfile.txt"); // new FileInputStream으로 생성 x
            // DataInputStream으로 한번 덧대도 된다(안해도 됨)
            dis = new DataInputStream(fis);
            String data = dis.readUTF(); // String 읽기

            internalTxt.setText(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dis != null){
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
