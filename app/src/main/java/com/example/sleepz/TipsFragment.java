package com.example.sleepz;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TipsFragment extends Fragment implements View.OnClickListener {
    ImageButton play, skipBack, skipNext;
    MediaPlayer mediaPlayer;
    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar seekBar;
    ArrayList<TipMusic> arraySong;
    int position = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tips, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //button
        play = view.findViewById(R.id.btnPlay);
        skipBack = view.findViewById(R.id.btnSkipBack);
        skipNext = view.findViewById(R.id.btnSkipNext);

        //set time text view and title
        txtTimeSong = view.findViewById(R.id.timeSong);
        txtTimeTotal = view.findViewById(R.id.timeTotal);
        txtTitle = view.findViewById(R.id.Title);

        //seekbar
        seekBar = view.findViewById(R.id.seekbar);


        play.setOnClickListener(this);
        skipNext.setOnClickListener(this);
        skipBack.setOnClickListener(this);


        addSong();

        khoiTaoMedia();


    }

    // update time cho file text
    private void updateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));

                //tu nhay bai
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arraySong.size() - 1) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        khoiTaoMedia();
                        mediaPlayer.start();
                        play.setBackgroundResource(R.drawable.ic_pause_black_24dp);

                        //set text timetotal
                        setTimeTotal();

                        //update time
                        updateTimeSong();
                    }
                });

                //update seekbar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        }, 100);
    }


    //set thoi gian bai hat
    private void setTimeTotal() {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }


    //tao media
    private void khoiTaoMedia() {
        mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());
    }

    private void addSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new TipMusic("Bài 1", R.raw.bensoundallthat));
        arraySong.add(new TipMusic("Bài 2", R.raw.bensoundcreativeminds));
        arraySong.add(new TipMusic("Bài 3", R.raw.bensounddreams));
        arraySong.add(new TipMusic("Bài 4", R.raw.bensounderf));
        arraySong.add(new TipMusic("Bài 5", R.raw.bensoundmemories));
        arraySong.add(new TipMusic("Bài 6", R.raw.bensoundonceagain));
        arraySong.add(new TipMusic("Bài 7", R.raw.bensoundsummer));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    play.setBackgroundResource(R.drawable.ic_play);
                } else if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    play.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                }

                //set text timetotal
                setTimeTotal();

                //update time
                updateTimeSong();

                //set seekbar
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mediaPlayer.seekTo(seekBar.getProgress());
                    }
                });


                break;


            case R.id.btnSkipNext:
                position++;
                if (position > arraySong.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                khoiTaoMedia();
                mediaPlayer.start();
                play.setBackgroundResource(R.drawable.ic_pause_black_24dp);

                //set text timetotal
                setTimeTotal();

                //update time
                updateTimeSong();
                break;
            case R.id.btnSkipBack:
                position--;
                if (position < 0) {
                    position = arraySong.size() - 1;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                khoiTaoMedia();
                mediaPlayer.start();
                play.setBackgroundResource(R.drawable.ic_pause_black_24dp);

                //set text timetotal
                setTimeTotal();

                //update time
                updateTimeSong();
                break;
        }

    }
}
