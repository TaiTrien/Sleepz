package com.example.sleepz;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.app.PendingIntent;
import androidx.annotation.Nullable;
import android.app.Notification;
import android.app.NotificationManager;

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
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder SZnotification  = new Notification.Builder(this)
                .setContentTitle("TIME TO SLEEP")
                .setContentText("ngủ đi bạn ơi")
                .setSmallIcon(R.drawable.icon_png_transparent)
                .setContentIntent(pIntent)
                .setAutoCancel(true);
        `
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,SZnotification.build());
        return START_NOT_STICKY;
    }
}
