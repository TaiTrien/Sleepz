package com.example.sleepz;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.example.sleepz.DBManager.SleepManager;
import com.example.sleepz.model.Sleepy;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmLayout extends AppCompatActivity {
    Switch switchStop;
    MediaPlayer ringtone;
    Intent intentAlarm;
    private static final String TIME_FORMAT_24 = "HH:mm";
    //String pathDB = getDatabasePath("sleepy_manager").getPath();
    //private String TagSQL;
    private SleepManager sleepManager = new SleepManager(this);

//    public void setSleepManager(SleepManager sleepManager) {
//        this.sleepManager = sleepManager;
//    }
//
//    public void updateTime(Sleepy sleepy){
//        SQLiteDatabase db = null;
//        try {
//            db = SQLiteDatabase.openOrCreateDatabase(pathDB, null);
//        }
//        catch (SQLiteException ex) {
//            Log.e(TagSQL, ex.getMessage());
//        }
//        sleepManager.
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(,sleepy.getWakeTime());
//        return db.update(TABLE_NAME,contentValues,SLEEP_TIME + " = " + sleepy.getSleepTime(),null);
//        db.close();
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        switchStop = findViewById(R.id.switchStop);
        //getExtra();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getExtra();
    }

    public void getExtra(){
        intentAlarm = getIntent();
        Uri uriMusic = Uri.parse(intentAlarm.getStringExtra("pathMusic"));
        ringtone = MediaPlayer.create(this,uriMusic);
        ringtone.start();
        switchStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ringtone.stop();
                ringtone.release();
                Sleepy sleepy = updateTime();
                if(sleepy!=null){
                    sleepManager.updateTime(sleepy);
                }
            }
        });
    }

    private Sleepy updateTime(){
        String sleepTime = "1:22";
        String wakeTime = getTime24String();
        Sleepy sleepy = new Sleepy(sleepTime,wakeTime);
        return sleepy;
    }

    public static String getTime24String(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_24);
        return format.format(date);
    }

}
