package com.android.listfragmentproj;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Fragment BlankFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BlankFragment = new BlankFragment();
        getFragmentManager().beginTransaction().add(R.id.aboveFrag,BlankFragment).commit();
    }
}
