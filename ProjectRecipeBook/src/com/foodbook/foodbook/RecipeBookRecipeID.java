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
	 * Returns the identification code for a recipe
	 * @param inputArray recipe list
	 * @return  a list of recipes
	 */
	public static ArrayList<String> getRecipeid(ArrayList<Recipe> inputArray) {
		ArrayList<String> outputArrayList = new ArrayList<String>();
		for (Recipe recipe : inputArray) {
			outputArrayList.add(recipe.getRecipeid());
		}
		return outputArrayList;
	}

	public boolean containsRecipeid(ArrayList<Recipe> targetList,
			String recipeid) {
		for (Recipe thisRecipe : targetList) {
			if (thisRecipe.getRecipeid().equals(recipeid)) {
				return true;
			}
		}
		return false;
	}

	public Recipe searchById(String recipeid, RecipeBook recipeBook) {
		for (Recipe recipe : recipeBook.getRecipeBook()) {
			if (recipe.getRecipeid().equals(recipeid)) {
				return recipe;
			}
		}
		return null;
	}

	/**
	 * Searches recipe book if it contains the given recipe ID
	 * @param recipeidIn
	 * @return  true if found
	 * @return  false if not found
	 */
	public boolean containsRecipeOfID(String recipeidIn, RecipeBook recipeBook) {
		for (Recipe eachRecipe : recipeBook.getRecipeBook()) {
			if (eachRecipe.getRecipeid().equals(recipeidIn)) {
				return true;
			}
		}
		return false;
	}

	public Intent makeRecipeIntentFromRecipeID(String recipeid,
			RecipeBook recipeBook) {
		Recipe found = searchById(recipeid, recipeBook);
		if (found != null) {
			return RecipeBook.makeRecipeIntent(found);
		} else {
			return null;
		}
	}

	public ArrayList<String> getPicturesById(String recipeid,
			RecipeBook recipeBook) {
		Recipe found = searchById(recipeid, recipeBook);
		if (found != null) {
			return found.getPictures();
		}
		return new ArrayList<String>();
	}

	public void updatePic(String recipeid, ArrayList<String> newpic,
			RecipeBook recipeBook) {
		Recipe found = this.searchById(recipeid, recipeBook);
		found.setPictures(newpic);
	}

	/**
	 * Remove a recipe by finding it with its ID
	 * 
	 * 
	 * This method was moved due to type envy suggested by JDeodorant 
	 * 
	 * 
	 * @param recipeid recipe to be deleted
	 * @param mine
	 * @param downloads
	 * @param recipeBook
	 */
	public void deleteById(String recipeid, ArrayList<Recipe> mine,
			ArrayList<Recipe> downloads, RecipeBook recipeBook) {
		Recipe found = searchById(recipeid, recipeBook);
		if (containsRecipeid(mine, recipeid)) {
			mine.remove(found);
		} else {
			downloads.remove(found);
		}
	}
}