package com.foodbook.foodbook;

import com.foodbook.foodbook.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
	
public class ViewRecipeBookActivity extends TitleBarOverride {
	Bundle saver;
	@Override
	public void onCreate (Bundle savedInstanceState) {
		saver = savedInstanceState;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_details);
		RelativeLayout deleteThisone = (RelativeLayout) findViewById(R.id.recipeDetails_shareButtonLayout);
		//deleteThisone.setVisibility(RelativeLayout.GONE);
	}
	

	public void testButtonClick(){
		Intent intent = new Intent(this, MakeRecipeActivity.class);
		startActivity(intent);
	}
}
