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

	
	// Trying to setup GitHub on my laptop. Don't mind me! kthxbai -Jasmine 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getApplicationContext();
		setContentView(R.layout.activity_main);
		DataBaseHandle db = new DataBaseHandle(this);
		// databasehelper = new DatabaseSQL(this, "name", null, 1);
		// SQLiteDatabase database = databasehelper.getWritableDatabase();
		
		
		
		
		
		
		
		
		// anything below this line will be deleted later
		///////////////////////////////////////////////////
		Button seeWhatICanMake;
		seeWhatICanMake = (Button) findViewById(R.id.myFridgeSeeWhatICanMake);
		seeWhatICanMake.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, RecipeDetailsActivity.class);
				startActivity(intent);
			}
		});
		/////////////////////////////////////////////////
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		return true;

	}

}
