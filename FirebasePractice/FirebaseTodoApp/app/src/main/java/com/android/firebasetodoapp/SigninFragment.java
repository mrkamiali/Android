package com.android.firebasetodoapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {
    private EditText email, pasword;
    private Button signIn_Button, signUp_Button;
    private DatabaseReference firebase;
    private FirebaseAuth mAuth;
    FragmentManager mManager;
    SignUpFragment mSignUpFragment;

    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        firebase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) view.findViewById(R.id.edittext_signin_email);
        pasword = (EditText) view.findViewById(R.id.edittext_signin_pasword);
        signIn_Button = (Button) view.findViewById(R.id.button_signin_frag);
        signUp_Button = (Button) view.findViewById(R.id.button_signUp_frag);
        mSignUpFragment = new SignUpFragment();

        signinProcess();
        signUpProcess();


        return view;


    }

    private void signUpProcess() {

        signUp_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.my_layout, mSignUpFragment).addToBackStack("").commit();
            }
        });
    }

    private void signinProcess() {

        signIn_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), pasword.getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());
                            if(task.isSuccessful()) {
                                Intent i = new Intent(getActivity(), To_doActivity.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(getActivity(), "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();

                                Log.d("TAG", "createUserWithEmail:onError" + task.getException());

                            }

                        }

                    });
                } catch (Exception e){
                    Toast.makeText(getActivity()," "+e,Toast.LENGTH_LONG).show();
                }


            }


        });

    }


}
