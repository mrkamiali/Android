package com.android.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mButton;
    private ListView mListView;
    private Firebase mFirebase;
    private ArrayList<String> name;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);


        aliList();
        moosaList();
    }

    private void aliList() {
        mEditText = (EditText) findViewById(R.id.edit_View_Ali);
        mButton = (Button) findViewById(R.id.button_Ali);
        mListView = (ListView) findViewById(R.id.list_ali);


        mFirebase = new Firebase("https://listviewapp.firebaseio.com/");

        name = new ArrayList<>();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        mListView.setAdapter(mAdapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebase.child("Ali").push().setValue(mEditText.getText().toString());

                //  name.add(mEditText.getText().toString());
                mEditText.setText("");
                mAdapter.notifyDataSetChanged();

            }
        });

        mFirebase.child("Ali").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                name.add(dataSnapshot.getValue().toString());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void moosaList() {
        final EditText editText = (EditText) findViewById(R.id.edit_View_Moosa);
        Button button = (Button) findViewById(R.id.button_Moosa);
        ListView listView = (ListView) findViewById(R.id.list_Moosa);
        final ArrayList<String> moosa = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, moosa);

        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebase.child("Moosa").push().setValue(editText.getText().toString());
                editText.setText("");
                adapter.notifyDataSetChanged();

            }
        });
        mFirebase.child("Moosa").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                moosa.add(dataSnapshot.getValue().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
