package com.example.projectrecipebook;
import com.database.projectrecipebookdata.DataBaseHandle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
public class MainActivity extends TitleBarOverride {
	//GLOBAL VARIABLES
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationContext();
        setContentView(R.layout.activity_main);
        DataBaseHandle db = new DataBaseHandle(this);
        //databasehelper = new DatabaseSQL(this, "name", null, 1);
        //SQLiteDatabase database = databasehelper.getWritableDatabase();
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
        
    }
    
    
    
}
