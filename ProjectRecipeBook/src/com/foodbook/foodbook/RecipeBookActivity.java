package com.foodbook.foodbook;

import android.content.Intent;
import android.os.Bundle;

public class RecipeBookActivity extends TitleBarOverride {

	private Intent in;
	private boolean showMineOnly;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_recipe_book);
		
		in = getIntent();
		showMineOnly = in.getBooleanExtra("showMineOnly", false);

		
		
	}
}
