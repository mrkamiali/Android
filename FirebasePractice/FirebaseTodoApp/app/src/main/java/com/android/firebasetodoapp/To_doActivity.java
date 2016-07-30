package com.android.firebasetodoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class To_doActivity extends AppCompatActivity {
    private ListView todoListView;
    private Button addButton, signoutButton;
    private CustomAdapter messageAdapter;
    private ArrayList<Message> message;
    private DatabaseReference firebase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        firebase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
//        String s = mAuth.getCurrentUser().getUid().toString();
//        Log.d("TAG", s);


        todoListView = (ListView) findViewById(R.id.listView_Todo);
        addButton = (Button) findViewById(R.id.todo_add_button);
        signoutButton = (Button) findViewById(R.id.signout_button);

        message = new ArrayList<>();
        messageAdapter = new CustomAdapter(message, this);


        todoListView.setAdapter(messageAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(To_doActivity.this);

                builder.setMessage("Enter Proper name and Message !");
                View view = getLayoutInflater().inflate(R.layout.add_message_layout, null);
                final EditText nameedit = (EditText) view.findViewById(R.id.add_messages_name);
                final EditText msgedit = (EditText) view.findViewById(R.id.add_messages_msg);
                builder.setView(view);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("Data").child(mAuth.getCurrentUser().getUid()).push().setValue(new
                                Message(nameedit.getText().toString(),
                                msgedit.getText().toString()));
                    }
                });
                builder.setNegativeButton("BACK", null);
                builder.setCancelable(false);
                builder.create().show();

            }
        });

            firebase.child("Data").child(mAuth.getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Message msg = dataSnapshot.getValue(Message.class);

                    message.add(new Message(msg.getName(), msg.getMessage()));
                    messageAdapter.notifyDataSetChanged();
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
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(To_doActivity.this);

                builder.setMessage("Click delete to delete Item!");
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("Data").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int i = 0;
                                for (DataSnapshot msg : dataSnapshot.getChildren()) {
                                    if (i == position) {
                                        DatabaseReference ref = msg.getRef();
                                        ref.removeValue();
                                        message.remove(position);
                                        messageAdapter.notifyDataSetChanged();
                                    }
                                    i++;
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
                builder.create().show();
            }
        });
        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(To_doActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}
