package com.app.potatoidentifer.models;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 23/09/2014.
 */
public class GlossaryDataSource extends BaseDataSource {
    private String[] categoryColumnsInfoQuery = { GLOSSARY_ID, GLOSSARY_SYMPTOM, GLOSSARY_TYPE, GLOSSARY_IMAGE1, GLOSSARY_IMAGE2};

    public GlossaryDataSource(Context context) {
        super(context);
    }

    public List<GlossaryBean> getGlossaryInfo(String category) {
        List<GlossaryBean> glossaryList = new ArrayList<GlossaryBean>();
        Cursor cursor = database.query(GLOSSARY_TABLE, categoryColumnsInfoQuery, GLOSSARY_TYPE+"=?", new String[] { category }, null, null, null);
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

    private GlossaryBean cursorToGlossary(Cursor cursor) {
        GlossaryBean gi = new GlossaryBean();
        gi.setId(cursor.getInt(getIndex(GLOSSARY_ID, cursor)));
        gi.setSymptom(cursor.getString(getIndex(GLOSSARY_SYMPTOM, cursor)));
        gi.setType(cursor.getString(getIndex(GLOSSARY_TYPE, cursor)));
        gi.setImageId(cursor.getBlob(getIndex(GLOSSARY_IMAGE1, cursor)));
        gi.setImageId2(cursor.getBlob(getIndex(GLOSSARY_IMAGE2, cursor)));
        return gi;
    }
}
