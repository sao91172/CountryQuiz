package com.example.countryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * Class that represents the initial(splash) screen for app.
 */
public class SplashActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "Country Quiz";


    //UI object references
    private TextView pastQuizButton;
    private Button   newQuizButton;
    private Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d( DEBUG_TAG, "SplashActivity.onCreate(): savedInstanceState: " + savedInstanceState );
        setContentView(R.layout.activity_splash);




        // obtain references to UI objects
        newQuizButton = findViewById( R.id.button);
        pastQuizButton = findViewById( R.id.button2);
        helpButton = findViewById(R.id.button8);




        //Button that starts the Main Activity when pressed
        newQuizButton.setOnClickListener(event ->  {

            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        });


        //Button that starts the Main Activity when pressed
        helpButton.setOnClickListener(event ->  {

            startActivity(new Intent(SplashActivity.this, Help.class));
            finish();
        });
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