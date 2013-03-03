/**
 * Recipe: This class is the RecipeBook object.  The user
 * can create recipes.  *Add more as we know more*
 * @author rjanes
 * @version 1.0
 */
package com.foodbook.foodbook;

import java.util.ArrayList;

/**
 * This class will be fixed guys, I'm just trying to create an object to get the
 * database set up. But SHOULD OUR RECIPE INPUT BE A STRING OR A TOUCH INTERFACE
 * WITH CUPS TEASPOONS ETC.
 * 
 * @author rjanes
 * 
 */
public class Recipe {
	private static final String NULL = null;
	private String recipename, category, recipeinstructions, user;
	private ArrayList<String> ingredients;
	final int recipeid;
	
	// we will allow user to change the display name freely. So we will need to give each user a unique id of some kind.
	// this id will be used for online actions.

	

	public Recipe(int recipeid, String user, String recipename, String category, String recipeinstructions, ArrayList<String> ingredients) {
		
		this.recipename = recipename;
		if (recipename == null){
			recipename = "NoName";
		}
		this.category = category;
		if (category == null){
			category = "NoCategorySelected";
		}
		this.recipeinstructions = recipeinstructions;
		if (recipeinstructions == null){
			recipeinstructions = "NoInstructionsIncluded";
		}
		this.user = user;
		if (user == null){
			user = "Anonymous";
		}
		this.ingredients = ingredients;
		if (ingredients.isEmpty()){
			ingredients.add("No Ingredients Listed");
		}
		this.recipeid = recipeid;
		
	}

	public String getRecipename() {
		return recipename;
	}

	public void setRecipename(String recipename) {
		this.recipename = recipename;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRecipeinstructions() {
		return recipeinstructions;
	}

	public void setRecipeinstructions(String recipeinstructions) {
		this.recipeinstructions = recipeinstructions;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	public int getRecipeid() {
		return recipeid;
	}
	public String toString() {
		return "Recipe [id=" + recipeid + ", user=" + user + "Category= " + category + "name=" + recipename + ", ingredients="
				+ ingredients + ", directions=" + recipeinstructions + "]";
	}
}
