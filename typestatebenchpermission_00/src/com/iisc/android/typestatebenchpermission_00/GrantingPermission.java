package com.iisc.android.typestatebenchpermission_00;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
/**
 * 
 * @author ashish
 *Typestate violation - None
 * ISSTA FP - revoking permission in RevokeUriPermission without first granting.
 * AsynAware - No Typestate violation found
 */
public class GrantingPermission extends ActionBarActivity {

	public static final  Uri uri = Uri.parse("content://com.iisc.android.mydataprovider/data/1");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_granting_permission);

		// granting URI permission
		
		
	     Intent intent = new Intent();
	     intent.setClassName("com.iisc.android.targetpackage", "TargetClass");
	     intent.setData(uri);
	     startActivity(intent); // SEND INTENT TO BE RESOLVED
	 
	        // EXPLICIT INTENT EXAMPLE
	    
		Intent otherIntent = new Intent(this, RevokingPermissionActivity.class);
		startActivity(otherIntent);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

        // EXPLICIT INTENT EXAMPLE
        grantUriPermission("com.iisc.android.targetpackage", uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		revokeUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.granting_permission, menu);
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
			View rootView = inflater.inflate(
					R.layout.fragment_granting_permission, container, false);
			return rootView;
		}
	}
	
	

}
