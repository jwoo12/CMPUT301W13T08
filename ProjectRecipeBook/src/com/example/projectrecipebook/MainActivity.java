package com.example.projectrecipebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.database.projectrecipebookdata.DataBaseHandle;

public class MainActivity extends TitleBarOverride {
	// GLOBAL VARIABLES

	
	
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
