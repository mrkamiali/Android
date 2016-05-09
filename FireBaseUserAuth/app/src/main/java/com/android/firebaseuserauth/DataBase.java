package com.android.firebaseuserauth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 5/10/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String TODOLISTDATABASE = "DATA_BASE";
    private static final String COL_ID = "ID";
    private static final String COL_TITLE = "TITLE";
    private static final String COL_MSG = "MSG";
    private static final String COL_CHECKBOX = "CHECKBOX";

    public DataBase(Context context) {
        super(context, "ToDoListDataBase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s INTEGER primary key AUTOINCREMENT,%s TEXT not null, %s TEXT , %s TEXT )", TODOLISTDATABASE, COL_ID, COL_TITLE, COL_MSG, COL_CHECKBOX);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void savingList(Message messages) {
        SQLiteDatabase writableDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_TITLE, messages.getTitle());
        values.put(COL_MSG, messages.getSender());
        values.put(COL_CHECKBOX, messages.isRead());
        Log.d("TAG", "Message and title is" + messages.getTitle() + " : " + messages.getSender());
        writableDatabase.insert(TODOLISTDATABASE, null, values);

        writableDatabase.close();
    }

    public List<Message> reterivingList() {
        List<Message> messages = new ArrayList<Message>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String sql = String.format("select %s,%s,%s,%s from %s order by %s", COL_ID, COL_TITLE, COL_MSG, COL_CHECKBOX, TODOLISTDATABASE, COL_ID);
        Cursor cursor = readableDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String msg = cursor.getString(2);
            String checkBoxx = cursor.getString(3);
            Log.d("ID is ", "MSG:" + id);
            messages.add(new Message(title, msg, id, Boolean.valueOf(checkBoxx)));

        }


        readableDatabase.close();
        return messages;
    }

    public void deleteItem(int pos) {
        this.getWritableDatabase().delete(TODOLISTDATABASE, COL_ID + "=" + pos, null);
    }
}
