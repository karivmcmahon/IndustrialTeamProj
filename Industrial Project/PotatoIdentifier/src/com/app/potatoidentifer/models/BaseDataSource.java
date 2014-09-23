package com.app.potatoidentifer.models;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mark on 22/09/2014.
 */
public abstract class BaseDataSource {
    protected SQLiteDatabase database;
    protected DatabaseHelper dbHelper;

    protected BaseDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
