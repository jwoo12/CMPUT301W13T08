package com.example.projectrecipebook;

import android.os.Bundle;

import com.database.projectrecipebookdata.DataBaseHandle;

public class MainActivity extends TitleBarOverride {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getApplicationContext();
		setContentView(R.layout.activity_main);
		DataBaseHandle db = new DataBaseHandle(this);
		// databasehelper = new DatabaseSQL(this, "name", null, 1);
		// SQLiteDatabase database = databasehelper.getWritableDatabase();
		
		
	}
	

}
