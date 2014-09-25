package com.app.potatoidentifer.activities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.potatoidentifer.models.BitmapScaler;
import com.example.potatoidentifier.R;

import java.io.IOException;

/**
 * This class creates a custom list view for the categories in the glossary
 * @author Kari
 *
 */
public class CategoriesCustomListView extends ArrayAdapter<String>  {
	private final Activity context;
    private final String[] glossaryNames;
    private final Integer[] imageId;

    public CategoriesCustomListView(Activity context, String[] web, Integer[] imageId) {
        super(context, R.layout.glossary_fragment_layout, web);
        this.context = context;
        this.glossaryNames = web;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list_view, null, false);
        
        TextView glossaryListText = (TextView) rowView.findViewById(R.id.txt);
        glossaryListText.setText(glossaryNames[position]);

        ImageView glossaryListImage = (ImageView) rowView.findViewById(R.id.img);
//        glossaryListImage.setImageResource(imageId[position]);

        Resources res = context.getResources();
        try {
            BitmapScaler scaler = new BitmapScaler(res, imageId[position], 200);
            glossaryListImage.setImageBitmap(scaler.getScaled());
        } catch(IOException e) {
            e.printStackTrace();
        }


        return rowView;
    }
}
