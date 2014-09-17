package com.app.potatoidentifer.activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.app.potatoidentifer.R;

public class SlideshowViewPagerAdapter extends PagerAdapter {

    Activity activity;
    int imageArray[];

    public SlideshowViewPagerAdapter(Activity activity, int[] imageArray) {
        this.imageArray = imageArray;
        this.activity = activity;
    }

    public int getCount() {
        return imageArray.length;
    }

    public Object instantiateItem(View collection, final int position) {
        ImageView view = new ImageView(activity);
        view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));
        view.setScaleType(ScaleType.FIT_XY);
        view.setBackgroundResource(imageArray[position]);
        ((ViewPager) collection).addView(view, 0);
        view.setOnClickListener(new OnClickListener() {

                                    @Override
                                    public void onClick(View arg0) {
                                        // TODO Auto-generated method stub
                                        Log.v("HEELO", "Hello" + imageArray[position]);
                                        Dialog dialog = new Dialog(activity);
                                        dialog.setContentView(R.layout.full_screen_image);
                                        dialog.setCancelable(true);
                                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                                        lp.copyFrom(dialog.getWindow().getAttributes());
                                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                                        lp.height = WindowManager.LayoutParams.MATCH_PARENT;


                                        //set up image view
                                        ImageView img = (ImageView) dialog.findViewById(R.id.imageView1);
                                        img.setImageResource(imageArray[position]);


                                        //now that the dialog is set up, it's time to show it
                                        dialog.show();

                                    }

                                }
        );
        return view;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}