package com.example.countryquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CountriesDBHelper extends SQLiteOpenHelper {
    //Debug tag
    private static final String DEBUG_TAG = "CountriesDBHelper";

    //The name of our database file
    private static final String DB_NAME = "countries.db";
    //Not sure what this is
    private static final int DB_VERSION = 1;

    //the country_continent table
    public static final String TABLE_COUNTRY_CONTINENT = "country_continent";
    //The first column of the country_continent table which is country_name
    public static final String COUNTRY_CONTINENT_COLUMN_COUNTRY_NAME = "_country_name";
    //The second column of the country_continent table which is continent
    public static final String COUNTRY_CONTINENT_COLUMN_CONTINENT = "_continent";
    //The third column of the country_continent table which is Field3 (it is the id) (primary key)
    public static final String COUNTRY_CONTINENT_COLUMN_FIELD3 = "_Field3";


    //the quizzes table
    public static final String TABLE_QUIZZES = "quizzes";
    //The first column of the quizzes table which is quiz_id (primary key)
    public static final String QUIZZES_COLUMN_QUIZ_ID = "_quiz_id";
    //The second column of the quizzes table which is quiz date
    public static final String QUIZZES_COLUMN_QUIZ_DATE = "_quiz_date";
    //The third column of the quizzes table which is quiz result
    public static final String QUIZZES_COLUMN_QUIZ_RESULT = "_quiz_result";

    // This is a reference to the only instance for the helper.
    private static CountriesDBHelper helperInstance;

    // Note that the constructor is private!
    // So, it can be called only from
    // this class, in the getInstance method.
    private CountriesDBHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    // Access method to the single instance of the class.
    // It is synchronized, so that only one thread can executes this method, at a time.
    public static synchronized CountriesDBHelper getInstance( Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new CountriesDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    // We must override onCreate method, which will be used to create the database if
    // it does not exist yet.
    @Override
    public void onCreate( SQLiteDatabase db ) {
        //db.execSQL(CREATE_COUNRY_CONTINENT); THIS IS CREATING THE TABLE BUT OURS IS ALREADY CREATED
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRY_CONTINENT + " created" );
    }

    // We should override onUpgrade method, which will be used to upgrade the database if
    // its version (DB_VERSION) has changed.  This will be done automatically by Android
    // if the version will be bumped up, as we modify the database schema.
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_COUNTRY_CONTINENT );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRY_CONTINENT + " upgraded" );
    }


}
