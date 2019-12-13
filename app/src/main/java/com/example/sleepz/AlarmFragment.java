package com.example.sleepz;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmFragment extends Fragment {
    private static final int MY_PERMISSION_REQUEST = 1;
    TimePicker timePicker;
    Button btnTimeChooser;// btn to choose time and set alarm
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent; // intent to time
    Intent intent = new Intent();
    Spinner spinnerMusic;
    ArrayList<String> arraySong;
    ArrayAdapter<String> arrayAdapter;
    String selectedSong = null; // title of song which is selected
    Toast alarmNoti; // Toast to notify user know success or not
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = this.getContext();
        timePicker = view.findViewById(R.id.alarmTimePicker);
        btnTimeChooser = view.findViewById(R.id.btnAlarm);
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(mContext, AlarmReceiver.class);
        timePicker.setIs24HourView(true);
        btnTimeChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = timePicker.getHour();
                int minutes = timePicker.getMinute();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minutes);
                intent.putExtra("Hour", hour);
                intent.putExtra("Minutes", minutes);
                setMusicPath(selectedSong,intent);
                pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
               try {
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                       alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                   else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                       alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                   else
                       alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                   alarmNoti = Toast.makeText(mContext, "Set alarm successfully", Toast.LENGTH_LONG * 2);
                   alarmNoti.show();
               }
               catch (Exception e) {
                   alarmNoti = Toast.makeText(mContext, "Set alarm failed", Toast.LENGTH_LONG * 2);
                   alarmNoti.show();
               }

            }
        });
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        } else {
            doStuff();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        doStuff();
    }

    public void doStuff() {
        spinnerMusic = getView().findViewById(R.id.spinnerAlarm);
        arraySong = new ArrayList<>();
        getMusic();

        arrayAdapter = new ArrayAdapter(getActivity(), R.layout.custom_spinner, arraySong);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMusic.setAdapter(arrayAdapter);

        spinnerMusic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = spinnerMusic.getItemAtPosition(position).toString();
                Toast.makeText(getActivity().getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                setTitleSelectedItem(selectedItem);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setTitleSelectedItem(String title){
        selectedSong = title;
    }
    public void setMusicPath(String title, Intent pathMusic) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

        if (songCursor != null && songCursor.moveToFirst()) {

            do {

                if (songCursor.getString(songCursor.getColumnIndex(MediaStore.Video.Media.TITLE)).equals(title))
                {
                    pathMusic.putExtra("pathMusic",
                            songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                    return;
                }
            } while (songCursor.moveToNext());
        }
    }

    public void getMusic() {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

        if (songCursor != null && songCursor.moveToFirst()) {
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);

            do {
                String currentTitle = songCursor.getString(songTitle);
                arraySong.add(currentTitle);
            } while (songCursor.moveToNext());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_SHORT).show();

                        doStuff();
                    }
                } else {
                    Toast.makeText(getActivity(), "No Permission granted", Toast.LENGTH_SHORT).show();

                    getActivity().finish();
                }
                return;
            }
        }
    }


}
