package com.app.potatoidentifer.activities;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.potatoidentifer.models.BitmapScaler;
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
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list_view, null, false);
        TextView glossaryListText = (TextView) rowView.findViewById(R.id.txt);
        ImageView glossaryListImage = (ImageView) rowView.findViewById(R.id.img);
        glossaryListText.setText( glossarySymptomNames.get(position));
        Resources res = context.getResources();
        try {
            BitmapScaler scaler = new BitmapScaler(res, imageId.get(position), 200);
            glossaryListImage.setImageBitmap(scaler.getScaled());
        } catch(IOException e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
