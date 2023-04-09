package com.example.countryquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * starts quiz questions fragment
 */
public class StartQuizFragment extends Fragment {



    private String country; //Country for current question
    private String continent; //Continent for current country
    private int position; //Current fragment slide number
    int correctIndex = 0; //Index for the correct answer

    TextView textView;//where the question # shows
    TextView questionView; //where the question shows
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    ArrayList<String> continents = new ArrayList<String>();

    private static final String DEBUG_TAG = "Countries";

    /**
     * newInstance for swiping
     * @return
     */
    public static StartQuizFragment newInstance(int questionNumber, String questionCountry, String questionContinent) {
        StartQuizFragment startQuizFragment = new StartQuizFragment();
       // points = 0.0;
        Bundle args = new Bundle();
        args.putString( "country", questionCountry );
        args.putString( "continent" , questionContinent );
        args.putInt("position", questionNumber );
        Log.d(DEBUG_TAG, "StartQuizFragment.newInstance(): Country: " + questionCountry);
        Log.d(DEBUG_TAG, "StartQuizFragment.newInstance(): Continent: " + questionContinent);

        startQuizFragment .setArguments( args );
        return startQuizFragment ;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        if( getArguments() != null ) {
            country = getArguments().getString("country");
            continent = getArguments().getString("continent");
            position = getArguments().getInt("position");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {

        questionView = view.findViewById(R.id.promptcountry); // initialize question text view
        radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.check(R.id.radioButton);
        radioButton1 =  view.findViewById(R.id.radioButton); // radio button 1
        radioButton2 =  view.findViewById(R.id.radioButton2); // radio button 2
        radioButton3 =  view.findViewById(R.id.radioButton3); // radio button 3

        //initialize choice array and randomize choices
        String[] choix = setRadioButtonChoice();

        RadioButton[] buttons = {radioButton1, radioButton2, radioButton3};

        //Set the questions and answers
        questionView.setText("What continent is " + country + " located in?");
        radioButton1.setText(choix[0]);
        radioButton2.setText(choix[1]);
        radioButton3.setText(choix[2]);

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctIndex == 0) {
                   QuizFragment.setGrade(position,1);
                } else {
                    QuizFragment.setGrade(position,0);
                }
            }
        });


        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctIndex == 1) {
                    QuizFragment.setGrade(position,1);
                } else {
                    QuizFragment.setGrade(position,0);
                }
            }
        });


        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctIndex == 2) {
                    QuizFragment.setGrade(position,1);
                } else {
                    QuizFragment.setGrade(position,0);
                }
            }
        });


    }


    public String[] setRadioButtonChoice() {
        String[] option = new String[3];

        continents.add("North America");
        continents.add("South America");
        continents.add("Europe");
        continents.add("Asia");
        continents.add("Oceania");
        continents.add("Africa");

        Random rand = new Random();

        //Initialize strings to hold random selections
        String newContinent = "", usedContinent = "";

        //Obtain a random position for the correct answer
        correctIndex = rand.nextInt(3);
        //Log.d(TAG, "QuestionFragment.setOptions(): Correct answer index: " + correctIndex);

        int i = 0;

        while (i < 3) {

            //If i = correct answer slot, insert the correct answer passed from the parameter
            if (i == correctIndex) {
                option[i] = continent;
                i++;
            } else {
                //Obtain a random continent from the list of continents
                newContinent = continents.get(rand.nextInt(6));
                //Log.d(TAG, "QuestionFragment.setOptions(): Random continent in position" + i + ": " + newContinent);

                //  Check if the randomly chosen continent is neither the correct answer
                //  nor a random continent already chosen
                if (!(newContinent.equals(continent)) && !(newContinent.equals(usedContinent))) {
                    //place the new continent into the options array
                    option[i] = newContinent;
                    //Mark the new continent as used now.
                    usedContinent = newContinent;
                    i++;
                } //if
            } //else

        } //while


        return option;
    }





}