package com.foodbook.onlinemanager;

import com.foodbook.foodbook.R;
import com.foodbook.foodbook.TitleBarOverride;
import com.foodbook.foodbook.R.layout;

import android.os.Bundle;

/**
 * 
 * <p> Queried recipes are displayed </p>
 * 
 * 
 * @see Recipe
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */

public class SearchResult extends TitleBarOverride {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_search_result);
	}
	
}
