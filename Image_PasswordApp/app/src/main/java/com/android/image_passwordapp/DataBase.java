package com.android.image_passwordapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 2/8/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String COL_Y = "y";
    private static final String POINT_TABLE = "POINTS";
    private static final String COL_ID = "ID";
    private static final String COL_X = "x";

    public DataBase(Context context) {
        super(context, "note.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("CREATE TABLE %s( %s INTEGER primary key, %s INTEGER not null, %s INTEGER not null)", POINT_TABLE, COL_ID, COL_X, COL_Y);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storePoints(List<Point> points) {
        SQLiteDatabase writableDatabase = getWritableDatabase();

        writableDatabase.delete(POINT_TABLE, null, null);
        int i = 0;
        for (Point point : points) {
            ContentValues values = new ContentValues();

            values.put(COL_ID, i);
            values.put(COL_X, point.x);
            values.put(COL_Y, point.y);

            writableDatabase.insert(POINT_TABLE, null, values);

            i++;
        }

        writableDatabase.close();
    }
    public List<Point> getPoints(){
        List<Point> points = new ArrayList<Point>();

        SQLiteDatabase readableDatabase = getReadableDatabase();
        String sql = String.format("SELECT %s , %s FROM %s ORDER BY %s", COL_X, COL_Y, POINT_TABLE, COL_ID);
        Cursor cursor = readableDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()){

            int x = cursor.getInt(0);
            int y = cursor.getInt(1);

            points.add(new Point(x,y));
        }

        readableDatabase.close();
        return  points;
    }
}
