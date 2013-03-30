package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Intent;

/**
 * <p>
 * A subclass of EditRecipeActivity.
 * </p>
 * <p>
 * Only difference is that this will make a new recipe when Save button is clicked, instead of changing an existing recipe.
 * </p>
 * 
 * 
 * @see EditRecipeActivity
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 * */
public class MakeRecipeActivity extends EditRecipeActivity {

	@Override
	protected void readIntent() {
		pictures = new ArrayList<String>();
	}

	@Override
	protected void updateTextFields() {
		// do nothing because this is a new recipe.
	}

	/**
	 * This function creates a new recipe from the user input, and then puts it into the local recipe book.
	 */

	@Override
	public void sendRecipeInfoToRecipeBook() {

		// Add to the recipe book and get the recipe id back
		String recipeidNew = RecipeBook.getInstance().addRecipe(name, desc, inst, ingredientsArrayList, categoryArrayList, pictures);
		recipeid = recipeidNew;

	}

}