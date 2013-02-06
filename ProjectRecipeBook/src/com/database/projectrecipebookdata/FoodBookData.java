package com.database.projectrecipebookdata;

import android.database.sqlite.SQLiteDatabase;

public class FoodBookData {
	SQLiteDatabase database;
	public FoodBookData(SQLiteDatabase newdatabase){
		this.database = newdatabase;
	}

	public void insert(){
		
		//this.database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		
		
	}
}
