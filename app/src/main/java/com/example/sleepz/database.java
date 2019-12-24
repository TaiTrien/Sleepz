package com.example.sleepz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class database  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbname";

    private static final String TABLE_NAME = "tbtime";
    private static final String COLUMN_SLEEP = "sleeptime";
    private static final String COLUMN_WAKE = "waketime";
    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String value = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT)", TABLE_NAME, COLUMN_SLEEP, COLUMN_WAKE);
        db.execSQL(value);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String value = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(value);
        onCreate(db);
    }
    public long create(String sleeptime, String waketime)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SLEEP, sleeptime);
        values.put(COLUMN_WAKE, waketime);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }
    public int update(String sleeptime, String waketime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COLUMN_SLEEP, sleeptime);
        values.put(COLUMN_WAKE, waketime);
        return db.update(TABLE_NAME, values, COLUMN_SLEEP + " = ?",
                new String[]{String.valueOf(sleeptime)});
    }
    public void delete(String sleeptime) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_SLEEP + " = ?", new String[]{String.valueOf(sleeptime)});
        db.close();
    }
    public TIMESLEEP getValue(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_SLEEP, COLUMN_WAKE}, COLUMN_SLEEP + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
    TIMESLEEP times = new TIMESLEEP();
        times.setSleeptime(cursor.getString(cursor.getColumnIndex(COLUMN_SLEEP)));
        times.setWaketime(cursor.getString(cursor.getColumnIndex(COLUMN_WAKE)));
        cursor.close();
        return times;
    }
    public List<TIMESLEEP> getAll() {
        List<TIMESLEEP> list = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TIMESLEEP times = new TIMESLEEP();
                times.setSleeptime(cursor.getString(cursor.getColumnIndex(COLUMN_SLEEP)));
                times.setWaketime(cursor.getString(cursor.getColumnIndex(COLUMN_WAKE)));
                list.add(times);
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }
}
