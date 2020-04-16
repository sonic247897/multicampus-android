package multi.android.material_design_pro.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
// 리스트뷰 형태의 fragment가 많이 쓰이므로 ListFragment가 지원된다.
public class ListTestFragment extends ListFragment {

    public ListTestFragment() {
        // Required empty public constructor
    }

    // ListFragment의 spec에 의해 가능. onViewCreated에서 뷰를 inflate 해주고 있다.
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] data = {"이민호", "공유", "존메이어", "톰크루즈", "브레드피트", "원빈"};
        // ListFragment는 ListView를 내장하고 있으므로 ArrayAdapter를 getActivity()로 가져올 수 있다.
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, //simple_list_item_1 소스 내부의 텍스트뷰의 id
                        data);
        setListAdapter(adapter);
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }*/
}
