package com.foodbook.onlinemanager;

import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * SQLite implementation
 * 
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */

public class FoodBookData {
	SQLiteDatabase database;

	private FoodBookData(SQLiteDatabase newdatabase) {
		this.database = newdatabase;
	}

	public void insert() {

		// this.database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);

	}

	public void createTables(SQLiteDatabase db) {

	}

}