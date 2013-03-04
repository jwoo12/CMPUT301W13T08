/**
 * Recipe: This class is the RecipeBook object.  The user
 * can create recipes.  *Add more as we know more*
 * @author rjanes
 * @version 1.0
 */
package com.foodbook.foodbook;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class will be fixed guys, I'm just trying to create an object to get the
 * database set up. But SHOULD OUR RECIPE INPUT BE A STRING OR A TOUCH INTERFACE
 * WITH CUPS TEASPOONS ETC.
 * 
 * @author rjanes
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

	public Recipe(String userid, String username) {
		this.recipename = "";
		this.recipeinstructions = "";
		this.recipeDescriptions = "";
		this.username = username;
		this.ingredients = new ArrayList<String>();
		this.category = new ArrayList<String>();
		this.userid = userid;
		this.recipeid = userid + String.valueOf((new Date()).getTime());
	}
	
	
	
	public Recipe(String recipename, String recipeinstructions,
			String recipeDescriptions, String username,
			ArrayList<String> ingredients, ArrayList<String> category,
			String recipeid, String userid) {
		super();
		this.recipename = recipename;
		this.recipeinstructions = recipeinstructions;
		this.recipeDescriptions = recipeDescriptions;
		this.username = username;
		this.ingredients = ingredients;
		this.category = category;
		this.recipeid = recipeid;
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