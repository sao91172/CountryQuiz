package com.example.countryquiz;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CountryQuizPagerAdapter extends FragmentStateAdapter {

    public CountryQuizPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle ) {
        super(fragmentManager, lifecycle);
    } //constructor

    /*
     * method will be called everytime user swipes left or right
     */
    public Fragment createFragment(int position) {
//        return AndroidVersionFragment.newInstance( position );
        //it will return the quizFragment which displays a fragment with the question
        return QuizFragment.newInstance( position );
    }

    @Override
    /*
     * Android calls this method to know how many pages will be available while swiping
     */
    public int getItemCount() {
        return 7;
    }

}
