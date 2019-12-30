package com.example.sleepz;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Ringtone extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Uri uriMusic = Uri.parse(intent.getStringExtra("pathMusic"));
        Intent intentRingTone = new Intent(this, AlarmLayout.class);
        intentRingTone.putExtra("pathMusic",intent.getExtras().getString("pathMusic"));
        intentRingTone.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intentRingTone);
        return START_NOT_STICKY;
    }

}
