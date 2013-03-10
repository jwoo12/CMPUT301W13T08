package com.foodbook.foodbook;

import android.content.Intent;

/**
* <p> A subclass of EditRecipeActivity. </p>
* <p> Only difference is that this will make a new recipe when Save button is clicked, 
* instead of changing an existing recipe. </p>
*     
*     
* @see EditRecipeActivity     
* 
*  
* @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes) 
*  
*  */ 
public class MakeRecipeActivity extends EditRecipeActivity {
	
	/**
	 * This function creates a new recipe from the user input, and then puts it into the local recipe book.
	 */
	@Override
	public void saveButtonClicked() {
		
		// Add to the recipe book and get the recipe id back
		String recipeid = FridgeActivity.myRecipeBook.addRecipe(name, descriptions, instructions, ingredientsArrayList, categoryArrayList);
		
		// make a new intent for viewing the details of the newly created recipe (this recipe)
		Intent recipeDetailsIntent = new Intent();
		recipeDetailsIntent.setClass(this, RecipeDetailsActivity.class);
		recipeDetailsIntent.putExtra("recipeid", recipeid);
		recipeDetailsIntent.putExtra("name", name);
		recipeDetailsIntent.putExtra("descriptions", descriptions);
		recipeDetailsIntent.putExtra("instructions", instructions);
		recipeDetailsIntent.putExtra("ingredients", ingredients);
		recipeDetailsIntent.putExtra("category", category);
		recipeDetailsIntent.putExtra("author", FridgeActivity.myRecipeBook.getAuthor());
		
		// start a new activity
		startActivity(recipeDetailsIntent);
	}
}