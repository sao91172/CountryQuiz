package com.example.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class PastQuizActivity extends AppCompatActivity {
    public static final String DEBUG_TAG = "PastQuizActivity";


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    private QuizData quizData = null;
    private List<Quiz> countryQuizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_quiz_recycler_adapter);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);





        //use a linear layout manager for the recycler view
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create a QuizData instance, since we will need to save a new Quiz to the dn.
        quizData = new QuizData(this);

        //Execute the retrieval of the job leads in an asynchronous way,
        //without blocking the UI thread.
        new CountryDBReaderTask().execute();
    }


    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of job leads, asynchronously.
    private class CountryDBReaderTask extends AsyncTask<Void, Void, List<Quiz>> {

        // This method will run as background process to read from db
        // It returns a list of retrieved quiz objects
        // It will be automatically invoked by Android, when we call the excute method
        // in the onCreate callback (the job leads review activity is started)

        @Override
        protected List<Quiz> doInBackground(Void... params)
        {
            quizData.open();
            countryQuizList = quizData.retrieveAllQuizzes();

            Log.d(DEBUG_TAG, "CountryQuizDBReaderTask: Quizzes retrieved: " + countryQuizList.size());

            return countryQuizList;
        }

        // This method will be automatically called by Android once the db reading
        // background process is finished. It will then create and set an adapter to provide
        // values for the RecyclerView.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class
        @Override
        protected void onPostExecute(List<Quiz> countryQuizList)
        {
            super.onPostExecute(countryQuizList);
            recyclerAdapter = new CountryQuizRecyclerAdapter(countryQuizList);
            recyclerView.setAdapter(recyclerAdapter);
        }

    }
    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "ReviewQuizDataActivity.onResume()" );
        // open the database in onResume
        if( quizData != null )
            quizData.open();
        super.onResume();
        //recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "ReviewQuizDataActivity.onPause()" );
        // close the database in onPause
        if( quizData != null )
            quizData.close();
        super.onPause();
    }

    // These activity callback methods are not needed and are for edational purposes only
    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "PastQuizActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "PastQuizActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "PastQuizActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "PastQuizActivity.onRestart()" );
        super.onRestart();
    }
}