package com.android.tablayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 4/15/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String TABLAYOUTDB = "DATABASE";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_MSG = "MESSAGE";
    private static final String COL_CHECKBOX = "CHECKBOX";

    public DataBase(Context context) {
        super(context, "TabLayoutDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT not null,%s TEXT,%s TEXT)", TABLAYOUTDB, COL_ID, COL_NAME, COL_MSG, COL_CHECKBOX);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveItem(Message message) {
        SQLiteDatabase writableDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_NAME, message.getName());
        values.put(COL_MSG, message.getMsg());
        values.put(COL_CHECKBOX, message.isRead());

        writableDatabase.insert(TABLAYOUTDB, null, values);

        writableDatabase.close();
    }

    public List<Message> reteriveItem() {
        List<Message> messages = new ArrayList<>();

        SQLiteDatabase readableDatabase = getReadableDatabase();
        String sql = String.format("select %s,%s,%s,%s from %s order by %s", COL_ID, COL_NAME, COL_MSG, COL_CHECKBOX, TABLAYOUTDB, COL_ID);

        Cursor cursor = readableDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String msg = cursor.getString(2);
            String tick = cursor.getString(3);
            Log.d("DataBaseList", "name and msg is " + name + msg);

            messages.add(new Message(name, msg, id, Boolean.valueOf(tick)));

        }
        readableDatabase.close();


        return messages;
    }

    public void deleteItem(int id) {
        getWritableDatabase().delete(TABLAYOUTDB, COL_ID + " = " + id, null);
    }
}
