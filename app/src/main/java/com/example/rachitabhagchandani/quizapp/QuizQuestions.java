package com.example.rachitabhagchandani.quizapp;

import android.content.Context;

import java.util.ArrayList;

public class QuizQuestions {
    DatabaseHelper databaseHelper;

    QuizQuestions(Context context){
        databaseHelper = new DatabaseHelper(context);
        context.deleteDatabase("Quiz.db");
        insertQuizData();
    }
    void insertQuizData(){
        databaseHelper.insertData(new Question("The Language that the computer can understand is called Machine Language.","true"));
        databaseHelper.insertData(new Question("Magnetic Tape used random access method.","false"));
        databaseHelper.insertData(new Question("Twitter is an online social networking and blogging service.","true"));
        databaseHelper.insertData(new Question("GNU / Linux is a open source operating system..","true"));
        databaseHelper.insertData(new Question("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","false"));
        databaseHelper.insertData(new Question("Worms and trojan horses are easily detected and eliminated by antivirus software.","true"));
        databaseHelper.insertData(new Question("Whaling / Whaling attack is a kind of phishing attacks that target senior executives and other high profile to access valuable information.","true"));
        databaseHelper.insertData(new Question("Freeware is software that is available for use at no monetary cost.","false"));
        databaseHelper.insertData(new Question("IPv6 Internet Protocol address is represented as eight groups of four Octal digits.","true"));
        databaseHelper.insertData(new Question("The hexadecimal number system contains digits from 1 - 15.","true"));
        databaseHelper.insertData(new Question("Octal number system contains digits from 0 - 7.","false"));
        databaseHelper.insertData(new Question("The Language that the computer can understand is called Machine Language.","true"));
        databaseHelper.insertData(new Question("Magnetic Tape used random access method.","false"));
        databaseHelper.insertData(new Question("Twitter is an online social networking and blogging service.","true"));
        databaseHelper.insertData(new Question("GNU / Linux is a open source operating system..","true"));
        databaseHelper.insertData(new Question("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","false"));
        databaseHelper.insertData(new Question("Worms and trojan horses are easily detected and eliminated by antivirus software.","true"));
        databaseHelper.insertData(new Question("Whaling / Whaling attack is a kind of phishing attacks that target senior executives and other high profile to access valuable information.","true"));
        databaseHelper.insertData(new Question("Freeware is software that is available for use at no monetary cost.","false"));
        databaseHelper.insertData(new Question("IPv6 Internet Protocol address is represented as eight groups of four Octal digits.","true"));
        databaseHelper.insertData(new Question("The hexadecimal number system contains digits from 1 - 15.","true"));
        databaseHelper.insertData(new Question("Octal number system contains digits from 0 - 7.","false"));
        databaseHelper.insertData(new Question("Magnetic Tape used random access method.","false"));
        databaseHelper.insertData(new Question("Twitter is an online social networking and blogging service.","true"));
        databaseHelper.insertData(new Question("GNU / Linux is a open source operating system..","true"));
        databaseHelper.insertData(new Question("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","false"));
        databaseHelper.insertData(new Question("Worms and trojan horses are easily detected and eliminated by antivirus software.","true"));
        databaseHelper.insertData(new Question("Whaling / Whaling attack is a kind of phishing attacks that target senior executives and other high profile to access valuable information.","true"));
        databaseHelper.insertData(new Question("Freeware is software that is available for use at no monetary cost.","false"));
        databaseHelper.insertData(new Question("IPv6 Internet Protocol address is represented as eight groups of four Octal digits.","true"));
        databaseHelper.insertData(new Question("The hexadecimal number system contains digits from 1 - 15.","true"));
        databaseHelper.insertData(new Question("Octal number system contains digits from 0 - 7.","false"));
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questions = databaseHelper.getAllQuestions();
        return questions;
    }

    public void saveResponseTodb(int id, String answer){
        databaseHelper.addDataToDb(id,answer);
    }
}
