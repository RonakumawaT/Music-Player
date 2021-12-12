package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class playSong extends AppCompatActivity {

    ArrayList<File> songs;
    int position;
    String song;
    MediaPlayer mediaPlayer;
    TextView songText,playingSong;
    SeekBar seekBar;
    ImageView next,previous,pause,back;
    Thread updateSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        song=getIntent().getStringExtra("currentSong");
        position=getIntent().getIntExtra("position",0);
        songs=(ArrayList) bundle.getParcelableArrayList("allSongs");

        songText =findViewById(R.id.songText);
        songText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        songText.setText(song);
        songText.setSelected(true);

        seekBar=findViewById(R.id.seekBar);
        next=findViewById(R.id.BtnNext);
        previous=findViewById(R.id.BtnPrevious);
        pause=findViewById(R.id.BtnPause);
        back=findViewById(R.id.BtnBack);
        playingSong=findViewById(R.id.Now);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(playSong.this,MainActivity.class));
                finish();
            }
        });

        playingSong.setText("Now playing "+song+" form Track");

        updateSeekBar=new Thread(){
            @Override
            public void run() {

                int totalDuration=mediaPlayer.getDuration();
                int currentPosition=0;

                while (currentPosition<totalDuration){
                    try{
                        sleep(500);
                        currentPosition=mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Uri uri=Uri.parse(songs.get(position).toString());
        mediaPlayer=MediaPlayer.create(this,uri);
        mediaPlayer.start();
        seekBar.setMax(mediaPlayer.getDuration());
        updateSeekBar.start();

        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.design_default_color_primary),PorterDuff.Mode.MULTIPLY);
        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.design_default_color_primary),PorterDuff.Mode.SRC_IN);


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

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setMax(mediaPlayer.getDuration());
                if(mediaPlayer.isPlaying()) {
                    pause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayer.pause();
                }else {
                    pause.setImageResource(R.drawable.ic_baseline_pause_24);
                    mediaPlayer.start();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position=((position+1)%songs.size());

                Uri uri=Uri.parse(songs.get(position).toString());
                mediaPlayer=MediaPlayer.create(playSong.this,uri);
                song=songs.get(position).getName().toString();
                songText.setText(song);

                mediaPlayer.start();

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position=((position-1)<0)?(songs.size()-1):(position-1);
                Uri uri1=Uri.parse(songs.get(position).toString());
                mediaPlayer=MediaPlayer.create(playSong.this,uri1);

                song=songs.get(position).getName().toString();
                songText.setText(song);

                mediaPlayer.start();
            }
        });

    }
}