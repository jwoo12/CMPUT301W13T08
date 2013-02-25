/**
 * Recipe: This class is the RecipeBook object.  The user
 * can create recipes.  *Add more as we know more*
 * @author rjanes
 * @version 1.0
 */
package com.example.projectrecipebook;

/**
 * This class will be fixed guys, I'm just trying to create an object to get the
 * database set up. But SHOULD OUR RECIPE INPUT BE A STRING OR A TOUCH INTERFACE
 * WITH CUPS TEASPOONS ETC.
 * 
 * @author rjanes
 * 
 */
public class Recipe {
	private String recipename, recipeinstructions, locationinserted;
	private float teaspoon, tablespoon, cup, ratiovalue;
	private int recipeid, numtablespoons, numteaspoons;
	
	// we will allow user to change the display name freely. So we will need to give each user a unique id of some kind.
	// this id will be used for online actions.

	// private Date insert date; SHOULD NOT NEED>> WE'LL SEE.
	/*
	 * public Recipe(){ this.recipename = recipename; this.recipeinstructions =
	 * recipeinstructions; this.locationinserted = locationinserted;
	 * this.teaspoon = teaspoon; this.tablespoon = tablespoon; this.cup = cup;
	 * this.ratiovalue = ratiovalue; this.recipeid = recipeid; }
	 */

	public Recipe() {
		this.recipename = recipename;
	}
}
