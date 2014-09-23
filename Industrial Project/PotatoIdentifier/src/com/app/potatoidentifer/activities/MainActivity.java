package com.app.potatoidentifer.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import com.app.potatoidentifer.models.DatabaseHelper;
import com.example.potatoidentifier.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends FragmentActivity implements
		OnTabChangeListener {

	private TabHost tabHost;
	private String currentSelectedTab;
	private HashMap<String, ArrayList<Fragment>> hashMapTabs;
	final int TEXT_ID = 100;
	private MyTabView arrTabs[] = new MyTabView[4];

	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		

		hashMapTabs = new HashMap<String, ArrayList<Fragment>>();
		hashMapTabs.put(Const.TAB_FIRST, new ArrayList<Fragment>());
		hashMapTabs.put(Const.TAB_SECOND, new ArrayList<Fragment>());
		hashMapTabs.put(Const.TAB_THIRD, new ArrayList<Fragment>());
		hashMapTabs.put(Const.TAB_FORTH, new ArrayList<Fragment>());

		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setOnTabChangedListener(this);
		tabHost.setup();

	

		TabHost.TabSpec spec = tabHost.newTabSpec(Const.TAB_FIRST);
		tabHost.setCurrentTab(0);
		arrTabs[0] = new MyTabView(this, "", R.drawable.ic_glossary_tab);
		spec.setContent(new TabHost.TabContentFactory() 
		{
			public View createTabContent(String tag)
			{
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(arrTabs[0]);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec(Const.TAB_SECOND);
		arrTabs[1] = new MyTabView(this,  "",R.drawable.ic_question_tab);
		spec.setContent(new TabHost.TabContentFactory() 
		{
			public View createTabContent(String tag) 
			{
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(arrTabs[1]);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec(Const.TAB_THIRD);
		arrTabs[2] = new MyTabView(this,  "",R.drawable.ic_video_tab);
		spec.setContent(new TabHost.TabContentFactory() 
		{
			public View createTabContent(String tag) 
			{
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(arrTabs[2]);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec(Const.TAB_FORTH);
		arrTabs[3] = new MyTabView(this,  "",R.drawable.ic_settings_tab);
		spec.setContent(new TabHost.TabContentFactory() 
		{
			public View createTabContent(String tag) 
			{
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(arrTabs[3]);
		tabHost.addTab(spec);

		// set background for Selected Tab
		TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(TEXT_ID);
		tv.setTextColor(Color.parseColor("#ffffff"));
		View iv = (View) tabHost.getCurrentTabView();
		iv.setBackgroundResource(R.color.twitter);

		// Listner for Tab 1//
		tabHost.getTabWidget().getChildAt(0).setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (hashMapTabs.size() > 0) {

					if (tabHost.getTabWidget().getChildAt(0)
							.isSelected()) {
						if (hashMapTabs.get(Const.TAB_FIRST).size() > 1) {
							resetFragment();
						}
					}
					tabHost.getTabWidget().setCurrentTab(0);
					tabHost.setCurrentTab(0);
				}
			}
		});

		/* Listner for Tab 2 */
		tabHost.getTabWidget().getChildAt(1).setOnClickListener(new OnClickListener() 
		{

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (hashMapTabs.size() > 0) {
	
						if (tabHost.getTabWidget().getChildAt(1)
								.isSelected()) {
							if (hashMapTabs.get(Const.TAB_SECOND).size() > 1) {
								resetFragment();
							}
						}
						tabHost.getTabWidget().setCurrentTab(1);
						tabHost.setCurrentTab(1);
					}
				}
		});

		/* Listner for Tab 3 */
		tabHost.getTabWidget().getChildAt(2).setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (hashMapTabs.size() > 0) {

					if (tabHost.getTabWidget().getChildAt(2)
							.isSelected()) {
						if (hashMapTabs.get(Const.TAB_THIRD).size() > 1) {
							resetFragment();
						}
					}
					tabHost.getTabWidget().setCurrentTab(2);
					tabHost.setCurrentTab(2);
				}
			}
		});
		
		/* Listner for Tab 4 */
		tabHost.getTabWidget().getChildAt(3).setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (hashMapTabs.size() > 0) {

					if (tabHost.getTabWidget().getChildAt(3)
							.isSelected()) {
						if (hashMapTabs.get(Const.TAB_FORTH).size() > 1) {
							resetFragment();
						}
					}
					tabHost.getTabWidget().setCurrentTab(3);
					tabHost.setCurrentTab(3);
				}
			}
		});

        //Build the database required for the app.
        buildDatabase();
    }

	public void buildDatabase() {
        DatabaseHelper myDbHelper = new DatabaseHelper(this);
        myDbHelper.deleteDatabase();
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
    }

	public void addFragments(String tabName, Fragment fragment, boolean add) 
	{
		if (add) 
		{
			hashMapTabs.get(tabName).add(fragment);
		}
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();
	}

	
	/**
	 * Removes fragments
	 * 
	 */
	public void removeFragment() 
	{
		Fragment fragment = hashMapTabs.get(currentSelectedTab).get(
				hashMapTabs.get(currentSelectedTab).size() - 2);
		hashMapTabs.get(currentSelectedTab).remove(
				hashMapTabs.get(currentSelectedTab).size() - 1);
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();
	}

	/**
	 * Resets fragment if the fragment's tab is re-clicked on
	 */
	private void resetFragment() {
		Fragment fragment = hashMapTabs.get(currentSelectedTab).get(0);
		hashMapTabs.get(currentSelectedTab).clear();
		hashMapTabs.get(currentSelectedTab).add(fragment);
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();

	}

	@Override
	public void onBackPressed() {

		if (hashMapTabs.get(currentSelectedTab).size() <= 1) {
			super.onBackPressed();
		} else {
			removeFragment();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (hashMapTabs.get(currentSelectedTab).size() == 0) 
		{
			return;
		}
		hashMapTabs.get(currentSelectedTab)
				.get(hashMapTabs.get(currentSelectedTab).size() - 1)
				.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onTabChanged(String tabName) 
	{
		
		currentSelectedTab = tabName;

		// Iterates unselected tab and gives them regular bg colour
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) 
		{
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i)
					.findViewById(TEXT_ID);

			View iv = (View) tabHost.getTabWidget().getChildAt(i);
			iv.setBackgroundColor(Color.parseColor("#555555"));
		}
		TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(TEXT_ID); // for Selected Tab
		tv.setTextColor(Color.parseColor("#ffffff"));

		View iv = (View) tabHost.getCurrentTabView();

		if (hashMapTabs.get(tabName).size() == 0) 
		{

			if (tabName.equals(Const.TAB_FIRST)) 
			{
				addFragments(tabName, new GlossaryCategoriesListFragment(),
						 true);
			} 
			else if (tabName.equals(Const.TAB_SECOND)) 
			{
				addFragments(tabName, new QuestionFragment(), true);
			} 
			else if (tabName.equals(Const.TAB_THIRD)) 
			{
				addFragments(tabName, new VideoFragment(), true);
			} 
			else if (tabName.equals(Const.TAB_FORTH)) 
			{
				addFragments(tabName, new VideoFragment(),  true);
			}
		} 
		else 
		{
			addFragments(
					tabName,
					hashMapTabs.get(tabName).get(hashMapTabs.get(tabName).size() - 1),
					 false);
		}

		switch (tabHost.getCurrentTab()) 
		{
			case 0:
				iv.setBackgroundResource(R.color.twitter);
				break;
			case 1:
				iv.setBackgroundResource(R.color.twitter);
				break;
			case 2:
				iv.setBackgroundResource(R.color.twitter);
				break;
			case 3:
				iv.setBackgroundResource(R.color.twitter);
				break;

		}

	}

	private class MyTabView extends LinearLayout
	{
		TextView tv;

		public MyTabView(Context c, String label, int id) {
			super(c);
			ImageView iv = new ImageView(c);
			tv = new TextView(c);
			
			tv.setGravity(Gravity.CENTER_HORIZONTAL);
			tv.setTextSize(11.0f);
			tv.setId(TEXT_ID);
			tv.setTextColor(Color.parseColor("#ffffff"));
			iv.setImageResource(id);
			setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
			addView(iv);
			addView(tv);
		}
	}

}
