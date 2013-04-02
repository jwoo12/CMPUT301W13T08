package com.foodbook.foodbook;


import java.util.ArrayList;

/**
 * 
 * This class was made as a suggestion from JDeodorant. 
 * 
 * God Classes from the Fridge class, dealing with editing ingredients, were moved to this class
 * 
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *  
 *
 */


public class FridgeRemove {
	/**
	 * Remove an ingredient from the Fridge list.
	 * @param ingrdIndex element of the ingredient selected
	 */
	public void removeIngredientByIndex(int ingrdIndex,
			ArrayList<String> ingredients) {
		ingredients.remove(ingrdIndex);
	}

	/**
	 * Remove an ingredient from the Fridge list by name instead of ID
	 * @param targetName
	 */
	public void removeIngredientByName(String targetName,
			ArrayList<String> ingredients) {
		ArrayList<String> tmp = ingredients;
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).equals(targetName)) {
				this.removeIngredientByIndex(i, ingredients);
				return;
			}
		}
	}
}