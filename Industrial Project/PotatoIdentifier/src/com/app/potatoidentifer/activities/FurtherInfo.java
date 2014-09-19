package com.app.potatoidentifer.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class FurtherInfo extends BaseFragment {
    private int slideshowImageArray[] = {R.drawable.leaf, R.drawable.leaf2, R.drawable.leaf3, R.drawable.leaf4, R.drawable.leaf5, R.drawable.leaf6};
    private int buttonIds[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6};
    private List<Button> buttonArray;
    private View v;
    private ViewPager myViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.further_info_layout, container, false);

        SlideshowViewPagerAdapter adapter = new SlideshowViewPagerAdapter(getActivity(), slideshowImageArray);
        myViewPager = (ViewPager) v.findViewById(R.id.slideshowviewpager);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);
        myViewPager.setOnPageChangeListener(viewPagerListenerHandler);
        initButton();
        return v;
    }

    private ViewPager.OnPageChangeListener viewPagerListenerHandler = new OnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int position) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            btnAction(position);
        }
    };

    private void btnAction(int position) {
        Button btn;
        for (int id = 0; id < buttonIds.length; id++) {
            if (id == position) {
                btn = buttonArray.get(id);
                setButton(btn, "round_cell_off");
            } else {
                btn = buttonArray.get(id);
                setButton(btn, "round_cell_on");
            }
        }
    }

    private void initButton() {
        buttonArray = new ArrayList<Button>();
        for (int id : buttonIds) {
            Button button = (Button) v.findViewById(id);
            if (id == 0) {
                setButton(button, "round_cell_off");
            } else {
                setButton(button, "round_cell_on");
            }
            buttonArray.add(button);
        }
    }

    private void setButton(Button btn, String layoutType) {
        if (layoutType.equals("round_cell_on")) {
            btn.setBackgroundResource(R.drawable.round_cell_on);
        } else {
            btn.setBackgroundResource(R.drawable.round_cell_off);
        }
    }
}