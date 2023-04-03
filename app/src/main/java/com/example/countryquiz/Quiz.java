package com.example.countryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


/*
Object is used for the saving the quizzes from the csv and using the attributes in the quiz fragment &
references to 6 Question class objects, a date, a current score, and the number of
questions answered so far
 */
public class Quiz {
    public static final String DEBUG_TAG = "Quiz";
    private long   id;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;



    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private double score;
    private int answered;
    private String date_time;



    public Quiz(){
        this.id = -1;
        this.question = null;
        this.answerA = null;
        this.answerB = null;
        this.answerC = null;


        this.score = Double.parseDouble(null);
        this.answered = Integer.parseInt(null);
        this.date_time = null;

    }//default constructor

    public Quiz( String question, String answerA, String answerB, String answerC,
                 double score, int answered, String time){
        this.id = -1;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;

        this.score = score;
        this.answered =answered;
        this.date_time = time;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    //GETTERS
    public String getAnswerA() {
        return answerA;
    }
    public String getAnswerB() {
        return answerB;
    }
    public String getAnswerC() {
        return answerC;
    }
    public double getScore() {
        return score;
    }
    public int getAnswered() {
        return answered;
    }
    public String getDate_time() {
        return date_time;
    }


    //SETTERS
    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }


    public void setQuestion1(String question) {
        this.question1 = question;
    }
    public void setQuestion2(String question) {
        this.question2 = question;
    }
    public void setQuestion3(String question) {
        this.question3 = question;
    }
    public void setQuestion4(String question) {
        this.question4 = question;
    }
    public void setQuestion5(String question) {
        this.question5 = question;
    }
    public void setQuestion6(String question) {
        this.question6 = question;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setAnswered(int answered) {
        this.answered = answered;
    }
    public void setDate_time(String time) {
        this.date_time = time;
    }


/*

    public ContentValues input() {
        ContentValues values = new ContentValues();
        //  values.put(QuizDBHelper.RESULTS_COLUMN_ID, this.id);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION1, this.question1);
        System.out.println(this.question1);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION2, this.question2);
        System.out.println(this.question2);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION3, this.question3);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION4, this.question4);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION5, this.question5);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION6, this.question6);
        values.put(QuizDBHelper.RESULTS_NUM_OF_ANSWERED, this.answered);
        values.put(QuizDBHelper.RESULTS_NUM_OF_CORRECT, this.score);
        values.put(QuizDBHelper.RESULTS_DATETIME, this.date_time);
        return values;
    }
    */

}