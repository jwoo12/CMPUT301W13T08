package com.database.projectrecipebookdata;

import com.example.projectrecipebook.MainActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQL extends SQLiteOpenHelper{
	DatabaseSQL databasehelper;
	FoodBookData database;
	public DatabaseSQL(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		 databasehelper = new DatabaseSQL(MainActivity.context, "name", null, 1);
		 database = new FoodBookData(databasehelper.getWritableDatabase());
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}


}