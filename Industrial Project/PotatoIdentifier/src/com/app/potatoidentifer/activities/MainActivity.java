package com.app.potatoidentifer.activities;

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

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("glossaryTab").setIndicator("Glossary", null),
                GlossaryFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("questionTab").setIndicator("Question", null),
                QuestionFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("videoTab").setIndicator("Video", null),
                VideoFragment.class, null);
    }
} 
