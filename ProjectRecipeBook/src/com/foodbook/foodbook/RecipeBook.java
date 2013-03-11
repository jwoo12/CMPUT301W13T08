package com.foodbook.foodbook;

import java.util.ArrayList;

import android.util.Log;

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
	private String userid;
	private String author;
	
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
		this.author = "i am the author";
		
	}
	
	/**
	 * 
	 * "Getter" method to return user created recipes
	 * 
	 * @return a list of user created recipes
	 */
	
	public ArrayList<Recipe> getMine() {
		return mine;
	}

	/**
	 * 
	 * "Setter" method to assign user created recipes
	 * 
	 * @param mine a list of user created recipes
	 */
	
	
	public void setMine(ArrayList<Recipe> mine) {
		this.mine = mine;
	}

	/**
	 * "Getter" method to return downloaded recipes
	 * 
	 * 
	 * @return a list of downloaded recipes
	 */
	
	
	public ArrayList<Recipe> getDownloads() {
		return downloads;
	}
	
	/**
	 * 
	 * Takes a given list of recipes and converts them to an array of strings
	 * 
	 * @param inputArray a list of recipes to be converted to strings
	 * @return outputArray an ArrayList of strings representing recipes
	 * 
	 * 
	 */
	
	
	public static ArrayList<String> convertRecipeBookToStringArray(ArrayList<Recipe> inputArray) {
		
		ArrayList<String> outputArray = new ArrayList<String>();
		
		for (Recipe recipe : inputArray) {
			outputArray.add(recipe.getRecipename());
		}
		
		return outputArray;
	}
	
	
	/**
	 * 
	 * Returns the identification code for a recipe
	 * 
	 * 
	 * @param inputArray recipe list
	 * @return a list of recipes
	 */
	
	
	public static ArrayList<String> getAllRecipeid(ArrayList<Recipe> inputArray) {
		ArrayList<String> outputArrayList = new ArrayList<String>();
		
		for (Recipe recipe : inputArray) {
			outputArrayList.add(recipe.getRecipeid());
		}
		
		return outputArrayList;
	}
	
	/**
	 * 
	 * "Getter" method for downloaded recipes
	 * 
	 * @param downloads a list of downloaded recipes
	 */

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
	
	public String addRecipe(String recipename,
			String recipeDescriptions,
			String recipeinstructions,
			ArrayList<String> ingredients,
			ArrayList<String> category) {
		
		Recipe newRecipe = new Recipe(recipename, recipeDescriptions, recipeinstructions, ingredients, category, this.userid, this.author);
		this.mine.add(newRecipe);
		
		return newRecipe.getRecipeid();
	}
	
	/**
	 * "getter" method for a userid
	 * 
	 * @return userid assigned id
	 */
	
	public String getUserid() {
		return userid;
	}

	/**
	 * 
	 * "setter" method for a userid
	 * 
	 * @param userid id to assign
	 */
	
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * 
	 * "getter" method for a recipe's author 
	 * 
	 * @return the recipe's author
	 */
	
	public String getAuthor() {
		return author;
	}

	
	/**
	 * "setter" method for a recipe's author
	 * 
	 * @param author a string to be assigned to an author
	 */
	
	public void setAuthor(String author) {
		this.author = author;
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
			ArrayList<String> category, String recipeid) {
		ArrayList<Recipe> combinedList = this.getRecipeBook();
		for (int i=0; i < combinedList.size(); i++) {
			if (combinedList.get(i).getRecipeid().equals(recipeid)) {
				int offset = i - this.getMine().size();
				if (offset < 0) {
					this.mine.get(i).setRecipename(recipename);
					this.mine.get(i).setRecipeDescriptions(recipeDescriptions);
					this.mine.get(i).setRecipeinstructions(recipeinstructions);
					this.mine.get(i).setIngredients(ingredients);
					this.mine.get(i).setCategory(category);
					this.mine.get(i).setauthor(FridgeActivity.myRecipeBook.getAuthor());
				}
				else {
					this.downloads.get(offset).setRecipename(recipename);
					this.downloads.get(offset).setRecipeDescriptions(recipeDescriptions);
					this.downloads.get(offset).setRecipeinstructions(recipeinstructions);
					this.downloads.get(offset).setIngredients(ingredients);
					this.downloads.get(offset).setCategory(category);
					this.downloads.get(offset).setauthor(FridgeActivity.myRecipeBook.getAuthor());
				}
				return;
			}
		}
	}
	
	/**
	 * 
	 * Converts a recipe to an ArrayList of Strings 
	 * 
	 * 
	 * @param recipeid the id of a recipe to be printed
	 * @return an Arraylist of strings representing a recipe
	 */
	
	public ArrayList<String> getRecipeInfo(String recipeid) {
		ArrayList<String> outputArray = new ArrayList<String>();
		for (Recipe recipe : this.getRecipeBook()) {
			if (recipe.getRecipeid().equals(recipeid)) {
				outputArray.add(recipeid);
				outputArray.add(recipe.getRecipename());
				outputArray.add(recipe.getauthor());
				outputArray.add(recipe.getRecipeDescriptions());
				outputArray.add(recipe.getRecipeinstructions());
				outputArray.add(recipe.getIngredientsString());
				outputArray.add(recipe.getCategoryString());
				outputArray.add(recipe.getUserid());
				return outputArray;
			}
		}
		return null;
	}
	
	protected void deleteById(String recipeid) {
		ArrayList<Recipe> combinedList = this.getRecipeBook();
		for (int i=0; i < combinedList.size(); i++) {
			if (combinedList.get(i).getRecipeid().equals(recipeid)) {
				int offset = i - this.getMine().size();
				if (offset < 0) {
					this.mine.remove(i);
				}
				else {
					this.downloads.remove(offset);
				}
				return;
			}
		}
	}

}
