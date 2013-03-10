package com.foodbook.foodbook;

import java.util.ArrayList;

	/**
	 * <p> RecipeBook is a collection of local and downloaded recipes. </p>
	 * 
	 * <p> This class is the centre of the Model. 
	 *     It has the collection of local (and downloaded) recipes, and class methods associated with it. </p>
	 *  
	 * 
	 * @see Recipe
	 * 
	 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
	 * 
	 * 
	 * 
	 * 
	 */

public class RecipeBook {

	
	
	ArrayList<Recipe> mine;
	ArrayList<Recipe> downloads;
	private String userid; // TODO this needs to be changed
	private String username; // username can be changed TODO this needs to be changed
	
	/**
	* This is a constructor method for RecipeBook. It creates new ArrayList of Recipe.
	* 
	* 
	* 
	*/
	public RecipeBook() {
		
		mine = new ArrayList<Recipe>();
		downloads = new ArrayList<Recipe>();
		this.userid = "dsfuabsiuf3528fb923r72b3eiw";
		this.username = "example name";
		
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
	
	public static ArrayList<String> convertRecipeBookToStringArray(ArrayList<Recipe> inputArray) {
		
		ArrayList<String> outputArray = new ArrayList<String>();
		
		for (Recipe recipe : inputArray) {
			outputArray.add(recipe.getRecipename());
		}
		
		return outputArray;
	}
	

	public void setDownloads(ArrayList<Recipe> downloads) {
		this.downloads = downloads;
	}
	/**
	* This is a getter for the entire RecipeBook.
	* Since there are two recipe books ("mine" and "downloaded"),
	* this method will simply combine them and return the resulting array.
	* 
	* 
	* @return List of downloaded and local Recipes 
	*/
	public ArrayList<Recipe> getRecipeBook() {
		
		
		
		// do you guys think sorting is something that has to be done here? - Jaeseo
		
		ArrayList<Recipe> combinedRecipeBook = new ArrayList<Recipe>();
		combinedRecipeBook.addAll(this.mine);
		combinedRecipeBook.addAll(this.downloads);
		
		return combinedRecipeBook;
		
	}
	
	/**
	* This function receives details of a recipe, and creates/adds a new recipe to the local RecipeBook.
	* 
	* @param recipename name of recipe
	* @param recipeDescriptions description of recipe
	* @param recipeinstructions recipe instructions
	* @param ingredients list of ingredients
	* @param category genre
	* 
	*/
	
	public void addRecipe(String recipename,
			String recipeDescriptions,
			String recipeinstructions,
			ArrayList<String> ingredients,
			ArrayList<String> category) {
		
		Recipe newRecipe = new Recipe(recipename, recipeDescriptions, recipeinstructions, ingredients, category, this.userid, this.username);
		this.mine.add(newRecipe);
	}
	
	/**
	* This methods is used to modify existing recipes in the recipe book.
	* Given the details about the recipe and the recipe ID, this method will apply changes.
	* @param recipename name of recipe
	* @param recipeDescriptions description of recipe
	* @param recipeinstructions recipe instructions
	* @param ingredients list of ingredients
	* @param category genre
	*/
	public void editRecipe(String recipename, String recipeDescriptions, String recipeinstructions, ArrayList<String> ingredients,
			ArrayList<String> category, int position) {
		
	}
	
}
