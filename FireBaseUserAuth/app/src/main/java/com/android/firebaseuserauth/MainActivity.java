package com.android.firebaseuserauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {
    private Firebase firebase;
    private EditText name_Edit, pas_Edit;
    private Button signIn_Button, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        firebase = new Firebase("https://loginaut.firebaseio.com/");
        name_Edit = (EditText) findViewById(R.id.editText_Name);
        pas_Edit = (EditText) findViewById(R.id.editText_pasword);
        signIn_Button = (Button) findViewById(R.id.buttonSignIn);
        signUpButton = (Button) findViewById(R.id.buttonSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "SignUp button fired in MAin");

                Intent i = new Intent(MainActivity.this, SignUpForm.class);
                startActivity(i);
            }
        });
        signIn_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "SignIn button fired in MAin");

                firebase.authWithPassword(name_Edit.getText().toString(), pas_Edit.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Toast.makeText(MainActivity.this, "Successfully login!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, TodoListFire.class);
                        startActivity(i);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.EXPIRED_TOKEN:
                                Toast.makeText(MainActivity.this, "Time Ecpired", Toast.LENGTH_LONG).show();
                                break;
                            case FirebaseError.INVALID_EMAIL:
                                Toast.makeText(MainActivity.this, "Invalid Email or Passowrd", Toast.LENGTH_LONG).show();
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                Toast.makeText(MainActivity.this, "Invalid Email or Passowrd", Toast.LENGTH_LONG).show();
                                break;
                            case FirebaseError.NETWORK_ERROR:
                                Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_LONG).show();
                                break;
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                Toast.makeText(MainActivity.this, "User not exist! ", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
            }
        });


    }
}
