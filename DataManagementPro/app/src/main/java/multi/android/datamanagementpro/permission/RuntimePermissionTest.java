package multi.android.datamanagementpro.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import multi.android.datamanagementpro.R;

// @ 권한이 필요한 작업마다 매번 같은일을 해줘야 하므로 class로 따로 만들어서 써도 좋다!
//  (메소드 하나 만들고 매개변수 넘기기)
public class RuntimePermissionTest extends AppCompatActivity {
    // 퍼미션의 상태를 저장할 변수(클릭 메소드 안에서 내가 쓰려고 임의로 만든 flag 변수) => "패턴"
    boolean permission_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission_test);
        // 1. Permission을 먼저 체크
        // 리턴값이 int이기 때문에 PackageManager가 제공하는 상수와 비교한다
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED){
            permission_state = true;
            printToast("권한이 설정되었습니다.");
        }else{
            permission_state = false;
            printToast("권한을 설정해야 합니다.");
            // 2. 권한이 없는 경우 권한을 설정하는 메시지를 띄운다.
            // (ActivityCompat은 Activity의 상위 클래스)
            // activity: 어느 창에 띄울것인지
            // requestCode: 권한의 종류가 여러개일 수 있으므로 식별 목적으로 사용
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1000);
        }
    }

    // 3. requestPermissions의 메시지창에서 선택한 후 호출되는 메소드
    //    결과를 리턴 - 결과에 따라 다르게 처리할 수 있도록 구현
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 권한의 성공 설정에 대한 결과가 있다는 의미
        if(requestCode==1000 && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                permission_state = true; //requestPermission의 결과
                printToast("권한 설정 마무리 완료");
            }else{
                printToast("권한 설정을 하지 않았으므로 기능을 사용할 수 없습니다.");
            }
        }
    }

    public void printToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void runCamera(View v){
        // <uses-permission android:name="android.permission.CAMERA"/>이 없으면 그냥 카메라 뷰로 가는데
        // 있으면 위험권한에 대한 처리를 하지 않으면 앱이 튕긴다.
        if(permission_state){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        }else{
            printToast("권한을 설정해야 이 기능을 쓸 수 있습니다.");
            // 권한을 설정할 수 있는 액티비티로 자동 이동되도록
        }
    }
}
