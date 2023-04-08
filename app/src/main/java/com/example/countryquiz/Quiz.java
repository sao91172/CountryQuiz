package com.example.countryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;


/*
Object is used for the saving the quizzes from the csv and using the attributes in the quiz fragment &
references to 6 Question class objects, a date, a current score, and the number of
questions answered so far
 */
public class Quiz implements Serializable {
    public static final String DEBUG_TAG = "Quiz";
    private long id;
    private String date;
    private List<String> questions;
    private List<String> questionAnswers;
    private int result;

    public Quiz() {
        this.id = -1;
        this.date = null;
        this.questions = new ArrayList<>();
        this.questionAnswers = new ArrayList<>();
        this.result = 0;
    }

    public Quiz(List<String> questions) {
        this.id = -1;
        this.date = null;
        this.questions = new ArrayList<>();
        this.questionAnswers = new ArrayList<>();
        for (String question: questions) {
            this.questions.add(question);
        }
        this.result = 0;
    }

    public Quiz(String date, List<String> questions, int result) {
        this.id = -1;
        this.date = date;
        this.questions = new ArrayList<>();
        this.questionAnswers = new ArrayList<>();
        for (String question: questions) {
            this.questions.add(question);
        }
        this.result = result;
    }

    public Quiz(String date, List<String> questions, List<String> questionAnswers, int result) {
        this.id = -1;
        this.date = date;
        this.questions = new ArrayList<>();
        this.questionAnswers = new ArrayList<>();
        for (String question: questions) {
            this.questions.add(question);
        }
        for (String answer: questionAnswers) {
            this.questionAnswers.add(answer);
        }
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        if (this.questions == null)
            this.questions = new ArrayList<>();

        for (String question: questions) {
            this.questions.add(question);
        }
    }

    public List<String> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<String> questionAnswers) {
        if (this.questionAnswers == null)
            this.questionAnswers = new ArrayList<>();

        for (String answer: questionAnswers) {
            this.questions.add(answer);
        }
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String toString() {
        return id + ": " + date + " " + result;
    }

}