package multi.android.datamanagementpro.filesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import multi.android.datamanagementpro.R;

// external은 권한에 대한 설정을 해줘야 한다.
public class ExternalFileMgr extends AppCompatActivity {
    boolean permission_state;
    TextView externalTxt;
    Button btn;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file_mgr);

        externalTxt = findViewById(R.id.fileTxt);
        btn = findViewById(R.id.button3);
        // 1. Permission을 먼저 체크 - 이미 설정되어있으면 매번 실행할 때마다 체크할 필요x
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            // onClick메소드에서 쓰기 위해서 true 설정
            permission_state = true;
            printToast("권한설정완료");
        }else{
            permission_state = false;
            printToast("권한설정하세요");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    2000);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==2000 && grantResults.length>0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                permission_state = true;
                printToast("권한 설정 마무리 완료");
            }else{
                printToast("권한 설정을 하지 않았으므로 기능을 사용할 수 없습니다.");
            }
        }
    }

    void printToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // 파일 I/O - 자바에서 썼던 input/output stream
    public void saveExternalFileSystem(View v){
        if(permission_state){
            printToast("권한설정완료");
            // 외부 저장소를 사용할 수 있는지 state를 추출
            String state = Environment.getExternalStorageState();
            if(state.equals(Environment.MEDIA_MOUNTED)){ //사용가능한 상태
                printToast("사용가능");
                // deprecated: 이전 버전
                File external = Environment.getExternalStorageDirectory();
                //String dirPath = external.getAbsolutePath()+"/myApp"; // 경로 정보 가져오기
                // File dir = new File(dirPath);
                // -> 외부저장소(sdcard)/myApp(임의의 디렉토리) 생성
                //      : 앱을 삭제 해도 저장소가 사라지지 않는다.
                String dirPath = external.getAbsolutePath();
                String pkg = getPackageName();
                File dir = new File(dirPath+"/android/data/"+pkg);
                // -> 외부저장소/android/data/앱의 패키지명으로 디렉토리 생성
                //      : 앱을 삭제하면 데이터가 같이 삭제된다.
                // 디렉토리 없으면 생성
                if(!dir.exists()){
                    dir.mkdir();
                }
                FileWriter fw = null;
                try {
                    fw = new FileWriter(dir+"/test1.txt");
                    fw.write("외부저장소 테스트중");
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
                printToast("사용 불가능");
            }

        }else{
            // 권한 필요성에 대한 설명
            printToast("권한설정하세요");
        }
    }


    public void openExternalFile(View v) {

    }
}
