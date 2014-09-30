package com.app.potatoidentifer.activities;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import com.app.potatoidentifer.models.GlossaryBean;
import com.app.potatoidentifer.models.YourClass;
import com.example.potatoidentifier.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchResultFragment extends BaseFragment{

    private View v;
	ListView results;
	ArrayList<GlossaryBean> glossarraylistreceiver = new ArrayList<GlossaryBean>();

	Bitmap[] images;
	byte[] intakeImage;
	String[] symptoms;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.search_result_layout, container, false);
        
        glossarraylistreceiver.clear();
        
        Bundle bundle = this.getArguments();
        glossarraylistreceiver = bundle.getParcelableArrayList("arraylist");
                        
        int listLength = glossarraylistreceiver.size();
        Log.i("disease" , Integer.toString(listLength));
        
        symptoms = new String[listLength];
        images = new Bitmap[listLength];
        
        
        for(int i = 0 ; i < listLength ; i++)
        {
        	symptoms[i] = glossarraylistreceiver.get(i).getSymptom();
        	try{
        		byte[] intake = glossarraylistreceiver.get(i).getImageId();
            	ByteArrayInputStream imageStream = new ByteArrayInputStream(intake);
                images[i] = BitmapFactory.decodeStream(imageStream);
        	}
        	catch (Exception e)
        	{
        		Log.i("disease" , "Something went wrong at " + Integer.toString(i));
        	}
        	finally
        	{
        		
        	}
        	
        }
        
        CategoriesCustomListView adapter = new CategoriesCustomListView(getActivity(), symptoms, images);
		results = (ListView) v.findViewById(R.id.searchResultList);
		results.setAdapter(adapter);
		//Sets up on an item click listener - to check for when a click occurs on the item list
		results.setOnItemClickListener(listViewListenerHandler);
         
        return v;
	}
	
	private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView parent, View v, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("category", symptoms[position]);
        FurtherInfo fi = new FurtherInfo();
        fi.setArguments(bundle);
        fragmentTabActivity.addFragments(Const.TAB_FIRST, fi, true);
    	}
	};
}
