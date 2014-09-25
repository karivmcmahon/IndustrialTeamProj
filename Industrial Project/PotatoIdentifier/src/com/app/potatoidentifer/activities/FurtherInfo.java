package com.app.potatoidentifer.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.potatoidentifer.models.FurtherInfoBean;
import com.app.potatoidentifer.models.FurtherInfoDataSource;
import com.example.potatoidentifier.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the further information page for a selected glossary item
 *
 * @author Kari
 */
public class FurtherInfo extends BaseFragment {
    ArrayList<Integer> slideshowImageArray;
    private int buttonIds[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6};
    private List<Button> buttonArray;
    private View v;
    private ViewPager myViewPager;
    private String basicFacts;
    private String symptomTitle;
    private String diagnostics;
    private String control;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.further_info_layout, container, false);

        Bundle bundle = this.getArguments();
        String category = bundle.getString("category");

        Context context = this.getActivity();
        FurtherInfoDataSource gs = new FurtherInfoDataSource(context);
        gs.open();
        List<FurtherInfoBean> furtherInfo = gs.getFurtherInfo(category);
        slideshowImageArray = new ArrayList<Integer>(6);
        for (int i = 0; i < furtherInfo.size(); i++) {
            symptomTitle = furtherInfo.get(i).getSymptom();

            String id1 = furtherInfo.get(i).getImageID();
            if (id1 != null) {
                int resID = getResources().getIdentifier(id1, "drawable", context.getPackageName());
                slideshowImageArray.add(resID);
            }

            String id2 = furtherInfo.get(i).getImageID2();
            if (id2 != null) {
                int resID = getResources().getIdentifier(id2, "drawable", context.getPackageName());
                slideshowImageArray.add(resID);
            }

            String id3 = furtherInfo.get(i).getImageID3();
            if (id3 != null) {
                int resID = getResources().getIdentifier(id3, "drawable", context.getPackageName());
                slideshowImageArray.add(resID);
            }

            String id4 = furtherInfo.get(i).getImageID4();
            if (id4 != null) {
                int resID = getResources().getIdentifier(id4, "drawable", context.getPackageName());
                slideshowImageArray.add(resID);
            }

            String id5 = furtherInfo.get(i).getImageID5();
            if (id5 != null) {
                int resID = getResources().getIdentifier(id5, "drawable", context.getPackageName());
                slideshowImageArray.add(resID);
            }

            String id6 = furtherInfo.get(i).getImageID6();
            if (id6 != null) {
                int resID = getResources().getIdentifier(id6, "drawable", context.getPackageName());
                slideshowImageArray.add(resID);
            }

            basicFacts = furtherInfo.get(i).getBasicFacts();
            control = furtherInfo.get(i).getControl();
            diagnostics = furtherInfo.get(i).getDiagnostics();
        }

        TextView basicFactsTextView = (TextView) v.findViewById(R.id.basicFactTextView);
        basicFactsTextView.setText(basicFacts);

        TextView diagnosticsTextView = (TextView) v.findViewById(R.id.diagnosticsTextView);
        diagnosticsTextView.setText(diagnostics);

        TextView controlTextView = (TextView) v.findViewById(R.id.controlTextView);
        controlTextView.setText(control);

        TextView symptomTitleTextView = (TextView) v.findViewById(R.id.titleTextView);
        symptomTitleTextView.setText(symptomTitle);

        SlideshowViewPagerAdapter adapter = new SlideshowViewPagerAdapter(getActivity(), slideshowImageArray, context);
        myViewPager = (ViewPager) v.findViewById(R.id.slideshowviewpager);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);
        myViewPager.setOnPageChangeListener(viewPagerListenerHandler);
        initButton();
        return v;
    }

    private ViewPager.OnPageChangeListener viewPagerListenerHandler = new OnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int position) {}

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}

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