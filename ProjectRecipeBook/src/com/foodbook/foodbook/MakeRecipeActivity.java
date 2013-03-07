package com.foodbook.foodbook;

public class MakeRecipeActivity extends EditRecipeActivity {
	
	/**
	 * A subclass of EditRecipeActivity.
	 * Only difference is that this will make a new recipe when Save button is clicked, instead of changing an existing recipe.
	 */ 
	
	@Override
	public void saveButtonClicked() {
		
		/**
		 * This function creates a new recipe from the user input, and then puts it into the local recipe book.
		 */
		
		// Add to the recipe book
		FridgeActivity.myRecipeBook.addRecipe(name, descriptions, instructions, ingredientsArrayList, categoryArrayList);
		
	}
}