package com.app.potatoidentifer.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Mark on 22/09/2014.
 */
public class GlossaryCategoriesDataSource extends BaseDataSource {
    private String[] categoryColumnsInfoQuery = { GLOSSARY_ID, GLOSSARY_TYPE, GLOSSARY_IMAGE1};
    Context con;
    public GlossaryCategoriesDataSource(Context context) {
        super(context);
        con = context;
    }

    public Cursor doesDisexist(String disease)
    {
    	String args = "%" + disease + "%";
		
    	Cursor dbSelect = database.query(
    			GLOSSARY_TABLE, // Table Name
    			null, // Columns
    			GLOSSARY_SYMPTOM + " like ?", // Rows ('selection')
    			new String[] { args }, // If selection has a '?' it is replaced by what this value, passed as a string
    			null, // group by
    			null, // having
    			null, // order by
    			null // limit
    			);
    		
    	return dbSelect;
    }
   
    public boolean doesDieaseExist(String disease)
    {
    	Log.v("disease","disease " + disease);
    	
    	String a = "%" + disease + "%";
    	//System.out.println(a);
    	Log.v("disease" , a);
    	
    	boolean exist = false;
    	Cursor cursor = database.query(
			    			GLOSSARY_TABLE, // Table Name
			    			null, // Columns
			    			GLOSSARY_SYMPTOM + " like ?", // Rows ("selection")
			    			new String[] { a }, // If selection has a '?' it is replaced by what this value, passed as a string
			    			null, // group by
			    			null, // having
			    			null, // order by
			    			null // limit
			    			);
    	
    	if(cursor.getCount() > 0)
    	{
    		Log.v("disease" , Integer.toString(cursor.getCount()));
    		exist = true;
    	}
    	else
     	{
    		Log.v("disease" , Integer.toString(cursor.getCount()));
    		exist = false;
    	}
    	cursor.close();
        //close();
        return exist;
    }
    //Searches the database for the information required for the categories page.
    public ArrayList<GlossaryCategoriesBean> getGlossaryCategoryInfo() {
        ArrayList<GlossaryCategoriesBean> glossaryList = new ArrayList<GlossaryCategoriesBean>();
        Cursor cursor = database.query(true, GLOSSARY_TABLE, categoryColumnsInfoQuery, null, null, GLOSSARY_TYPE, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                glossaryList.add(cursorToGlossary(cursor));
            }
        }
        cursor.close();
        close();
        return glossaryList;
    }
    
    public boolean doesDieaseExistByID(String _id)
    {
    	//Log.v("disease","disease " + disease);
    	boolean exist = false;
    	Cursor cursor = database.query(GLOSSARY_TABLE, null, "_id=?", new String[] {  _id }, null, null, null);
    	if(cursor.getCount() > 0)
    	{
    		exist = true;
    	}
    	else
     	{
    		exist = false;
    	}
    	cursor.close();
        close();
        return exist;
    }
    
    public void update(String _id,String symptom, String type, String basicFacts, String diagnostics, String control)
    {
    	open();
    	ContentValues cv = new ContentValues();
    	cv.put("symptom",symptom);
    	cv.put("type",type);
    	cv.put("basicFacts",basicFacts);
    	cv.put("diagnostics", diagnostics);
    	cv.put("control", control);
    	Log.v("cv","cv " + cv);
    	int i = database.update(GLOSSARY_TABLE, cv, "_id=?", new String[] { _id });
    	Log.v("i","i " + i);
    	close();
    	Log.v("update","update");
    }

    public void insert(String _id,String symptom, String type, Bitmap image, String basicFacts, String diagnostics, String control)
    {
    	
    
    	byte[] data = getBitmapAsByteArray(image);
    	open();
    	ContentValues cv = new ContentValues();
    	cv.put("_id",_id);
    	cv.put("symptom",symptom);
    	cv.put("type",type);
    	cv.put("imageid", data);
    	cv.put("imageid2",data);
    	cv.put("basicFacts",basicFacts);
    	cv.put("diagnostics", diagnostics);
    	cv.put("control", control);
    	Log.v("cv","cv " + cv);
        database.insert(GLOSSARY_TABLE, null,cv);
    	close();
    	Log.v("insert","insert");
    }
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 0, outputStream);       
        return outputStream.toByteArray();
    }
    private GlossaryCategoriesBean cursorToGlossary(Cursor cursor) {
        GlossaryCategoriesBean gi = new GlossaryCategoriesBean();
        gi.setID(cursor.getInt(getIndex(GLOSSARY_ID, cursor)));
        gi.setTitle(cursor.getString(getIndex(GLOSSARY_TYPE, cursor)));
        gi.setImageID(cursor.getBlob(getIndex(GLOSSARY_IMAGE1, cursor)));
        return gi;
    }
}
