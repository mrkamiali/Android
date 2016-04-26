package com.android.firebaseobjectapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kamran ALi on 4/25/2016.
 */
public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private List<Message> mMessages;
    private Context context;

    public CustomAdapter(List<Message> messages, Context context) {
        mMessages = messages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mMessages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.message_item_list, null);
        TextView nameView = (TextView) view.findViewById(R.id.name_View);
        TextView msgView = (TextView) view.findViewById(R.id.msg_View);
        Message msg = mMessages.get(position);

        String name = msg.getName();
        String msg1 = msg.getMsg();

        nameView.setText(name);
        msgView.setText(msg1);
        notifyDataSetChanged();
        return view;
    }
}
