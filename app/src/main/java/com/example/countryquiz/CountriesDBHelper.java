package com.example.countryquiz;

//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//public class CountriesDBHelper extends SQLiteOpenHelper {
//    //Debug tag
//    private static final String DEBUG_TAG = "CountriesDBHelper";
//
//    //The name of our database file
//    private static final String DB_NAME = "countries.db";
//    //Not sure what this is
//    private static final int DB_VERSION = 1;
//
//    //the country_continent table
//    public static final String TABLE_COUNTRY_CONTINENT = "country_continent";
//    //The first column of the country_continent table which is country_name
//    public static final String COUNTRY_CONTINENT_COLUMN_COUNTRY_NAME = "_country_name";
//    //The second column of the country_continent table which is continent
//    public static final String COUNTRY_CONTINENT_COLUMN_CONTINENT = "_continent";
//    //The third column of the country_continent table which is Field3 (it is the id) (primary key)
//    public static final String COUNTRY_CONTINENT_COLUMN_FIELD3 = "_Field3";
//
//
//    //the quizzes table
//    public static final String TABLE_QUIZZES = "quizzes";
//    //The first column of the quizzes table which is quiz_id (primary key)
//    public static final String QUIZZES_COLUMN_QUIZ_ID = "_quiz_id";
//    //The second column of the quizzes table which is quiz date
//    public static final String QUIZZES_COLUMN_QUIZ_DATE = "_quiz_date";
//    //The third column of the quizzes table which is quiz result
//    public static final String QUIZZES_COLUMN_QUIZ_RESULT = "_quiz_result";
//
//    // This is a reference to the only instance for the helper.
//    private static CountriesDBHelper helperInstance;
//
//    // Note that the constructor is private!
//    // So, it can be called only from
//    // this class, in the getInstance method.
//    private CountriesDBHelper( Context context ) {
//        super( context, DB_NAME, null, DB_VERSION );
//    }
//
//    // Access method to the single instance of the class.
//    // It is synchronized, so that only one thread can executes this method, at a time.
//    public static synchronized CountriesDBHelper getInstance( Context context ) {
//        // check if the instance already exists and if not, create the instance
//        if( helperInstance == null ) {
//            helperInstance = new CountriesDBHelper( context.getApplicationContext() );
//        }
//        return helperInstance;
//    }
//
//    // We must override onCreate method, which will be used to create the database if
//    // it does not exist yet.
//    @Override
//    public void onCreate( SQLiteDatabase db ) {
//        //db.execSQL(CREATE_COUNRY_CONTINENT); THIS IS CREATING THE TABLE BUT OURS IS ALREADY CREATED
//        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRY_CONTINENT + " created" );
//    }
//
//    // We should override onUpgrade method, which will be used to upgrade the database if
//    // its version (DB_VERSION) has changed.  This will be done automatically by Android
//    // if the version will be bumped up, as we modify the database schema.
//    @Override
//    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
//        db.execSQL( "drop table if exists " + TABLE_COUNTRY_CONTINENT );
//        onCreate( db );
//        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRY_CONTINENT + " upgraded" );
//    }
//
//
//}


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CountriesDBHelper extends SQLiteOpenHelper {
    private static String DEBUG_TAG = "CountriesDBHelper"; // Tag just for the LogCat window
    //destination path (location) of our database on device
    private static String DB_PATH = "";
    //private static String DB_NAME ="(students).sqlite";// Database name
    private static String DB_NAME ="countries.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public CountriesDBHelper(Context context) {
        super(context, DB_NAME, null, 1);// 1? its Database Version
//        DB_PATH = "/data/data/" + context.getDatabasePath(DB_NAME) + "/databases/";
        DB_PATH = context.getDatabasePath(DB_NAME).getAbsolutePath();
        Log.i(DEBUG_TAG, DB_PATH);
        //context gives us access to resources
        this.myContext=context;
        boolean dbexist = checkDataBase();
        if (dbexist) {
            openDataBase();
        } else {
            Log.i(DEBUG_TAG, "CountriesDBHelper: DATABASE DOES NOT EXIST");
            createDataBase();
        }
    } //CountriesDBHelper

    public void createDataBase()
    {
        //If database not exists copy it from the assets
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
                Log.e(DEBUG_TAG, "createDataBase method: database created");
            }
            catch (IOException mIOException) {
                Log.i(DEBUG_TAG, "createDataBase method: ERROR COPYING DATABASE"+mIOException+"");
            }
        }
    }
    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File dbFile = new File(DB_PATH + DB_NAME);
            checkDB = dbFile.exists();
        } catch(SQLiteException e) {
            Log.i(DEBUG_TAG, "checkDatabase method: DATABASE DOES NOT EXIST");
        }
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return checkDB;
    } //checkDataBase

    //Copy the database from assets
    private void copyDataBase() throws IOException {
        try {
            //opens the local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);
            //Path to the just created empty DB
        String outFileName = DB_PATH + DB_NAME;
        // open the empty db as the output stream
        OutputStream mOutput = new FileOutputStream(outFileName);
        //transfer byte from input file to output file
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = myInput.read(mBuffer))>0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        myInput.close();
    }
    catch (IOException mIOException) {
            Log.i(DEBUG_TAG,"copyDataBase method"+ mIOException+"");
        }
    } //copyDataBase

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return myDataBase != null;
    } //openDataBase

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    } //close

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }


    //Make a method to update the Quizzes table should have
    //
    public void addQuizResult(Integer quiz_id, String quiz_date, String quiz_result) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(QUIZZES_COLUMN_QUIZ_ID, quiz_id);
        values.put(QUIZZES_COLUMN_QUIZ_DATE, quiz_date);
        values.put(QUIZZES_COLUMN_QUIZ_RESULT, quiz_result);



        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_QUIZZES, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    } //addQuizResult

    // we have created a new method for reading all the courses.
    public ArrayList<CourseModal> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                        cursorCourses.getString(4),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }
} //class
