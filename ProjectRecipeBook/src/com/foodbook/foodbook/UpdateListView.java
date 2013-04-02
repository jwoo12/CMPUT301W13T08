package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 * A view in the MVC architecture. Handles displaying of ingredients to screen
 * 
 * @see ArrayAdapter
 * 
 * @param myFridgeToBeDisplayed
 *            Fridge object being displayed
 * @param listView
 *            Space in which ingredients are listed
 */

public class UpdateListView {
	
	/**
	 * Creates an adapter to update the contents of given ListView.
	 * 
	 * @param c context of the activity that calls this method from
	 * @param sourceList
	 * @param targetListView
	 * 
	 */

	public static void updateListView(Context c, ArrayList<String> sourceList, ListView targetListView) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, R.layout.mytextview, sourceList);
		targetListView.setAdapter(adapter);
	}

}
