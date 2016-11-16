package com.iisc.android.typestatebenchcamera_00;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
/*
 * @description- No violation in FA.onStart (Line 56 , 57, 59)
 * @requires- Sound modelling of asynchronous calls and atomicity of call-backs.
 * @unsoundness_leadsto - FP , if modelled startActivity treated as synchronous. (Incorrect path - FA.onStart -> CIA.onCreate.close -> FA.onCreate.startPreview)
 */
public class FirstActivity extends Activity {
 public static final String TAG = "FirstActivity";
	public static android.hardware.Camera cam= null;
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
		/** Check if this device has a camera */
		
		if(checkCameraHardware(this))
			Log.d(TAG, "The device has a camera");
		else 
			Log.d(TAG, "No Camera on the device");
		
	    try {
	    	cam = android.hardware.Camera.open(0); // attempt to get a Camera instance
	    	Log.d(TAG, "Camra "+cam);
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    	Log.d(TAG, "Exception"+ e.getMessage());
	    }
	    	
		Log.d(TAG, "Cam "+cam);
		Intent startCaptureActivity = new Intent(this, CaptureImageActivity.class);
		startActivity(startCaptureActivity);
		cam.startPreview();
		cam.startFaceDetection();
		cam.release();
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
