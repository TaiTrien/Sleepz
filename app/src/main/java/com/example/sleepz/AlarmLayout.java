package com.example.sleepz;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmLayout extends AppCompatActivity {
    Switch switchStop;
    MediaPlayer ringtone;
    Intent intentAlarm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        switchStop = findViewById(R.id.switchStop);
        Window window = getWindow();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
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
            }
        });
    }

}
