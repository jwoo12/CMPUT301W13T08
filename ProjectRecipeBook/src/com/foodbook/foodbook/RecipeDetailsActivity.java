package com.foodbook.foodbook;

import android.content.Intent;
import android.os.Bundle;

public class RecipeDetailsActivity extends TitleBarOverride {
	
	private Intent in;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_details);
		
		
	}
}
