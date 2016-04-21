package com.android.extendablelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private HashMap<String, List<Double>> androidVersionName;

    private Exp_Adapter adapter;
//    private List<String> version_List;

    private ArrayList<Parent>  parents;

    private ExpandableListView exp_View;

//    private ListView versionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exp_View = (ExpandableListView) findViewById(R.id.android_Versions_list);

        parents=new ArrayList<>();

        for (int i=0;i<10;i++){
            ArrayList<Child> children=new ArrayList<>();
            for (int j=0;j<10;j++){
                children.add(new Child("Child is "+j));
            }
            parents.add(new Parent("Parent is :"+i,children));
        }

//        androidVersionName = VersionList.getInfo();
  //      version_List = new ArrayList<String>(androidVersionName.keySet());
        adapter = new Exp_Adapter(this,parents);
        exp_View.setAdapter(adapter);
    }
}
