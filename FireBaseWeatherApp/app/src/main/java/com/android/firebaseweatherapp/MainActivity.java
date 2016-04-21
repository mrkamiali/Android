package com.android.firebaseweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Firebase mFirebase;
    private Button sunnyButton;
    private Button foggyButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mTextView = (TextView) findViewById(R.id.text_view);
        sunnyButton = (Button) findViewById(R.id.buttonSunny);
        foggyButton = (Button) findViewById(R.id.buttonFoogy);

        mFirebase = new Firebase("https://wethertesting.firebaseio.com/condition");

        mFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String newCondition = (String) dataSnapshot.getValue();

                Log.d("Fire", "FireBase Value" + newCondition);

                mTextView.setText((String) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        sunnyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebase.setValue("Sunny");
            }
        });
        foggyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebase.setValue("Foggy");
            }
        });
    }
}
