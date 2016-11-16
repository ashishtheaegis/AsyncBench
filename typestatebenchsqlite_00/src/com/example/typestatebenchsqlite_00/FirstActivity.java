package com.example.typestatebenchsqlite_00;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
 * @description - NO typestate violations
 * @requires -  sound Asynchrony modeling
 * @cause - FP - if not modeled corrcetly (FA.onCreate -> DBA.onStart -> FA.onResume)
 * 
 */
public class FirstActivity extends ActionBarActivity {

	public static SQLiteDatabase mydatabase = null;
	private final String TAG ="FirstActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		Log.v(TAG, "onCreate");
		setContentView(R.layout.activity_first);
		MyDBHelper myDBhelper = new MyDBHelper(this);
		mydatabase = myDBhelper.getWritableDatabase(); // init -- > Connect --> open
		//mydatabase = openOrCreateDatabase("myDataBase",MODE_PRIVATE,null);
		/*mydatabase.execSQL("CREATE TABLE IF NOT EXISTS myTable(Username VARCHAR,Password VARCHAR);");
		mydatabase.execSQL("INSERT INTO myTable VALUES('ashish','ashish');");
*/
		
		
		//preapare the intent for the Capture audio 
		Intent intent = new Intent(this, DataBaseActivity.class);
		startActivity(intent);
		Cursor resultSet = mydatabase.rawQuery("Select * from myTable",null);
		resultSet.moveToFirst();
		String username = resultSet.getString(1);
		//String password = resultSet.getString(2);
		
		Toast.makeText(this, "userName "+username+ " pasword *******", 5);
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Cursor resultSet = mydatabase.rawQuery("Select * from myTable WHERE Username = \"ashish\" " ,null);
		resultSet.moveToFirst();
		String username = resultSet.getString(1);
		//String password = resultSet.getString(2);
		
		Toast.makeText(this, "userName "+username+ " pasword *******", 5);
	
		
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
