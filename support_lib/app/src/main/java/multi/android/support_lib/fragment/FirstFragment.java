package multi.android.support_lib.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import multi.android.support_lib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    // <lifecycle 메소드: onXXX> =======================================================
    // => lifecycle 단계에서 처리해줘야 핸드폰이 뜨거워지지 않는다.
    // 프래그먼트가 액티비티에 Attach될 때 호출
    @Override
    public void onAttach(@NonNull Context context) {
        // 다른 작업하기 전에 super를 먼저 call한다.
        super.onAttach(context);
        Log.d("lifecycle", "fragment ==> onAttach");
    }
    // 초기화작업 - View초기화 작업은 할 수 없고, 값을 초기화해서 넘겨주거나 해야할 때 초기화
    // (fragment의 변수 초기화)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lifecycle", "fragment ==> onCreate");
    }

    // (뷰 초기화! - inflate)
    // fragment뷰가 만들어질때 호출되는 메소드
    // 액티비티에 배치될때 호출되는 메소드 - view를 그리기 위해서 호출하는 메소드
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("lifecycle", "fragment ==> onCreateView");
        return inflater.inflate(R.layout.fragment_first2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("lifecycle", "fragment ==> onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("lifecycle", "fragment ==> onActivityCreated");
    }
    // 사용자가 프레그먼트를 볼 수 있는 시점(볼 수 있게 화면에 찍어줌)
    @Override
    public void onStart() {
        super.onStart();
        Log.d("lifecycle", "fragment ==> onStart");
    }
    // 사용자와 상호작용이 가능한 상태(모든 게 초기화되고 화면 출력되고 이벤트 받을 수 있는준비 끝난 상태)
    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifecycle", "fragment ==> onResume");

    }

    // ==============================================================================
    // 프래그먼트를 다른 액티비티가 가리기 시작할 때 호출
    @Override
    public void onPause() {
        super.onPause();
        Log.d("lifecycle", "fragment ==> onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("lifecycle", "fragment ==> onStop");
    }

    // 완전히 덮어서 안 보일 때
    // 프레그먼트에서 뷰를 지우기
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("lifecycle", "fragment ==> onDestroyView");
    }
    // 모두 지워지고 호출
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "fragment ==> onDestroy");
    }
    // 액티비티에서 프레그먼트가 교체될 때
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("lifecycle", "fragment ==> onDetach");
    }

}
