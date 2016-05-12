package com.android.firebaseuserauth;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class TodoListFire extends AppCompatActivity {
    private Firebase firebase;
    ListView listView;
    ArrayList<Message> message;
    CustomAdapter adapter;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_fire);

        firebase = new Firebase("https://loginaut.firebaseio.com/");

        listView = (ListView) findViewById(R.id.list_View);
        addButton = (Button) findViewById(R.id.buttonAdd);
        //initializing Array list
        message = new ArrayList<>();
        //Initiliazing Custom Adapter
        adapter = new CustomAdapter(this, message);
        //Adding item to ArrayList of type Message Class
        //message.add(new Message("Kamran", "ALi"));
        //setting adapter to listView
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoListFire.this);
                builder.setTitle("Add Item");
                builder.setMessage("Add Name and Message inthe above Field!");
                View view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
                final EditText nameView = (EditText) view.findViewById(R.id.editTextName);
                final EditText msgView = (EditText) view.findViewById(R.id.editTextMsg);
                final CheckBox checkBox = (CheckBox) view.findViewById(R.id.edit_checkBox);
                builder.setView(view);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("Data").child(firebase.getAuth().getUid()).push().setValue(new Message(nameView.getText().toString(), msgView.getText().toString(), checkBox.isChecked()));
                        //  listView.setAdapter(adapter);


                        //     adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("BACK", null);

                builder.create().show();
            }
        });

        firebase.child("Data").child(firebase.getAuth().getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Message msg = dataSnapshot.getValue(Message.class);

                message.add(new Message(msg.getName().toString(), msg.getMsg().toString(), msg.isCheckBox()));
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoListFire.this);
                builder.setTitle("Delete Item !");
                builder.setMessage("Wanna delete Selected Item Click Delete.");
                builder.setNegativeButton("Back", null);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int i = 0;
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    if (i == position) {
                                        Firebase ref = data.getRef();
                                        ref.removeValue();
                                        message.remove(position);
                                        adapter.notifyDataSetChanged();
                                    }
                                    i++;
                                }

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                });

                builder.create().show();
            }
        });


    }

   /* @Override
    public void valueCheck(final int pos, final boolean checkk) {
        firebase.child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (i == pos) {
                        Firebase ref = data.getRef();
                        ref.child("checkBox").setValue(!checkk);
                        // adapter.notifyDataSetChanged();
                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }*/
}
