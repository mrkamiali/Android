package com.android.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator {
    private Fragment FirstFragment, SecondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirstFragment = new FirstFragment();
        SecondFragment = new SecondFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.above_Fragment, FirstFragment, "A").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.below_Fragment, SecondFragment, "B").commit();

    }

    @Override
    public void respond(String data) {
        FragmentManager manager = getSupportFragmentManager();
        SecondFragment f2 = (SecondFragment) manager.findFragmentById(R.id.below_Fragment);
        f2.changeData(data);
    }

}
