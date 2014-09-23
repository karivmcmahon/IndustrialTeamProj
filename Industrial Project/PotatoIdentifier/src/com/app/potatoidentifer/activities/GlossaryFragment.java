package com.app.potatoidentifer.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.potatoidentifier.R;

import java.util.ArrayList;

/**
 * This class sets up the glossary list once a category has been selected
 * @author Kari
 *
 */
public class GlossaryFragment extends BaseFragment {
    ListView list;
    ArrayList<String> glossary_list;
    ArrayList<Integer> glossary_id;
    ArrayList<Integer> imageId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        String category = bundle.getString("category");
        Log.v("TESTING", category);
    	glossary_list = new ArrayList<String>() {{
     	   add("Leaf");
     	   add("Potato");
     	   add("Placeholder");
     	   add("Bug");
     	   add("Window");
     	   add("Insect");
     	   add("Nitrogen");
     	}};
     	
     	imageId = new ArrayList<Integer>() {{
      	   add(R.drawable.leaf);
      	   add(R.drawable.leaf);
      	   add(R.drawable.leaf);
      	   add(R.drawable.leaf);
      	   add(R.drawable.leaf);
      	   add(R.drawable.leaf);
      	   add(R.drawable.leaf);
      	}};

//        Context context = this.getActivity();
//        GlossaryDataSource ds = new GlossaryDataSource(context);
//        ds.open();
//        ds.getGlossaryCategoryInfo();

//	    List<GlossaryBean> val = ds.getGlossaryCategoryInfo();
//	    for (val : values) {
//	    	glossary_id.add(val.getID());
//			glossary_list.add(val.getSymptom());
//			imageId.add(val.getImageID());
//	    }

      	final View v = inflater.inflate(R.layout.glossary_fragment_layout,
				container, false);
		CustomListView adapter = new CustomListView(getActivity(),
				glossary_list, imageId);
		list = (ListView) v.findViewById(R.id.glossary_listview);
		list.setAdapter(adapter);
		list.setOnItemClickListener(listViewListenerHandler);
		return v;
    }

    private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() 
    {
    	public void onItemClick(AdapterView parent, View v, int position, long id) 
        {
    		/**
    		 * Database code for when database is available
    		 * fragmentTabActivity.addFragments(Const.TAB_FIRST,
    				new FurtherInfo( glossary_id.get(position), true);
    		 */
        	fragmentTabActivity.addFragments(Const.TAB_FIRST,
    				new FurtherInfo(), true);
        }
    };
}
