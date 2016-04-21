package com.android.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {

    Communicator communicator;
    Button mButton;
    int counter = 0;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        communicator = (Communicator) getActivity();
        mButton = (Button) getActivity().findViewById(R.id.click_Me);
        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        counter++;
        communicator.respond("Button was Clicked " + counter + " times!");
    }
}
