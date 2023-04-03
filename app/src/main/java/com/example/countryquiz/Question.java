package com.example.countryquiz;

import android.content.ContentValues;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
A Question class should have the country name (randomly selected from the list/array of countries), its
continent (the correct answer) and two randomly selected other continents (incorrect answers).
 */
public class Question extends Fragment {

    private static final String DEBUG_TAG = "Questions";
    public static final int questions = 6;
    private int questionNum;

    //QuizData quizdata = null;

    /*
    List<Quiz> quiz = null;
    private static Double points = 0.0;
    public static QuizCompleteFragment completeFragment;
    ContentValues values;
    */


    TextView titleView;
    TextView question;
    RadioGroup radioGroup;
    RadioButton btn1;
    RadioButton btn2;
    RadioButton btn3;


    public final static String[] listOfContinents = new String[]{
            "Asia", "North America", "Europe",
            "Africa", "Oceania", "South America",
            "Australia"
    };

    public static ArrayList<String> correctAnswers = new ArrayList<String>(2);
    public static ArrayList<String> continents = new ArrayList<String>();

    //created to have variations within the answer selections
    //Ex: Africa shouldn't always be placed in the first radio button
    public static ArrayList<Integer> orderOfAnswers = new ArrayList<Integer>(3);

    Quiz quiz = new Quiz();

    //makes the answer selection
    public static void makeAnswers() {
        continents.add("Asia");
        continents.add("Africa");
        continents.add("Antartica");
        continents.add("Europe");
        continents.add("North America");
        continents.add("South America");
        continents.add("Oceania");

    }











}
