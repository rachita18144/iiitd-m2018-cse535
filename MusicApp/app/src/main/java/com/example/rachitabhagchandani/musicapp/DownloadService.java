package com.example.rachitabhagchandani.musicapp;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadService extends IntentService {

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Handler handler = new Handler(getMainLooper());
        boolean status = checkNetworkConnectivity();
        if(status){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showToast("Downloading music file");
                }
            });
            downloadMusicFile();
        }else{
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showToast("Unable to download file, network issue");
                }
            });
        }
    }

   boolean checkNetworkConnectivity() {
        ConnectivityManager conn = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        if(info != null && info.isConnectedOrConnecting()) {
            return true;
        }else{
            return false;
        }
   }

   void downloadMusicFile(){
       //File output = new File(Environment.getExternalStorageDirectory(), "sample.mp3");
       File output = new File(this.getFilesDir(), "sample.mp3");
       if (output.exists()) {
           output.delete();
       } try {
           URL url = new URL("http://faculty.iiitd.ac.in/~mukulika/s1.mp3");
           URLConnection conn = url.openConnection();
           InputStream stream = conn.getInputStream();
           conn.connect();
           FileOutputStream fos = new FileOutputStream(output);
           int next = -1;
           while ((next = stream.read()) != -1) {
               //Log.d("Log", "value: " + next);
               fos.write(next);
           }
           final String yourFilePath = this.getFilesDir() + "/" + "sample.mp3";
           //final File yourFile = new File(yourFilePath);
           Log.i("LOg", yourFilePath);

           MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse(yourFilePath));
           mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
           mediaPlayer.start();

           Handler handler = new Handler(getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showToast("File downloaded successfully");
                }
            });

            stream.close();
            fos.close();

       } catch (Exception e) {
           e.printStackTrace();
       }
   }

  /* void downloadMusicFile(){
        String url = "http://faculty.iiitd.ac.in/~mukulika/s1.mp3";
        try{
            URL downloadurl = new URL(url);
            URLConnection connection = downloadurl.openConnection();
            connection.connect();
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            Log.d("log",path);
            File file = new File(path, "s1.mp3");
            if (file.exists()) {
                file.delete();
            }

            InputStream input = new BufferedInputStream(connection.getInputStream());
            FileOutputStream output = new FileOutputStream(file.getPath());
            byte data[] = new byte[1024];
            int count;
            while((count = input.read(data)) != -1){
                Log.d("log","here");
                output.write(data, 0, count);
            }
            output.close();
            input.close();
            Handler handler = new Handler(getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showToast("File downloaded successfully");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
   }*/

    void showToast(CharSequence  text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
   }

}
