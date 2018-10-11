package com.example.rachitabhagchandani.quizapp;

public class Question {
    int id;
    String question;
    String answer;
    String savedAnswer;

    public Question(){

    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSavedAnswer() {
        return savedAnswer;
    }

    public void setSavedAnswer(String savedAnswer) {
        this.savedAnswer = savedAnswer;
    }
}
