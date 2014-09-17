package com.example.tabexample;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


//No change was really made all i added was a comment.

// The way i have done the tabs is deprecated so perhaps there is a better way of doing them
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity 
{
            /** Called when the activity is first created. */
            @Override
            public void onCreate(Bundle savedInstanceState)
            {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);

                    // create the TabHost that will contain the Tabs
                    TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


                    TabSpec tab1 = tabHost.newTabSpec("First Tab");
                    TabSpec tab2 = tabHost.newTabSpec("Second Tab");
                    TabSpec tab3 = tabHost.newTabSpec("Third tab");
                    TabSpec tab4 = tabHost.newTabSpec("Fourth tab");

                   // Set the Tab name and Activity
                   // that will be opened when particular Tab will be selected
                    tab1.setIndicator("Glossary");
                    tab1.setContent(new Intent(this,GlossaryTab.class));
                    
                    tab2.setIndicator("Questions");
                    tab2.setContent(new Intent(this,QuestionTab.class));

                    tab3.setIndicator("Video");
                    tab3.setContent(new Intent(this,VideoTab.class));
                    
                    tab4.setIndicator("Info");
                    tab4.setContent(new Intent(this,FutherInfo.class));
                    
                    /** Add the tabs  to the TabHost to display. */
                    tabHost.addTab(tab1);
                    tabHost.addTab(tab2);
                    tabHost.addTab(tab3);
                    tabHost.addTab(tab4);

            }
} 
