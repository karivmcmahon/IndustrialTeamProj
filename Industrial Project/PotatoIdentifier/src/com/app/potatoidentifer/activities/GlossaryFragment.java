package com.app.potatoidentifer.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.app.potatoidentifer.models.GlossaryBean;
import com.app.potatoidentifer.models.GlossaryDataSource;
import com.example.potatoidentifier.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This class sets up the glossary list once a category has been selected
 *
 * @author Kari
 */
public class GlossaryFragment extends BaseFragment {
    ListView list;
    ArrayList<String> glossaryList;
    ArrayList<Integer> glossaryId;
    ArrayList<Integer> imageId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        String category = bundle.getString("symptom");

        System.out.println("We go here?");
        
        Context context = this.getActivity();
        GlossaryDataSource gs = new GlossaryDataSource(context);
        gs.open();
        List<GlossaryBean> categoryInfo = gs.getGlossaryInfo(category);

        //Set sizes of arrays when we know the size of the list.
        glossaryList = new ArrayList<String>(categoryInfo.size());
        imageId = new ArrayList<Integer>(categoryInfo.size());
        glossaryId = new ArrayList<Integer>(categoryInfo.size());

        //Convert and add the list to the appropriate arrays.
        for (int i = 0; i < categoryInfo.size(); i++) {
            glossaryId.add(i, categoryInfo.get(i).getId());
            //Converting strings to a drawable.
            String mDrawableName = categoryInfo.get(i).getImageId();
            int resID = getResources().getIdentifier(mDrawableName, "drawable", context.getPackageName());
            imageId.add(i, resID);
            glossaryList.add(i, categoryInfo.get(i).getSymptom());
        }

        final View v = inflater.inflate(R.layout.glossary_fragment_layout,
                container, false);
        CustomListView adapter = new CustomListView(getActivity(),
                glossaryList, imageId);
        list = (ListView) v.findViewById(R.id.glossary_listview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(listViewListenerHandler);
        return v;
    }

    private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Bundle bundle = new Bundle();
            bundle.putString("category", glossaryList.get(position).toString());
            FurtherInfo fi = new FurtherInfo();
            fi.setArguments(bundle);
            fragmentTabActivity.addFragments(Const.TAB_FIRST, fi, true);
        }
    };
}
