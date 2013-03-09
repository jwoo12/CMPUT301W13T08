package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Context;

/**
 * <p> Fridge is a collection of User created ingredients. </p>
 * 
 * <p> The User is able to record/edit/view/modify ingredients listed within the "Fridge". </p>
 * 
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */



public class Fridge {

	private ArrayList<String> ingredients;
	
	
	/**
	 * 
	 * Creates a new Fridge object.
	 * 
	 * 
	 * @param context the application context
	 */
	
	public Fridge(Context context) {
		// TODO try to load from file
		// if file doesn't exist, make a new ArrayList and save to file.
		this.ingredients = new ArrayList<String>();
	}
	
	/**
	 * Adds an Ingredient (String) to the Fridge object. 
	 * 
	 * 
	 * @param newIngredient name of the ingredient to be added (String)
	 */
	
	
	public void addIngredient(String newIngredient) {
		this.ingredients.add(newIngredient);
	}
	
	
	/**
	 * 
	 * Edit an existing ingredient.
	 * 
	 * @param ingredientIndex element of the ingredient selected
	 * @param newName the newly created name of the ingredient
	 */
	
	public void editIngredient (int ingredientIndex, String newName) {
		this.ingredients.set(ingredientIndex, newName);
	}
	
	/**
	 * 
	 * Remove an ingredient from the Fridge list.
	 * 
	 * @param ingrdIndex element of the ingredient selected
	 */
	
	public void removeIngredientByIndex (int ingrdIndex) {
		this.ingredients.remove(ingrdIndex);
	}
	
	/**
	 * 
	 * Load previous ingredients from file.
	 * 
	 */
	
	public void loadFromFile() {
		
	}
	
	/**
	 * 
	 * Save current ingredients to file.
	 * 
	 * @param context the application's context
	 */
	
	public void saveToFile(Context context) {
		
	}

	/**
	 * 
	 * Display a list of the current ingredients
	 * 
	 * @return the list of ingredients
	 */
	
	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	
	/**
	 * 
	 * Assign an ingredient to be added to the Fridge list
	 * 
	 * @param ingredients assign an ingredient
	 */
	
	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	/**
	 * 
	 * Select an ingredient from the Fridge list 
	 * 
	 * @param index element at which the selected ingredient is located
	 * @return the selected ingredient
	 */
	
	public String getIngredientAtIndex(int index) {
		return this.ingredients.get(index);
	}
}