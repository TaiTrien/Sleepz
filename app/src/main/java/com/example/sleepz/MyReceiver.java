package com.example.sleepz;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        NotificationManager notificationManager =(NotificationManager) context.getSystemService((Context.NOTIFICATION_SERVICE));
        notificationManager.cancelAll();

    }
}
