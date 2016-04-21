package com.android.fragtranscationproj;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.Communicator {
    FragmentA f1;
    Fragment_B f2;
    FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        f1 = (FragmentA) mFragmentManager.findFragmentById(R.id.fragment);
        f1.setCommunicator(this);
    }


    @Override
    public void respond(int index) {
        f2 = (Fragment_B) mFragmentManager.findFragmentById(R.id.fragment2);
        if (f2.isVisible() && f2!=null){
            f2.changeData(index);
        }else {
            Intent intent = new Intent(this,Another_activity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }

    }
}
