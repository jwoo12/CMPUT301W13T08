package com.foodbook.foodbook;

import android.content.Intent;

public class MakeRecipeActivity extends EditRecipeActivity {
	@Override
	public void saveButtonClicked() {
		
		/**
		 * A variation of EditRecipeActivity class.
		 * When returning the recipe through intent, it sets the result code to 1.
		 * By convention, code 1 is "making a new recipe" and 2 is "editing an existing recipe".
		 */ 
		
		// read contents of textfields
		readTextfields();
		
		// setup returning intent
		returningIntent = new Intent();
		returningIntent.putExtra("recipe", recipe);
		setResult(1, returningIntent);
		
		// close the activity
		finish();
		
	}
}