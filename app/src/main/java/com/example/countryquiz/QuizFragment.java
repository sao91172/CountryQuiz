package com.example.countryquiz;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 A Question class should have the country name (randomly selected from the list/array of countries), its
 continent (the correct answer) and two randomly selected other continents (incorrect answers).
 */
public class QuizFragment extends Fragment {

    private static final String DEBUG_TAG = "QuizFragment";
    public static final int questions = 6;
    private int questionNum;
    private static Double points = 0.0;

    QuizData quizdata = null;

    Quiz newQuiz = new Quiz();




    List<Quiz> quiz = null;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
   /*
    private static Double points = 0.0;

    public static QuizCompleteFragment completeFragment;
    ContentValues values;
    */


    TextView textView;//where the question # shows
    TextView questionView; //where the question shows
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    private String countryName;

    protected String selectedAnswer;
    private boolean correctAnswerSelected;

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
//        args.putInt("question",questionNum);
        args.putString("country", questionCountry);
        args.putString("continent", questionContinent);
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        quizFragment.setArguments(args);
        return quizFragment;

    }



    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
//        setContentView( R.layout.activity_question );

        //The following chunk connects to the database and stores some variables to be used in the questions
        CountriesDBHelper countriesData = CountriesDBHelper.getInstance(this.getContext());
        SQLiteDatabase db = countriesData.getReadableDatabase();

        QuizData quizData = new QuizData(this.getContext());
//        quizData.retrieveCountriesForQuiz();

        List<Country> allCountries = quizData.retrieveCountriesForQuiz();
        quizCountries = new ArrayList<>();
        //String[] allContinents = quizData.retrieveCountriesForQuiz();
        Random random = new Random();
        int[] selectedIndices = new int[6];
        int numSelected = 0;

        // We want to use a while loop instead of a for loop so that if the index is already in the
        // Array it tries again instead of skipping that index
        while (numSelected < 6) {
            int randomIndex = random.nextInt(allCountries.size());
            if (!quizCountries.contains(randomIndex)) {
                quizCountries.add(allCountries.get(randomIndex));
//                quizCountries[numSelected] = allCountries[randomIndex];
                selectedIndices[numSelected] = randomIndex;
                numSelected++;
            } // if
        } // while

//        quizdata = new QuizData(this);
//        quizdata.open();
//        new QuizDBReaderTask().execute();
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {

        // Inflate the layout for this fragment
        View quizView = inflater.inflate(R.layout.fragment_quiz, container, false);



        Bundle bundle = getArguments();
        questionIndex = bundle.getInt("questionNumber");

        //get newQuiz questions retrieved from database
       // Quiz newQuiz = (Quiz) bundle.getSerializable("newQuiz");
        List<String> quizQuestions = new ArrayList<>();
        for( int i = 0; i < quizCountries.size(); i++) {
            Country country = quizCountries.get(i);
            String name = country.getCountry();
            quizQuestions.add(name);
        }
         Quiz newQuiz = new Quiz(quizQuestions);
        //bundle.getSerializable(newInstance(newQuiz,questionNum));
        //this.newQuiz = (Quiz) getIntent().getSerializable("newQuiz");;
        List<String> newQuizQuestions = newQuiz.getQuestions();
        List<String> newQuizQuestionsAnswers = newQuiz.getQuestionAnswers();
//        List<String> newQuizQuestions = QuizData.retrieveCountriesForQuiz();


        Random random = new Random();


        //country_Name = "Angola"; //Using Angola for testing purpose, will use random generator later
        countryName = newQuizQuestions.get(questionIndex);
//        Country country = quizCountries.get(0);
//        countryName = country.getCountry();
        questionView = (TextView) quizView.findViewById(R.id.promptcountry); // initialize question text view
        radioGroup = (RadioGroup) quizView.findViewById(R.id.radioGroup);
        radioGroup.check(R.id.radioButton);
        radioButton1 = (RadioButton) quizView.findViewById(R.id.radioButton); // radio button 1
        radioButton2 = (RadioButton) quizView.findViewById(R.id.radioButton2); // radio button 2
        radioButton3= (RadioButton) quizView.findViewById(R.id.radioButton3); // radio button 3

        //RadioButton[] buttons = {radioButton1, radioButton2, radioButton3};

        continents.add("North America");
        continents.add("South America");
        continents.add("Europe");
        continents.add("Africa");
        continents.add("Asia");
        continents.add("Oceania");

        int correctAnswerIndex = random.nextInt(3) + 1;
        String correctAnswer = newQuizQuestionsAnswers.get(questionIndex);
//        String correctAnswer = country.getContinent();
        String continent1 = "";
        String continent2 = "";
        String continent3 = "";
        int nextAvailableChoiceIndex = 1;
        if(correctAnswerIndex == 1){
            radioButton1.setText(correctAnswer);
            continent1 = correctAnswer;
            nextAvailableChoiceIndex = 2;
        } else if(correctAnswerIndex == 2){
            radioButton2.setText(correctAnswer);
            continent2 = correctAnswer;
        }
        else if(correctAnswerIndex == 3){
            radioButton3.setText(correctAnswer);
            continent3 = correctAnswer;
        }

        int numChoicesDetermined = 1;
        while (numChoicesDetermined != 3){
            index = random.nextInt(continents.size());
            String randomContinent = continents.get(index);

            if(!randomContinent.equalsIgnoreCase(continent1) && !randomContinent.equalsIgnoreCase(continent2) && !randomContinent.equalsIgnoreCase(continent3)){
                numChoicesDetermined++;

                if(nextAvailableChoiceIndex == 1) {
                    radioButton1.setText(randomContinent);
                    continent1 = randomContinent;
                }
                else if(nextAvailableChoiceIndex == 2){
                    radioButton2.setText(randomContinent);
                    continent2 = randomContinent;
                }
                else if(nextAvailableChoiceIndex == 3){
                    radioButton3.setText(randomContinent);
                    continent3 = randomContinent;
                }

                nextAvailableChoiceIndex++;
                if(nextAvailableChoiceIndex == correctAnswerIndex)
                    nextAvailableChoiceIndex++;
            }
        }

        questionView.setText(questionIndex + ". What continent is " + countryName + " located in?");
        selectedAnswer = continent1;
        if(selectedAnswer.equalsIgnoreCase(correctAnswer)){
            QuizFinishedFragment.points++;
            correctAnswerSelected = true;
        } else {
            correctAnswerSelected = false;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton:
                        selectedAnswer = radioButton1.getText().toString();
                        break;
                    case R.id.radioButton2:
                        selectedAnswer = radioButton2.getText().toString();
                        break;
                    case R.id.radioButton3:
                        selectedAnswer = radioButton3.getText().toString();
                        break;
                }
                if(selectedAnswer.equalsIgnoreCase(correctAnswer) && !correctAnswerSelected){
                    QuizFinishedFragment.points++;
                    correctAnswerSelected = true;
                } else if(!selectedAnswer.equalsIgnoreCase(correctAnswer) && correctAnswerSelected){
                    QuizFinishedFragment.points--;
                    correctAnswerSelected = false;
                }

                Log.d(DEBUG_TAG, "Choice Selected: " + selectedAnswer);
            }
        });
        return quizView;
    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
//        super.onViewCreated( view, savedInstanceState );
//       Log.d(DEBUG_TAG, String.valueOf(points));
//             Log.d(DEBUG_TAG, "onViewCreated");
//
//
//    }//onViewCreated



//    @Override
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
//        super.onViewCreated( view, savedInstanceState );
//        Log.d(DEBUG_TAG, String.valueOf(points));
//        //     Log.d(TAG, "onViewCreated");
//
//        quizdata = new QuizData(getActivity());
//        quizdata.open();
//        quizdata.close();
//        textView = view.findViewById( R.id.questionNumber );
//        questionView = view.findViewById(R.id.promptcountry);
//        radioGroup = view.findViewById(R.id.radioGroup);
//
//
//        String textString = getString(R.string.questionNumber) + " " + (questionNum+1);
//        textView.setText( textString );
//        questionView.setText("Which continent is  " + quiz.get(questionNum).getQuestion() + " located in ?");
//
//
//        List<Integer> buttons = new ArrayList<>();
//        buttons.add(R.id.radioButton3);
//        buttons.add(R.id.radioButton2);
//        buttons.add(R.id.radioButton);
//        Collections.shuffle(buttons);
//
//        // shuffle button ids and set them to buttons
//        radioButton1 = view.findViewById(buttons.get(2));
//        radioButton2 = view.findViewById(buttons.get(0));
//        radioButton3 = view.findViewById(buttons.get(1));
//
//
//
//        radioButton1.setText(quiz.get(questionNum).getAnswerA());
//        radioButton2.setText(quiz.get(questionNum).getAnswerB());
//        radioButton3.setText(quiz.get(questionNum).getAnswerC());
//
//        /**
//         * on click for radio group
//         */
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            int checked = 0;
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Log.d("chk", "id" + checkedId);
//
//
//                if (checkedId == radioButton1.getId() && quiz.get(questionNum).getAnswerA().equals(radioButton1.getText())) {
//                    //R.id.a = RadioButton ID in layout
//                    if(checked < 1) {
//                        points = points + 1.0;
//                    }
//                    checked++;
//                }
//                else if (checkedId == radioButton2.getId() && quiz.get(questionNum).getAnswerA().equals(radioButton2.getText())) {
//                    if(checked < 1) {
//                        points = points + 1.0;
//                    }
//                    checked++;
//                }
//                else if (checkedId == radioButton3.getId() && quiz.get(questionNum).getAnswerA().equals(radioButton3.getText())) {
//                    if(checked < 1) {
//                        points = points + 1.0;
//                    }
//                    checked++;
//                }
//                else{
//                    if(checked > 1){
//                        points --;
//                    }
//                }
//
//            }
//        });
//    }
//
//    /**
//     * used for page counter
//     * @return
//     */
//    public static int getNumberOfQuestions() {
//        return questions;
//    }

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
        System.out.println(points);
        Log.d( DEBUG_TAG, "onPause()" );
        super.onPause();
    }
    public double getPoints() {
        return this.points;
    }
    public void setPoints(double score) {
        this.points = score;
    }

}