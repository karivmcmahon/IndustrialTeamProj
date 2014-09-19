package com.app.potatoidentifer.activities;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomListView extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] glossaryNames;
    private final Integer[] imageId;

    public CustomListView(Activity context, String[] web, Integer[] imageId) {
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
        ImageView glossaryListImage = (ImageView) rowView.findViewById(R.id.img);
        glossaryListText.setText(glossaryNames[position]);
        glossaryListImage.setImageResource(imageId[position]);
        return rowView;
    }
}
