package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Context;

/**
 * <p>
 * Fridge is a collection of User created ingredients.
 * </p>
 * 
 * <p>
 * The User is able to record/edit/view/modify ingredients listed within the "Fridge".
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */

public class Fridge {

	private FridgeRemove fridgeRemove = new FridgeRemove();
	private FridgeIO fridgeIO = new FridgeIO();
	private static final Fridge fridgeInstance = new Fridge();;
	private ArrayList<String> ingredients;
	/**
	 * 
	 * Creates a new Fridge object.
	 * 
	 * 
	 * @param context
	 *            the application context
	 */

	private Fridge() {
		this.ingredients = new ArrayList<String>();
	}
	
	/**
	 * 
	 * Returns an instance of Fridge object. Follows Singleton design pattern.
	 * 
	 * @return Fridge object
	 */

	public static Fridge getInstance() {
		return fridgeInstance;
	}

	/**
	 * Adds an Ingredient (String) to the Fridge object.
	 * 
	 * 
	 * @param newIngredient
	 *            name of the ingredient to be added (String)
	 */

	public void addIngredient(String newIngredient) {
		this.ingredients.add(newIngredient);
	}

	/**
	 * 
	 * Edits an existing ingredient.
	 * 
	 * @param ingredientIndex
	 *            element of the ingredient selected
	 * @param newName
	 *            the newly created name of the ingredient
	 */

	public void editIngredient(int ingredientIndex, String newName) {
		this.ingredients.set(ingredientIndex, newName);
	}

	/**
	 * 
	 * Finds ingredient by name, and changes it to the new name.
	 * 
	 * @param oldName
	 *            name to be changed
	 * @param newName
	 *            new name to be assigned
	 */

	public void editIngredientByName(String oldName, String newName) {
		ArrayList<String> tmp = this.getIngredients();
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).equals(oldName)) {
				this.editIngredient(i, newName);
				return;
			}
		}
	}

	/**
	 * 
	 * Remove an ingredient from the ingredient list.
	 * 
	 * @param ingrdIndex
	 *            element of the ingredient selected
	 */

	public void removeIngredientByIndex(int ingrdIndex) {
		fridgeRemove.removeIngredientByIndex(ingrdIndex, ingredients);
	}

	/**
	 * 
	 * Remove an ingredient from the Fridge list by name instead of ID
	 * 
	 * @param targetName
	 */

	public void removeIngredientByName(String targetName) {
		fridgeRemove.removeIngredientByName(targetName, ingredients);
	}

	/**
	 * 
	 * Load previous ingredients from file.
	 * 
	 */

	public void loadFromFile(Context context) {

		fridgeIO.loadFromFile(context, this);

	}

	/**
	 * 
	 * Save current ingredients to file.
	 * 
	 * @param context
	 *            the application's context
	 */

	public void saveToFile(Context context) {

		fridgeIO.saveToFile(context, ingredients);

	}

	/**
	 * 
	 * Returns a list of the current ingredients
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
	 * @param ingredients
	 *            assign an ingredient
	 */

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * 
	 * Select an ingredient from the Fridge list
	 * 
	 * @param index
	 *            element at which the selected ingredient is located
	 * @return the selected ingredient
	 */

	public String getIngredientAtIndex(int index) {
		return this.ingredients.get(index);
	}

	/**
	 * Remove all items from the fridge list
	 * 
	 */

	public void clearFridge() {
		this.ingredients.clear();
	}

}