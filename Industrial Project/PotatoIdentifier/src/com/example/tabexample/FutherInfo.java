package com.example.tabexample;

import com.example.tabexample.R;
import com.example.tabexample.SlideshowViewPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class FutherInfo extends Activity {
	
	private int slideshowImageArray[] = { R.drawable.leaf, R.drawable.leaf2, R.drawable.leaf3, R.drawable.leaf4, R.drawable.leaf5 , R.drawable.leaf6 };
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        // Placeholder. But calls this layout to display the page
        setContentView(R.layout.futher_info_layout);
        
        //Image slideshow
        SlideshowViewPagerAdapter adapter = new SlideshowViewPagerAdapter(this, slideshowImageArray);
        ViewPager myViewPager = (ViewPager) findViewById(R.id.slideshowviewpager);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);
        

     
    }

}
