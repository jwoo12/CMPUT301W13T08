package com.foodbook.foodbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import android.util.Log;

/**
 * A recipe is a set of instructions that a User can record or save to describe how to prepare a food item.
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
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */

@SuppressWarnings("serial")
public class Recipe implements Serializable {

	private static final String NULL = null;

	private String name;
	private String inst;
	private String desc;
	private String author; // author will be changable at any time.
	private ArrayList<String> ingredients;
	private ArrayList<String> category; // there can be multiple categories (somewhat similar to the idea of "tagging")
	private String recipeid; // recipeid is permanent, and will be in this format: (userid) + (date in milisecond) + (first character of the food name)
	private String userid; // userid is permanent.
	private ArrayList<String> pictures;
	
	private ArrayList<String> tags = new ArrayList<String>();

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

	public Recipe(String recipename, String recipeDescriptions, String recipeInstructions, ArrayList<String> ingredients, ArrayList<String> category, String userid, String author, ArrayList<String> pics) {
		this.name = recipename;
		this.inst = recipeInstructions;
		this.desc = recipeDescriptions;
		this.author = author;
		this.ingredients = ingredients;
		this.category = category;
		this.recipeid = userid + String.valueOf((new Date()).getTime()) + recipename.charAt(0);
		this.userid = userid;
		this.pictures = pics;
	}
	

	/**
	 * Description "getter" for a given recipe
	 * 
	 * return recipeDescriptions a description of the recipe
	 */

	public String getDesc() {
		return desc;
	}

	/**
	 * Description "setter" for a given recipe
	 * 
	 * @param recipeDescriptions
	 *            a description of the recipe
	 */

	public void setDesc(String recipeDescriptions) {
		this.desc = recipeDescriptions;
	}

	/**
	 * Recipe's name "getter"
	 * 
	 * @return recipename the name of the recipe
	 */

	public String getName() {
		return name;
	}

	/**
	 * Recipe's name "setter"
	 * 
	 * @param recipename
	 *            the name of the recipe
	 */

	public void setName(String recipename) {
		this.name = recipename;
	}

	/**
	 * Recipe's instructions "getter"
	 * 
	 * @return recipeinstructions instructions of the recipe
	 */

	public String getInst() {
		return inst;
	}

	/**
	 * 
	 * Recipe's instructions "setter"
	 * 
	 * @param recipeinstructions
	 *            instructions of the recipe
	 */

	public void setInst(String recipeinstructions) {
		this.inst = recipeinstructions;
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

	public void setAuthor(String author) {
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
	 * Getter method for the pictures
	 * 
	 * @return pictures
	 */
	
	public ArrayList<String> getPictures() {
		return pictures;
	}
	
	/**
	 * 
	 * setter method for the pictures
	 * 
	 * @param pictures
	 */

	public void setPictures(ArrayList<String> pictures) {
		this.pictures = pictures;
	}

	/**
	 * This method will return list of ingredients as a string
	 */

	public String getIngredientsString() {

		if (this.ingredients.size() == 0) {
			return "";
		} else {
			return StringOperations.intoOneString(ingredients, ", ");
		}
	}

	/**
	 * This method will return list of category as a string
	 */

	public String getCategoryString() {

		if (this.category.size() == 0) {
			return "";
		} else {
			return StringOperations.intoOneString(category, ", ");
		}

	}
	
	/**
	 * 
	 * Generates tagging when being uploaded online.
	 * This allows users to search recipes by title, categories, and/or ingredients.
	 * 
	 */
	
	public void generateTags() {
		this.tags.clear();
		
		ArrayList<String> sourcesOfTags = new ArrayList<String>();
		sourcesOfTags.add(this.author);
		sourcesOfTags.add(this.getName());
		sourcesOfTags.addAll(this.category);
		sourcesOfTags.addAll(this.getIngredients());
		
		HashSet<String> tmpSet = new HashSet<String>();
		
		for (String item : sourcesOfTags) {
			tmpSet.add(item);
			ArrayList<String> itemSplit = new ArrayList<String>(Arrays.asList(item.split(" ")));
			for (String subitem : itemSplit) {
				tmpSet.add(subitem);
			}
		}
		
		this.tags.addAll(tmpSet);
		
		
		Log.v("tests", "hashSet" + tags.toString());
		
	}
}