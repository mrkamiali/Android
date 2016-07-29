package com.android.extencustomlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Parent> parent;
    private Exp_Adapter adapter;
    private ExpandableListView exp_View;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        exp_View = (ExpandableListView) findViewById(R.id.android_Versions_list);

        parent = new ArrayList<>();
        for (int i = 0 ;i<10)

        adapter = new Exp_Adapter(this,parent);

        exp_View.setAdapter(adapter);
    }
}
