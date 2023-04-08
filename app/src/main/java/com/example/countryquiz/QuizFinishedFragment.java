package com.example.countryquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * this fragment is for when the quiz is completed and displays the grade
 */
public class QuizFinishedFragment extends Fragment {

    private static final String DEBUG_TAG = "QuizFinishedFragment";


    private int questionNum;
    private TextView dateTextView;

    private Button returnHomePageButton;

    public static Double points;


    public static QuizFinishedFragment newInstance(int questionNum) {
        QuizFinishedFragment quizFinishedFragment = new QuizFinishedFragment();
        Bundle args = new Bundle();
        return quizFinishedFragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );



        if( getArguments() != null ) {
            questionNum = getArguments().getInt( "questionNum" );
            points = this.getArguments().getDouble("points");
            Log.d(DEBUG_TAG, "" + this.getArguments().getDouble("points"));
            Log.d(DEBUG_TAG, String.valueOf(points));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View resultsView = inflater.inflate(R.layout.fragment_quiz_finished, container, false);


        dateTextView = (TextView) resultsView.findViewById(R.id.date);
        returnHomePageButton = (Button) resultsView.findViewById(R.id.returnHome);
        //dateTextView.findViewById(R.id.date);
        // returnHomePageButton.findViewById(R.id.returnHome);
        /*
         * When review quiz button pressed, takes the user back to the main (splash screen)
         */
        returnHomePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent review = new Intent(getActivity(), SplashActivity.class);
                startActivity(review);
            }
        });

        return resultsView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
        // todo calculate grade and save
        QuizFinishedFragment temp = QuizFinishedFragment.newInstance(6);
        this.points = temp.getPoints();
        Log.d(DEBUG_TAG, "onViewCreated()");
        TextView titleView = view.findViewById( R.id.quiz_score);
        Double gradeCalc = this.points/6.0;
        gradeCalc = gradeCalc * 100;
        double gradeRound = Math.round(gradeCalc * 100.0) / 100.0;
        String grade = String.valueOf(gradeRound);
        grade += "%";
        titleView.setText( grade );
    }

    public void setPoints(double score) {
        this.points = score;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    public double getPoints() {
        return this.points;
    }
}