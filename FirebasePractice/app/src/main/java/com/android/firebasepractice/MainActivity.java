package com.android.firebasepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Firebase firebase;
    TextView mTextView;
    Button bSunny, bFoggy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        mTextView = (TextView) findViewById(R.id.textViewCondition);
        bSunny = (Button) findViewById(R.id.buttonSunny);
        bFoggy = (Button) findViewById(R.id.buttonFoggy);


        firebase = new Firebase("https://wethertesting.firebaseio.com/condition");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String msg = dataSnapshot.getValue(String.class);
                mTextView.setText(msg);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
