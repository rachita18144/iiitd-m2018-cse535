package com.example.rachitabhagchandani.musicapp;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service {

    List<String> songs = new ArrayList<String>();
    private MediaPlayer mediaPlayer = null;

    public MusicService(){
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            Field[] fields = R.raw.class.getFields();
            for (int count = 0; count < fields.length; count++) {
                songs.add(fields[count].getName());
            }
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            int pos = intent.getIntExtra("pos", 0);
            int resID = getResources().getIdentifier(songs.get(pos).toString(), "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, resID);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null)
            mediaPlayer.release();
    }
}
