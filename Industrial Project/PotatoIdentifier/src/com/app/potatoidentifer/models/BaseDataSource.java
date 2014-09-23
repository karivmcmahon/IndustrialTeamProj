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

    protected final String GLOSSARY_TABLE = "glossary";
    protected final String GLOSSARY_ID = "_id";
    protected final String GLOSSARY_SYMPTOM = "symptom";
    protected final String GLOSSARY_TYPE = "type";
    protected final String GLOSSARY_IMAGE1 = "imageid";
    protected final String GLOSSARY_IMAGE2 = "imageid3";
    protected final String GLOSSARY_IMAGE3 = "imageid4";
    protected final String GLOSSARY_IMAGE4 = "imageid5";
    protected final String GLOSSARY_IMAGE5 = "imageid6";
    protected final String GLOSSARY_BASIC_FACTS = "basicFacts";
    protected final String GLOSSARY_CONTROL = "control";
    protected final String GLOSSARY_DIAGNOSTICS = "diagnostics";

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
