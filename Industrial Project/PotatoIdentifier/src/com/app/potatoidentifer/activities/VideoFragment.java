package com.app.potatoidentifer.activities;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.potatoidentifier.R;

public class VideoFragment extends BaseFragment {
	View v;
	MediaController mediaControls;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.video_fragment_layout, container, false);
        splashPlayer();
        return v;
    }
    
    public void splashPlayer() 
    {
    	 final VideoView videoHolderTest1 = (VideoView) v.findViewById(R.id.videoView1);
    	 final ImageButton playButton = (ImageButton) v.findViewById(R.id.play_button);
    	 final VideoView videoHolderTest2 = (VideoView) v.findViewById(R.id.videoView2);
    	 final ImageButton playButton2 = (ImageButton) v.findViewById(R.id.play_button2);
    	 Uri video1 = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"
    	    + R.raw.lfd2);
    	 Uri video2 = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"
    	    	    + R.raw.fta);
    	 if (mediaControls == null) 
    	 {
    	      mediaControls = new MediaController(getActivity());
    	}
    	 mediaControls.setAnchorView(videoHolderTest1);
    	 videoHolderTest1.setMediaController(mediaControls);
    	 videoHolderTest1.setVideoURI(video1);
    	 playButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				videoHolderTest1.start();
				mediaControls.show();
				playButton.setVisibility(View.GONE);
			}
    		 
    	 });
    	
    	 videoHolderTest1.setOnCompletionListener(new OnCompletionListener() {
    	  public void onCompletion(MediaPlayer mp) {
    	    playButton.setVisibility(View.VISIBLE);
    	  }
    	 });
    	 
    	 mediaControls.setAnchorView(videoHolderTest2);
    	 videoHolderTest2.setMediaController(mediaControls);
    	 videoHolderTest2.setVideoURI(video2);
    	 playButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				videoHolderTest2.start();
				mediaControls.show();
				playButton2.setVisibility(View.GONE);
			}
    		 
    	 });
    	
    	 videoHolderTest2.setOnCompletionListener(new OnCompletionListener() {
    	  public void onCompletion(MediaPlayer mp) {
    	    playButton2.setVisibility(View.VISIBLE);
    	  }
    	 });
   
    	}
}