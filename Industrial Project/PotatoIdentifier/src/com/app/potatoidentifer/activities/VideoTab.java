package com.app.potatoidentifer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class VideoTab extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Different to glossary, this calls text dynamically. But i think the glossary page is the
        // proper way of doing things
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is Tab3 Activity");

        setContentView(tv);
    }
}
