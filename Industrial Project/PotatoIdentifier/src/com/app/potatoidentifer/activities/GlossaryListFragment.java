package com.app.potatoidentifer.activities;

import java.util.ArrayList;
import java.util.List;


//import com.app.potatoidentifier.R;







import android.app.ListFragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GlossaryListFragment extends android.support.v4.app.ListFragment implements OnItemClickListener{
	
		// The array which holds the titles, and an array which holds the icons.
		String[] menutitles;
	    TypedArray menuIcons;

	    Button but;
	    
	    GlossaryListAdapter adapter;
	    private List<GlossaryRowBean> rowItems;

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	    	
	    	//Inflate the activity with this fragment.
	        return inflater.inflate(R.layout.glossary_list_fragment, null , false);
	        
	    }
	    
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {

	        super.onActivityCreated(savedInstanceState);

	        //Get the array from the values folder for the list of titles.
	        menutitles = getResources().getStringArray(R.array.titles);
	        
	        // does this need fixed?
	        //Same for the items.
	        menuIcons = getResources().obtainTypedArray(R.array.icons);

	       
	        
	        rowItems = new ArrayList<GlossaryRowBean>();

	        //Fill the list.
	        for (int i = 0; i < menutitles.length; i++) {
	            GlossaryRowBean items = new GlossaryRowBean(menutitles[i], menuIcons.getResourceId(i, -1));

	            rowItems.add(items);
	        }

	        adapter = new GlossaryListAdapter(getActivity(), rowItems);
	        setListAdapter(adapter);
	        getListView().setOnItemClickListener(this);
	        
	   

	    }
	
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position,
	            long id) {

	    	// Will be changed to add more functionality.
	        Toast.makeText(getActivity(), menutitles[position], Toast.LENGTH_SHORT)
	                .show();
	        
	     // Create new fragment and transaction
	        TestFragment newFragment = new TestFragment();
	        FragmentTransaction transaction = getFragmentManager().beginTransaction();

	        // Replace whatever is in the fragment_container view with this fragment,
	        // and add the transaction to the back stack
	        transaction.replace(R.id., newFragment);
	        transaction.addToBackStack(null);

	        // Commit the transaction
	        transaction.commit();
	        
	    }
	    
}
