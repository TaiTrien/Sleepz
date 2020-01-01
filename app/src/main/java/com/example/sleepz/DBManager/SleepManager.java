package com.example.sleepz.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.sleepz.AlarmFragment;
import com.example.sleepz.model.Sleepy;

import java.sql.Time;
import java.util.Calendar;

import androidx.annotation.Nullable;

public class SleepManager extends SQLiteOpenHelper {
    private final String TAG = "SleepManager";
    private static final String DATABASE_NAME = "sleepy_manager";
    private static final String TABLE_NAME = "sleepy";
    private static final String SLEEP_TIME = "sleep_time";
    private static final String WAKE_TIME = "wake_time";
    private static final int VERSION = 1;

    private String SQLquery = "CREATE TABLE " + TABLE_NAME + " (" +
            SLEEP_TIME + " TEXT, " +
            WAKE_TIME + " TEXT)";

    public SleepManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Log.d(TAG,"SleepManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLquery);
        Log.d(TAG,"onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG,"onUpgrade: ");
    }

    public void addTime(Sleepy sleepy){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SLEEP_TIME,sleepy.getSleepTime());
        contentValues.put(WAKE_TIME,sleepy.getWakeTime());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        Log.d(TAG,"addTime Successfully");
    }

    public int updateTime(Sleepy sleepy){
        SQLiteDatabase sqLiteDatabase1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WAKE_TIME,sleepy.getWakeTime());
        return sqLiteDatabase1.update(TABLE_NAME,contentValues,"WAKE_TIME = "+sleepy.getWakeTime(),null);
    }
}
