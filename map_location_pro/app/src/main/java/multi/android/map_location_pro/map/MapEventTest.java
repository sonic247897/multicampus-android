package multi.android.map_location_pro.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import multi.android.map_location_pro.R;

/*
* 지도화면이 출력된 후에 이벤트를 연결할 수 있어야 한다. 지도를 클릭하거나
* 지도를 드래그, 지도를 줌레벨버튼을 이용해서 줌레벨을 변경
*   OnMapClickListener => 지도(특정위치)를 클릭할 때 발생하는 이벤트에 대한 처리
*   OnMapLongClickListener => 맵을 길게 눌렀을 때 발생하는 이벤트에 대한 처리
*   OnCameraMoveListener => 지도의 위치가 바뀌거나 줌레벨이 변경되어
*                           카메라가 이동될 때 이벤트에 대한 처리
*   OnCameraMoveStartedListener => 지도의 위치가 바뀌거나 줌레벨이 변경되어
*                               카메라가 이동되기 시작할 때 이벤트에 대한 처리
*   [순서]
*   OnCameraMoveStartedListener -> OnCameraMoveListener
* */
public class MapEventTest extends AppCompatActivity implements OnMapReadyCallback,
                                                    GoogleMap.OnMapClickListener,
                                                GoogleMap.OnMapLongClickListener,
                                                GoogleMap.OnCameraMoveListener,
                                            GoogleMap.OnCameraMoveStartedListener {
    GoogleMap map;
    MarkerOptions markerOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_event_test);
        //Map프레그먼트로 부터 맵을 얻기
        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)manager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if(map!=null){
            //위도,경도지정 -> 현재 위치 받아오려면 Location객체에서 받아온다.
            // gps, 네트워크 둘 다에서 받아오기 (BasicLocationTest2)
            LatLng latLng= new LatLng(37.5858031,126.9763605);
            // 지도 확대축소 버튼을 추가
            map.getUiSettings().setZoomControlsEnabled(true);
            // 현재 나의 위치를 마커가 아닌 포인트로 표시
            // - 위치 기반 서비스에 대한 퍼미션 체크가 완료되어야 표시
            map.getUiSettings().setMyLocationButtonEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));


            // map에 이벤트 연결
            map.setOnMapClickListener(this);
            map.setOnMapLongClickListener(this);
            map.setOnCameraMoveListener(this);
            map.setOnCameraMoveStartedListener(this);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        map.clear(); // 없으면 반경 계속 추가됨
        Toast.makeText(this, "지도를 클릭했습니다 => " +
                "위도:"+latLng.latitude+", 경도:"+latLng.longitude, Toast.LENGTH_SHORT).show();
        // 반경을 반투명한 원으로 표현
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.strokeWidth(10); // circle의 선 width
        circleOptions.strokeColor(Color.GREEN); // 선 색
        // 알파값: 투명도 (55), 색상코드: RGB코드
        circleOptions.fillColor(Color.parseColor("#550000ff"));
        circleOptions.center(latLng); // circle의 중심
        circleOptions.radius(300); // 미터 단위
        map.addCircle(circleOptions);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Toast.makeText(this, "지도를 길게 클릭했습니다 => " +
                "위도:"+latLng.latitude+", 경도:"+latLng.longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraMove() {
        // 줌레벨을 바꾸거나 다 움직인 후에도 미세하게 계속 각도조절한다.
        Toast.makeText(this, "카메라가 이동됩니다.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCameraMoveStarted(int i) {
        Toast.makeText(this, "카메라 이동이 시작됩니다.", Toast.LENGTH_SHORT).show();
    }
}
