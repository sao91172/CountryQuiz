package com.example.countryquiz;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class CountryQuizPagerAdapter extends FragmentStateAdapter {

    public static final String DEBUG_TAG = "CountryQuizPagerAdapter";

    private Quiz newQuiz;
    private List<QuizFragment> pageFragments;
    private int quizResults;
    private int fragmentPosition;
    QuizFragment quizFragment;

    Country[] quizCountries = new Country[6];
   String[] quizCountryStrings = new String[6];
    String[] quizContinentStrings = new String[6];


    public CountryQuizPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        setQuiz();
        this.quizResults = 0;
        this.pageFragments = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            pageFragments.add(new QuizFragment());
        }
    }

    public void setQuiz() {
        this.newQuiz = newQuiz;
    }



    public Fragment createFragment(int position) {

        return getItem(position);
        //return quizFragment.newInstance(position, quizCountryStrings[posi]);
    }

    /**
     * the amount of pages that are available to swipe
      * @return
     */
    public int getItemCount() { return 7; }


    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    //@Override
    public Fragment getItem(int position) {
        Log.d(DEBUG_TAG, "Page: " + position);
        /*
            Here, check the position, and if position is past the last question [6]
            create a quiz result fragment
//         */
        Fragment fragment = new Fragment();
        if (position <= 5) {
            fragment = QuizFragment.newInstance(position, quizCountryStrings[position], quizContinentStrings[position]);
        } else if (position == 6) {
            fragment = new QuizFinishedFragment();
        }
        return fragment;
    }
//        if(position == 1){
//            QuizFinishedFragment.points = 0.0;
//        }
//
//        setFragmentPosition(position);
//
//        if(position >= 6) {
//            Fragment pageFragment = new QuizFinishedFragment();
//            checkUserAnswerChoices();
//            Bundle bundle = new Bundle();
//            bundle.putInt("result", this.quizResults);
//            bundle.putSerializable("newQuiz", newQuiz);
//            pageFragment.setArguments(bundle);
//            return pageFragment;
//        }
//        Bundle bundle = new Bundle();
//        bundle.putInt("questionNumber", position+1);
//        bundle.putSerializable("newQuiz", newQuiz);
//        pageFragments.get(position).setArguments(bundle);
//
//        return pageFragments.get(position);

//        StartQuizFragment newFrag;
//        if( position == 6 ){                                        // after swiping past question 6 inflate a new layout to display
//            StartQuizFragment  temp = QuizFragment.newInstance(position);
//            QuizFinishedFragment completeFragment = QuizFinishedFragment.newInstance(position);
//            completeFragment.setPoints(temp.getPoints());
//            return completeFragment;
//        }
//        else if(position == 0){                                     // if first question reset point counter
//            newFrag = QuizFragment.newInstance(position);
//            newFrag.setPoints(0);
//        }
//        else {
//            QuizFragment temp = QuizFragment.newInstance(position);
//            newFrag = QuizFragment.newInstance(position);
//            newFrag.setPoints(temp.getPoints());
//        }
//        return newFrag;
//    }

    /**
     * @return the fragment position
     */
    public int getFragmentPosition()
    {
        return this.fragmentPosition;
    }

    /**
     * sets the fragment position
     * @param thePosition
     */
    public void setFragmentPosition(int thePosition)
    {
        this.fragmentPosition = thePosition;
    }


    /**
     * Return the number of views available.
     */
   //@Override
    public int getCount() {
        return 7;
    }

    private void checkUserAnswerChoices(){
        List<String> quizAnswers = newQuiz.getQuestionAnswers();
        for(int i = 0; i < 6; i++){
            if(pageFragments.get(i).selectedAnswer.equalsIgnoreCase(quizAnswers.get(i)))
                quizResults++;
        }
        Log.d(DEBUG_TAG, "Quiz Results: " + this.quizResults);
    }


}
