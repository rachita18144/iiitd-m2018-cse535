package com.example.rachitabhagchandani.quizapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String answer = "";
    int id;

    RelativeLayoutFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new RelativeLayoutFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layout_container, fragment);
        fragmentTransaction.commit();
    }

    public void openQuizFragment(String data, int id) {

        Bundle bundle = new Bundle();
        bundle.putString("question", data);
        bundle.putInt("id", id);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        QuizFragment quizFragment = new QuizFragment();
        quizFragment.setArguments(bundle);
        ft.replace(R.id.layout_container, quizFragment, quizFragment.toString());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_false:
                if (checked)
                    answer = "false";
                break;
            case R.id.radio_true:
                if (checked)
                    answer = "true";
                break;
        }
    }

    public void submitButtonClick(int id) {

        String fileName = createCSVOfDb();
        new DownloadFilesTask().execute();
        //Toast.makeText(this, "Your Answers have been sent to server", Toast.LENGTH_LONG).show();
    }

    public void saveButtonClick(int id) {
        if(answer.equals("")){
            Toast.makeText(this, "Please select one option", Toast.LENGTH_LONG).show();
            return;
        }
        Bundle args = new Bundle();
        args.putString("answer", answer);
        args.putInt("id", id);
        fragment.putArguments(args);
        Toast.makeText(this, "Your Answer Has been saved", Toast.LENGTH_LONG).show();
        answer = "";
    }

    public String createCSVOfDb() {
        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
        File output = new File(Environment.getExternalStorageDirectory(),"");
        if (!output.exists())
        {
            output.mkdirs();
        }

        File file = new File(output, "newcsv.csv");
        if(file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM quiz_details", null);
            FileOutputStream fos = new FileOutputStream(file);
            int next = -1;
            String first = "id" + "," + "question" + "," + "answer" + "," + "saved_answer" + "\n";
            fos.write(first.getBytes());
            while (curCSV.moveToNext()) {
                String value = curCSV.getString(0) + "," + curCSV.getString(1) + "," + curCSV.getString(2) + "," + curCSV.getString(3) + "\n";
                fos.write(value.getBytes());
            }
            fos.close();
            curCSV.close();
        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
        return "newcsv.csv";
    }

    private class DownloadFilesTask extends AsyncTask<Void, Integer, Void> {
        ProgressDialog progressBar;
        boolean status = false;

        public DownloadFilesTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            Handler handler = new Handler(getMainLooper());
            status = checkNetworkConnectivity();
            if(status){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        showToast("Network connectivity ok. File can be uploaded.");
                    }
                });
            }else{
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        showToast("Unable to upload file, network issue");
                    }
                });
            }
            if(status) {
                //Progress bar code
                progressBar = new ProgressDialog(MainActivity.this);
                progressBar.setMessage("Uploading file to server ...");
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.show();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(status) {
                super.onProgressUpdate(values);
                progressBar.setProgress(values[0]);
            }else{
                return;
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            final String yourFilePath = Environment.getExternalStorageDirectory() + "/" + "newcsv.csv";
            File uploadFile1 = new File(yourFilePath);
            long fileSize = uploadFile1.length();
            if(status) {
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                for (int count = 0; count <= fileSize; count++) {
                    publishProgress(count);
                }
                boolean val = sendRequest(uploadFile1);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(status){
                progressBar.dismiss();
            }else{
                return;
            }
            super.onPostExecute(aVoid);
        }

        protected boolean sendRequest(File file) {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("Data", "Quiz Data")
                    .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("text/csv"), file))
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.50.91:8080/signup")
                    .post(body)
                    .build();
            try{
                Response response = client.newCall(request).execute();
                Log.d("response",response.body().string());
            } catch (Exception e){
                e.printStackTrace();
            }
            return true;
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
    void showToast(CharSequence  text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
