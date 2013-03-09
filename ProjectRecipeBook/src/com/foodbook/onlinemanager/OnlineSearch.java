package com.foodbook.onlinemanager;

import com.foodbook.foodbook.R;
import com.foodbook.foodbook.TitleBarOverride;

import android.os.Bundle;


/**
 * 
 * <p> Implementation for the ability to query recipes </p>
 * 
 * 
 * @see Recipe
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */

public class OnlineSearch extends TitleBarOverride {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_search);
	}
	
}

