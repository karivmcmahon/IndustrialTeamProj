package com.app.potatoidentifer.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.potatoidentifier.R;

public class GlossaryFragment extends BaseFragment {
	
private int slideshowImageArray[] = { R.drawable.leaf, R.drawable.leaf2, R.drawable.leaf3, R.drawable.leaf4, R.drawable.leaf5 , R.drawable.leaf6 };
	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.futher_info_layout, container, false);
        
        
        //Image slideshow
        SlideshowViewPagerAdapter adapter = new SlideshowViewPagerAdapter(getActivity(), slideshowImageArray);
        ViewPager myViewPager = (ViewPager) v.findViewById(R.id.slideshowviewpager);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);
        
        return v;
    }
}
