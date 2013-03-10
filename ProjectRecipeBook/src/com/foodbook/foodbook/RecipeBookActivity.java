package com.foodbook.foodbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;
/**
	 * <p> This class is an activity class for local recipes. </p>
	 * 
	 * <p> There are three tabs that users can select. "All", "Mine", and "Downloads"
	 * 	   Upon clicking of each tab, the corresponding list of recipes will be displayed. </p>
	 * 
	 * 
	 * 
	 * 
	 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
	 * 
	 */
public class RecipeBookActivity extends TitleBarOverride {

	
	
	private Intent in;
	private boolean showMineOnly;

	private TabHost tabHost;
	private ListView list_all;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_recipe_book);
		
		in = getIntent();
		showMineOnly = in.getBooleanExtra("showMineOnly", false);

		tabHost = (TabHost) findViewById(R.id.recipebookTabHost);
		
		// setup the tabs
		//... do things here
		switchTab(1);
		
		if (showMineOnly) {
			switchTab(2);
		}
	}
	
	/**
	* This function switches the selected tab. 
	*/
	private void switchTab(int i) {
		
		
	}
	
}
