package com.foodbook.foodbook;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;

/**
 * <p>
 * Fridge is a collection of User created ingredients.
 * </p>
 * 
 * <p>
 * The User is able to record/edit/view/modify ingredients listed within the
 * "Fridge".
 * </p>
 * 
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */

@SuppressWarnings("unchecked")
public class Fridge {

	private static final Fridge fridgeInstance = new Fridge();;
	private ArrayList<String> ingredients;
	private final String fridgeFilename = "fridge.sav";

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
	 * Edit an existing ingredient.
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
	 * change an ingredient by name instead of ID
	 * 
	 * @param oldName name to be changed
	 * @param newName new name to be assigned
	 */
	
	public void editIngredientByName (String oldName, String newName) {
		ArrayList<String> tmp = this.getIngredients();
		for (int i=0; i < tmp.size(); i++) {
			if (tmp.get(i).equals(oldName)) {
				this.editIngredient(i, newName);
				return;
			}
		}
	}

	/**
	 * 
	 * Remove an ingredient from the Fridge list.
	 * 
	 * @param ingrdIndex
	 *            element of the ingredient selected
	 */

	public void removeIngredientByIndex(int ingrdIndex) {
		this.ingredients.remove(ingrdIndex);
	}
	
	/**
	 * 
	 * Remove an ingredient from the Fridge list by name instead of ID
	 * 
	 * @param targetName
	 */
	
	
	public void removeIngredientByName (String targetName) {
		ArrayList<String> tmp = this.getIngredients();
		for (int i=0; i < tmp.size(); i++) {
			if (tmp.get(i).equals(targetName)) {
				this.removeIngredientByIndex(i);
				return;
			}
		}
	}

	/**
	 * 
	 * Load previous ingredients from file.
	 * 
	 */

	public void loadFromFile(Context context) {

		try {
			ObjectInputStream in = new ObjectInputStream(
					context.openFileInput(fridgeFilename));
			ArrayList<String> myFridge = (ArrayList<String>) in.readObject();
			this.setIngredients(myFridge);
		} catch (EOFException eof) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * Save current ingredients to file.
	 * 
	 * @param context
	 *            the application's context
	 */

	public void saveToFile(Context context) {

        try {
            @SuppressWarnings("static-access")
            ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput(fridgeFilename, context.MODE_PRIVATE));
            out.writeObject(this.ingredients);
            out.close();
        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }  
        
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