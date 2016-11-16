package com.iisc.androidanalysis.typestatebenchmedia_01;

import com.iisc.androidanalysis.typestatebenchmedia_01.CaptureAudioActivity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

/* @description - Typestate violation at line 24 25 31 32 in Capture AudioActivity, 
 * The MediaPlayer handle is reset before the dispatch of the call to AudioActivity
 * @handling - Should model correct asynchronous call semantics, FA.onCreate -> FA.onResume.reset -> {CAA.onCreate.isPlaying, pause, getVideoHeight , getVideoWidth}
 * @Cause of error - Unsound model of asynchronous call semantics at line 57 FA. 
 * 
 */
public class FirstActivity extends Activity {
	public static final String TAG = "FirstActivity";
	public static MediaPlayer mp = null;
	public static final String mediaSourcePath = "//mediaPath";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);

		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		
		super.onStart();
		
		try{
		mp = new MediaPlayer(); //  mp_state --> idle
		mp.setDataSource(mediaSourcePath); //mp_state --> initialized
		mp.prepare(); //mp_state --> prepared
		
		mp.start(); // mp_state --> started 
		}catch (Exception e) {
			// TODO: handle exception
		}
		//preapare the intent for the Capture audio 
		Intent intent = new Intent(this, CaptureAudioActivity.class);
		startActivity(intent);
		
		Log.v(TAG, "onStart Ends");
		
		
		
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
		
		mp.reset(); // mp_state --> idle
		
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
