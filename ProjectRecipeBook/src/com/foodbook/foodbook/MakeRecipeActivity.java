package com.foodbook.foodbook;


public class MakeRecipeActivity extends EditRecipeActivity {
	@Override
	public void saveButtonClicked() {
		
		/**
		 * A subclass of EditRecipeActivity.
		 * Only difference is that this will make a new recipe when Save button is clicked, instead of changing an existing recipe.
		 */ 
		
		// read contents of textfields
		readTextfields();
		
		// Make
		
		
		// close the activity
		finish();
		
	}
}