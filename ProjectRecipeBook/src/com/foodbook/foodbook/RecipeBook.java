package com.foodbook.foodbook;

import java.util.ArrayList;

public class RecipeBook {

	/**
	 * This class is the centre of the Model.
	 * it has the collection of local (and downloaded) recipes, and class methods associated with it.
	 */
	
	ArrayList<Recipe> mine;
	ArrayList<Recipe> downloads;
	private String userid = "dsfuabsiuf3528fb923r72b3eiw"; // TODO this needs to be changed
	private String username = "me!!!!"; // username can be changed TODO this needs to be changed
	
	public RecipeBook() {
		
		/**
		 * This is a constructor method for RecipeBook. It creates new ArrayList of Recipe.
		 */
		
		mine = new ArrayList<Recipe>();
		downloads = new ArrayList<Recipe>();
	}
	
	public ArrayList<Recipe> getMine() {
		return mine;
	}

	public void setMine(ArrayList<Recipe> mine) {
		this.mine = mine;
	}

	public ArrayList<Recipe> getDownloads() {
		return downloads;
	}

	public void setDownloads(ArrayList<Recipe> downloads) {
		this.downloads = downloads;
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
	
	public void addRecipe(String recipename, String recipeinstructions, String recipeDescriptions, ArrayList<String> ingredients,
			ArrayList<String> category) {
		/**
		 * This function receives details of a recipe, and creates/adds a new recipe to the local RecipeBook.
		 */
		Recipe newRecipe = new Recipe(recipename, recipeinstructions, recipeDescriptions, username, ingredients, category, userid);
		mine.add(newRecipe);
	}
	
	public void editRecipe(String recipename, String recipeinstructions, String recipeDescriptions, ArrayList<String> ingredients,
			ArrayList<String> category, int position) {
		
	}
	
}
