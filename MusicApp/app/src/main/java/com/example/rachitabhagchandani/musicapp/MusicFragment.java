package com.example.rachitabhagchandani.musicapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MusicFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.music_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button stopMusicButton = (Button) getView().findViewById(R.id.stop_music);
        stopMusicButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent serviceIntent = new Intent(getActivity(), MusicService.class);
                getActivity().stopService(serviceIntent);
            }
        });
        Button downloadButton = (Button) getView().findViewById(R.id.download_song);
        downloadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent downloadIntent = new Intent(getActivity(), DownloadService.class);
                getActivity().startService(downloadIntent);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
