package com.database.projectrecipebookdata;

import android.database.sqlite.SQLiteDatabase;

public class FoodBookData {
	SQLiteDatabase database;
	private FoodBookData(SQLiteDatabase newdatabase){
		this.database = newdatabase;
	}

	public void insert(){
		
		//this.database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
	
	}
	
	public void createTables(SQLiteDatabase db){
	
	}

}