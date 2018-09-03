package com.example.rachitabhagchandani.musicapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ReceiveBroadcasts extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String log = intent.getAction();
        Log.d("LOG", log);
        Toast.makeText(context, log, Toast.LENGTH_SHORT).show();
    }
}
