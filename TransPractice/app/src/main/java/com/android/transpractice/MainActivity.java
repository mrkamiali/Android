package com.android.transpractice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    Button addA;
    Button removeA;
    Button replaceAwithB;
    Button addB;
    Button removeB;
    Button replaceBwithA;
    Button attachA;
    Button detachA;
    Button back;
    Button pop;
    private FirstFragment FragmentA;
    private SeconndFragment FragmentB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();

        FragmentA = new FirstFragment();
        FragmentB = new SeconndFragment();

        addA = (Button) findViewById(R.id.addA);
        removeA = (Button) findViewById(R.id.removeA);
        replaceAwithB = (Button) findViewById(R.id.replaceAwithB);
        addB = (Button) findViewById(R.id.addB);
        removeB = (Button) findViewById(R.id.removeB);
        replaceBwithA = (Button) findViewById(R.id.replaceBwithA);
        attachA = (Button) findViewById(R.id.atachA);
        detachA = (Button) findViewById(R.id.detachB);
        back = (Button) findViewById(R.id.back);
        pop = (Button) findViewById(R.id.popAddB);


        adddA();
        removeeA();
        repplaceAwithB();
        adddB();
        removeeB();
        repplaceBwithA();

    }


    private void adddA() {
        addA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.group, FragmentA, "A").addToBackStack("addA").commit();
            }
        });
    }

    private void removeeA() {
        removeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.Fragment f1 = manager.findFragmentByTag("A");

                if (f1 != null) {
                    manager.beginTransaction().remove(f1).commit();
                } else {
                    Toast.makeText(MainActivity.this, "Fragment A not Exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void repplaceAwithB() {

        replaceAwithB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manager.beginTransaction().replace(R.id.group, FragmentB).commit();
            }
        });
    }

    private void adddB() {
        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction().add(R.id.group, FragmentB, "B").commit();
            }
        });

    }

    private void removeeB() {
        removeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f2 = manager.findFragmentByTag("B");
                if (f2 != null) {
                    manager.beginTransaction().remove(f2).commit();
                }
            }
        });
    }


    private void repplaceBwithA() {
        replaceBwithA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manager.beginTransaction().replace(R.id.group, FragmentA).commit();

            }
        });

    }


}
