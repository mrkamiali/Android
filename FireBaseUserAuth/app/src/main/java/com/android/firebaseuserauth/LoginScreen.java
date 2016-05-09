package com.android.firebaseuserauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {
    private EditText user_Edit, pasword_edit;
    private Button sign_In_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        user_Edit = (EditText) findViewById(R.id.editText_NameLogin);
        user_Edit = (EditText) findViewById(R.id.editText_PassLogin);
        sign_In_Button = (Button) findViewById(R.id.buttonSignIn);

    }
}
