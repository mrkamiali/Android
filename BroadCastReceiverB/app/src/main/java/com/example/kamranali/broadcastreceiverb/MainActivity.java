package com.example.kamranali.broadcastreceiverb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyReceiver myReceiver;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        IntentFilter intentFilter = new IntentFilter("com.example.kamranali.broadcastreceivertask");
        myReceiver = new MyReceiver();

        if (intentFilter != null) {
            registerReceiver(myReceiver, intentFilter);

        }
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String data_fromApp_a = intent.getStringExtra("abc");
            Log.d("TAG",data_fromApp_a);
//            textView.setText(data_fromApp_a);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver != null)
            unregisterReceiver(myReceiver);
    }
}
