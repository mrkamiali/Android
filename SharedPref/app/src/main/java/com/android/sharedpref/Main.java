package com.android.sharedpref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class Main extends AppCompatActivity {
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.edit_Text1);
        et2 = (EditText) findViewById(R.id.edit_Text2);

        SharedPreferences settings = getSharedPreferences("MYPREFS", 0);

        et1.setText(settings.getString("tvalue1", ""));
        et2.setText(settings.getString("tvalue2", ""));

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("tvalue1", et1.getText().toString());
        editor.putString("tvalue2", et2.getText().toString());
        editor.commit();
    }
}
