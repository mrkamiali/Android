package com.android.tablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kamran ALi on 4/15/2016.
 */
public class ListViewSecondAdapter extends BaseAdapter implements ListAdapter {
    private List<Message> mMessages;
    private Context context;

    public ListViewSecondAdapter(List<Message> messages, Context context) {
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
        View view = inflater.inflate(R.layout.second_frag_message_item, null);

        TextView nameView_second = (TextView) view.findViewById(R.id.name_view_second);
        TextView msgView_second = (TextView) view.findViewById(R.id.msg_view_second);
        final ImageView imgView_second = (ImageView) view.findViewById(R.id.img_view_second);

        nameView_second.setText(mMessages.get(position).getName());
        msgView_second.setText(mMessages.get(position).getMsg());
//        imgView_second.setImageResource(mMessages.get(position));

        return view;
    }
}
