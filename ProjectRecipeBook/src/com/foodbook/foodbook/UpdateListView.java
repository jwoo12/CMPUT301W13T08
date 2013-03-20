package com.foodbook.foodbook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 * 
 * EDIT
 * 
 * A view in the MVC architecture. Handles displaying of ingredients to
 * screen
 * 
 * @see ArrayAdapter
 * 
 * @param myFridgeToBeDisplayed
 *            Fridge object being displayed
 * @param listView
 *            Space in which ingredients are listed
 */




public class UpdateListView extends Activity{
	
	
	
	public static void updateListView(Context c, ArrayList<String> sourceList, ListView targetListView) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, R.layout.mytextview, sourceList);
		targetListView.setAdapter(adapter);
	}

}
