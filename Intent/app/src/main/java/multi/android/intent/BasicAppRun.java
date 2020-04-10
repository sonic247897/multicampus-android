package multi.android.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BasicAppRun extends AppCompatActivity {
    // 승인받을 권한의 목록 - 권한이 String으로 관리되기 때문에 String배열
    // (리소스는 int이므로 int배열)
    String[] permission_list = {
            Manifest.permission.CALL_PHONE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_app_run);

        // 앱이 실행될 때 권한체크 메소드를 호출
        runPermission();
    }

    // 구글맵 실행
    public void runGoogleMap(View v){
        // 콜론 앞의 문자열로 식별
        // 지도를 Activity로 표현하는 것이 아니라 단순히 앱을 실행
        Uri uri = Uri.parse("geo:37.501579,127.039585");
        // ACTION_VIEW: 보여주는 역할
        // uri: 데이터 정보
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    // 웹브라우저 실행
    public void runWeb(View v){
        Uri uri = Uri.parse("https://www.daum.net");
        // ACTION_VIEW: 보여주는 역할
        // uri: 데이터 정보
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    //전화걸기화면만 실행
    public void runDial(View v){
        Uri uri = Uri.parse("tel:00000000");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }
    // 실제 전화를 걸기 위한 메소드
    public void runCallPhone(View v){
        Intent intent = null;
        int chk = PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        // permission이 정상적으로 전부 허가된 상태
        if(chk==PackageManager.PERMISSION_GRANTED){
            // 5556: AVD 전화번호
            Log.d("tel", "성공");
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:5556"));
        }else{
            Log.d("tel", "실패");
            return;
        }
        startActivity(intent);
    }

    // 전화통화는 요금, 개인정보와 관련되어 있으므로
    // manifest에 등록하고 권한을 체크-승인처리 해줘야 한다.
    public void runPermission(){
        //하위버전이면 실행되지 않도록 처리(하위버전은 권한체크-승인 필요x)
        // Build: 빌드정보 저장된 객체
        // M: 마시멜로 버전
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }
        // 모든 권한을 셀프체크
        for(String permission:permission_list){
            int chk = checkCallingOrSelfPermission(permission);
            // 안드로이드 상수로 정의되어 있음
            if(chk == PackageManager.PERMISSION_DENIED){
                requestPermissions(permission_list, 0);
                break;
            }
        }
    }
}
