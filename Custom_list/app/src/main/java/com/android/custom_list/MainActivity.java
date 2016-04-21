package com.android.custom_list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Message> messages;
    private MessageAdapter adapter;
    private ListView emailView;
    private Button addButton;
    private DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUPEmailList();
        adddItemAlert();
    }

    private void adddItemAlert() {
        addButton = (Button) findViewById(R.id.message_addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                View view = getLayoutInflater().inflate(R.layout.dialoge_layout, null);

                final EditText titleField = (EditText) view.findViewById(R.id.message_addTitle_Field);
                final EditText msgField = (EditText) view.findViewById(R.id.message_addMsg_Field);
                final CheckBox tickViewField = (CheckBox) view.findViewById(R.id.message_CheckBox);

                builder.setView(view);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = titleField.getText().toString();
                        String msg = msgField.getText().toString();
                        boolean read = tickViewField.isChecked();
                        Message email=new Message(msg, title, 0, read);
                        Log.d("TAG","Chcek box is :"+read);
                        messages.add(email);
                        db.savingList(email);
                        adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });

    }

    private void setUPEmailList() {
        messages = new ArrayList<Message>();

        messages = db.reterivingList();

        adapter = new MessageAdapter(messages, this);

        emailView = (ListView) findViewById(R.id.email_List);

        emailView.setAdapter(adapter);

        emailView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Press Delete to delete Item !");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = messages.get(position).getId();
                        messages.remove(position);
                        db.deleteItem(id);
                        adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
                return true;
            }
        });

    }

}
