package com.android.sleepcounterapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText weekDay, weekEnd, optimalSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weekDay = (EditText) findViewById(R.id.weekdays_edit_text);
        weekEnd = (EditText) findViewById(R.id.weekend_edit_text);
        optimalSleep = (EditText) findViewById(R.id.optimalSleep_edit_text);



    }
    public void clculateSleep(View view){
        calculateSleep();
    }

    private void calculateSleep() {
        String weekday = weekDay.getText().toString();
        int weekDaysSleep = Integer.parseInt(weekday);

        Log.e("TAG", "weekDaysSleep" + weekDaysSleep);
        int weekEndSleep = Integer.parseInt(weekEnd.getText().toString());
        Log.e("TAG", "weeKEndSleep" + weekEndSleep);
    }
}
