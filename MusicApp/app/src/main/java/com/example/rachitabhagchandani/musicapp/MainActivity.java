package com.example.rachitabhagchandani.musicapp;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> songs = new ArrayList<String>();
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Field[] fields=R.raw.class.getFields();
        for(int count=0; count < fields.length; count++){
            songs.add(fields[count].getName());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, songs);
        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                playSong(position);
            }
        });
        BroadcastReceiver br = new ReceiveBroadcasts();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        this.registerReceiver(br, filter);
    }

    public void playSong(int pos) {
        if(!flag) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MusicFragment musicFragment = new MusicFragment();
            fragmentTransaction.add(R.id.music_container, musicFragment);
            fragmentTransaction.commit();
        }
        Intent serviceIntent = new Intent(this, MusicService.class);
        serviceIntent.putExtra("pos", pos);
        startService(serviceIntent);
        flag = true;
    }

    void stopMusicPlyaer() {
        Intent serviceIntent = new Intent(this, MusicService.class);
        stopService(serviceIntent);
    }
}
