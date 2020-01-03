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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmLayout extends AppCompatActivity {
    Switch swtStop;
    MediaPlayer ringtone;
    Intent intentAlarm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        swtStop = findViewById(R.id.swtStop);
        Window window = getWindow();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getExtra();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getExtra(){
        intentAlarm = getIntent();
        Uri uriMusic = Uri.parse(intentAlarm.getStringExtra("pathMusic"));
        ringtone = MediaPlayer.create(this,uriMusic);
        ringtone.start();
        swtStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ringtone.release();
                finish();
            }
        });
    }


}
