package com.android.custom_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kamran ALi on 2/29/2016.
 */
public class MessageAdapter extends BaseAdapter implements ListAdapter {
    private List<Message> message ;
    private Context context ;


    public MessageAdapter(List<Message> message, Context context) {
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
        return message.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.message_item_list, null);

        TextView titleView = (TextView) view.findViewById(R.id.message_item_title);
        TextView senderView = (TextView) view.findViewById(R.id.message_item_sender);
        final ImageView iconView = (ImageView) view.findViewById(R.id.message_item_icon);
        final Message messages = message.get(position);

        String sender = messages.getSender();
        String title = messages.getTitle();
        int id = messages.getId();

        int icon = messages.isRead() ? R.drawable.btn_radio_on_holo_dark : R.drawable.btn_radio_on_disabled_holo_dark;
        Log.d("TSG", messages.getTitle()+"Bollen value in asdapter"+messages.isRead());

        iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (messages.isRead()) {
                    messages.setRead(false);
                } else {
                    messages.setRead(true);
                }
                notifyDataSetChanged();


            }
        });

        iconView.setImageResource(icon);

        titleView.setText(title);
        senderView.setText(sender);
        return view;
    }
}
