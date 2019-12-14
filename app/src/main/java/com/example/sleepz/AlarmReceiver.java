package com.example.sleepz;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("hello","xin chao");
        Intent receiveIntent = new Intent(context,Ringtone.class);
        receiveIntent.putExtra("pathMusic", intent.getExtras().getString("pathMusic"));
        context.startService(receiveIntent);
    }
}
