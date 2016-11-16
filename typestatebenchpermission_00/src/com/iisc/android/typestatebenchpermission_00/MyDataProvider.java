package com.iisc.android.typestatebenchpermission_00;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyDataProvider extends ContentProvider {

		static final String PROVIDER_NAME = "com.iisc.android.mydataprovider";
	   static final String URL = "content://" + PROVIDER_NAME + "/compiler";
	   static final Uri CONTENT_URI = Uri.parse(URL);

	   
	   
	 /*
     * Defines a handle to the database helper object. The MainDatabaseHelper class is defined
     * in a following snippet.
     */
    private MyDatabaseHelper mDbHelper;

    // Defines the database name
    private static final String DBNAME = "mydb";

    // Holds the database object
    private SQLiteDatabase db;

    public boolean onCreate() {

        mDbHelper = new MyDatabaseHelper(
            getContext(),        // the application context
            DBNAME              // the name of the database)
         );

        return true;
    }

	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		   db = mDbHelper.getWritableDatabase();
		return uri;
		
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	protected static final class MyDatabaseHelper extends SQLiteOpenHelper {
		private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
			    "main " +                       // Table's name
			    "(" +                           // The columns in the table
			    " _ID INTEGER PRIMARY KEY, " +
			    " WORD TEXT" +
			    " FREQUENCY INTEGER " +
			    " LOCALE TEXT )";
	 
		MyDatabaseHelper(Context context , String dbName) {
	        super(context, dbName, null, 1);
	    }

	   

		/*
	     * Creates the data repository. This is called when the provider attempts to open the
	     * repository and SQLite reports that it doesn't exist.
	     */
	    public void onCreate(SQLiteDatabase db) {

	        // Creates the main table
	        db.execSQL(SQL_CREATE_MAIN);
	    }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
	}


}
