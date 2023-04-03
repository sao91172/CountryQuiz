package com.example.countryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {
    private TextView helpTextView;

//    private int conversionType = MainActivity.;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // obtain references to UI objects
        helpTextView = findViewById(R.id.textView);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


//        helpTextView.setText("Helper instructions go here");



    }
}