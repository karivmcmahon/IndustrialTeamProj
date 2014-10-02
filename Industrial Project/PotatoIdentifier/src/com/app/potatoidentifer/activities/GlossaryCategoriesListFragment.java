package com.app.potatoidentifer.activities;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.app.potatoidentifer.models.GlossaryBean;
import com.app.potatoidentifer.models.GlossaryCategoriesBean;
import com.app.potatoidentifer.models.GlossaryCategoriesDataSource;
import com.example.potatoidentifier.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class GlossaryCategoriesListFragment extends BaseFragment {
	private ListView list;
	private String[] glossary_list;
	private Bitmap[] imageId;
	private Integer[] categoryId;

	ArrayList<GlossaryBean> glossarraylist = new ArrayList<GlossaryBean>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View v = inflater.inflate(R.layout.glossary_fragment_layout, container, false);

		final Context context = this.getActivity();

		// open a connection to the database
		final GlossaryCategoriesDataSource ds = new GlossaryCategoriesDataSource(context);
		// GlossaryCategoriesDataSource(context);

		ds.open();

		// make sure that the list is empty every time it is used.
		glossarraylist.clear();

		// Search Code
		final EditText srchText = (EditText) v.findViewById(R.id.searchbar);

		// make sure that the searchbar is empty
		srchText.setText("");

		Button btn = (Button) v.findViewById(R.id.searchButton);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Makes the keyboard disappear when the user searches.
				InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		        mgr.hideSoftInputFromWindow(srchText.getWindowToken(), 0);

				// Search Query Call
				Cursor searchQuery = ds.doesDisexist(srchText.getText().toString());

				if (searchQuery.getCount() == 0) {
					// If the search provides nothing, let them know and reset
					// the search bar
					Toast.makeText(v.getContext(), "Your search returned found nothing. Please search again", Toast.LENGTH_LONG).show();
					srchText.setText("");
				} else {
					// If there is a search result

					// Move to the start, first row of the result.
					searchQuery.moveToFirst();
					// get the row count.
					int counter = searchQuery.getCount();

					// for each result put it in a bean
					for (int i = 0; i < counter; i++) {
						GlossaryBean gb = new GlossaryBean();
						gb.setId(searchQuery.getInt(searchQuery.getColumnIndex(searchQuery.getColumnName(0))));
						gb.setSymptom(searchQuery.getString(searchQuery.getColumnIndex(searchQuery.getColumnName(1))));
						gb.setType(searchQuery.getString(searchQuery.getColumnIndex(searchQuery.getColumnName(2))));
						gb.setImageId(searchQuery.getBlob(searchQuery.getColumnIndex(searchQuery.getColumnName(3))));
						gb.setImageId2(searchQuery.getBlob(searchQuery.getColumnIndex(searchQuery.getColumnName(4))));

						// add each bean to an arraylist
						glossarraylist.add(gb);

						// move to next row
						searchQuery.moveToNext();
					}

					// pass the array list to a new fragment which will display
					// the information in a list view
					Bundle searchBundle = new Bundle();
					searchBundle.putParcelableArrayList("arraylist", glossarraylist);
					SearchResultFragment srf = new SearchResultFragment();
					srf.setArguments(searchBundle);
					// Still using tab one.
					fragmentTabActivity.addFragments(Const.TAB_FIRST, srf, true);
				}
				searchQuery.close();
				srchText.setText("");
				// dssearch.close();
			}

		});
		// End of search code.

		// List to hold all of the categories, right now this is three.
		List<GlossaryCategoriesBean> categories = ds.getGlossaryCategoryInfo();
		// Set sizes of arrays when we know the size of the list.
		glossary_list = new String[categories.size()];
		imageId = new Bitmap[categories.size()];
		categoryId = new Integer[categories.size()];

		// Convert and add the list to the appropriate arrays.
		for (int i = 0; i < categories.size(); i++) {
			categoryId[i] = categories.get(i).getID();
			// Converting strings to a drawable.
			byte[] blob = categories.get(i).getImageID();
			ByteArrayInputStream imageStream = new ByteArrayInputStream(blob);
			imageId[i] = BitmapFactory.decodeStream(imageStream);
			glossary_list[i] = categories.get(i).getTitle();
		}

		// Sets up custom list view for categories
		CategoriesCustomListView adapter = new CategoriesCustomListView(getActivity(), glossary_list, imageId);

		list = (ListView) v.findViewById(R.id.glossary_listview);
		list.setAdapter(adapter);
		// Sets up on an item click listener - to check for when a click occurs
		// on the item list
		list.setOnItemClickListener(listViewListenerHandler);

		ds.close();

		return v;

	}

	private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			
			Bundle bundle = new Bundle();
			int a = imageId.length;
			// recycle the bitmaps. This helps with RAM usage.
			for (int i = 0; i < a; i++) {
				imageId[i].recycle();
			}
			
			// passing arguments to the next fragment.
			bundle.putString("symptom", glossary_list[position]);
			GlossaryFragment gf = new GlossaryFragment();
			gf.setArguments(bundle);
			fragmentTabActivity.addFragments(Const.TAB_FIRST, gf, true);
		}
	};

}