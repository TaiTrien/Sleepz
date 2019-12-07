package com.example.sleepz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Time;
import java.util.Calendar;

public class AlarmFragment extends Fragment {
    private Context mContext;
    TimePicker timePicker;
    Button btnTimeChooser;// btn to choose time and set alarm
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent; // intent to time
    Intent intent = new Intent();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        mContext = this.getContext();
        timePicker = view.findViewById(R.id.alarmTimePicker);
        btnTimeChooser = view.findViewById(R.id.btnAlarm);
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        timePicker.setIs24HourView(true);
        btnTimeChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AlarmReceiver.class);
                int hour = timePicker.getHour();
                int minutes = timePicker.getMinute();
                intent.putExtra("Hour", hour);
                intent.putExtra("Minutes", minutes);
                pendingIntent = PendingIntent.getBroadcast(mContext,0,intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            }
        });
        return view;
    }

}
