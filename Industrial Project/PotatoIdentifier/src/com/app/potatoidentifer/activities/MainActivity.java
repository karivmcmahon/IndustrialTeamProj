package com.app.potatoidentifer.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import com.example.potatoidentifier.R;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("glossaryTab").setIndicator("", res.getDrawable(R.drawable.ic_tab_glossary)),
                GlossaryFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("questionTab").setIndicator("", res.getDrawable(R.drawable.ic_tab_glossary)),
                QuestionFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("videoTab").setIndicator("", res.getDrawable(R.drawable.ic_tab_glossary)),
                VideoFragment.class, null);
    }
} 
