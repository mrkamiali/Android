package com.android.tablayout;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private ListView listview;
    private ListViewSecondAdapter listViewAdapterSecond;
    private DataBaseSecond db2;
    private DataBase db1;
    private List<Message> mMessages;
    private ListViewFirstAdapter listViewAdapterFirst;

    public SecondFragment() {
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

    public void refreshInvoked() {
        getitems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        listview = (ListView) view.findViewById(R.id.second_Frag_listView);

        getitems();


        // listViewAdapterSecond.notifyDataSetChanged();
        undoItem();
        return view;

    }

    private void getitems() {
        db1 = new DataBase(getActivity());
        db2 = new DataBaseSecond(getActivity());
        mMessages = db2.reteriveData();

        listViewAdapterSecond = new ListViewSecondAdapter(mMessages, getActivity());

        listview.setAdapter(listViewAdapterSecond);

        listViewAdapterSecond.notifyDataSetChanged();

    }


    private void undoItem() {
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Undo or delete ITem !");
                builder.setPositiveButton("Undo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Message message = mMessages.get(position);
                        String name = message.getName();
                        String msg1 = message.getMsg();
                        int id = message.getId();
                        db1.saveItem(message);
                        db2.deleteItem(id);
                        mMessages.remove(position);

                        listViewAdapterSecond.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Message message = mMessages.get(position);
                        int id = message.getId();
                        db2.deleteItem(id);
                        mMessages.remove(position);
                        listViewAdapterSecond.notifyDataSetChanged();
                    }
                });

                builder.create().show();
                return false;
            }
        });

    }


}
