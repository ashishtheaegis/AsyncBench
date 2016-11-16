package com.iisc.android.typestatebenchmedia_02;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
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
 * @description - Typestate violation in CAA.onStop and CAA.onPause - No of violations = 2
 * 1 ) The Media Player is reset in FA.onResume, thus typeState violation should be caught in the called Activity CaptureAudioActivity.onStop
 * 2) The Media Player is reset in FA.onResume, and then accessed in CAA.onPause.
 * @ handling - correct modelling of asynchronous calls and interleavings of component life-cycle call backs
 * @cause - FN - at CAA.onStop 
 * FN - at CAA.onPause
 */
public class FirstActivity extends ActionBarActivity {
	public static final String TAG = "FirstActivity";
	public static MediaPlayer mp = null;
	public static final String mediaSourcePath = "//mediaPath";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);

		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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
		
		
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mp.reset();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_first,
					container, false);
			return rootView;
		}
	}

}
