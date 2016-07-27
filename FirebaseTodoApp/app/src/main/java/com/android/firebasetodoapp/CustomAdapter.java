package com.android.firebasetodoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kamran ALi on 6/4/2016.
 */
public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Message> message;
    private Context context;

    public CustomAdapter(ArrayList<Message> message, Context context) {
        this.message = message;
        this.context = context;
    }

    @Override
    public int getCount() {
        return message.size();
    }

    @Override
    public Object getItem(int position) {
        return message.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viewing_message_layout, null);

        TextView nameView = (TextView) view.findViewById(R.id.name_View);
        TextView msgView = (TextView) view.findViewById(R.id.message_View);

        Message msge = this.message.get(position);

        nameView.setText(msge.getName().toString());
        msgView.setText(msge.getMessage().toString());

        notifyDataSetChanged();

        return view;
    }
}
