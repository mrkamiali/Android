package com.android.todofire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kamran ALi on 5/4/2016.
 */
public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private Context mContext;
    private ArrayList<Message> message;
    MainActivity mMainActivity;

    public CustomAdapter(Context context, ArrayList<Message> message) {
        mContext = context;
        this.message = message;
        mMainActivity = (MainActivity) context;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.message_item, null);
        TextView nameView = (TextView) view.findViewById(R.id.name_View);
        TextView msgView = (TextView) view.findViewById(R.id.msg_View);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_box);
        //reteriving msg

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chek chek = mMainActivity;
                chek.valueCheck(position, checkBox.isChecked());

            }
        });


        Message message1 = this.message.get(position);
        String name = message1.getName();
        String msg = message1.getMsg();
        boolean check = message1.isCheckBox();
        checkBox.setChecked(check);
        //setting to text view
        nameView.setText(name);
        msgView.setText(msg);
        //  notifyDataSetChanged();
        return view;
    }

    interface Chek {
        void valueCheck(int pos, boolean checkk);
    }
}
