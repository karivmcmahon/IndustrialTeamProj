package com.app.potatoidentifer.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.potatoidentifier.R;

import java.util.ArrayList;
import java.util.List;

public class FurtherInfo extends BaseFragment {
	ArrayList<Integer> slideshowImageArray = new ArrayList<Integer>() {{
  	   add(R.drawable.leaf);
  	   add(R.drawable.leaf2);
  	   add( R.drawable.leaf3);
  	   add(R.drawable.leaf4);
  	   add( R.drawable.leaf5);
  	   add( R.drawable.leaf6);
  	}};
  	
 
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
        v = inflater.inflate(R.layout.futher_info_layout, container, false);
// 
//		FutherInfoDataSourcw datasource = new FutherInfoDataSource();
//	    datasource.open();
//	    List<FurtherInfoBean> val = datasource.getGlossaryInfo();
//	    for (FurtherInfoBean val : values)
//	    { 
//	    	slideshowImageArray.add(val.getImageID());
            //slideshowImageArray.add(val.getImageID2());
        //slideshowImageArray.add(val.getImageID3());
        //slideshowImageArray.add(val.getImageID4());
        //slideshowImageArray.add(val.getImageID5());
        //slideshowImageArray.add(val.getImageID6());
//			symptomTitle = val.getSymptom();
//			basicFacts = val.getBasicFacts();
        //	diagnostics = val.getDiagnostics();
        //  control = val.getControl();
//	    }
//      	final View v = inflater.inflate(R.layout.glossary_fragment_layout,
//				container, false);
//		CustomListView adapter = new CustomListView(getActivity(),
//				glossary_list, imageId);
//		list = (ListView) v.findViewById(R.id.glossary_listview);
//		list.setAdapter(adapter);
//		list.setOnItemClickListener(listViewListenerHandler);
//		return v;
//        TextView basicFactsTextView = (TextView) v.findViewById(R.id.basicFactTextView);
//        basicFactsTextView.setText( basicFacts );
//        TextView diagnosticsTextView = (TextView) v.findViewById(R.id.diagnosticsTextView);
//        diagnosticsTextView.setText(  diagnostics );
//        TextView controlTextView = (TextView) v.findViewById(R.id.controlTextView);
//        controlTextView.setText( control);
//        TextView symptomTitleTextView = (TextView) v.findViewById(R.id.titleTextView);
//        symptomTitleTextView.setText( symptomTitle );
//        SlideshowViewPagerAdapter adapter = new SlideshowViewPagerAdapter(getActivity(), slideshowImageArray );
//        myViewPager = (ViewPager) v.findViewById(R.id.slideshowviewpager);
//        myViewPager.setAdapter(adapter);
//        myViewPager.setCurrentItem(0);
//        myViewPager.setOnPageChangeListener(viewPagerListenerHandler);
//        initButton();
//        return v;
        
        
        TextView basicFactsTextView = (TextView) v.findViewById(R.id.basicFactTextView);
        basicFactsTextView.setText( "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin quis libero consectetur, hendrerit est ut, fringilla quam. Mauris varius, nisi non interdum dapibus, nisl velit pretium eros, nec venenatis sapien risus id dolor. Proin sit amet dolor sagittis, finibus ex nec, auctor tortor. Duis eu tristique sapien, eget placerat urna. Nulla facilisi. Suspendisse ac erat dolor. In hac ");
        TextView diagnosticsTextView = (TextView) v.findViewById(R.id.diagnosticsTextView);
        diagnosticsTextView.setText( "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin quis libero consectetur, hendrerit est ut, fringilla quam. Mauris varius, nisi non interdum dapibus, nisl velit pretium eros, nec venenatis sapien risus id dolor. Proin sit amet dolor sagittis, finibus ex nec, auctor tortor. Duis eu tristique sapien, eget placerat urna. Nulla facilisi. Suspendisse ac erat dolor. In hac ");
        TextView controlTextView = (TextView) v.findViewById(R.id.controlTextView);
        controlTextView.setText( "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin quis libero consectetur, hendrerit est ut, fringilla quam. Mauris varius, nisi non interdum dapibus, nisl velit pretium eros, nec venenatis sapien risus id dolor. Proin sit amet dolor sagittis, finibus ex nec, auctor tortor. Duis eu tristique sapien, eget placerat urna. Nulla facilisi. Suspendisse ac erat dolor. In hac ");
        TextView symptomTitleTextView = (TextView) v.findViewById(R.id.titleTextView);
        symptomTitleTextView.setText( "Symptom XYZ");
        SlideshowViewPagerAdapter adapter = new SlideshowViewPagerAdapter(getActivity(), slideshowImageArray );
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