package com.app.potatoidentifer.activities;

import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import com.app.potatoidentifer.models.BitmapScaler;
import com.example.potatoidentifier.R;

/**
 * This class creates the scrollable slideshow for the app
 * @author Kari
 *
 */
public class SlideshowViewPagerAdapter extends PagerAdapter {

	Activity activity;
	ArrayList<Integer> imageArray;
    Context context;

	public SlideshowViewPagerAdapter(Activity activity, ArrayList<Integer> imageArray, Context context) {
		this.imageArray = imageArray;
		this.activity = activity;
        this.context = context;
	}

	public int getCount() {
		return imageArray.size();
	}

	public Object instantiateItem(ViewGroup collection, final int position) {
		
		ImageView view = new ImageView(activity);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		view.setScaleType(ScaleType.FIT_XY);
		//view.setBackgroundResource(imageArray.get(position));

        Resources res = context.getResources();
        try {
            BitmapScaler scaler = new BitmapScaler(res, imageArray.get(position), 750);
            view.setImageBitmap(scaler.getScaled());
        } catch(IOException e) {
            e.printStackTrace();
        }
		collection.addView(view, 0);
//		view.setOnClickListener(new OnClickListener() {
//
//			@SuppressLint("NewApi")
//			@Override
//			public void onClick(View arg0) {
//				DisplayMetrics dm = new DisplayMetrics();
//				activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//				int width = dm.widthPixels;
//				int height = dm.heightPixels;
//
//				Bitmap bitmap = BitmapFactory.decodeResource(
//						activity.getResources(), imageArray.get(position));
//				// Get target image size
//				int bitmapHeight = bitmap.getHeight();
//				int bitmapWidth = bitmap.getWidth();
//
//				BitmapDrawable resizedBitmap = new BitmapDrawable(activity
//						.getResources(), Bitmap.createScaledBitmap(bitmap,
//						width, width * bitmapHeight / bitmapWidth, false));
//
//				final Dialog dialog = new Dialog(activity);
//				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//				dialog.setContentView(R.layout.full_screen_image);
//				dialog.setCancelable(true);
//
//				// Regular image view
//				// ImageView img = (ImageView)
//				// dialog.findViewById(R.id.imageView1);
//				// img.setBackground(resizedBitmap);
//
//				// TODO set up image view with pinch for zoom - Needs tested on
//				// device not sure it works
//				TouchImageView img = (TouchImageView) dialog
//						.findViewById(R.id.imageView1);
//				//img.setBackground(resizedBitmap);
//
//                Resources res = context.getResources();
//                try {
//                    BitmapScaler scaler = new BitmapScaler(res, imageArray.get(position), 100);
//                    img.setImageBitmap(scaler.getScaled());
//                } catch(IOException e) {
//                    e.printStackTrace();
//                }
//
//				final Button next = (Button) dialog.findViewById(R.id.button1);
//				next.setBackgroundResource(R.drawable.ic_image_zoom_cross);
//
//				next.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						dialog.dismiss();
//					}
//				});
//				// now that the dialog is set up, it's time to show it
//				dialog.show();
//			}
//
//		});
		return view;
	}

	@Override
	public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
		arg0.removeView((View) arg2);
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
