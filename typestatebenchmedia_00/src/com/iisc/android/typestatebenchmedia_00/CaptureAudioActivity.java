package com.iisc.android.typestatebenchmedia_00;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

/*
 *	 
 */
public class CaptureAudioActivity extends ActionBarActivity {

	
	private static final String TAG = "CaptureAudioActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int duration =FirstActivity.mp.getDuration(); // mp_state --> ERROR
		if(FirstActivity.mp.isPlaying()){
			// pause the video and get the remaining Duration
			FirstActivity.mp.pause(); // mp_state --> Violation State
			Toast.makeText(this, "video paused", 5);
			
		}else{
			// get the height and width of the video
			int height =FirstActivity.mp.getVideoHeight(); //mp_state --> violation state
			int width =FirstActivity.mp.getVideoWidth(); //mp_state --> violation state
			
			Toast.makeText(this, "height "+height+" width "+width, 5);
			
		}
		
		FirstActivity.mp.reset();	
		
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "onStart");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPuase");
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume");
		
		
		
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop");
	}
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d(TAG, "onRestart");
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		
	}
	
}
