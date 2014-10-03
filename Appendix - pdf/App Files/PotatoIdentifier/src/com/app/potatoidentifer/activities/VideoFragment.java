package com.app.potatoidentifer.activities;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.VideoView;
import com.example.potatoidentifier.R;

/**
 * This class creates a video fragment to display the video tutorials
 * 
 * @author Kari
 */
public class VideoFragment extends BaseFragment {
	View v;
	MediaController mediaControls;
	MediaController mediaControls2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// layout used for this fargment.
		v = inflater.inflate(R.layout.video_fragment_layout, container, false);

		ScrollView sv = (ScrollView) v.findViewById(R.id.scrollview);
		sv.smoothScrollTo(0, 0);
		splashPlayer(sv);
		return v;
	}

	public void splashPlayer(ScrollView sv) {
		final VideoView videoHolderTest1 = (VideoView) v.findViewById(R.id.videoView1);
		final ImageButton playButton = (ImageButton) v.findViewById(R.id.play_button);
		final VideoView videoHolderTest2 = (VideoView) v.findViewById(R.id.videoView2);
		final ImageButton playButton2 = (ImageButton) v.findViewById(R.id.play_button2);
		Uri video1 = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.lfd2);
		Uri video2 = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.fta);
		if (mediaControls == null) {
			mediaControls = new MediaController(getActivity());
		}
		if (mediaControls2 == null) {
			mediaControls2 = new MediaController(getActivity());
		}
		mediaControls.setAnchorView(videoHolderTest1);
		videoHolderTest1.setMediaController(mediaControls);
		videoHolderTest1.setVideoURI(video1);
		videoHolderTest1.setFocusable(false);
		mediaControls2.setAnchorView(videoHolderTest2);
		videoHolderTest2.setMediaController(mediaControls2);
		videoHolderTest2.setVideoURI(video2);
		videoHolderTest2.setFocusable(false);

		playButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				videoHolderTest1.start();
				mediaControls.show();
				// make the play button disappear.
				playButton.setVisibility(View.GONE);
			}

		});

		videoHolderTest1.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				playButton.setVisibility(View.VISIBLE);
			}
		});

		playButton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				videoHolderTest2.start();
				mediaControls2.show();
				playButton2.setVisibility(View.GONE);
			}

		});

		videoHolderTest2.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				playButton2.setVisibility(View.VISIBLE);
			}
		});

		// If screen is scrolled, hide the media controls.
		sv.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
			@Override
			public void onScrollChanged() {
				mediaControls2.hide();
				mediaControls.hide();
			}
		});
	}
}
