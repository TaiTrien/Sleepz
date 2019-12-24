package com.example.sleepz;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class SLEEP extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        NotificationManager notificationManager =(NotificationManager) context.getSystemService((Context.NOTIFICATION_SERVICE));
        notificationManager.cancelAll();
    }
    public void create(String sleeptime, String waketime) {
        database db = new database(null,null,null,1);
        db.create(sleeptime,waketime);
        TIMESLEEP time = new TIMESLEEP();
        time.setSleeptime(sleeptime);
        time.setWaketime(waketime);
    }
    public void savetime()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        create(dateFormat.format(cal),null);
    }
}
