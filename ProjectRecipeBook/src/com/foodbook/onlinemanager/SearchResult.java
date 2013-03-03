package com.foodbook.onlinemanager;

import com.foodbook.foodbook.R;
import com.foodbook.foodbook.TitleBarOverride;
import com.foodbook.foodbook.R.layout;

import android.os.Bundle;

public class SearchResult extends TitleBarOverride {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);
	}
	
}
