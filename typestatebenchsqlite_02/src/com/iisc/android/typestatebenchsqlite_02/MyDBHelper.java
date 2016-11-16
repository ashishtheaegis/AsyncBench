package com.iisc.android.typestatebenchsqlite_02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * @author ashish - Data Base Creation Class
 */
public class MyDBHelper extends SQLiteOpenHelper{
	 
	public static final int DATABASE_VERSION = 1;
	  public static final String DATABASE_NAME = "FeedReader.db";
	  public static final String TABLE_NAME = "testTable";
	  public static final String COLUMN_NAME_TESTID = "testid";
	  public static final String COLUMN_NAME_TESTTITLE = "testtitle";
	  public static final String COLUMN_NAME_TESTSUBTITLE = "testsubtitle";
		
		
	  
	  private static final String TEXT_TYPE = " TEXT";
	  private static final String COMMA_SEP = ",";
	  private static final String SQL_CREATE_ENTRIES =
	      "CREATE TABLE " +TABLE_NAME + " (" +
	      COLUMN_NAME_TESTID + " INTEGER PRIMARY KEY," +
	      COLUMN_NAME_TESTID + TEXT_TYPE + COMMA_SEP +
	      COLUMN_NAME_TESTTITLE + TEXT_TYPE + COMMA_SEP +
	       " )";
	  
	  public MyDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

	  private static final String SQL_DELETE_ENTRIES =
	      "DROP TABLE IF EXISTS " + TABLE_NAME;
	
	  public void onCreate(SQLiteDatabase db) {
	        db.execSQL(SQL_CREATE_ENTRIES);
	    }
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // This database is only a cache for online data, so its upgrade policy is
	        // to simply to discard the data and start over
	        db.execSQL(SQL_DELETE_ENTRIES);
	        onCreate(db);
	    }
	    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        onUpgrade(db, oldVersion, newVersion);
	    }

}
