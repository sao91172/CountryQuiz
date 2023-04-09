package com.example.countryquiz;


import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class CountryQuizPagerAdapter extends FragmentStateAdapter {

    public static final String DEBUG_TAG = "CountryQuizPagerAdapter";
    ArrayList <Country> quizCountries = new ArrayList<>();

    ArrayList <String> quizCountryStrings = new ArrayList<>();
    ArrayList <String> quizContinentStrings = new ArrayList<>();



    public CountryQuizPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, ArrayList<Country> countries) {
        super(fragmentManager, lifecycle);
        //retrieve Country array and transfer it's data to a Array List
        quizCountries = countries;
        for (int i = 0; i< quizCountries.size(); i++) {
            Log.d(DEBUG_TAG, "here" +  quizCountryStrings.add(quizCountries.get(i).getCountry()));
            quizCountryStrings.add(quizCountries.get(i).getCountry());
            quizContinentStrings.add(quizCountries.get(i).getContinent());

        }
    }




    @Override
    public Fragment createFragment(int position) {

        return StartQuizFragment.newInstance(position, quizCountryStrings.get(position), quizContinentStrings.get(position));
        //return quizFragment.newInstance(position, quizCountryStrings[posi]);
    }

    /**
     * the amount of pages that are available to swipe
      * @return
     */
    @Override
    public int getItemCount() { return 7; }



}
