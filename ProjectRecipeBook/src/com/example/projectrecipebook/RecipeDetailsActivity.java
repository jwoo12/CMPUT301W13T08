package com.example.projectrecipebook;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

public class RecipeDetailsActivity extends TitleBarOverride {
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_details);
		
		RelativeLayout deleteThisone = (RelativeLayout) findViewById(R.id.recipeDetails_shareButtonLayout);
		//deleteThisone.setVisibility(RelativeLayout.GONE);
	}
	
}
