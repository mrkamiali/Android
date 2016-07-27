package com.android.firebasetodoapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
public class SignUpFragment extends Fragment {
    private EditText firstname, lastname, email, pasword, dob, gender;
    private Button signup;
    private DatabaseReference firebase;
    private FirebaseAuth mAuth;
    private User user = new User();
    private SigninFragment mSigninFragment;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        firebase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        email = (EditText) view.findViewById(R.id.edtviewEmail);
        pasword = (EditText) view.findViewById(R.id.edtviewPassword);
        firstname = (EditText) view.findViewById(R.id.edtviewFirstName);
        lastname = (EditText) view.findViewById(R.id.edtviewLastName);
        dob = (EditText) view.findViewById(R.id.editTextDob);
        gender = (EditText) view.findViewById(R.id.editGender);

        mSigninFragment = new SigninFragment();
        signup = (Button) view.findViewById(R.id.signup_frag_button);


        signupProcess();


        return view;
    }

    private void signupProcess() {

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), pasword.getText().toString()).addOnCompleteListener(getActivity(),
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                getFragmentManager().beginTransaction().replace(R.id.my_layout,mSigninFragment).commit();

                        firebase.child("User").child(mAuth.getCurrentUser().getUid()).setValue(new User(firstname.getText().toString(),
                                lastname.getText().toString(),
                                email.getText().toString(),
                                pasword.getText().toString(),
                                dob.getText().toString(),
                                gender.getText().toString(),
                                mAuth.getCurrentUser().getUid()));


                                Toast.makeText(getActivity(), "Successfull", Toast.LENGTH_SHORT).show();
                                Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getActivity(), " " + task.getException(), Toast.LENGTH_SHORT).show();
                                    Log.d("TAG", "createUserWithEmail:onError:" + task.getException());

                                }
                            }
                        });
            }
        });
    }

}
