package com.android.dynamicviewadd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int i = 0;
    private EditText mEditText;
    private Button sendButton;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit);
        final LinearLayout vi = (LinearLayout) findViewById(R.id.container);
        sendButton = (Button) findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mEditText.getText().toString();

                vi.addView(textView(i, s));
                mEditText.setText("");
            }
        });


        i++;


    }

    private TextView textView(int id, String text) {

        TextView textView = new TextView(this);
        textView.setId(id);
        textView.setText(text);
        return textView;

    }

}
