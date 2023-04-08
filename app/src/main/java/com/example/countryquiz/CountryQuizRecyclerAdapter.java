package com.example.countryquiz;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CountryQuizRecyclerAdapter extends RecyclerView.Adapter<CountryQuizRecyclerAdapter.QuizHolder>{



    public static final String DEBUG_TAG = "QuizLeadRecyclerAdapter";

    private List<Quiz> countryQuizList;

    public CountryQuizRecyclerAdapter ( List<Quiz> countryQuizList)
    {
        this.countryQuizList =  countryQuizList;
    }

    // The adapter must have a view holder class to "hold" one item to show.
    class QuizHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView quizScore;

        public QuizHolder (View itemView)
        {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.dateView);
            quizScore = (TextView) itemView.findViewById(R.id.resultsView);
        }
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pastquiz_layout, parent, false);
        return new QuizHolder(view);
    }

    /**
     * This method fills in the values of a holder to show a quiz
     * The position paramter indicates the position on the list of quiz list
     */

    @Override
    public void onBindViewHolder(QuizHolder holder, int position)
    {
        Quiz quiz = countryQuizList.get(position);

        Log.d(DEBUG_TAG, "onBindViewHolder: " + quiz);


        holder.date.setText("Date: " + quiz.getDate() + " ");
        holder.quizScore.setText("Your Score: " + quiz.getResult() + "/6");


    }

    @Override
    public int getItemCount()
    {
        return countryQuizList.size();
    }
}