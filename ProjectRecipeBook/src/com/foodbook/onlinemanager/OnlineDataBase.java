package com.foodbook.onlinemanager;

import java.util.ArrayList;

import com.foodbook.foodbook.Recipe;


/**
 * 
 * HTTP and web service implementation
 * 
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */


public class OnlineDataBase {

	/**
	 * Given the keyword, returns a list of recipes that match
	 * 
	 * @param keyword searchable term
	 * @return list of relevant recipes
	 */
	
	public static ArrayList<Recipe> searchByKeyword(String keyword) {
		ArrayList<Recipe> ouput = new ArrayList<Recipe>();
		return ouput;
	}
	
	/**
	 * Iterate through the web sevice to find recipes that can be made from what is in the fridge
	 * 
	 * @param ingredients
	 * @return
	 */

	public static ArrayList<Recipe> searchByIngredientsOnline(
			ArrayList<String> ingredients) {
		ArrayList<Recipe> ouput = new ArrayList<Recipe>();
		return ouput;
	}

}
