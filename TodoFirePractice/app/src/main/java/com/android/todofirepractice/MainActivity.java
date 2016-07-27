package com.android.todofirepractice;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements CustomAdapter.CheckIn {
  //yaha per tumnyb main acttvity ko implement kerwa lia theek...?
    //ok  ab anhgar koi b activity ya koi b java ki class ko hum kisi b
    //interace se implement ya kisi b clas s e tend kerty hain to  hum apni us class ko
    //un tamam interfaces or class me implicit cast ker sakty hain...
    // theek?;/ / ok kar sakty hyn but sawal yeh hy k hum ny inter dface ko yahan implent karaya or
    //waha se hum direct 2 value already bhej rhy hyn yeh wali see
    // jb hum interface ko implement kar lyn to hamaray pas un value ka access hyna >??
    //interface humny use kia bridge banany k liye...
    // han wo theel hy matlab temperory storage hy sirf hold kar k aget deta hy Lakin yar yeh q kia is kaam k baghair nhi kar skty kia >????
    //>>?? deakh
    private ListView mListView;
    private CustomAdapter adapter;
    private ArrayList<Message> message;
    private Button addButton;
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        mListView = (ListView) findViewById(R.id.message_ListView_main);
        addButton = (Button) findViewById(R.id.add_button);
        firebase = new Firebase("https://objectap.firebaseio.com/");

        message = new ArrayList<>();

        adapter = new CustomAdapter(this, message);
        mListView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Enter Data");
                builder.setMessage("Enter Correct name and Message");
                View view = getLayoutInflater().inflate(R.layout.dialogue_layout, null);
                final EditText nameEdit = (EditText) view.findViewById(R.id.name_edit);
                final EditText msgEdit = (EditText) view.findViewById(R.id.msg_edit);
                final CheckBox checkBoxEdit = (CheckBox) view.findViewById(R.id.checkin_edit);
                builder.setView(view);
                builder.setNegativeButton("Back", null);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("VALUE").push().setValue(new Message(nameEdit.getText().toString(), msgEdit.getText().toString(), checkBoxEdit.isChecked()));

                        adapter.notifyDataSetChanged();
                    }
                });

                builder.create().show();
            }
        });

        firebase.child("VALUE").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message msg = dataSnapshot.getValue(Message.class);
                message.add(new Message(msg.getName().toString(), msg.getMsg().toString(), msg.isCheckin()));
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

        //removing value from list and and fire base
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete Item !");
                builder.setMessage("Want to Delete Selected ITem Click Delete Button.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("VALUE").addListenerForSingleValueEvent(new ValueEventListener() {
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
                builder.setNegativeButton("Back", null);


                builder.create().show();
            }
        });


    }

    @Override
    public void checkBoxCondition(final int pos, final boolean cond) {
        firebase.child("VALUE").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (i == pos) {

                        Firebase ref = data.getRef();
                        ref.child("checkin").setValue(!cond);
                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
