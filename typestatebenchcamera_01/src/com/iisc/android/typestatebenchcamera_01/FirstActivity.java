package com.iisc.android.typestatebenchcamera_01;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
/*
 * @description - App violates the typestate property of Camera, 
 * @requires - Sound model of Asynchronous call
 * @unsoundness_leadsto - FN - (FA.onStart -> FA.onResume -> CIA.onStart ) 
 */
public class FirstActivity extends ActionBarActivity {
	 	public static final String TAG = "FirstActivity";
		public static android.hardware.Camera mycam= null;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_first);
			Log.i(TAG, "onCreate");
		
		}
		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			Log.i(TAG, "onStart");
			if(checkCameraHardware(this))
				Log.d(TAG, "The device has a camera");
			else 
				Log.d(TAG, "No Camera on the device");
		    try {
		    	mycam = android.hardware.Camera.open(0); // attempt to get a Camera instance
		    	Log.d(TAG, "Camra "+mycam);
		    }
		    catch (Exception e){
		        // Camera is not available (in use or does not exist)
		    	Log.d(TAG, "Exception"+ e.getMessage());
		    }
		   	Log.d(TAG, "Cam "+mycam);
			Intent startCaptureActivity = new Intent(this, CaptureImageActivity.class);
			startActivity(startCaptureActivity);
			mycam.startPreview();
			
		}
		
		
	@Override
	protected void onResume() {
	// TODO Auto-generated method stub
		super.onResume();
		mycam.release();
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

	private boolean checkCameraHardware(Context context) {
	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	        // this device has a camera
	        return true;
	    } else {
	        // no camera on this device
	        return false;
	    }
	}

}
