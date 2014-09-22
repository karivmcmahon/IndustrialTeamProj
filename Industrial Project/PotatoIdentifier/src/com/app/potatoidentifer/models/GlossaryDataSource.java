package com.app.potatoidentifer.models;

import java.util.*;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GlossaryDataSource {
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
    private final String GLOSSARY_TABLE = "glossary";
    private final String GLOSSARY_TITLE_CATEGORY = "type";
    private final String GLOSSARY_IMAGE = "imageid";
    private final String GLOSSARY_ID = "_id";
	private String[] allGlossaryColumns = { GLOSSARY_ID, GLOSSARY_TITLE_CATEGORY, GLOSSARY_IMAGE };

    public GlossaryDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
	    dbHelper.close();
	}
	
	public List<GlossaryBean> getGlossaryCategoryInfo() {
        List<GlossaryBean> glossaryList = new ArrayList<GlossaryBean>();
        Cursor cursor = database.query(true, GLOSSARY_TABLE, allGlossaryColumns, null, null, GLOSSARY_TITLE_CATEGORY, null, null, null);
        if(cursor!=null && cursor.getCount() > 0) {
            for(int i = 0; i < cursor.getCount(); i++){
                cursor.moveToPosition(i);
                glossaryList.add(cursorToGlossary(cursor));
            }
        }
        close();
        return glossaryList;
	}

	private GlossaryBean cursorToGlossary(Cursor cursor) {
	    GlossaryBean glossaryItem = new GlossaryBean();
	    glossaryItem.setID(cursor.getInt(0));
	    glossaryItem.setTitle(cursor.getString(1));
	    glossaryItem.setImageID(cursor.getString(2));
	    return glossaryItem;
	  }
}
