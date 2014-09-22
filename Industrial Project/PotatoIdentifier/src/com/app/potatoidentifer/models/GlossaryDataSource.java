package com.app.potatoidentifer.models;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GlossaryDataSource {
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	//private String[] allColumns = { DatabaseHelper.COLUMN_NAME,
		//      DatabaseHelper.COLUMN_IMAGE };
	
	public void open() throws SQLException 
	{
	    database = dbHelper.getWritableDatabase();
	}
	
	public void close() 
	{
	    dbHelper.close();
	}
	
//	public List<GlossaryBean> getGlossaryList( String type )
//	{
//		List<GlossaryBean> glossaryList = new ArrayList<GlossaryBean>();
//		Cursor cursor = database.query(DatabaseHelper.TEST_TABLE,
//		        null, type, null, null, null, null);
//
//		    cursor.moveToFirst();
//		    while (!cursor.isAfterLast()) {
//		      GlossaryBean glossaryItem = cursorToGlossary(cursor);
//		      glossaryList.add(glossaryItem);
//		      cursor.moveToNext();
//		    }
//		    // make sure to close the cursor
//		    cursor.close();
//		return glossaryList;
//	}
//	
//	private GlossaryBean cursorToGlossary(Cursor cursor) {
//	    GlossaryBean glossaryItem = new GlossaryBean();
//	    glossaryItem.setID(cursor.getInt(0));
//	    glossaryItem.setSymptom(cursor.getString(1));
//	    glossaryItem.setImageID(cursor.getInt(3));
//	    return glossaryItem;
//	  }
	  
}
