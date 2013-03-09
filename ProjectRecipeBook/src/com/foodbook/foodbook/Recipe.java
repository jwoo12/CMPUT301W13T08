
package com.foodbook.foodbook;

import java.util.ArrayList;
import java.util.Date;

/**
 * A recipe is a set of instructions that a User can record or “save” to describe how to prepare a food item.
 * 
 * <p> Recipes contain: </p>
 * <ul> 
 * 		<li> a title </li>
 * 		<li> a list of ingredients </li>
 * 		<li> instructions </li>
 * 		<li> a recipe id </li>
 * 		<li> a user id </li>
 * 		<li> photos (optional) </li>
 * 		<li> category (optional) </li>
 * 
 * </ul>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */

public class Recipe{
	
	private static final String NULL = null;
	
	private String recipename;
	private String recipeinstructions;
	private String recipeDescriptions;
	private String username; // username will be changable at any time.
	private ArrayList<String> ingredients;
	private ArrayList<String> category; // there can be multiple categories (somewhat similar to the idea of "tagging")
	final String recipeid; // recipeid is permanent, and will be in this format: (userid) + (date in milisecond) 
	final String userid; // userid is permanent.

	// userid is perminant id, given to each user, this will be used to identify the owner of recipes.

	public String getRecipeDescriptions() {
		return recipeDescriptions;
	}

	public void setRecipeDescriptions(String recipeDescriptions) {
		this.recipeDescriptions = recipeDescriptions;
	}

	public String getRecipename() {
		return recipename;
	}

	public void setRecipename(String recipename) {
		this.recipename = recipename;
	}

	public String getRecipeinstructions() {
		return recipeinstructions;
	}

	public void setRecipeinstructions(String recipeinstructions) {
		this.recipeinstructions = recipeinstructions;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}


	public static String getNull() {
		return NULL;
	}

	public String getRecipeid() {
		return recipeid;
	}

	public String getUserid() {
		return userid;
	}

	
	public Recipe(String recipename,
			String recipeDescriptions,
			String recipeInstructions,
			ArrayList<String> ingredients,
			ArrayList<String> category,
			String userid,
			String username) {
		this.recipename = recipename;
		this.recipeinstructions = recipeInstructions;
		this.recipeDescriptions = recipeDescriptions;
		this.username = username;
		this.ingredients = ingredients;
		this.category = category;
		this.recipeid = userid + String.valueOf((new Date()).getTime());
		this.userid = userid;
	}

	public String getIngredientsString() {
		
		/**
		 * This method will return list of ingredients as a string, in the following format:
		 * ingr_0, ingr_1, ingr_2 ... ingr_n
		 * If there is no ingredients added yet, then empty string is returned.
		 */
		
		if (this.ingredients.size() == 0) {
			return "";
		}
		
		String listOfIngr = this.ingredients.get(0);
		int i;
		for (i=1; i < this.ingredients.size(); i++) {
			listOfIngr += ", " + this.ingredients.get(i);
		}
		
		return listOfIngr;
	}
	
	public String getCategoryString() {
		
		/**
		 * This method will return list of categories as a string, in the following format:
		 * categ_0, categ_1, categ_2 ... categ_n
		 * If there is no categories added yet, then empty string is returned.
		 */
		
		if (this.category.size() == 0) {
			return "";
		}
		
		String listOfCateg = this.category.get(0);
		int i;
		for (i=1; i < this.category.size(); i++) {
			listOfCateg += ", " + this.category.get(i);
		}
		
		return listOfCateg;
	}
}