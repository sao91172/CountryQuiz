package com.example.countryquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
import java.util.ArrayList;
/**
 * This is a SQLiteOpenHelper class, which Android uses to create, upgrade, delete an SQLite database
 * in an app.
 *
 * This class is a singleton, following the Singleton Design Pattern.
 * Only one instance of this class will exist.  To make sure, the
 * only constructor is private.
 * Access to the only instance is via the getInstance method.
 */

public class CountriesDBHelper extends SQLiteOpenHelper {
    private static String DEBUG_TAG = "CountriesDBHelper"; // Tag just for the LogCat window
    //destination path (location) of our database on device
    private static final int DB_VERSION = 1;

    public static final String TABLE_QUIZZES = "quizzes";
    //The first column of the quizzes table which is quiz_id (primary key)
    public static final String QUIZZES_COLUMN_QUIZ_ID = "_quiz_id";
    //The second column of the quizzes table which is quiz date
    public static final String QUIZZES_COLUMN_QUIZ_DATE = "_quiz_date";
    //The third column of the quizzes table which is quiz result
    public static final String QUIZZES_COLUMN_QUIZ_RESULT = "_quiz_result";

    public static final String TABLE_COUNTRIES = "countries";
    public static final String COUNTRIES_COLUMN_ID = "_id";
    public static final String COUNTRIES_COLUMN_COUNTRY = "country";
    public static final String COUNTRIES_COLUMN_CONTINENT = "continent";


    public static final String QUIZZES_COLUMN_QUESTION1 = "question1";
    public static final String QUIZZES_COLUMN_QUESTION2 = "question2";
    public static final String QUIZZES_COLUMN_QUESTION3 = "question3";
    public static final String QUIZZES_COLUMN_QUESTION4 = "question4";
    public static final String QUIZZES_COLUMN_QUESTION5 = "question5";
    public static final String QUIZZES_COLUMN_QUESTION6 = "question6";
    public static final String QUIZZES_COLUMN_RESULT = "result";


    private static String DB_PATH = "";
    //private static String DB_NAME ="(students).sqlite";// Database name
    private static String DB_NAME ="countries.db";
    private SQLiteDatabase myDataBase;

    //A Create table SQL statement to create a table for countries
    //_id is an auto increment primary key, i.e. the database will automatically
    //generate unique id values as keys
    private static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + " ("
                    + COUNTRIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COUNTRIES_COLUMN_COUNTRY + " TEXT, "
                    + COUNTRIES_COLUMN_CONTINENT + " TEXT "
                    + ")";

    //A Create table SQL statement to create a table for quizzes
    //_id is an auto increment primary key, i.e. the database will automatically
    //generate unique id values as keys
    private static final String CREATE_QUIZZES =
            "create table " + TABLE_QUIZZES + " ("
                    +  QUIZZES_COLUMN_QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +  QUIZZES_COLUMN_QUIZ_DATE + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION1 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION2 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION3 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION4 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION5 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION6 + " TEXT, "
                    + QUIZZES_COLUMN_RESULT + " INTEGER"
                    + ")";

    private static CountriesDBHelper helperInstance;

    //    private final Context myContext;
//
    //since this constructor is private, it can only called from this class in the getInstance method
    private CountriesDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Access method to single instance of the class.
    //It is synchronized, so that only one thread executes this method.
    public static synchronized CountriesDBHelper getInstance(Context context) {
        //check if the instance already exists and if not, create the instance
        if(helperInstance == null) {
            helperInstance = new CountriesDBHelper(context.getApplicationContext());
        }
        return helperInstance;
    }

    //We must override onCreate method, which will be used to create the database if
    //it does not exist yet.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COUNTRIES);
        Log.d(DEBUG_TAG, "Table " + TABLE_COUNTRIES + " created");
        db.execSQL(CREATE_QUIZZES);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUIZZES + " created");
    }

    //We should override onUpgrade method, which will be used to upgrade the database if
    //its version (DB_VERSION) has changed. This will be done automatically by Android
    //if the version will be bumped up, as we modify the database schema.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_COUNTRIES);
        Log.d(DEBUG_TAG, "Table " + TABLE_COUNTRIES + " upgraded");
        db.execSQL("drop table if exists " + TABLE_QUIZZES);
        onCreate(db);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUIZZES + " upgraded");
    }

    public SQLiteDatabase getReadableDatabase() {
        if (myDataBase == null) {
            myDataBase = super.getReadableDatabase();
        } // if
        return myDataBase;
    } // getReadableDatabase()
}







//    public CountriesDBHelper(Context context) {
//        super(context, DB_NAME, null, 1);// 1? its Database Version
////        DB_PATH = "/data/data/" + context.getDatabasePath(DB_NAME) + "/databases/";
//        DB_PATH = context.getDatabasePath(DB_NAME).getAbsolutePath();
//        Log.i(DEBUG_TAG, DB_PATH);
//        //context gives us access to resources
//        this.myContext=context;
//        boolean dbexist = checkDataBase();
//        if (dbexist) {
//            openDataBase();
//        } else {
//            Log.i(DEBUG_TAG, "CountriesDBHelper: DATABASE DOES NOT EXIST");
//            createDataBase();
//        }
//    } //CountriesDBHelper
//
//    //    private CountriesDBHelper(Context context ) {
////        super( context, DB_NAME, null, DB_VERSION );
////    }
//    //Access method to the single instance of the class.
//    // It is synchronized, so that only one thread can executes this method, at a time.
//    public static synchronized CountriesDBHelper getInstance( Context context ) {
//        // check if the instance already exists and if not, create the instance
//        if( helperInstance == null ) {
//            helperInstance = new CountriesDBHelper(context.getApplicationContext() );
//        }
//        return helperInstance;
//    }
//
//    public void createDataBase()
//    {
//        //If database not exists copy it from the assets
//        boolean mDataBaseExist = checkDataBase();
//        if(!mDataBaseExist) {
//            this.getReadableDatabase();
//            this.close();
//            try {
//                //Copy the database from assests
//                copyDataBase();
//                Log.e(DEBUG_TAG, "createDataBase method: database created");
//            }
//            catch (IOException mIOException) {
//                Log.i(DEBUG_TAG, "createDataBase method: ERROR COPYING DATABASE"+mIOException+"");
//            }
//        }
//    }
//    //Check that the database exists here: /data/data/your package/databases/Da Name
//    private boolean checkDataBase() {
//        boolean checkDB = false;
//        try {
//            File dbFile = new File(DB_PATH + DB_NAME);
//            checkDB = dbFile.exists();
//        } catch(SQLiteException e) {
//            Log.i(DEBUG_TAG, "checkDatabase method: DATABASE DOES NOT EXIST");
//        }
//        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
//        return checkDB;
//    } //checkDataBase
//
//    //Copy the database from assets
//    private void copyDataBase() throws IOException {
//        try {
//            //opens the local db as the input stream
//            InputStream myInput = myContext.getAssets().open(DB_NAME);
//            //Path to the just created empty DB
//            String outFileName = DB_PATH + DB_NAME;
//            // open the empty db as the output stream
//            OutputStream mOutput = new FileOutputStream(outFileName);
//            //transfer byte from input file to output file
//            byte[] mBuffer = new byte[1024];
//            int mLength;
//            while ((mLength = myInput.read(mBuffer))>0) {
//                mOutput.write(mBuffer, 0, mLength);
//            }
//            mOutput.flush();
//            mOutput.close();
//            myInput.close();
//        }
//        catch (IOException mIOException) {
//            Log.i(DEBUG_TAG,"copyDataBase method"+ mIOException+"");
//        }
//    } //copyDataBase
//
//    //Open the database, so we can query it
//    public boolean openDataBase() throws SQLException
//    {
//        String mPath = DB_PATH + DB_NAME;
//        //Log.v("mPath", mPath);
//        myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
//        //myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
//        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
//        return myDataBase != null;
//    } //openDataBase
//
//    @Override
//    public synchronized void close() {
//        if(myDataBase != null)
//            myDataBase.close();
//        super.close();
//    } //close
//
//    @Override
//    public void onCreate(SQLiteDatabase arg0) {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // TODO Auto-generated method stub
//    }
//
//
//} //class