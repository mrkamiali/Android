package com.android.practiceproj_todo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements MainActivity.UpdateFrag{
    private TextView textView;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgView);
        textView = (TextView) view.findViewById(R.id.text_view);
        return view;
    }

    @Override
    public void inc(int val) {
        textView.setText(String.valueOf(val));
    }
}
