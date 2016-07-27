package com.android.firebasework;

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
    Firebase firebase;
    TextView textView;
    Button bSunny, bFoggy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        firebase = new Firebase("https://wethertesting.firebaseio.com/condition");

        textView = (TextView) findViewById(R.id.textViewCondition);
        bSunny = (Button) findViewById(R.id.buttonSunny);
        bFoggy = (Button) findViewById(R.id.buttonFoggy);


        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String msg = dataSnapshot.getValue(String.class);
                Log.d("Tag", "onDataChange " + dataSnapshot.toString());
                textView.setText(msg);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        bSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.setValue("Sunny");
                textView.setText("Sunny");
            }
        });
        bFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.setValue("Foggy");
                textView.setText("Foggy");


            }
        });
    }
}
