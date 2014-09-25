package com.app.potatoidentifer.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.potatoidentifier.R;

/**
 * This class creates the custom list view for the glossary items in the list
 * @author Kari
 *
 */
public class CustomListView extends ArrayAdapter<String> {
    private final Activity context;
    private final  ArrayList<String> glossarySymptomNames;
    private final ArrayList<Integer> imageId;

    public CustomListView(Activity context,  ArrayList<String> glossarySymptomNames, ArrayList<Integer> imageId) {
        super(context, R.layout.glossary_fragment_layout, glossarySymptomNames);
        this.context = context;
        this.glossarySymptomNames = glossarySymptomNames;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
    	    	
    	System.out.println("We also go here?");
    	
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list_view, null, false);
        
        TextView glossaryListText = (TextView) rowView.findViewById(R.id.txt);
        glossaryListText.setText( glossarySymptomNames.get(position));

        ImageView glossaryListImage = (ImageView) rowView.findViewById(R.id.img);        
        
        System.out.println("This is the imageID " + imageId.get(position).toString() + " And the position " + position);
        
        /*
         * This is where the issue is.
         * Leaf image is of too high quality.
         */
        glossaryListImage.setImageResource(0);
        
        //glossaryListImage.setImageResource( imageId.get(position));
        return rowView;
    }
}
