package com.foodbook.onlinemanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * DataBaseController is what a view activity will create and call methods from to control the database functions.
 * 
 * @author Robert
 * 
 */
public class DataBaseController extends Activity {
	DataBaseHandle handle;
	SQLiteDatabase database;
	Context context;

	/**
	 * Controller constructor that will
	 * 
	 * @param context
	 */
	public DataBaseController(Context context) {
		this.context = context;
		this.handle = new DataBaseHandle(context, database);
		database = handle.getWritableDatabase();
		// database.query("Select Name from Recipe where name = poopascoopa");
		// System.out.println(database.getPath());

		createPracticeRows();
		// handle.open();
		Cursor c = handle.getRecipe(1);
		DisplayRecipe(c);
		handle.getSingleNoReturn(1); // /THIS RETURNS ONE VALUE in a recipe,
										// CAN BE CHANGED to RETURN any amount.
		// handle.close();
		List<String> getalllist = new ArrayList<String>();
		handle.getAllRecipes(getalllist);
		// List<String> list = handle.wtf();
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		/*
		 * ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, objectlist); setListAdapter(adapter); }
		 */
		// System.out.println(getalllist);
		// System.out.println(list);
		Log.i("TAGGG", "MAKING HANDLE HERE");

	}

	/**
	 * just being used to show some of the functionality of the database. delete later.
	 */
	public void createPracticeRows() {
		// ---add an assignment---

		// handle.open();
		// long id = handle.insertRecipe("Fruit Loops", "JAE", "cold cereal", "tastes fruity", "Cereals", "milk, dry cereal", "recipeidhere", "UserId1");
		// id = handle.insertRecipe("GLASS SHARDS", "JAS", "HURTY", "BLOODYMESS", "WINDOW", "small pieces, large pieces:", "jazzrecipeid", "jazzwooid");
		// handle.close();

		// ---get all Records---
		/*
		 * handle.open(); Cursor c = handle.getAllRecipes(); if (c.moveToFirst()) { do { DisplayRecipe(c); } while (c.moveToNext()); } handle.close();
		 */
	}

	public void DeleteDataBase() {
		database.execSQL("DROP TABLE IF EXISTS RECIPE_TABLE");
	}

	/**
	 * displays information of recipe or whatever has indeed been added.
	 * 
	 * @param c
	 */
	public void DisplayRecipe(Cursor c) {

		Toast.makeText(context, "id: " + c.getString(0) + "\n" + "Title: " + c.getString(1) + "\n" + "Description: " + c.getString(2), Toast.LENGTH_SHORT).show();

	}

	/**
	 * 
	 * @param view
	 */
	public void addRecipeToView(View view) {

		Intent i = new Intent(); // put what class etc, in the intent
		startActivity(i);
	}

	public ArrayList<String> single(String id) {
		ArrayList<String> output = handle.getSingle(id);
		return output;
	}
}
