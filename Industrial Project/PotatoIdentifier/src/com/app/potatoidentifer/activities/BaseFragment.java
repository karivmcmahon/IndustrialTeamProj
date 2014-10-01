package com.app.potatoidentifer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * This class is the base fragment class which all the fragments inherit from
 * @author Kari
 *
 */
public class BaseFragment extends Fragment {
	
	@SuppressWarnings("unused")
	private SearchRequestedCallback mSearchRequestedCallback;
	
	protected MainActivity fragmentTabActivity;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Sets up the tabs for the activity in the application
		fragmentTabActivity = (MainActivity) this.getActivity();
	}

	public boolean onBackPressed() {
		return false;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {}
	
	public void setSearchRequestedCallback(SearchRequestedCallback callback) {
	    mSearchRequestedCallback = callback;
	}
	public interface SearchRequestedCallback {
	    void onSearchRequested();
	}
}
