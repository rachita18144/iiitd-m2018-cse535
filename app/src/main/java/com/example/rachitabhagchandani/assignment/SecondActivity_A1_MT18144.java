package com.example.rachitabhagchandani.assignment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity_A1_MT18144 extends AppCompatActivity {
    private static final String TAG = "INFO";
    TextView textViewName, textViewRoll, textViewBranch, textViewCourse;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_a1_mt18144);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            textViewName = (TextView) findViewById(R.id.textViewName);
            String name =(String) bundle.get("name");
            textViewName.setText(name);

            textViewRoll = (TextView) findViewById(R.id.textViewRollNumber);
            String roll =(String) bundle.get("roll_number");
            textViewRoll.setText(roll);

            textViewBranch = (TextView) findViewById(R.id.textViewBranch);
            String branch =(String) bundle.get("branch");
            textViewBranch.setText(branch);

            textViewCourse = (TextView) findViewById(R.id.textViewCourses);
            String course =(String) bundle.get("course1");
            course = course + " ," + (String) bundle.get("course2");
            course = course + " ," + (String) bundle.get("course3");
            course = course + " ," + (String) bundle.get("course4");
            textViewCourse.setText(course);
        }
    }
    protected void onStart(){
        super.onStart();
        String message = "State of activity " + this.getClass().getSimpleName() + " Changed from created to started";
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        Log.d(TAG,message) ;
    }
    protected void onResume(){
        super.onResume();
        String message = "State of activity " + this.getClass().getSimpleName() + " Changed from paused to resumed";
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        Log.d(TAG,message) ;
    }
    protected void onPause(){
        super.onPause();
        String message = "State of activity " + this.getClass().getSimpleName() + " Changed from resumed to paused";
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        Log.d(TAG,message) ;
    }
    protected void onStop(){
        super.onStop();
        String message = "State of activity " + this.getClass().getSimpleName() + " Changed from paused to stopped";
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        Log.d(TAG,message) ;
    }
    protected void onDestroy(){
        super.onDestroy();
        String message = "State of activity " + this.getClass().getSimpleName() + " Changed from stopped to destroyed";
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        Log.d(TAG,message) ;
    }
}
