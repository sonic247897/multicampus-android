package multi.android.material_design_pro.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import multi.android.material_design_pro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment3 extends Fragment {

    public ViewFragment3() {
        // Required empty public constructor
    }

    // 프래그먼트가 액티비티에 붙을때 활성화
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view3, container, false);
    }
}
