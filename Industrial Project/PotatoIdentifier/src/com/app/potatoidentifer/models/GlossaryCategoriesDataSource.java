package com.app.potatoidentifer.models;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;

/**
 * Created by Mark on 22/09/2014.
 */
public class GlossaryCategoriesDataSource extends BaseDataSource {
    private String[] categoryColumnsInfoQuery = { GLOSSARY_ID, GLOSSARY_TYPE, GLOSSARY_IMAGE1};

    public GlossaryCategoriesDataSource(Context context) {
        super(context);
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

    private GlossaryCategoriesBean cursorToGlossary(Cursor cursor) {
        GlossaryCategoriesBean gi = new GlossaryCategoriesBean();
        gi.setID(cursor.getInt(getIndex(GLOSSARY_ID, cursor)));
        gi.setTitle(cursor.getString(getIndex(GLOSSARY_TYPE, cursor)));
        gi.setImageID(cursor.getString(getIndex(GLOSSARY_IMAGE1, cursor)));
        return gi;
    }
}
