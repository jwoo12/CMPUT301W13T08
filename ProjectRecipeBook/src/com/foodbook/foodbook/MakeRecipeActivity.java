package com.foodbook.foodbook;
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
	
	
	
	@Override
	public void saveButtonClicked() {
		
		/**
		 * This function creates a new recipe from the user input, and then puts it into the local recipe book.
		 */
		
		// Add to the recipe book
		FridgeActivity.myRecipeBook.addRecipe(name, descriptions, instructions, ingredientsArrayList, categoryArrayList);
		
	}
}