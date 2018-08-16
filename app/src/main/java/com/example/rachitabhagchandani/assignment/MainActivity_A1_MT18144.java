package com.example.rachitabhagchandani.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_A1_MT18144 extends AppCompatActivity {

    EditText editTextName, editTextRollNumber, editTextcourse1,editTextcourse2,editTextcourse3,editTextcourse4, editTextBranch;
    private static final String TAG = "INFO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__a1__mt18144);
        Button b = (Button)findViewById(R.id.buttonSubmit);
        Button clear = (Button)findViewById(R.id.buttonClear);

        editTextName = (EditText) findViewById(R.id.editName);
        editTextBranch = (EditText) findViewById(R.id.editBranch);
        editTextRollNumber = (EditText) findViewById(R.id.editRollNumber);
        editTextcourse1 = (EditText) findViewById(R.id.editCouse1);
        editTextcourse2 = (EditText) findViewById(R.id.editCouse2);
        editTextcourse3 = (EditText) findViewById(R.id.editCouse3);
        editTextcourse4 = (EditText) findViewById(R.id.editCouse4);

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name, branch, rollNumber, course1, course2, course3, course4;
                name = editTextName.getText().toString();
                branch = editTextBranch.getText().toString();
                course1 = editTextcourse1.getText().toString();
                course2 = editTextcourse2.getText().toString();
                course3 = editTextcourse3.getText().toString();
                course4 = editTextcourse4.getText().toString();
                rollNumber = editTextRollNumber.getText().toString();
                if(name.trim().length() == 0  || branch.trim().length() == 0 || course1.trim().length() == 0 || course2.trim().length() == 0 || course3.trim().length() == 0 || course4.trim().length() == 0 || rollNumber.trim().length() == 0){
                    String errormsg = "Please Enter All Details ";
                    Toast.makeText(getApplicationContext(), errormsg , Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG,"Submit Button is clicked");
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"));
                Intent intent = new Intent(v.getContext(), SecondActivity_A1_MT18144.class);
                intent.putExtra("name", name);
                intent.putExtra("branch",branch);
                intent.putExtra("course1", course1);
                intent.putExtra("course2", course2);
                intent.putExtra("course3", course3);
                intent.putExtra("course4", course4);
                intent.putExtra("roll_number", rollNumber);
                startActivity(intent);
            }
        });
        clear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG,"clear button clicked");
                editTextName.setText("");
                editTextBranch.setText("");
                editTextRollNumber.setText("");
                editTextBranch.setText("");
                editTextcourse1.setText("");
                editTextcourse2.setText("");
                editTextcourse3.setText("");
                editTextcourse4.setText("");
            }
        });
    }
    protected void onStart(){
        super.onStart();
        String message = "State of activity " + this.getClass().getSimpleName() + " Changed from created to started";
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        Log.d(TAG,message) ;
    }
    protected void onResume(){
        super.onResume();
        String message = "State of activity " + this.getClass().getSimpleName() + " Changed from created to resumed";
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

    public void click(View V) {
        //Press option+enter to import a class
        Log.d(TAG,"Button is clicked");
    }
}
