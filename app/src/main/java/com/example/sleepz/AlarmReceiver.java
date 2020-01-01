package com.example.sleepz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent receiveIntent = new Intent(context,Ringtone.class);
        receiveIntent.putExtra("pathMusic", intent.getExtras().getString("pathMusic"));
        context.startService(receiveIntent);
        //Intent myIntent = new Intent(context,AlarmLayout.class);
        //myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(myIntent);
    }
}
