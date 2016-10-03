package com.android.datepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private TextView mTextView;
    private SimpleDateFormat dateFormater;
    private DatePickerDialog mDatePickerDialog;
    long mPeriod = 1553146;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mTextView = (TextView) findViewById(R.id.Date_Show_view);
        dateFormater = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeDialogue();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePickerDialog.show();
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
                Date date = new Date();
                String format = dateFormat.format(new Date(dateFormat.getCalendar().getTimeInMillis()));
                Log.d("format", "" + format);
                int hours1 = date.getHours();
                Log.d("format", "" + hours1);

                int hours = (int) ((mPeriod / (1000 * 60 * 60)) % 24);
                int minutes = (int) ((mPeriod / (1000 * 60)) % 60);

                int seconds = (int) (mPeriod / 1000) % 60;
                String period = "" + hours + ":" + minutes + ":" + seconds;
                Log.d("TotalPeriod", period);
            }
        });
    }

    private void setDateTimeDialogue() {
        Calendar newCalender = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(MainActivity.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, date);
                mTextView.setText(dateFormater.format(calendar.getTime()));
            }
        }, newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH), newCalender.get(Calendar.DATE));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
