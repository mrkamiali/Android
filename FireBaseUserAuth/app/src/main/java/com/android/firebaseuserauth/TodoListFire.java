package com.android.firebaseuserauth;

import android.app.AlertDialog;
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

public class TodoListFire extends AppCompatActivity {
    private ListView emailList;
    private Button addItem;
    private List<Message> messages;
    private MessageAdapter adapter;
    private DataBase db = new DataBase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_fire);


        emailList = (ListView) findViewById(R.id.message_item_ListView);
        addItem = (Button) findViewById(R.id.button_add);
        setupEmailList();
        addItemPrompt();

    }

    public void addItemPrompt() {
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoListFire.this);

                View view = getLayoutInflater().inflate(R.layout.dialoge_layout, null);

                final EditText addTitle = (EditText) view.findViewById(R.id.add_item_title);
                final EditText addMsg = (EditText) view.findViewById(R.id.add_item_message);
                final CheckBox checkView = (CheckBox) view.findViewById(R.id.tick_box_View);

                builder.setView(view);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = addTitle.getText().toString();
                        String msg = addMsg.getText().toString();
                        boolean read = checkView.isChecked();

                        Message email = new Message(title, msg, 0, read);
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

    private void setupEmailList() {

        messages = new ArrayList<Message>();

//        messages.add(new Message("Kamran", "My name is Kamran", 0, false));

        Log.d("TAG", "Message is" + messages);

        emailList = (ListView) findViewById(R.id.message_item_ListView);

        messages = db.reterivingList();

        adapter = new MessageAdapter((ArrayList<Message>) messages, this);


        emailList.setAdapter(adapter);


        emailList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoListFire.this);

                builder.setMessage("Click delete to delete Item!");
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = messages.get(position).getId();
                        messages.remove(position);
                        db.deleteItem(id);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }
}
