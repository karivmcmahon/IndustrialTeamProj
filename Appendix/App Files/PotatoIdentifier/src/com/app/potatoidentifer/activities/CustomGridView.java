package com.app.potatoidentifer.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.potatoidentifier.R;

/**
 * This class creates the custom list view for the glossary items in the list
 * @author Kari
 *
 */
public class CustomGridView extends ArrayAdapter<String> {
	
    private final Activity context;
    @SuppressWarnings("unused")
	// Don't know if this is used anymore?
    private final  ArrayList<String> glossarySymptomNames;
    
    private final ArrayList<Bitmap> imageId;

    public CustomGridView(Activity context,  ArrayList<String> glossarySymptomNames, ArrayList<Bitmap> imageId) {
        super(context, R.layout.glossary_fragment_layout, glossarySymptomNames);
        this.context = context;
        this.glossarySymptomNames = glossarySymptomNames;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        
        //Uses this layout.
        View gridView = inflater.inflate(R.layout.grid_single, null, false);
        
        ImageView glossaryListImage = (ImageView) gridView.findViewById(R.id.grid_image);
        glossaryListImage.setImageBitmap(imageId.get(position));
        return gridView;
    }
}
