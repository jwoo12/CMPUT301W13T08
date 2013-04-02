package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Intent;

/**
 *
 * Container for search results.
 * Adopted singleton design patter to achieve global access.
 *
 */

public class ResultsBook {

	public static final ResultsBook resultsBookInstance = new ResultsBook();

	protected ArrayList<Recipe> resultsbooklist;

	/**
	 * 
	 * Constructor. creates a new ArrayList of Recipe
	 * 
	 */
	public ResultsBook() {
		this.resultsbooklist = new ArrayList<Recipe>();
	}

	/**
	 * 
	 * Singleton-based trigger to get the instance of the class object
	 * 
	 * @return
	 */
	
	public static ResultsBook getInstance() {
		return resultsBookInstance;
	}
	
	/**
	 * 
	 * empties the class object. called when a new search is being performed.
	 * 
	 */

	public void reset() {
		resultsBookInstance.resultsbooklist.clear();
	}
	
	/**
	 * 
	 * Returns Recipe obejct of the given recipdID
	 * 
	 * @param recipeid
	 * @return Recipe of the given recipeID
	 */

	protected Recipe searchById(String recipeid) {
		if (resultsBookInstance.resultsbooklist.size() == 0) {
			return null;
		}

		for (Recipe currentRecipe : resultsBookInstance.resultsbooklist) {
			if (currentRecipe.getRecipeid().equals(recipeid)) {
				return currentRecipe;
			}
		}

		return null;

	}
	
	/**
	 * 
	 * Parses intent based on the Recipe, specified by the given recipeID
	 * 
	 * @param recipeid
	 * @return intent for the Recipe of the given recipeID
	 */

	public Intent makeRecipeIntentFromRecipeID(String recipeid) {
		Recipe found = this.searchById(recipeid);
		if (found != null) {
			return RecipeBook.makeRecipeIntent(found);
		}
		return null;
	}
	
	/**
	 *
	 * Copies the Recipe of the given recipeID to local recipe book.
	 * 
	 * @param targetRecipeId
	 */

	public void download(String targetRecipeId) {
		
		if (RecipeBook.getInstance().containsRecipeOfID(targetRecipeId)) {
			// delete the duplicate if there is one.
			RecipeBook.getInstance().deleteById(targetRecipeId);
		}
		
		Recipe found = this.searchById(targetRecipeId);
		
		if (RecipeBook.getInstance().getUserid().equals(found.getUserid())) {
			RecipeBook.getInstance().mine.add(found);
		} else {
			RecipeBook.getInstance().downloads.add(found);			
		}
	}
	
	/**
	 * 
	 * setter method for the list of results
	 * 
	 * @param newResults
	 */

	public void setSearchResults(ArrayList<Recipe> newResults) {
		if (newResults != null) {
			this.resultsbooklist = newResults;
		}
	}
	
	/**
	 * 
	 * Returns pictures belonging to the Recipe of the given recipeID
	 * 
	 * @param recipeid
	 * @return pictures of the Recipe of the given recipeID
	 * @return null, if Recipe not found.
	 */

	public ArrayList<String> getPicturesById(String recipeid) {

		Recipe found = this.searchById(recipeid);

		if (found != null) {
			return found.getPictures();
		}

		return null;

	}

}