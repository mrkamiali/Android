package com.android.todofirepractice;

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
 * Created by Kamran ALi on 5/8/2016.
 */
public class CustomAdapter extends BaseAdapter implements ListAdapter {
   
    private Context mContext;
    private ArrayList<Message> message;
    private MainActivity mainActivity;//uper line dekh class k namewali
    //ismy 3 name hain in 3 ki type k instase var bnao
    //isi class ma ?hn
//is construtr me q main k varaible ma context assign kia or cast karaya
    public CustomAdapter(Context context, ArrayList<Message> message) {
        mContext = context;
        this.message = message;

        //kuch samjh aaya matlab k aapki class k header me jtny b name hain
        //aap ki class un sab k value me convert ho sakti hai matlab cast
        //ye cheez clear hai...? han



        //yeh kia kia hy main actibity k sath or

        //humary pas is baat ki surity thi k hamari mainact wali class
        //humary interfaceko implement kerti hai to hum ny usko cast
        //kerdia or phir us k instace se humny wo interface wala overriden method run
        //b kerdia... or us methid me sirf humny vals ko pass ker k main me use ker lia...
        //samjha? kch kch
        //kia nahi samjh aaya...?
        // bai app free hu jao phr btata hn tb tk faiz ki marta hn
        //me free hun
         // yar yaha ma smja nhi paa rha
        //shortcut btaoun...?
        // pouch rha q h
        //acha this ka matlab pata hai kia hota hai..?
        // han activity ka obj jis main ho ap currently
        //sirf activity nahi kisi b javaki class ka obj current obj only
        //woi na  do it !
        mainActivity = (MainActivity) context;
        //ab yaha per jo humny main ko interface me cast kerdia to uski waja b yehi hai
        // k main k header me ye interface mention hIn...
        /// ooh tery ki ab smj aua
        //
        //ab neechy jo humny method call kia hai wo humny main k intance ko convert kia
        //interface me then humny interfacek method ko run kia hai...
        //done ! smj gya
        //waqai samjh gaya ya nneeend aarahi hai...?
        // bai ab coding karty hwy neend nhi aatii
        //good keep it up....
        // bas ab hum dono ko thora chat app ka scnerio bta de ta ky hum start lyn
        // faiz ny UI takreeban bna b lia h
        //msngr per aaoaja
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
        View view = inflater.inflate(R.layout.message_layout, null);
        TextView nameView = (TextView) view.findViewById(R.id.name_View);
        TextView msgView = (TextView) view.findViewById(R.id.msg_view);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox_View);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yeh interface k variable ko main activity assign kia hy particular value change k lie or bas
                CheckIn checkIn = mainActivity;
                checkIn.checkBoxCondition(position, checkBox.isChecked());
            }
        });
        Message msg = message.get(position);
        String name = msg.getName();
        String msg1 = msg.getMsg();
        boolean checkin = msg.isCheckin();
        checkBox.setChecked(checkin);

        nameView.setText(name);
        msgView.setText(msg1);

        return view;
    }

    public interface CheckIn {
        void checkBoxCondition(int pos, boolean cond);
    }
}
