package com.foodbook.foodbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * A recipe is a set of instructions that a User can record or save to describe
 * how to prepare a food item.
 * 
 * <p>
 * Recipes contain:
 * </p>
 * <ul>
 * <li>a title</li>
 * <li>a list of ingredients</li>
 * <li>instructions</li>
 * <li>a recipe id</li>
 * <li>a user id</li>
 * <li>photos (optional)</li>
 * <li>category (optional)</li>
 * 
 * </ul>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */

@SuppressWarnings("serial")
public class Recipe implements Serializable {

	private static final String NULL = null;

	private String recipename;
	private String recipeinstructions;
	private String recipeDescriptions;
	private String author; // author will be changable at any time.
	private ArrayList<String> ingredients;
	private ArrayList<String> category; // there can be multiple categories
										// (somewhat similar to the idea of
										// "tagging")
	private String recipeid; // recipeid is permanent, and will be in this
								// format: (userid) + (date in milisecond) +
								// (first character of the food name)
	private String userid; // userid is permanent.

	// userid is perminant id, given to each user, this will be used to identify
	// the owner of recipes.

	/**
	 * Description "getter" for a given recipe
	 * 
	 * return recipeDescriptions a description of the recipe
	 */

	public String getRecipeDescriptions() {
		return recipeDescriptions;
	}

	/**
	 * Description "setter" for a given recipe
	 * 
	 * @param recipeDescriptions
	 *            a description of the recipe
	 */

	public void setRecipeDescriptions(String recipeDescriptions) {
		this.recipeDescriptions = recipeDescriptions;
	}

	/**
	 * Recipe's name "getter"
	 * 
	 * @return recipename the name of the recipe
	 */

	public String getRecipename() {
		return recipename;
	}

	/**
	 * Recipe's name "setter"
	 * 
	 * @param recipename
	 *            the name of the recipe
	 */

	public void setRecipename(String recipename) {
		this.recipename = recipename;
	}

	/**
	 * Recipe's instructions "getter"
	 * 
	 * @return recipeinstructions instructions of the recipe
	 */

	public String getRecipeinstructions() {
		return recipeinstructions;
	}

	/**
	 * 
	 * Recipe's instructions "setter"
	 * 
	 * @param recipeinstructions
	 *            instructions of the recipe
	 */

	public void setRecipeinstructions(String recipeinstructions) {
		this.recipeinstructions = recipeinstructions;
	}

	/**
	 * 
	 * Recipe's author "getter"
	 * 
	 * @param author
	 *            author's author of the recipe
	 */

	public String getauthor() {
		return author;
	}

	/**
	 * 
	 * Recipe's author "setter"
	 * 
	 * @param author
	 *            author's author of the recipe
	 */

	public void setauthor(String author) {
		this.author = author;
	}

	/**
	 * 
	 * Recipe's list of required ingredients "getter"
	 * 
	 * @param ingredients
	 *            a list of ingredients for the recipe
	 */

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	/**
	 * 
	 * Recipe's list of required ingredients "setter"
	 * 
	 * @param ingredients
	 *            a list of ingredients for the recipe
	 */

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * 
	 * Recipe's category "getter"
	 * 
	 * @param category
	 *            the genre of the recipe
	 */

	public ArrayList<String> getCategory() {
		return category;
	}

	/**
	 * 
	 * Recipe's category "setter"
	 * 
	 * @param category
	 *            the genre of the recipe
	 */

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}

	/**
	 * 
	 * If no input is given, a NULL value is assigned
	 * 
	 * @param NULL
	 */

	public static String getNull() {
		return NULL;
	}

	/**
	 * 
	 * Recipe's id "getter"
	 * 
	 * @param recipeid
	 *            the identification code of the recipe
	 */

	public String getRecipeid() {
		return recipeid;
	}

	/**
	 * 
	 * Recipe's id "setter"
	 * 
	 * @param recipeid
	 *            the identification code of the recipe
	 */

	public String getUserid() {
		return userid;
	}

	/**
	 * 
	 * Creates a new Recipe object.
	 * 
	 * @param recipename
	 *            name of recipe
	 * @param recipeDescriptions
	 *            description of recipe
	 * @param recipeInstructions
	 *            recipe instructions
	 * @param ingredients
	 *            list of ingredients needed
	 * @param category
	 *            genre of recipe
	 * @param userid
	 *            id of user
	 * @param author
	 *            name of user
	 */

	public Recipe(String recipename, String recipeDescriptions,
			String recipeInstructions, ArrayList<String> ingredients,
			ArrayList<String> category, String userid, String author) {
		this.recipename = recipename;
		this.recipeinstructions = recipeInstructions;
		this.recipeDescriptions = recipeDescriptions;
		this.author = author;
		this.ingredients = ingredients;
		this.category = category;
		this.recipeid = userid + String.valueOf((new Date()).getTime())
				+ recipename.charAt(0);
		this.userid = userid;
	}

	/**
	 * This method will return list of ingredients as a string, in the following
	 * format: ingr_0\ningr_1\ningr_2 ... ingr_n If there is no ingredients
	 * added yet, then empty string is returned.
	 */

	public String getIngredientsString() {

		if (this.ingredients.size() == 0) {
			return "";
		}

		String listOfIngr = this.ingredients.get(0);
		int i;
		for (i = 1; i < this.ingredients.size(); i++) {
			listOfIngr += ", " + this.ingredients.get(i);
		}

		return listOfIngr;
	}

	/**
	 * This method will return list of categories as a string, in the following
	 * format: categ_0\ncateg_1\ncateg_2 ... categ_n If there is no categories
	 * added yet, then empty string is returned.
	 */

	public String getCategoryString() {

		if (this.category.size() == 0) {
			return "";
		}

		String listOfCateg = this.category.get(0);
		int i;
		for (i = 1; i < this.category.size(); i++) {
			listOfCateg += ", " + this.category.get(i);
		}

		return listOfCateg;

	}
}