package com.app.potatoidentifer.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.potatoidentifier.R;

/**
 * This class creates a custom list view for the categories in the glossary as
 * well as for the search results. The view being each "cell" or "row" of the
 * list view
 * 
 * @author Kari
 * 
 */
public class CategoriesCustomListView extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] symptoms;
	private final Bitmap[] imageId;

	public CategoriesCustomListView(Activity context, String[] symptoms, Bitmap[] imageId) {
		super(context, R.layout.glossary_fragment_layout, symptoms);
		this.context = context;
		this.symptoms = symptoms;
		this.imageId = imageId;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();

		// The layout that we are using for each row. In this case a text view
		// with an image view
		View rowView = inflater.inflate(R.layout.custom_list_view, null, false);

		TextView glossaryListText = (TextView) rowView.findViewById(R.id.categoryText);
		glossaryListText.setText(symptoms[position]);

		ImageView glossaryListImage = (ImageView) rowView.findViewById(R.id.categoryImage);
		glossaryListImage.setImageBitmap(imageId[position]);

		return rowView;
	}
}
