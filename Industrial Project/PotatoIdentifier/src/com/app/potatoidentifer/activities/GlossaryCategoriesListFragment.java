package com.app.potatoidentifer.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.app.potatoidentifer.models.GlossaryBean;
import com.app.potatoidentifer.models.GlossaryCategoriesBean;
import com.app.potatoidentifer.models.GlossaryCategoriesDataSource;
import com.example.potatoidentifier.R;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GlossaryCategoriesListFragment extends BaseFragment {
	private ListView list;
	private String[] glossary_list;
	private Bitmap[] imageId;
	private Integer[] categoryId;

	ArrayList<GlossaryBean> glossarraylist = new ArrayList<GlossaryBean>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.glossary_fragment_layout,
				container, false);

		final Context context = this.getActivity();
		final GlossaryCategoriesDataSource ds = new GlossaryCategoriesDataSource(
				context);
		ds.open();
		
		glossarraylist.clear();
		
		// Search Code
		final EditText srchText = (EditText) v.findViewById(R.id.editText1);
		Button btn = (Button) v.findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ds.open();
				boolean exists = ds.doesDieaseExist(srchText.getText()
						.toString());
				Log.v("Exists", "Exist " + exists);
				if (exists == true) {
					Log.v("disease", "What we have found is: ");
				} else {
					Log.v("disease", "hmmm");
				}

				Cursor searchQuery = ds.doesDisexist(srchText.getText()
						.toString());
				searchQuery.moveToFirst();
				int counter = searchQuery.getCount();

				for (int i = 0; i < counter; i++) {
					GlossaryBean gb = new GlossaryBean();
					gb.setId(searchQuery.getInt(searchQuery
							.getColumnIndex(searchQuery.getColumnName(0))));
					gb.setSymptom(searchQuery.getString(searchQuery
							.getColumnIndex(searchQuery.getColumnName(1))));
					gb.setType(searchQuery.getString(searchQuery
							.getColumnIndex(searchQuery.getColumnName(2))));
					gb.setImageId(searchQuery.getBlob(searchQuery
							.getColumnIndex(searchQuery.getColumnName(3))));
					gb.setImageId2(searchQuery.getBlob(searchQuery
							.getColumnIndex(searchQuery.getColumnName(4))));

					glossarraylist.add(gb);

					searchQuery.moveToNext();
				}

				Bundle extras1 = new Bundle();
				Log.i("disease", Integer.toString(glossarraylist.size()));
				extras1.putParcelableArrayList("arraylist", glossarraylist);
				SearchResultFragment srf = new SearchResultFragment();
				srf.setArguments(extras1);
				fragmentTabActivity.addFragments(Const.TAB_FIRST, srf, true);
			}

		});
		// End of search code.

		List<GlossaryCategoriesBean> categories = ds.getGlossaryCategoryInfo();
		// Set sizes of arrays when we know the size of the list.
		glossary_list = new String[categories.size()];
		imageId = new Bitmap[categories.size()];
		categoryId = new Integer[categories.size()];

		Bitmap theImage = null;
		// Convert and add the list to the appropriate arrays.
		for (int i = 0; i < categories.size(); i++) {
			categoryId[i] = categories.get(i).getID();
			// Converting strings to a drawable.
			byte[] blob = categories.get(i).getImageID();
			ByteArrayInputStream imageStream = new ByteArrayInputStream(blob);
			imageId[i] = BitmapFactory.decodeStream(imageStream);
			// int resID = getResources().getIdentifier(mDrawableName ,
			// "drawable", context.getPackageName());
			glossary_list[i] = categories.get(i).getTitle();
		}

		// Sets up custom list view for categories
		CategoriesCustomListView adapter = new CategoriesCustomListView( getActivity(), glossary_list, imageId);
		list = (ListView) v.findViewById(R.id.glossary_listview);
		list.setAdapter(adapter);
		// Sets up on an item click listener - to check for when a click occurs
		// on the item list
		list.setOnItemClickListener(listViewListenerHandler);
		return v;

	}

	private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView parent, View v, int position,
				long id) {
			Bundle bundle = new Bundle();
			bundle.putString("symptom", glossary_list[position]);
			GlossaryFragment gf = new GlossaryFragment();
			gf.setArguments(bundle);
			fragmentTabActivity.addFragments(Const.TAB_FIRST, gf, true);
		}
	};

}