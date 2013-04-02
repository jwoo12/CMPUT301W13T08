package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Intent;

/**
 * 
 * This class was made as a suggestion from JDeodorant.
 * 
 * God Classes from the RecipeBook class, dealing with getters and setters, and searching by recipe IDs, were moved to this class
 * 
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 * 
 */

public class RecipeBookRecipeID {

	/**
	 * 
	 * Given a list of Recipe objects, returns list of strings, each element corresponding to Recipe objects in the input array.
	 * 
	 * @param List
	 *            of Recipe
	 * @return List of recipeid (in the same order)
	 */

	public static ArrayList<String> getRecipeid(ArrayList<Recipe> inputArray) {
		ArrayList<String> outputArrayList = new ArrayList<String>();
		for (Recipe recipe : inputArray) {
			outputArrayList.add(recipe.getRecipeid());
		}
		return outputArrayList;
	}

	/**
	 * 
	 * Given a list of Recipe object, looks to see if the list contains the Recipe with the matching recipeID.
	 * 
	 * @param targetList
	 * @param recipeid
	 * @return true if targetList contains a Recipe with the given recipeID
	 */

	public boolean containsRecipeid(ArrayList<Recipe> targetList, String recipeid) {
		for (Recipe thisRecipe : targetList) {
			if (thisRecipe.getRecipeid().equals(recipeid)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Finds and returns the Recipe object with the matching recipeID.
	 * 
	 * @param recipeid
	 * @param recipeBook
	 * @return The matching Recipe object, or null (if not found)
	 */

	public Recipe searchById(String recipeid, RecipeBook recipeBook) {
		for (Recipe recipe : recipeBook.getRecipeBook()) {
			if (recipe.getRecipeid().equals(recipeid)) {
				return recipe;
			}
		}
		return null;
	}

	/**
	 * Searches recipe book if it contains the Recipe of given recipeID
	 * 
	 * @param recipeidIn
	 * @return true if found
	 * @return false if not found
	 */
	public boolean containsRecipeOfID(String recipeidIn, RecipeBook recipeBook) {
		return containsRecipeid(recipeBook.getRecipeBook(), recipeidIn);
	}

	/**
	 * 
	 * Finds and parses intent for a recipe of given recipeID
	 * 
	 * @param recipeid
	 * @param recipeBook
	 * @return intent for the corresponding Recipe
	 * @return null, if not found
	 */
	
	public Intent makeRecipeIntentFromRecipeID(String recipeid, RecipeBook recipeBook) {
		Recipe found = searchById(recipeid, recipeBook);
		if (found != null) {
			return RecipeBook.makeRecipeIntent(found);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * Returns array of pictures given the ID and the RecipeBook object that may contain the Recipe.
	 * 
	 * @param recipeid
	 * @param recipeBook
	 * @return pictures
	 * @return null, if not found
	 */

	public ArrayList<String> getPicturesById(String recipeid, RecipeBook recipeBook) {
		Recipe found = searchById(recipeid, recipeBook);
		if (found != null) {
			return found.getPictures();
		}
		return new ArrayList<String>();
	}
	
	/**
	 * 
	 * Updates pictures for the Recipe of the given recipeID
	 * 
	 * @param recipeid
	 * @param newpic
	 * @param recipeBook
	 */

	public void updatePic(String recipeid, ArrayList<String> newpic, RecipeBook recipeBook) {
		Recipe found = this.searchById(recipeid, recipeBook);
		found.setPictures(newpic);
	}

	/**
	 * Remove a recipe by finding it with its ID.
	 * 
	 * This method was moved due to type envy suggested by JDeodorant
	 * 
	 * @param recipeid
	 *            recipe to be deleted
	 * @param mine
	 * @param downloads
	 * @param recipeBook
	 */
	public void deleteById(String recipeid, ArrayList<Recipe> mine, ArrayList<Recipe> downloads, RecipeBook recipeBook) {
		Recipe found = searchById(recipeid, recipeBook);
		if (containsRecipeid(mine, recipeid)) {
			mine.remove(found);
		} else {
			downloads.remove(found);
		}
	}
}