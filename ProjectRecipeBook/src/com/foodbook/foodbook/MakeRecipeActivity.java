package com.foodbook.foodbook;


public class MakeRecipeActivity extends EditRecipeActivity {
	
	/**
	 * A subclass of EditRecipeActivity.
	 * Only difference is that this will make a new recipe when Save button is clicked, instead of changing an existing recipe.
	 */ 
	
	@Override
	public void saveButtonClicked() {
		
		// read contents of textfields
		readTextfields();
		
		// Add to the recipe book
		// Recipe.addNewRecipe(name, blahblah);
		
		// close the activity
		finish();
		
	}
}