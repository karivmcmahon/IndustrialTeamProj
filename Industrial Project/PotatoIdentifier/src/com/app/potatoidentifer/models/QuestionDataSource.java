package com.app.potatoidentifer.models;

import android.content.Context;
import android.database.Cursor;
import android.util.Pair;
import com.app.potatoidentifer.activities.FurtherInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ewan on 29/09/2014.
 */
public class QuestionDataSource extends BaseDataSource {

    Context C;

    public QuestionDataSource(Context c)
    {
        super(c);
        this.C = c;
        open();
    }

    public List<Pair<FurtherInfoBean,String>> getKnowledge() {

        FurtherInfoDataSource d = new FurtherInfoDataSource(C);
        d.open();
        List<FurtherInfoBean> infos = d.getAllFurtherInfos();

        Map<Integer, FurtherInfoBean> byId = new HashMap<Integer, FurtherInfoBean>();
        for(FurtherInfoBean bean : infos)
        {
            byId.put(bean.getID(), bean);
        }

        List<Pair<FurtherInfoBean,String>> output = new ArrayList<Pair<FurtherInfoBean,String>>();

        Cursor cursor = database.query("knowledge", new String[]{"glossary_id", "characteristic"}, null, null, null, null, null, null);
        if (cursor == null) throw new IllegalStateException();

        cursor.moveToFirst();

        for (int i = 0; i <cursor.getCount(); i++) {

            int id = cursor.getInt(0);
            if(byId.containsKey(id)) {
                output.add(Pair.create(byId.get(id), cursor.getString(1)));
            }
            cursor.moveToNext();
        }

        return output;
    }


}
