package com.app.potatoidentifer.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.app.potatoidentifer.models.GlossaryBean;
import com.app.potatoidentifer.models.GlossaryDataSource;
import com.example.potatoidentifier.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class sets up the glossary list once a category has been selected
 * 
 * @author Kari
 */
public class GlossaryFragment extends BaseFragment {
	GridView grid;
	ArrayList<String> glossaryList;
	ArrayList<Integer> glossaryId;
	ArrayList<Bitmap> imageId;
	Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle = this.getArguments();
		String category = bundle.getString("symptom");

		context = this.getActivity();
		GlossaryDataSource gs = new GlossaryDataSource(context);
		gs.open();
		List<GlossaryBean> categoryInfo = gs.getGlossaryInfo(category);

		// Set sizes of arrays when we know the size of the list.
		glossaryList = new ArrayList<String>(categoryInfo.size());
		imageId = new ArrayList<Bitmap>();
		glossaryId = new ArrayList<Integer>(categoryInfo.size());

		// Convert and add the list to the appropriate arrays.
		for (int i = 0; i < categoryInfo.size(); i++) {

			// Converting strings to a drawable.

			if (categoryInfo.get(i).getImageId() != null) {
				byte[] blob = categoryInfo.get(i).getImageId();
				ByteArrayInputStream imageStream = new ByteArrayInputStream(
						blob);
				imageId.add(BitmapFactory.decodeStream(imageStream));

			}

			glossaryId.add(categoryInfo.get(i).getId());
			glossaryList.add(categoryInfo.get(i).getSymptom());

			if (categoryInfo.get(i).getImageId2() != null) {
				byte[] blob = categoryInfo.get(i).getImageId2();
				ByteArrayInputStream imageStream = new ByteArrayInputStream(
						blob);
				imageId.add(BitmapFactory.decodeStream(imageStream));
			}

			glossaryId.add(categoryInfo.get(i).getId());
			glossaryList.add(categoryInfo.get(i).getSymptom());
		}
		final View v = inflater.inflate(R.layout.grid_view, container, false);
		CustomGridView adapter = new CustomGridView(getActivity(),
				glossaryList, imageId);
		grid = (GridView) v.findViewById(R.id.grid);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(listViewListenerHandler);
		return v;
	}

	private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			
			int a = imageId.size();
			for(int i = 0 ; i < a ; i++)
			{
				imageId.get(i).recycle();
			}
			
			Bundle bundle = new Bundle();
			bundle.putString("category", glossaryList.get(position).toString());
			FurtherInfo fi = new FurtherInfo();
			fi.setArguments(bundle);
			fragmentTabActivity.addFragments(Const.TAB_FIRST, fi, true);
		}
	};
}
