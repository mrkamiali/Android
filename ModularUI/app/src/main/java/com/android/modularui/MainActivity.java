package com.android.modularui;

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
    public void Respond(int i) {
        FragmentManager man = getSupportFragmentManager();
        FragmentB f2 = (FragmentB) man.findFragmentById(R.id.fragment2);
        f2.getIndex(i);
    }
}
