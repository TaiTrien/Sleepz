package com.example.sleepz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TipsFragment extends Fragment implements View.OnClickListener {
    ImageButton play, skipBack, skipNext;
    ImageView imageView;
    MediaPlayer mediaPlayer;
    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar seekBar;
    ArrayList<TipMusic> arraySong;
    int position = 0;
    Animation animationStart, animationStop;
    SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
    Button btnSwipUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if (savedInstanceState!=null){
          mediaPlayer.seekTo(savedInstanceState.getInt("TimeSong"));

       }

        return inflater.inflate(R.layout.fragment_tips, container, false);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mediaPlayer.stop();
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

        //image
        imageView = view.findViewById(R.id.iconPlayMusic);

        play.setOnClickListener(this);
        skipNext.setOnClickListener(this);
        skipBack.setOnClickListener(this);

        //button swip up
        btnSwipUp = (Button) view.findViewById(R.id.btn_Swip_up);
        btnSwipUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startTips(); // to open tips activity
            }
        });


        addSong();

        khoiTaoMedia();

        animationStart = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_picture);
        animationStop = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_stop);
    }

    public void startTips(){
        Intent openIntent = new Intent(this.getContext(), tips_content_activity.class);
        startActivity(openIntent);
    }
    // update time cho file text
    private void updateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
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
        arraySong.add(new TipMusic("Bài 1", R.raw.chucbengungon));
        arraySong.add(new TipMusic("Bài 2", R.raw.themoon));
        arraySong.add(new TipMusic("Bài 3", R.raw.muavanuocmat));
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//nut play
            case R.id.btnPlay:
                if (mediaPlayer.isPlaying()) {
                    imageView.startAnimation(animationStop);
                    mediaPlayer.pause();
                    play.setBackgroundResource(R.drawable.ic_play);
                } else if (!mediaPlayer.isPlaying()) {
                    imageView.startAnimation(animationStart);
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


//nut next
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
                imageView.startAnimation(animationStart);
                //update time
                updateTimeSong();
                break;

//nut back
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
                imageView.startAnimation(animationStart);
                //update time
                updateTimeSong();
                break;
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("TimeSong", mediaPlayer.getCurrentPosition());
        outState.putBoolean("isPlayerPlaying", mediaPlayer.isPlaying());
    }
}
