package com.android.firebaseobjectapp;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Firebase mFirebase;
    private EditText mEditText;
    private Button mButton;
    private List<Message> mMessages;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        mFirebase = new Firebase("https://objectap.firebaseio.com/");
        mEditText = (EditText) findViewById(R.id.edit_Text);
        mButton = (Button) findViewById(R.id.button_Upload);
        listView = (ListView) findViewById(R.id.list_View);
        mMessages = new ArrayList<>();
        final CustomAdapter adapter = new CustomAdapter(mMessages, this);


        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                }
                return true;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
                listView.setAdapter(adapter);
                sendMessage();
            }
        });

    }

    private void sendMessage() {
        String msg = mEditText.getText().toString();

        Random random = new Random();
        final String name = "Author" + random.nextInt(1000);
        Message message = new Message(name, msg, (int) System.currentTimeMillis(), 0);
        mFirebase.push().setValue(message);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id1 = mMessages.get(position).getId();
                mFirebase.child(name).removeValue();
            }
        });

    }

}



