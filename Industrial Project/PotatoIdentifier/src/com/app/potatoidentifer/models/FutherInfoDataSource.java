package com.app.potatoidentifer.models;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FutherInfoDataSource 
{
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
	
//	public List<FurtherInfoBean> getGlossaryInfo( int id )
//	{
//		List<FurtherInfoBean> glossaryInfo = new ArrayList<FurtherInfoBean>();
//		Cursor cursor = database.query(DatabaseHelper.TEST_TABLE,
//		        null, id, null, null, null, null);
//
//		    cursor.moveToFirst();
//		    while (!cursor.isAfterLast()) {
//		      GlossaryBean glossaryItem = cursorToGlossary(cursor);
//		      glossaryInfo.add(glossaryItem);
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
//    glossaryItem.setImageID2(cursor.getInt(4));
//    glossaryItem.setImageID3(cursor.getInt(5));
//    glossaryItem.setImageID4(cursor.getInt(6));
//    glossaryItem.setImageID5(cursor.getInt(7));
//    glossaryItem.setImageID6(cursor.getInt(8));
//    glossaryItem.setBasicFacts(cursor.getInt(9));
//    glossaryItem.setDiagnostics(cursor.getInt(10));
//    glossaryItem.setControl(cursor.getInt(11));
//	    return glossaryItem;
//	  }
}
