package com.example.countryquiz;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

/**
 A Question class should have the country name (randomly selected from the list/array of countries), its
 continent (the correct answer) and two randomly selected other continents (incorrect answers).
 */
public class QuizFragment extends Fragment {
    static int[] grades = {0,0,0,0,0,0};

    public static int[] getGrades() {
        return grades;
    }

    public static void setGrade( int position, int grade ) {
        grades[position] = grade;
        return;
    }

    private static final String DEBUG_TAG = "QuizFragment";
    private static Double points = 0.0;

    QuizData quizdata = null;

    Quiz newQuiz = new Quiz();


    private ArrayList<String> continents = new ArrayList<>();
    private ArrayList<Country> quizCountries = new ArrayList<>();
    boolean isTheSame = true;

    int index;
    private int questionIndex;



    /**
     * Default constructor
     */
    public static QuizFragment newInstance(int questionNum, String questionCountry, String questionContinent) {
        QuizFragment quizFragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt("question",questionNum);
        args.putString("country", questionCountry);
        args.putString("continent", questionContinent);
        quizFragment.setArguments(args);
        return quizFragment;

    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        //The following chunk connects to the database and stores some variables to be used in the questions
        QuizData quizData = new QuizData(this.getContext());
        CountriesDBHelper countriesData = CountriesDBHelper.getInstance(this.getContext());
        SQLiteDatabase db = countriesData.getReadableDatabase();



        List<Country> allCountries = quizData.retrieveCountriesForQuiz();
        quizCountries = new ArrayList<>();

        Random random = new Random();
        int[] selectedIndices = new int[6];
        int numSelected = 0;

        // We want to use a while loop instead of a for loop so that if the index is already in the
        // Array it tries again instead of skipping that index
        while (numSelected < 6) {
            int randomIndex = random.nextInt(allCountries.size());
            if (!quizCountries.contains(randomIndex)) {
                quizCountries.add(allCountries.get(randomIndex));
                selectedIndices[numSelected] = randomIndex;
                numSelected++;
            } // if
        } // while


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_quiz,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 pager = view.findViewById( R.id.viewpager2 );
        CountryQuizPagerAdapter countryQuizPagerAdapter = new CountryQuizPagerAdapter (
        getChildFragmentManager(), getLifecycle(),quizCountries);

        pager.setOrientation( ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter(countryQuizPagerAdapter);


    }

    @Override
    public void onResume() {
        Log.d( DEBUG_TAG, "on resume" );
        super.onResume();
        quizdata.open();
        // open the database in onResume
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( R.string.app_name );
    }

    // We need to save job leads into a file as the activity stops being a foreground activity
    @Override
    public void onPause() {
//        todo save quiz before closing
        quizdata.storeQuiz(newQuiz);
        quizdata.close();
        Log.d( DEBUG_TAG, "onPause()" );
        super.onPause();
    }

}