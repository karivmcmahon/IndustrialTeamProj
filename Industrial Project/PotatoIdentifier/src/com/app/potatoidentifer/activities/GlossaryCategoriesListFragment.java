package com.app.potatoidentifer.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.potatoidentifer.models.GlossaryBean;
import com.example.potatoidentifier.R;

/**
 * This class represents the fragment for the list of categories for the glossary. It's the front page that is shown on the app
 * @author Kari
 *
 */
public class GlossaryCategoriesListFragment extends BaseFragment 
{
	ListView list;
	//An array of categories 
	String[] glossary_list = { "Leaf Symptoms", "Insect Symptoms", "Tubers Symptoms" };
	//Image to be displayed next to category in list view
	Integer[] imageId = { R.drawable.book1, R.drawable.book1, R.drawable.book1,
			R.drawable.book1, };



	/**
	 * This method sets up the initial view of the categories list fragment
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		final View v = inflater.inflate(R.layout.glossary_fragment_layout,
				container, false);
		//Sets up custom list view for categories
		CategoriesCustomListView adapter = new CategoriesCustomListView(getActivity(),
			glossary_list	, imageId);
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