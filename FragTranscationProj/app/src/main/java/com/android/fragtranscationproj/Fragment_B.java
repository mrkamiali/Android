package com.android.fragtranscationproj;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_B extends Fragment {
    TextView mTextView;

    public Fragment_B() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__b, container, false);
        mTextView = (TextView) view.findViewById(R.id.text_View);

        return view;
    }
    public void changeData(int index){
        String[] description = getResources().getStringArray(R.array.description);
        mTextView.setText(description[index]);
    }

}
