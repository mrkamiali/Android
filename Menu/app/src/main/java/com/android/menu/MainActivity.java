package com.android.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView firstTextView, secondTextView, thirdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.logs_print_button);
        firstTextView = (TextView) findViewById(R.id.menu_item_1);
        secondTextView = (TextView) findViewById(R.id.menu_item_2);
        thirdTextView = (TextView) findViewById(R.id.menu_item_3);
    }

    public void printToLogs(View view) {
        Log.e("TAG","firstTextView "+firstTextView.getText().toString());
        Log.e("TAG","SecondTextView "+secondTextView.getText().toString());
        Log.e("TAG","ThirdTextView "+thirdTextView.getText().toString());

    }
}
