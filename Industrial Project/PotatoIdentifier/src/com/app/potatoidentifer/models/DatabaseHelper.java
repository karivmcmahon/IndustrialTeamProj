package com.app.potatoidentifer.models;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static String DB_NAME = "projectDB.sqlite";
    private SQLiteDatabase myDataBase;
    private Context myContext;
    private Resources res;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = "/data/data/" + context.getApplicationContext().getPackageName() + "/databases/";
        res = myContext.getResources();
    }

    public void createDataBase() throws IOException {
        if (databaseFileExists()) {
            if (checkDataBase()) {
                Log.v("Database Debug", "Database has already been created.");
            } else {
                this.getReadableDatabase();
                this.close();
                try {
                    copyDataBase();
                    Log.v("Database Debug", "Copied database successfully");
                } catch (IOException e) {
                    Log.v("Database Debug", "Error copying the database");
                    e.printStackTrace();
                    throw new Error("Error copying database");
                }
            }
        } else {
            Log.v("Database Debug", "Database file does not exist in assets folder.");
        }
    }

    private boolean databaseFileExists() {
        AssetManager mg = res.getAssets();
        try {
            mg.open("databases/projectDB.sqlite");
            Log.v("Database Debug", DB_NAME+" does exist.");
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.v("Database Debug", DB_NAME+" does not exist.");
            return false;
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            Log.v("Database Debug", "Database does exist");

        } catch (SQLiteException e) {
            Log.v("Database Debug", "Database doesn't exist yet.");
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
        Log.v("SHIT", "FUCK");
        InputStream myInput = myContext.getAssets().open("databases/projectDB.sqlite");
        Log.v("Database Debug", "1");

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;
        Log.v("Database Debug", "2");

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        Log.v("Database Debug", "3");

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        Log.v("Database Debug", "4");
        int length;
        Log.v("Database Debug", "5");
        while ((length = myInput.read(buffer)) > 0) {
            Log.v("Database Debug", "6");
            myOutput.write(buffer, 0, length);
        }
        Log.v("Database Debug", "7");
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Log.v("Database Debug", "database opened successfully.");

        //Testing if it worked by getting the names of the tables.
        Cursor c = myDataBase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                Log.v("Testing", c.getString(0));
                c.moveToNext();
            }
        }
        myDataBase.close();
    }

    public void deleteDatabase() throws SQLException {
        myContext.deleteDatabase(DB_NAME);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
