package com.example.sleepz;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class SLEEP extends BroadcastReceiver {
    //lay thoi gian khi bam nut va chuyen qua string de luu vao database
    Long gettime=System.currentTimeMillis();
    String currenttime=Long.toString(gettime);
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //tắt noti khi bấm vào nút
        NotificationManager notificationManager =(NotificationManager) context.getSystemService((Context.NOTIFICATION_SERVICE));
        notificationManager.cancelAll();
    }
    
}
