package com.foodbook.onlinemanager;

import com.foodbook.foodbook.FridgeActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * DatabaseSQL is what will make our actual database. This
 * Needs to be PERFECTLY set up to query(get, edit, remove)
 * items properly in the app.
 * @author rjanes
 *
 */
public class DataBaseHandle extends SQLiteOpenHelper{
	//Static Variables: need one for each part.//

	//TABLE 1: *RECIPE* (HAS name, id) so far..
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "foodbookData";
	
	private static final String TABLE_NAME = "Recipe"; //Name of Table
	private static final String RECIPE_TITLE = "Recipe title";
	private static final String TABLE_PRIMARY_KEY = "id";
	
	DataBaseHandle databasehelper;
	FoodBookData database;
	public DataBaseHandle(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This will create the tables defined in this method.
	 * Once the tables are created it will put them in the database
	 * using db.execSQL commands.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String CREATE_RECIPE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
		        "INTEGER PRIMARY KEY," + TABLE_PRIMARY_KEY + " TEXT, " + "RECIPE_TITLE" + ")";
		 db.execSQL(CREATE_RECIPE_TABLE);
		 database.createTables(db);
		 System.out.println("path of database is: " + db.getPath());
	}
	
	/**
	 * If the tables need to be altered, reset, or changed
	 * this we drop the tables in the database then re-add
	 * the original tables again. (To start fresh, without
	 * users that we added in testing, for example).
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}


}