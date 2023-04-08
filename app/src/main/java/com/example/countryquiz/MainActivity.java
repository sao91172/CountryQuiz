package com.example.countryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.opencsv.CSVReader;

public class MainActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "MainActivity";
    private QuizData quizData = null;

    private static int SPLASH_TIME = 3500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a QuizData instance, since we will need to save the countries to the database
        quizData = new QuizData(this);
        quizData.open();
//        Context context = getApplicationContext();
        Resources res = getResources();
//        Context context;

        //Execute the initializing of the database with the countries csv
        new InitializeCountryQuizDatabaseTask().execute(res);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(main);
                finish();
            }
        }, SPLASH_TIME);


    }


    // This is an AsyncTask class (it extends AsyncTask) to perform DB writing of all the countries, asynchronously.
//    private class InitializeCountryQuizDatabaseTask extends AsyncTask<Resources, Void, Resources> {

        // This method will run as a background process to write into db.
//        @Override
//        protected Resources doInBackground( Resources... res ) {
//            quizData.storeCountries( res[0] );
//            return res[0];
//        }
        private class InitializeCountryQuizDatabaseTask extends AsyncTask<Resources, Void, Resources> {
       // @Override
        protected Resources doInBackground(Resources... res ) {
            quizData.storeCountries( res[0] );

            return res[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute(Resources res) {
            super.onPostExecute( res );

            Log.d( DEBUG_TAG, "Database has been created");
        }
    }

    // These activity callback methods are not needed and are for educational purposes only
    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "MainActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "MainActivity.onResume()" );
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "MainActivity.onPause()" );
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "MainActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "MainActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "MainActivity.onRestart()" );
        super.onRestart();
    }
}