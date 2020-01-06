package com.example.sleepz;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Noti extends Service {
    private static final String CHANNEL_ID = "2298";
    public int notificationId = 0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public void notification (Intent intent)
    {
        createNotificationChannel();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,0);
        Intent Sintent = new Intent(this,SLEEP.class);
        Intent Wintent = new Intent(this,WAKE.class);
        Sintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Wintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent Spending = PendingIntent.getBroadcast(this, 0,Sintent,0);
        PendingIntent Wpending = PendingIntent.getBroadcast(this, 0,Wintent,0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Tới giờ ngủ rồi")
                .setContentText("Ngủ cho ngày mai có một sức khỏe tốt nào")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .addAction(R.mipmap.ic_launcher,"SLEEPING",Spending)
                .addAction(R.mipmap.ic_launcher,"WAKED",Wpending);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, mBuilder.build());
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notification(intent);

        return START_NOT_STICKY;
    }
}
