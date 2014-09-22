package com.app.potatoidentifer.models;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 22/09/2014.
 */
public class GlossaryDataSource extends BaseDataSource {
    private final String GLOSSARY_TABLE = "glossary";
    private final String GLOSSARY_TITLE_CATEGORY = "type";
    private final String GLOSSARY_IMAGE = "imageid";
    private final String GLOSSARY_ID = "_id";
	private String[] allGlossaryColumns = { GLOSSARY_ID, GLOSSARY_TITLE_CATEGORY, GLOSSARY_IMAGE };

    public GlossaryDataSource(Context context) {
        super(context);
    }

    //Searches the database for the information required for the categories page.
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
