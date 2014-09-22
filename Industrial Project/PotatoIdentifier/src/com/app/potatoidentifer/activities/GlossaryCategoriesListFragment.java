package com.app.potatoidentifer.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.app.potatoidentifer.models.GlossaryBean;
import com.app.potatoidentifer.models.GlossaryDataSource;
import com.example.potatoidentifier.R;
import java.util.List;

public class GlossaryCategoriesListFragment extends BaseFragment {
	private ListView list;
    private String[] glossary_list;
	private Integer[] imageId;
    private Integer[] categoryId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.glossary_fragment_layout,
				container, false);

        Context context = this.getActivity();
        GlossaryDataSource ds = new GlossaryDataSource(context);
        ds.open();

        List<GlossaryBean> categories = ds.getGlossaryCategoryInfo();
        //Set sizes of arrays when we know the size of the list.
        glossary_list = new String[categories.size()];
        imageId = new Integer[categories.size()];
        categoryId = new Integer[categories.size()];

        //Convert and add the list to the appropriate arrays.
        for(int i = 0; i < categories.size(); i++) {
            categoryId[i] = categories.get(i).getID();
            //Converting strings to a drawable.
            String mDrawableName = categories.get(i).getImageID();
            int resID = getResources().getIdentifier(mDrawableName , "drawable", context.getPackageName());
            imageId[i] =  resID;
            glossary_list[i] = categories.get(i).getTitle();
        }

		//Sets up custom list view for categories
		CategoriesCustomListView adapter = new CategoriesCustomListView(getActivity(),glossary_list,imageId);
		list = (ListView) v.findViewById(R.id.glossary_listview);
		list.setAdapter(adapter);
		//Sets up on an item click listener - to check for when a click occurs on the item list
		list.setOnItemClickListener(listViewListenerHandler);
		return v;

	}

	private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() 
	{
		public void onItemClick(AdapterView parent, View v, int position,
				long id) 
		{
			//When a category item is clicked a new fragment is opened with glossary items relating to this category
			/**
			 * Database code - When database is available
			 * fragmentTabActivity.addFragments(Const.TAB_FIRST,
					new GlossaryFragment( glossary_list[position] ),  true);
			 */
			fragmentTabActivity.addFragments(Const.TAB_FIRST,
					new GlossaryFragment(),  true);
		}
	};
}