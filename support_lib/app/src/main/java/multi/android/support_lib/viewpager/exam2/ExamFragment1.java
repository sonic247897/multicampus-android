package multi.android.support_lib.viewpager.exam2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import multi.android.support_lib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamFragment1 extends ListFragment {

    public ExamFragment1() {
        // Required empty public constructor
    }

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
        return inflater.inflate(R.layout.fragment_exam1, container, false);
    }*/
}
