package com.android.practiceproj_todo;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SecondFragment.Inc {
    private Fragment FirstFragment, SecondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirstFragment = new FirstFragment();
        SecondFragment= new SecondFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.first_fragment,FirstFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.second_fragment,SecondFragment).commit();



    }


    @Override
    public void update(int val) {
        ((UpdateFrag) FirstFragment).inc(val);
    }
    public interface UpdateFrag{
        void inc(int val);
    }
}
