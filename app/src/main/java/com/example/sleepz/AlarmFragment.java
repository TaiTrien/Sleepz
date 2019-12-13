package com.example.sleepz;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.sql.Time;
import java.util.ArrayList;

public class AlarmFragment extends Fragment {
    private static final int MY_PERMISSION_REQUEST = 1;
    TimePicker timePicker;

    Spinner spinnerMusic;
    ArrayList<String> arraySong;
    ArrayAdapter<String> arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        timePicker = view.findViewById(R.id.alarmTimePicker);
        timePicker.setIs24HourView(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(getActivity() ,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , MY_PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        } else{
            doStuff();
        }


    }
    //
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
                long idMusic = spinnerMusic.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getMusic(){
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri,null,null,null,null);

        if(songCursor != null && songCursor.moveToFirst()){
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);

            do{
                String currentTitle = songCursor.getString(songTitle);
                arraySong.add(currentTitle);
            } while (songCursor.moveToNext());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_SHORT).show();

                        doStuff();
                    }
                }else{
                    Toast.makeText(getActivity(), "No Permission granted", Toast.LENGTH_SHORT).show();

                    getActivity().finish();
                }
                return;
            }
        }
    }


}
