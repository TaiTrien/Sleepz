package com.example.sleepz;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

public class Ringtone extends Service {
    MediaPlayer ringtone;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Uri uriMusic = Uri.parse(intent.getStringExtra("pathMusic"));
        ringtone = MediaPlayer.create(this, uriMusic);
        ringtone.start();
        return START_NOT_STICKY;
    }
}
