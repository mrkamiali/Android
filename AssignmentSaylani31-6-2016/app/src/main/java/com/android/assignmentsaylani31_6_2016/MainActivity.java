package com.android.assignmentsaylani31_6_2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button sendButton;
    private TextView mTextView;
    private EditText nameView, emailView, ageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendButton = (Button) findViewById(R.id.send);
        nameView = (EditText) findViewById(R.id.nameView);
        emailView = (EditText) findViewById(R.id.emailView);
        ageView = (EditText) findViewById(R.id.ageView);
        mTextView = (TextView) findViewById(R.id.textVIewset);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = nameView.getText().toString();
                String email = emailView.getText().toString();
                String age = ageView.getText().toString();
                Toast.makeText(MainActivity.this, "You entered Name " + s+" Email "+email+" Age "+age, Toast.LENGTH_LONG).show();
                mTextView.setText(s+" "+email+" "+age);
            }
        });

    }
}
