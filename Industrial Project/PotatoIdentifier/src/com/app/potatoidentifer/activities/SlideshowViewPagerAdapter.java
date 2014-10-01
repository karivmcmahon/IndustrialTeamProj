package com.app.potatoidentifer.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.example.potatoidentifier.R;
import java.util.ArrayList;

/**
 * This class creates the scrollable slideshow for the app
 *
 * @author Kari
 */
public class SlideshowViewPagerAdapter extends PagerAdapter {
    Activity activity;
    ArrayList<Bitmap> imageArray;
    Context context;

    public SlideshowViewPagerAdapter(Activity activity, ArrayList<Bitmap> imageArray, Context context) {
        this.imageArray = imageArray;
        this.activity = activity;
        this.context = context;
    }

    public int getCount() {
        return imageArray.size();
    }

    public Object instantiateItem(ViewGroup collection, final int position) {
        ImageView view = new ImageView(activity);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        view.setScaleType(ScaleType.FIT_XY);
        view.setImageBitmap(imageArray.get(position));

        collection.addView(view, 0);
        view.setOnClickListener(new OnClickListener() {

            @SuppressLint("NewApi")
            @Override
            public void onClick(View arg0) {
                DisplayMetrics dm = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

                final Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.full_screen_image);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCancelable(true);

                TouchImageView img = (TouchImageView) dialog.findViewById(R.id.fullScreenImage);
                Bitmap bitmap = imageArray.get(position);
                int bitmapHeight = bitmap.getHeight();
                int bitmapWidth = bitmap.getWidth();

                dialog.getWindow().setLayout(bitmapWidth, bitmapHeight);
                img.setBackgroundColor(Color.TRANSPARENT);
                img.setImageBitmap(bitmap);

                final Button next = (Button) dialog.findViewById(R.id.searchButton);
                next.setBackgroundResource(R.drawable.ic_image_zoom_cross);

                next.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
        arg0.removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}