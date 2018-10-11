package com.example.rachitabhagchandani.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int VERSION_NUMBER = 10;
    public static final String DATABASE_NAME = "Quiz.db";
    public static final String TABLE_NAME = "quiz_details";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "question";
    public static final String COL_3 = "answer";
    public static final String COL_4 = "saved_answer";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUIZ_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COL_2 + " TEXT,"
                + COL_3 + " TEXT," + COL_4 + " TEXT)";
        db.execSQL(CREATE_QUIZ_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void insertData(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,question.getQuestion());
        values.put(COL_3, question.getAnswer());
        values.put(COL_4, "NULL");
        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d("log", "entered data");
    }

    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questions = new ArrayList<Question>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(cursor.getInt(0));
                question.setQuestion(cursor.getString(1));
                question.setAnswer(cursor.getString(2));
                question.setSavedAnswer(cursor.getString(3));
                questions.add(question);
            }while (cursor.moveToNext());
        }
        return questions;
    }

    public int addDataToDb(int id, String answer){
        Log.d("answer_value", answer);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_4, answer);
        return db.update(TABLE_NAME, values, COL_1 + " = " + id, null);
    }
}