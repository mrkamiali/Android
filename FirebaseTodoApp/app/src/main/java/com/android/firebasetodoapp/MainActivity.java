package com.android.firebasetodoapp;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private SigninFragment mSigninFragment;
//    private Fragment signinFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSigninFragment = new SigninFragment();

        /**###############################################
         * All Possible Solutions for Fragment Transaction
         * ###############################################
         * */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.my_layout, mSigninFragment).commit();

        //  int code = getSupportFragmentManager()
        //              .beginTransaction()
        //              .add(R.id.my_layout, new SigninFragment())
        //              .commit(); //:

      /*  getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.my_layout, new SigninFragment())
                .commit(); /*/


    }
}
