package com.example.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that represents the initial(splash) screen for app.
 */
public class SplashActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "Country Quiz";


    //UI object references
    private TextView pastQuizButton;
    private Button   newQuizButton;
    private Button helpButton;

    private QuizData quizData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d( DEBUG_TAG, "SplashActivity.onCreate(): savedInstanceState: " + savedInstanceState );
        setContentView(R.layout.activity_splash);

        quizData = new QuizData(this);





        // obtain references to UI objects
        newQuizButton = findViewById( R.id.button);
        pastQuizButton = findViewById( R.id.button2);
        helpButton = findViewById(R.id.button8);




        //Button that starts the quiz when pressed
        newQuizButton.setOnClickListener(new View.OnClickListener()  {

            @Override

            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                new RetrieveCountriesForQuizTask().execute();
                getSupportFragmentManager().beginTransaction().add(R.id.container,new StartQuizFragment()).commit();
            }


        });



        //Button that starts the Main Activity when pressed
        helpButton.setOnClickListener(new View.OnClickListener(){

            @Override


            public void onClick(View v) {
                setContentView(R.layout.fragment_help);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout,new HelpFragment()).commit();

                }

        });


        /*
         * When review quiz button pressed, takes the user to the review quiz fragment
         */
        pastQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveAllPastQuizzesTask().execute();
                Intent viewPastQuizzes = new Intent(SplashActivity.this, PastQuizActivity.class);
                startActivity(viewPastQuizzes);
            }
        });
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of the countries for the quiz, asynchronously.
    private class RetrieveCountriesForQuizTask extends AsyncTask<Void, Void, List<Country>> {

        // This method will run as a background process to read from db.
        @Override
        protected List<Country> doInBackground(Void... params ) {
            List<Country> countriesForQuiz = quizData.retrieveCountriesForQuiz();
            return countriesForQuiz;
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute( List<Country> countriesForQuiz ) {
            super.onPostExecute( countriesForQuiz);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String date = simpleDateFormat.format(new Date());
            List<String> questions = new ArrayList<>();
            List<String> questionAnswers = new ArrayList<>();
            for(Country country: countriesForQuiz){
                questions.add(country.getCountry());
                questionAnswers.add(country.getContinent());
            }
            int result = 0;
            Quiz newQuiz = new Quiz(date, questions, questionAnswers, result);

            QuizFragment newQuizFragment = new QuizFragment();
            //save the new quiz data in the new quiz fragment's Bundle data
            Bundle args = new Bundle();
            args.putSerializable("newQuiz", newQuiz);
            newQuizFragment.setArguments(args);

            //Code below will determine when newQuiz will be stored in database
            Log.d( DEBUG_TAG, "New quiz has been created");

            Intent countryQ = new Intent(SplashActivity.this, StartQuizFragment.class);
            countryQ.putExtras(args);
            startActivity(countryQ);
        }
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of the countries for the quiz, asynchronously.
    private class RetrieveAllPastQuizzesTask extends AsyncTask<Void, Void, ArrayList<Quiz>> {

        // This method will run as a background process to read from db.
        @Override
        protected ArrayList<Quiz> doInBackground( Void... params ) {
            ArrayList<Quiz> pastQuizzes = quizData.retrieveAllQuizzes();
            return pastQuizzes;
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute( ArrayList<Quiz> pastQuizzes ) {
            super.onPostExecute( pastQuizzes);

            if(pastQuizzes.size() > 0) {
                //Code below will determine how pastQuizzes will get sent to fragment
                QuizFragment newQuizFragment = new QuizFragment();
                //save all the past quiz data in the new quiz fragment's Bundle data
                Bundle args = new Bundle();
                args.putSerializable("pastQuizzes", pastQuizzes);
                newQuizFragment.setArguments(args);
            }

            Log.d( DEBUG_TAG, "Retrieved all past quizzes");
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState) {

        super.onRestoreInstanceState (savedInstanceState);
    }


    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "SplashActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "SplashActivity.onResume()" );
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "SplashActivity.onPause()" );
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "SplashActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "SplashActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "SplashActivity.onRestart()" );
        super.onRestart();
    }
}