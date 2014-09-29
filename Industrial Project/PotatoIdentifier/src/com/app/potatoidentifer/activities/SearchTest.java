package com.app.potatoidentifer.activities;


import java.util.List;

import com.app.potatoidentifer.models.GlossaryCategoriesBean;
import com.app.potatoidentifer.models.GlossaryCategoriesDataSource;
import com.example.potatoidentifier.R;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SearchTest extends BaseFragment{

	private Button testBut;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		final View v = inflater.inflate(R.layout.search_test, container, false);

        Context context = this.getActivity();
        //GlossaryCategoriesDataSource ds = new GlossaryCategoriesDataSource(context);
        //ds.open();

        //List<GlossaryCategoriesBean> categories = ds.getGlossaryCategoryInfo();
        //Set sizes of arrays when we know the size of the list.
        testBut = (Button) v.findViewById(R.id.btn_search);
        
        testBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {	
				
				//onSearchRequested();
			 //FragmentActivity.this.onSearchRequested();				
			}
		});	
        
		return v;

	}

	
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search_test);
		
		Button btn = (Button) findViewById(R.id.btn_search);	
		
			
	}*/
	
}
