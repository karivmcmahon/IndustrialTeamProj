package com.app.potatoidentifer.models;

import android.content.Context;
import android.database.Cursor;
import com.app.potatoidentifer.activities.FurtherInfo;

import java.util.ArrayList;
import java.util.List;

public class FurtherInfoDataSource extends BaseDataSource {
    private String[] furtherInfoColumnsQuery = { GLOSSARY_ID, GLOSSARY_SYMPTOM, GLOSSARY_TYPE, GLOSSARY_IMAGE1, GLOSSARY_IMAGE2, GLOSSARY_IMAGE3, GLOSSARY_IMAGE3, GLOSSARY_IMAGE4, GLOSSARY_IMAGE5, GLOSSARY_IMAGE6, GLOSSARY_BASIC_FACTS, GLOSSARY_CONTROL, GLOSSARY_DIAGNOSTICS };

    public FurtherInfoDataSource(Context context) {
        super(context);
    }

    public List<FurtherInfoBean> getFurtherInfo(String symptom) {
        List<FurtherInfoBean> glossaryInfo = new ArrayList<FurtherInfoBean>();
        Cursor cursor = database.query(GLOSSARY_TABLE, furtherInfoColumnsQuery, GLOSSARY_SYMPTOM+"=?", new String[] { symptom }, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            glossaryInfo.add(cursorToGlossary(cursor));
        }
		cursor.close();
        close();
        return glossaryInfo;
    }

    public List<FurtherInfoBean> getAllFurtherInfos()
    {
        List<FurtherInfoBean> glossaryInfo = new ArrayList<FurtherInfoBean>();
        Cursor cursor = database.query(GLOSSARY_TABLE, furtherInfoColumnsQuery, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            glossaryInfo.add(cursorToGlossary(cursor));
        }
        cursor.close();
        close();
        return glossaryInfo;

    }


    private FurtherInfoBean cursorToGlossary(Cursor cursor) {
        FurtherInfoBean gb = new FurtherInfoBean();
        gb.setID(cursor.getInt(getIndex(GLOSSARY_ID, cursor)));
        gb.setSymptom(cursor.getString(getIndex(GLOSSARY_SYMPTOM, cursor)));
        gb.setImageID(cursor.getBlob(getIndex(GLOSSARY_IMAGE1, cursor)));
        gb.setImageID2(cursor.getBlob(getIndex(GLOSSARY_IMAGE2, cursor)));
        gb.setImageID3(cursor.getBlob(getIndex(GLOSSARY_IMAGE3, cursor)));
        gb.setImageID4(cursor.getBlob(getIndex(GLOSSARY_IMAGE4, cursor)));
        gb.setImageID5(cursor.getBlob(getIndex(GLOSSARY_IMAGE5, cursor)));
        gb.setImageID6(cursor.getBlob(getIndex(GLOSSARY_IMAGE6, cursor)));
        gb.setDiagnostics(cursor.getString(getIndex(GLOSSARY_DIAGNOSTICS, cursor)));
        gb.setBasicFacts(cursor.getString(getIndex(GLOSSARY_BASIC_FACTS, cursor)));
        gb.setControl(cursor.getString(getIndex(GLOSSARY_CONTROL, cursor)));
        return gb;
    }
}