package com.iisc.android.typestatebenchmedia_00;






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
/*
 * @description - Typestate violation in CAA.onStart (28, 29, 30 , 36,37)
 * The Media Player is reset in onPuase, thus typeState violation should be caught in the called Activity CaptureAudioActivity.onCreate
 * @ handling - correct modelling of asynchronous calls required to capture typestate violation.
 * @cause - FP - at line 60 FA , due to unsound modelling of asynchronous calls. 
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
		
		Log.v(TAG, "Asynchronous call to CaptureAdioActivity");
		Intent intent = new Intent(this, CaptureAudioActivity.class);
		
		startActivity(intent);
		int duration = mp.getDuration(); // mp_state --> started
		Toast.makeText(this, "the duration of video "+duration, 5);
		
		
		
		
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPuase");
		mp.reset(); // mp_state --> idle
		
		
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
