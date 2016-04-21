package com.android.helloworld;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textViewMain);
        button = (Button) findViewById(R.id.buttonForText);
        ClickFunction();


        Log.d("TAG", "Previous Text" + textView.getText().toString());
    }
    private void TextChangingFun(){
        Log.d("TAG", "onClick");
        textView.setText("OnCLick Text Changed");
        textView.setTextSize(23f);
        button.setText("Again Click");
        textView.setText("Second Changes");

    }
    private void ClickFunction(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextChangingFun();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop");

    }

}
