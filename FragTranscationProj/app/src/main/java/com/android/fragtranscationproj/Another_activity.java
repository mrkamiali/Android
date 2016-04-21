package com.android.fragtranscationproj;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Another_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_activity);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        Fragment_B f2 = (Fragment_B) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        if (f2!=null)
        f2.changeData(index);
    }
}
