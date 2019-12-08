package com.example.sleepz;

import android.app.AlarmManager;
import android.app.PendingIntent;
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

import java.sql.Time;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmFragment extends Fragment {
    TimePicker timePicker;
    Button btnAlarm;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        timePicker = view.findViewById(R.id.alarmTimePicker);
        timePicker.setIs24HourView(true);
        btnAlarm = view.findViewById(R.id.btnAlarm);
        intent = new Intent(AlarmFragment.this,AlarmReceiver.class);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();

        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pendingIntent = PendingIntent.getBroadcast(AlarmFragment.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }
        });
        return view;
    }

}
