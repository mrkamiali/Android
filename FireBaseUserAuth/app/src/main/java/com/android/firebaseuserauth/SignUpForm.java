package com.android.firebaseuserauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUpForm extends AppCompatActivity {
    private Firebase firebase;
    private EditText nameEdit, passEdit, dobEdit, fNameEdit, genderEdit;
    private Button buttonSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        firebase = new Firebase("https://loginaut.firebaseio.com/");

        nameEdit = (EditText) findViewById(R.id.editText_Name_UP);
        passEdit = (EditText) findViewById(R.id.editText_Pass_UP);
        buttonSignUp = (Button) findViewById(R.id.button_SignUp_UP);
        dobEdit = (EditText) findViewById(R.id.editText_DOB_UP);
        fNameEdit = (EditText) findViewById(R.id.editText_Fname_UP);
        genderEdit = (EditText) findViewById(R.id.editText_gender_UP);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "SignUP button fired");
                firebase.createUser(nameEdit.getText().toString(), passEdit.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {

                        Log.d("TAG", "Values " + nameEdit.getText() + " " + passEdit.getText());
                        Toast.makeText(SignUpForm.this, "Successfully Created", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SignUpForm.this, MainActivity.class);
                        startActivity(i);
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("userName", nameEdit.getText().toString());
                        map.put("password", passEdit.getText().toString());
                        map.put("dob", dobEdit.getText().toString());
                        map.put("fName", fNameEdit.getText().toString());
                        map.put("gender", genderEdit.getText().toString());
                        firebase.child("User").child(stringObjectMap.get("uid").toString()).setValue(map);
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.EMAIL_TAKEN:
                                Toast.makeText(SignUpForm.this, "Email or USer name Already taken", Toast.LENGTH_LONG).show();
                                break;
                            case FirebaseError.NETWORK_ERROR:
                                Toast.makeText(SignUpForm.this, "Network Problem", Toast.LENGTH_LONG).show();
                                break;
                            case FirebaseError.EXPIRED_TOKEN:
                                Toast.makeText(SignUpForm.this, "Time Expired", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });

            }
        });


    }
}
