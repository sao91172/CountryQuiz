package com.example.countryquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * help fragment that provides info
 */
public class HelpFragment extends Fragment {

    public HelpFragment() {
        // Required empty public constructor
    }

    public static HelpFragment newInstance() {
        HelpFragment fragment = new HelpFragment();
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        // Up Button

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState ) {
//        // Inflate the layout for this fragment
//        //return inflater.inflate( R.layout.fragment_help, container, false );
//    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated(view,savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}