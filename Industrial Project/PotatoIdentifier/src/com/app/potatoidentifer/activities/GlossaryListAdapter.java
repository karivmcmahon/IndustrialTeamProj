package com.app.potatoidentifer.activities;

import java.util.List;







import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GlossaryListAdapter extends BaseAdapter{

	Context context;
	/*
	 * List made of rowitems
	 */
    List<GlossaryRowBean> rowItem;

    
    GlossaryListAdapter(Context context, List<GlossaryRowBean> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }
    
  //Calls to the Java Bean
    @Override
    //Gets size of list
    public int getCount() {

        return rowItem.size();
    }

    @Override
    // Gets the list item at the given position
    public Object getItem(int position) {

        return rowItem.get(position);
    }

    @Override
    // Gets the id for the list item, for displaying the image.
    public long getItemId(int position) {

        return rowItem.indexOf(getItem(position));
    }
    
 // displays the list itself with the correct items in the right places.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	// inflate the fragment with the glossary list layout.
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.glossary_list_item, null);            
        }
        
        // Gets the images and the text. 
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        
        // Get the position in the listview for displaying this row.
        GlossaryRowBean row_pos = rowItem.get(position);
        
        // setting the image resource and title
        imgIcon.setImageResource(row_pos.getIcon());
        txtTitle.setText(row_pos.getTitle());

        return convertView;

    }
	
}
