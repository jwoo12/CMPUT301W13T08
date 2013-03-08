package com.foodbook.foodbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

public class RecipeBookActivity extends TitleBarOverride {

	/**
	 * This class is an activity class for local recipes.
	 * There are three tabs that users can select. "All", "Mine", and "Downloads"
	 * Upon clicking of each tab, the corresponding list of recipes will be displayed.
	 */
	
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
		list_all = (ListView) findViewById(R.id.all_list);
		
		// setup the tabs
		//... do things here
		switchTab(1);
		
		if (showMineOnly) {
			switchTab(2);
		}
	}

	private void switchTab(int i) {
		/**
		 * This function switches the selected tab. 
		 */
		
	}
	
}
