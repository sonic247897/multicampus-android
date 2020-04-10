package multi.android.datamanagementpro.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import multi.android.datamanagementpro.R;

public class BasicPermissionTest extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_permission_test);
        /* 기본권한 - 실행시 알람x */
        // 내 앱에서 인터넷 연결 페이지 보고 싶을 때
        // Skill: 자바스크립트는 서버가 없어도 html페이지에도 만들 수 있다.(JSP&Servlet배우기 전)
        // html페이지에 자바스크립트로 차트 만들고  데이터를 셋팅해서 작업(데이터는 로컬파일 사용)
        webView = findViewById(R.id.webview);

        // 셋팅정보 얻어옴
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); //자바스크립트 허용
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.loadUrl("https://m.daum.net"); //로컬 파일 등록 or 모바일에서 볼 수 있는 웹페이지 주소
        // 권한 manifest에 등록 안하면 net::ERR_CACHE_MISS 에러 발생
        // http통신 권한 net::ERR_ACCESS_DENIED
        //  -> secure통신을 위한 문자열 처리 android:usesCleartextTraffic="true"


    }
}
