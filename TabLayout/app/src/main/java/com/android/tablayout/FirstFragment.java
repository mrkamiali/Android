package com.android.tablayout;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private ListView listeView;
    private Button button;
    private ListViewFirstAdapter listViewAdapter;
    private List<Message> messages;
    private DataBase db;
    private DataBaseSecond db2;


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Resume", "items");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Pause", "items");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        listeView = (ListView) view.findViewById(R.id.list_view);
        button = (Button) view.findViewById(R.id.add_item);

        messages = new ArrayList<>();
        //   messages.add(new Message("Kamran", "My name is Kamran ALi", 0, true));
        getItem();
        addItem();
        addToSecondFrag();

        return view;
    }

    public void refreshInvoked() {

        getItem();
    }

    public void addToSecondFrag() {

        listeView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select your Desired Option!");
                builder.setPositiveButton("FinishedItem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Message message = messages.get(position);
                        db2.saveData(message);
                        Log.d("newItem", "item saved " + message.getName());
                        refreshInvoked();
                        int id1 = messages.get(position).getId();
                        db.deleteItem(id1);
                        messages.remove(position);

                        listViewAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Message message = messages.get(position);
                        int id = messages.get(position).getId();
                        db.deleteItem(id);
                        messages.remove(position);
                        refreshInvoked();
                        listViewAdapter.notifyDataSetChanged();
                    }
                });

                builder.create().show();
                return true;
            }
        });

    }

    public void getItem() {
        db = new DataBase(getActivity().getApplicationContext());
        db2 = new DataBaseSecond(getActivity().getApplicationContext());

        messages = db.reteriveItem();

        listViewAdapter = new ListViewFirstAdapter((ArrayList<Message>) messages, getActivity());
//        messages = db.reteriveItem();
        listeView.setAdapter(listViewAdapter);
        listViewAdapter.notifyDataSetChanged();
    }

    public void addItem() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Add New Item!");
                final View dialogeView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                        .inflate(R.layout.dialoge_layout, null);
                builder.setView(dialogeView);
                builder.setNegativeButton("Cancel", null);
                builder.setCancelable(false);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText nameView = (EditText) dialogeView.findViewById(R.id.name_Edit);
                        EditText msgView = (EditText) dialogeView.findViewById(R.id.msg_Edit);
                        CheckBox checkBox_view = (CheckBox) dialogeView.findViewById(R.id.check_Edit);

                        String name = nameView.getText().toString();
                        String msg = msgView.getText().toString();
                        boolean checked = checkBox_view.isChecked();

                        Message email = new Message(name, msg, 0, checked);

                        messages.add(email);
                        db.saveItem(email);
                        listViewAdapter.notifyDataSetChanged();
                    }
                });

                builder.create().show();
            }
        });
    }
}
