package com.app.potatoidentifer.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.potatoidentifier.R;

public class GlossaryCategoriesListFragment extends BaseFragment 
{

	// The array which holds the titles, and an array which holds the icons.
	ListView list;
	String[] glossary_list = { "Leaf", "Insects", "Tubers", "Nutrient" };
	Integer[] imageId = { R.drawable.book1, R.drawable.book1, R.drawable.book1,
			R.drawable.book1, };


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		final View v = inflater.inflate(R.layout.glossary_fragment_layout,
				container, false);
		CategoriesCustomListView adapter = new CategoriesCustomListView(getActivity(),
				glossary_list, imageId);
		list = (ListView) v.findViewById(R.id.glossary_listview);
		list.setAdapter(adapter);
		list.setOnItemClickListener(listViewListenerHandler);
		return v;

	}

	private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() 
	{
		public void onItemClick(AdapterView parent, View v, int position,
				long id) 
		{
			fragmentTabActivity.addFragments(Const.TAB_FIRST,
					new GlossaryFragment(),  true);
		}
	};

}