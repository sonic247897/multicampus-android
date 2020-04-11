package multi.android.datamanagementpro.filesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;

import multi.android.datamanagementpro.R;

public class NotepadExam extends AppCompatActivity {
    boolean[] permission_state = new boolean[2];
    Button btn1;
    Button btn2;
    Button btn3;
    EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_exam);

        btn1 = findViewById(R.id.button5);
        btn2 = findViewById(R.id.button6);
        btn3 = findViewById(R.id.button7);
        txt = findViewById(R.id.examTxt);

        String[] permission = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        checkPermissions(permission);
    }

    public void printToast(String curPermission, String msg){
        Toast.makeText(this, curPermission+" "+msg, Toast.LENGTH_SHORT).show();
    }

    public void checkPermissions(String[] permissions){
        ArrayList<String> targetList = new ArrayList<String>();

        for(int i=0; i<permissions.length; ++i){
            String curPermission = permissions[i];
            // ContextCompat.checkSelfPermission(this, curPermission) 메소드로
            // permission_state참조할 필요 없이 권한 체크여부를 알 수 있다.
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
            if(permissionCheck == PackageManager.PERMISSION_GRANTED){
                printToast(curPermission, "권한 있음");
                // 온클릭 메소드에서 사용하기 위한 flag변수 임의로 정의
                permission_state[i] = true;
                Log.d("state", permission_state[i]+"");
            }else{
                printToast(curPermission, "권한 없음");
                // false로 초기화되어 있음
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)){
                    printToast(curPermission, "권한 설명 필요함");
                }else{
                    targetList.add(curPermission);
                }
            }
        }
        if(targetList.size() >0 ){
            String[] targets = new String[targetList.size()];
            targetList.toArray(targets);

            ActivityCompat.requestPermissions(this, targets, 101);
        }else{
            printToast("아무", "권한이 없습니다.");
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 101: {
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    printToast("쓰기", "권한을 사용자가 승인함");
                }else{
                    printToast("쓰기", "권한 거부됨");
                }
                if(grantResults.length>0 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    printToast("읽기", "권한을 사용자가 승인함");
                }else{
                    printToast("읽기", "권한 거부됨");
                }
            }
            return;
        }
    }

    public void saveExternalFileSystem(View v){
        if(permission_state[0]){
            Log.d("쓰기", permission_state[0]+"");
            printToast("쓰기", "권한설정완료");
            String state = Environment.getExternalStorageState();
            if(state.equals(Environment.MEDIA_MOUNTED)){ //사용가능한 상태
                printToast("쓰기", "사용가능");
                // deprecated: 이전 버전
                File external = Environment.getExternalStorageDirectory();
                String dirPath = external.getAbsolutePath()+"/mynote"; // 경로 정보 가져오기
                File dir = new File(dirPath);
                // 디렉토리 없으면 생성
                if(!dir.exists()){
                    dir.mkdir();
                }
                FileWriter fw = null;
                try {
                    fw = new FileWriter(dir+"/"+Calendar.YEAR +Calendar.MONTH+Calendar.DATE+"_memo.txt");
                    fw.write(txt.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                // output인 경우 finally에서 close 꼭 해줘야 함
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                printToast("쓰기", "사용 불가능");
            }
        }else{
        // 권한 필요성에 대한 설명
            printToast("쓰기", "권한설정하세요");
        }
    }


    public void openExternalFile(View v) {
        if(permission_state[1]) {
            Log.d("읽기", permission_state[1]+"");
            File external = Environment.getExternalStorageDirectory();
            String dirPath = external.getAbsolutePath() + "/mynote"; // 경로 정보 가져오기
            File dir = new File(dirPath);

            BufferedReader br = null;
            FileReader fr = null;
            try {
                fr = new FileReader(dir + "/" + Calendar.YEAR + Calendar.MONTH + Calendar.DATE + "_memo.txt");
                br = new BufferedReader(fr);
                String s = "";
                while((s=br.readLine())!=null){
                    txt.append(s+"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
        // 권한 필요성에 대한 설명
            printToast("읽기", "권한설정하세요");
        }
    }


    public void newFile(View v){
        txt.setText("");
    }
}