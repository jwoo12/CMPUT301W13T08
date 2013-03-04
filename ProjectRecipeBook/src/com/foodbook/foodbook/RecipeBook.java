package com.foodbook.foodbook;

import java.util.ArrayList;

public class RecipeBook {

	/**
	 * This class is the centre of the Model.
	 * it has the collection of local (and downloaded) recipes, and class methods associated with it.
	 */
	
	ArrayList<Recipe> mine;
	ArrayList<Recipe> downloads;
	String userid = "dsfuabsiuf3528fb923r72b3eiw"; // TODO this needs to be changed
	String username = "me!!!!"; // username can be changed TODO this needs to be changed
	
	public RecipeBook() {
		
		/**
		 * This is a constructor method for RecipeBook. It creates a new ArrayList object.
		 */
		
		mine = new ArrayList<Recipe>();
		downloads = new ArrayList<Recipe>();
	}
	
	public ArrayList<Recipe> getRecipeBook() {
		
		/**
		 * This is a getter for the entire RecipeBook.
		 * Since there are two recipe books ("mine" and "downloaded"), this method will simply combine them and return the resulting array.
		 */
		
		// do you guys think sorting is something that has to be done here? - Jaeseo
		
		ArrayList<Recipe> combinedRecipeBook = this.mine;
		combinedRecipeBook.addAll(this.downloads);
		
		return combinedRecipeBook;
		
	}
	
}
