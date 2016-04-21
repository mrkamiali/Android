package com.android.intercommunicationfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentA extends Fragment {
    private TextView mTextView;
    private String value;

    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_, container, false);
        if (savedInstanceState == null) {

        } else {
            value = savedInstanceState.getString("text");
            mTextView = (TextView) getActivity().findViewById(R.id.text_view);
            mTextView.setText(value);
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", value);
    }

    public void changeText(String data) {
        this.value = data;
        mTextView.setText(data);
    }
}
