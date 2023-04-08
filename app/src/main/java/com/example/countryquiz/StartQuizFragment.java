package com.example.countryquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.io.Serializable;
import java.util.List;

/**
 * starts quiz questions fragment
 */
public class StartQuizFragment extends Fragment {

    private static final String DEBUG_TAG = "start";

    ViewPager2 viewPager2;
    static QuizData quizdata;
    static List<Quiz> quiz;
    static Double points;
    private int thePos;
    CountryQuizPagerAdapter countryQuizPagerAdapter;
    //private QuizCompleteFragment fin;

    /**
     * newInstance for swiping
     * @return
     */
    public static StartQuizFragment newInstance() {
        StartQuizFragment fragment = new StartQuizFragment();
        points = 0.0;
        Bundle args = new Bundle();
        args.putSerializable( "quizData", (Serializable) quiz);
        args.putDouble("points", points);
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        points = 0.0;
        if( getArguments() != null ) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 pager = view.findViewById( R.id.viewpager2 );
        quizdata = new QuizData(getActivity());
        //quiz = (Quiz) args.getSerializable("newQuiz");
        //quiz = quizdata.getQuiz();// quiz(getrandomCountry(), getContinent(),
        points = 0.0;

        CountryQuizPagerAdapter countryQuizPagerAdapter = new CountryQuizPagerAdapter (
                getChildFragmentManager(), getLifecycle());

        viewPager2 = view.findViewById(R.id.viewpager2);

        /**
         * disables left swiping
         */
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            int previousPage = 0;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(position < previousPage){
                    pager.setCurrentItem(previousPage, false);
                } else {
                    previousPage = position;
                }
            }
        });

        pager.setOrientation( ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter(countryQuizPagerAdapter);

    }

    @Override
    public void onPause()
    {
        thePos =  countryQuizPagerAdapter.getFragmentPosition();
        super.onPause();
    }

    @Override
    public void onResume() {
//        Log.d( TAG, "on resume" );
        viewPager2.setCurrentItem(thePos - 1);
        super.onResume();
        points = 0.0;
    }

}