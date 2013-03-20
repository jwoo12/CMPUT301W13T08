package com.foodbook.foodbook;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * <p>
 * RecipeBook is a collection of local and downloaded recipes.
 * </p>
 * 
 * <p>
 * This class is the centre of the Model. It has the collection of local (and
 * downloaded) recipes, and class methods associated with it.
 * </p>
 * 
 * 
 * @see Recipe
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 * 
 * 
 * 
 */

@SuppressWarnings("serial")
public class RecipeBook implements Serializable {

	private ArrayList<Recipe> mine;
	private ArrayList<Recipe> downloads;
	private String userid;
	private String author;

	public static final String RecipeBookFilename = "recipe.sav";

	/**
	 * This is a constructor method for RecipeBook. It creates new ArrayList of
	 * Recipe.
	 * 
	 * 
	 * 
	 */
	public RecipeBook() {

		this.mine = new ArrayList<Recipe>();
		this.downloads = new ArrayList<Recipe>();
		this.userid = this.generateNewUserid();
		this.author = "noname";

	}

	/**
	 * 
	 * "Getter" method to return user created recipes
	 * 
	 * @return a list of user created recipes
	 */

	public ArrayList<Recipe> getMine() {
		return mine;
	}

	/**
	 * 
	 * "Setter" method to assign user created recipes
	 * 
	 * @param mine
	 *            a list of user created recipes
	 */

	public void setMine(ArrayList<Recipe> mine) {
		this.mine = mine;
	}

	/**
	 * "Getter" method to return downloaded recipes
	 * 
	 * 
	 * @return a list of downloaded recipes
	 */

	public ArrayList<Recipe> getDownloads() {
		return downloads;
	}

	/**
	 * 
	 * Takes a given list of recipes and converts them to an array of strings
	 * 
	 * @param inputArray
	 *            a list of recipes to be converted to strings
	 * @return outputArray an ArrayList of strings representing recipes
	 * 
	 * 
	 */

	public static ArrayList<String> getNames(
			ArrayList<Recipe> inputArray) {

		ArrayList<String> outputArray = new ArrayList<String>();

		for (Recipe recipe : inputArray) {
			outputArray.add(recipe.getRecipename());
		}

		return outputArray;
	}

	/**
	 * 
	 * Returns the identification code for a recipe
	 * 
	 * 
	 * @param inputArray
	 *            recipe list
	 * @return a list of recipes
	 */

	public static ArrayList<String> getRecipeid(ArrayList<Recipe> inputArray) {
		ArrayList<String> outputArrayList = new ArrayList<String>();

		for (Recipe recipe : inputArray) {
			outputArrayList.add(recipe.getRecipeid());
		}

		return outputArrayList;
	}

	/**
	 * 
	 * "Getter" method for downloaded recipes
	 * 
	 * @param downloads
	 *            a list of downloaded recipes
	 */

	public void setDownloads(ArrayList<Recipe> downloads) {
		this.downloads = downloads;
	}

	/**
	 * This is a getter for the entire RecipeBook. Since there are two recipe
	 * books ("mine" and "downloaded"), this method will simply combine them and
	 * return the resulting array.
	 * 
	 * 
	 * @return List of downloaded and local Recipes
	 */
	public ArrayList<Recipe> getRecipeBook() {

		// do you guys think sorting is something that has to be done here? -
		// Jaeseo

		ArrayList<Recipe> combinedRecipeBook = new ArrayList<Recipe>();
		combinedRecipeBook.addAll(this.mine);
		combinedRecipeBook.addAll(this.downloads);

		return combinedRecipeBook;

	}

	/**
	 * This function receives details of a recipe, and creates/adds a new recipe
	 * to the local RecipeBook.
	 * 
	 * @param recipename
	 *            name of recipe
	 * @param recipeDescriptions
	 *            description of recipe
	 * @param recipeinstructions
	 *            recipe instructions
	 * @param ingredients
	 *            list of ingredients
	 * @param category
	 *            genre
	 * 
	 */

	public String addRecipe(String recipename, String recipeDescriptions,
			String recipeinstructions, ArrayList<String> ingredients,
			ArrayList<String> category, ArrayList<Bitmap> bmpArray) {

		Recipe newRecipe = new Recipe(recipename, recipeDescriptions,
				recipeinstructions, formatStringArrayListEntries(ingredients),
				formatStringArrayListEntries(category), this.userid,
				this.author, bmpArray);
		this.mine.add(newRecipe);

		return newRecipe.getRecipeid();
	}

	/**
	 * "getter" method for a userid
	 * 
	 * @return userid assigned id
	 */

	public String getUserid() {
		return userid;
	}

	/**
	 * 
	 * "setter" method for a userid
	 * 
	 * @param userid
	 *            id to assign
	 */

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * 
	 * "getter" method for a recipe's author
	 * 
	 * @return the recipe's author
	 */

	public String getAuthor() {
		return author;
	}

	/**
	 * "setter" method for a recipe's author
	 * 
	 * @param author
	 *            a string to be assigned to an author
	 */

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 
	 * This function formats ArrayList of String so that no entry has duplicate
	 * whitespaces/newline charactesr/etc... Also, this function removes empty
	 * entries.
	 * 
	 * 
	 * @param input
	 *            The ArrayList to be formatted
	 * @return
	 */

	public static ArrayList<String> formatStringArrayListEntries(
			ArrayList<String> input) {

		ArrayList<String> output = new ArrayList<String>();

		for (String inputEntry : input) {
			String outputEntry = inputEntry;
			outputEntry = outputEntry.replace("\n", "");
			while (outputEntry.contains("  ")) {
				outputEntry = outputEntry.replace("  ", " ");
			}
			outputEntry = outputEntry.trim();

			if (!outputEntry.equals("")) {
				output.add(outputEntry);
			}
		}

		return output;

	}

	/**
	 * 
	 * Change details of a recipe by finding the ID then update to new values
	 * 
	 * @param recipename
	 *            name to be changed
	 * @param recipeDescriptions
	 *            description to be changed
	 * @param recipeinstructions
	 *            instructions to be changed
	 * @param ingredients
	 *            ingredients to be changed
	 * @param category
	 *            category to be changed
	 * @param recipeid
	 */

	public void editRecipe(String recipename, String recipeDescriptions,
			String recipeinstructions, ArrayList<String> ingredients,
			ArrayList<String> category, String recipeid) {
		
		ArrayList<Recipe> combinedList = this.getRecipeBook();
		for (int i = 0; i < combinedList.size(); i++) {
			if (combinedList.get(i).getRecipeid().equals(recipeid)) {
				int offset = i - this.getMine().size();
				if (offset < 0) {
					this.mine.get(i).setRecipename(recipename);
					this.mine.get(i).setRecipeDescriptions(recipeDescriptions);
					this.mine.get(i).setRecipeinstructions(recipeinstructions);
					this.mine.get(i).setIngredients(
							formatStringArrayListEntries(ingredients));
					this.mine.get(i).setCategory(
							formatStringArrayListEntries(category));
					this.mine.get(i).setauthor(
							FridgeActivity.myRecipeBook.getAuthor());
				} else {
					this.downloads.get(offset).setRecipename(recipename);
					this.downloads.get(offset).setRecipeDescriptions(
							recipeDescriptions);
					this.downloads.get(offset).setRecipeinstructions(
							recipeinstructions);
					this.downloads.get(offset).setIngredients(
							formatStringArrayListEntries(ingredients));
					this.downloads.get(offset).setCategory(
							formatStringArrayListEntries(category));
					this.downloads.get(offset).setauthor(
							FridgeActivity.myRecipeBook.getAuthor());
				}
				return;
			}
		}
	}

	/**
	 * 
	 * Converts a recipe to an ArrayList of Strings
	 * 
	 * 
	 * @param recipeid
	 *            the id of a recipe to be printed
	 * @return an Arraylist of strings representing a recipe
	 */

	public ArrayList<String> getRecipeInfo(String recipeid) {
		ArrayList<String> outputArray = new ArrayList<String>();
		Recipe found = searchById(recipeid);
		outputArray.add(recipeid);
		outputArray.add(found.getRecipename());
		outputArray.add(found.getauthor());
		outputArray.add(found.getRecipeDescriptions());
		outputArray.add(found.getRecipeinstructions());
		outputArray.add(found.getIngredientsString());
		outputArray.add(found.getCategoryString());
		outputArray.add(found.getUserid());
		return outputArray;
	}

	public Recipe searchById(String recipeid) {
		for (Recipe recipe : this.getRecipeBook()) {
			if (recipe.getRecipeid().equals(recipeid)) {
				return recipe;
			}
		}
		return null;
	}

	/**
	 * Remove a recipe by finding it with its ID
	 * 
	 * @param recipeid
	 *            recipe to be deleted
	 */

	public void deleteById(String recipeid) {

		Recipe found = searchById(recipeid);
		if (found != null) {
			try {
				this.downloads.remove(found);
			} catch (IndexOutOfBoundsException e) {
			}
			try {
				this.mine.remove(found);
			} catch (IndexOutOfBoundsException e2) {
			}
		}

	}

	/**
	 * 
	 * Creates a random generated code representing the user ID
	 * 
	 * @return String representing a new ID
	 */

	private String generateNewUserid() {
		SecureRandom randomKey = new SecureRandom();
		return (new BigInteger(130, randomKey).toString(32));
	}

	public boolean saveToFile(Context context) {

		try {
			context.deleteFile(RecipeBookFilename);
			ObjectOutputStream out = new ObjectOutputStream(
					context.openFileOutput(RecipeBookFilename,
							Context.MODE_APPEND));
			out.writeObject(this.getMine());
			out.writeObject(this.getDownloads());
			out.writeObject(this.getUserid());
			out.writeObject(this.getAuthor());
			out.close();

			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 
	 * Load previous recipes from file
	 * 
	 * @param context
	 * @return old recipes
	 */

	@SuppressWarnings("unchecked")
	public boolean loadFromFile(Context context) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					context.openFileInput(RecipeBookFilename));
			ArrayList<Recipe> mineIn = (ArrayList<Recipe>) in.readObject();
			ArrayList<Recipe> downloadsIn = (ArrayList<Recipe>) in.readObject();
			String useridIn = (String) in.readObject();
			String authorIn = (String) in.readObject();
			in.close();
			this.setMine(mineIn);
			this.setDownloads(downloadsIn);
			this.setUserid(useridIn);
			this.setAuthor(authorIn);

			return true;

		} catch (EOFException eof) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Upon change of author (username), this method will go through each recipe
	 * in "mine" array, and will update "author" field.
	 */

	public void updateAuthorInAllRecipes() {

		for (Recipe eachRecipe : this.mine) {
			eachRecipe.setauthor(this.getAuthor());
		}
	}

	/**
	 * This function searches local database for foods that user can make with
	 * ingredients that are currently in their fridge.
	 * 
	 */
	public ArrayList<Recipe> searchByIngredientsLocal(
			ArrayList<String> whatsInMyFridge) {

		ArrayList<Recipe> output = new ArrayList<Recipe>();
		boolean skip;
		for (Recipe recipe : this.getRecipeBook()) {
			skip = false;
			for (String ingredient : recipe.getIngredients()) {
				if (!whatsInMyFridge.contains(ingredient)) {
					skip = true;
					break;
				}
			}
			if (!skip) {
				output.add(recipe);
			}
		}
		return output;
	}

	/**
	 * 
	 * @param recipeArray
	 * @return
	 */

	public static ArrayList<ArrayList<String>> getNamesAndIDs(
			ArrayList<Recipe> recipeArray) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		output.add(getNames(recipeArray));
		output.add(getRecipeid(recipeArray));
		return output;
	}

	/**
	 * 
	 * Searches recipe book if it contains the given recipe ID
	 * 
	 * @param recipeidIn
	 * @return true if found
	 * @return false if not found
	 */

	public boolean containsRecipeOfID(String recipeidIn) {
		for (Recipe eachRecipe : this.getRecipeBook()) {
			if (eachRecipe.getRecipeid().equals(recipeidIn)) {
				return true;
			}
		}
		return false;
	}
}
