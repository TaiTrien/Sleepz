package com.example.sleepz;

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
        context.startService(receiveIntent);
    }
}
