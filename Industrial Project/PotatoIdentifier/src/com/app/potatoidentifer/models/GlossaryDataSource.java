package com.app.potatoidentifer.models;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 23/09/2014.
 */
public class GlossaryDataSource extends BaseDataSource {
    private String[] categoryColumnsInfoQuery = { GLOSSARY_ID, GLOSSARY_SYMPTOM, GLOSSARY_TYPE, GLOSSARY_IMAGE1};

    public GlossaryDataSource(Context context) {
        super(context);
    }

    public List<GlossaryBean> getGlossaryInfo(String category) {
        List<GlossaryBean> glossaryList = new ArrayList<GlossaryBean>();
        Cursor cursor = database.query(GLOSSARY_TABLE, categoryColumnsInfoQuery, "type=?", new String[] { category }, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                glossaryList.add(cursorToGlossary(cursor));
            }
        }
        close();
        return glossaryList;
    }

    private GlossaryBean cursorToGlossary(Cursor cursor) {
        GlossaryBean glossaryItem = new GlossaryBean();
        glossaryItem.setId(cursor.getInt(0));
        glossaryItem.setSymptom(cursor.getString(1));
        glossaryItem.setType(cursor.getString(2));
        glossaryItem.setImageId(cursor.getString(3));
        return glossaryItem;
    }
}
