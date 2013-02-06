package com.example.projectrecipebook;

import com.database.projectrecipebookdata.DatabaseSQL;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
public class MainActivity extends TitleBarOverride {
	private DatabaseSQL databasehelper;
	public static Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);
        databasehelper = new DatabaseSQL(this, "name", null, 1);
        SQLiteDatabase database = databasehelper.getWritableDatabase();
        database.execSQL("create table Jasmine");
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
        
    }
    
    
    
}
