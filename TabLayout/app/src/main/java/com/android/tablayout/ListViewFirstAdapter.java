package com.android.tablayout;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 4/15/2016.
 */
public class ListViewFirstAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Message> messages;
    private Context context;

    public ListViewFirstAdapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.message_item_list, null);

        TextView nameView = (TextView) view.findViewById(R.id.name_view);
        TextView msgView = (TextView) view.findViewById(R.id.msg_view);
        final ImageView imgView = (ImageView) view.findViewById(R.id.img_view);

        final Message msg = messages.get(position);

        String name = msg.getName();
        String msg1 = msg.getMsg();
        boolean read = msg.isRead();

        nameView.setText(name);
        msgView.setText(msg1);
        int iconId = msg.isRead() ? R.drawable.btn_circle_disable : R.drawable.btn_circle_pressed;
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (msg.isRead()) {
                    msg.setRead(false);
                } else {
                    msg.setRead(true);
                }
                notifyDataSetChanged();
            }
        });
        imgView.setImageResource(iconId);
        return view;
    }

}
