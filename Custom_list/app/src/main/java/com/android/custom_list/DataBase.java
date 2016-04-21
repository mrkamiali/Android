package com.android.custom_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 3/1/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String TODOLISTDB = "Data_Base";
    private static final String ID = "ID";
    private static final String TITLE_COL = "TITLE";
    private static final String MSG_COL = "MESSAGE";
    private static final String CHECKBOX = "CONDITION";
    private MessageAdapter adapter;

    public DataBase(Context context) {
        super(context, "DataBaseToDoList.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s(%s INTEGER primary key AUTOINCREMENT, %s TEXT not null,%s TEXT, %s TEXT)", TODOLISTDB, ID, TITLE_COL, MSG_COL, CHECKBOX);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void savingList(Message messages) {
        SQLiteDatabase writableDatabase = getWritableDatabase();

       // writableDatabase.delete(TODOLISTDB, null, null);

//        for (Message message : messages) {
        ContentValues values = new ContentValues();

        values.put(TITLE_COL, messages.getTitle());
        values.put(MSG_COL, messages.getSender());
        values.put(CHECKBOX, messages.isRead());

        writableDatabase.insert(TODOLISTDB, null, values);
        //      }

        writableDatabase.close();
    }

    public void deleteItem(int itemID) {
        this.getWritableDatabase().delete(TODOLISTDB, ID + "=" + itemID, null);

    }

    public List<Message> reterivingList() {
        List<Message> messages = new ArrayList<Message>();

        SQLiteDatabase readableDatabase = getReadableDatabase();

        String sql = String.format("select %s,%s,%s,%s from %s order by %s", ID, TITLE_COL, MSG_COL, CHECKBOX, TODOLISTDB, ID);
        Cursor cursor = readableDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(1);
            String msg = cursor.getString(2);
            String cb = cursor.getString(3);
            int id = cursor.getInt(0);
            Log.d("ID is ", "MSG:" + id);
            messages.add(new Message(msg, title, id,Boolean.valueOf(cb)));
        }

        readableDatabase.close();
        return messages;
    }


}
