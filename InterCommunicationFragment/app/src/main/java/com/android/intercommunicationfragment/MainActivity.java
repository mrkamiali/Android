package com.android.intercommunicationfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void Respond(String data) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentA f1 = (FragmentA) manager.findFragmentById(R.id.fragment);
        f1.changeText(data);
    }
}
