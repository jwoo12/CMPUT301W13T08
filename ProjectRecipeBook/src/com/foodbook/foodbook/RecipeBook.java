package com.foodbook.foodbook;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.foodbook.onlinemanager.WebServiceClient;

/**
 * <p>
 * RecipeBook is a collection of local and downloaded recipes.
 * </p>
 * 
 * <p>
 * This class is the centre of the Model. It has the collection of local (and downloaded) recipes, and class methods associated with it.
 * </p>
 * 
 * 
 * @see Recipe
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 * 
 * 
 * 
 */

public class RecipeBook {

	public static final String RecipeBookFilename = "recipe.sav";
	public static final RecipeBook recipeBookInstance = new RecipeBook();

	protected ArrayList<Recipe> mine;
	protected ArrayList<Recipe> downloads;
	protected String userid;
	protected String author;

	/**
	 * This is a constructor method for RecipeBook. It creates new ArrayList of Recipe.
	 */

	protected RecipeBook() {

		this.mine = new ArrayList<Recipe>();
		this.downloads = new ArrayList<Recipe>();
		this.userid = this.generateNewUserid();
		this.author = "noname";

	}

	/**
	 * A method to get the instance of this class object. Following singleton design pattern. Allows global access.
	 * 
	 * @return RecipeBook instance
	 */

	public static RecipeBook getInstance() {
		return recipeBookInstance;
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

	public static ArrayList<String> getNames(ArrayList<Recipe> inputArray) {

		ArrayList<String> outputArray = new ArrayList<String>();

		for (Recipe recipe : inputArray) {
			outputArray.add(recipe.getName());
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
	 * This is a getter for the entire RecipeBook. Since there are two recipe books ("mine" and "downloaded"), this method will simply combine them and return the resulting array.
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
	 * This function receives details of a recipe, and creates/adds a new recipe to the local RecipeBook.
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

	public String addRecipe(String recipename, String recipeDescriptions, String recipeinstructions, ArrayList<String> ingredients, ArrayList<String> category, ArrayList<String> pics) {

		Recipe newRecipe = new Recipe(recipename, recipeDescriptions, recipeinstructions, ingredients, category, this.userid, this.author, pics);
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
	 * Change details of a recipe by finding the ID then update to new values
	 * 
	 * @param recipename
	 *            name to be changed
	 * @param newDesc
	 *            description to be changed
	 * @param newInst
	 *            instructions to be changed
	 * @param newIngredients
	 *            ingredients to be changed
	 * @param newCategory
	 *            category to be changed
	 * @param recipeid
	 */

	public void editRecipe(String recipename, String newDesc, String newInst, ArrayList<String> newIngredients, ArrayList<String> newCategory, String recipeid, ArrayList<String> newPictures) {

		ArrayList<Recipe> combinedList = this.getRecipeBook();
		for (int i = 0; i < combinedList.size(); i++) {
			if (combinedList.get(i).getRecipeid().equals(recipeid)) {
				int offset = i - this.getMine().size();
				if (offset < 0) {
					this.mine.get(i).setName(recipename);
					this.mine.get(i).setDesc(newDesc);
					this.mine.get(i).setInst(newInst);
					this.mine.get(i).setIngredients(newIngredients);
					this.mine.get(i).setCategory(newCategory);
					this.mine.get(i).setAuthor(RecipeBook.getInstance().getAuthor());
					this.mine.get(i).setPictures(newPictures);
				} else {
					this.downloads.get(offset).setName(recipename);
					this.downloads.get(offset).setDesc(newDesc);
					this.downloads.get(offset).setInst(newInst);
					this.downloads.get(offset).setIngredients(newIngredients);
					this.downloads.get(offset).setCategory(newCategory);
					this.downloads.get(offset).setAuthor(RecipeBook.getInstance().getAuthor());
					this.downloads.get(offset).setPictures(newPictures);
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
		outputArray.add(found.getName());
		outputArray.add(found.getauthor());
		outputArray.add(found.getDesc());
		outputArray.add(found.getInst());
		outputArray.add(found.getIngredientsString());
		outputArray.add(found.getCategoryString());
		outputArray.add(found.getUserid());
		return outputArray;
	}

	public ArrayList<String> getPicturesById(String recipeid) {
		Recipe found = searchById(recipeid);
		if (found != null) {
			return found.getPictures();
		}
		return new ArrayList<String>();
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

	protected String generateNewUserid() {
		SecureRandom randomKey = new SecureRandom();
		return (new BigInteger(130, randomKey).toString(32));
	}

	public boolean saveToFile(Context context) {

		try {
			context.deleteFile(RecipeBookFilename);
			ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput(RecipeBookFilename, Context.MODE_APPEND));
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
			ObjectInputStream in = new ObjectInputStream(context.openFileInput(RecipeBookFilename));
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
	 * Upon change of author (username), this method will go through each recipe in "mine" array, and will update "author" field.
	 */

	public void updateAuthorInAllRecipes() {

		for (Recipe eachRecipe : this.mine) {
			eachRecipe.setAuthor(this.getAuthor());
		}
	}

	/**
	 * This function searches local database for foods that user can make with ingredients that are currently in their fridge.
	 * 
	 */
	public ArrayList<Recipe> searchByIngredientsLocal(ArrayList<String> whatsInMyFridge) {

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

	public static ArrayList<ArrayList<String>> getNamesAndIDs(ArrayList<Recipe> recipeArray) {
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

	public void updatePic(String recipeid, ArrayList<String> newpic) {
		Recipe found = this.searchById(recipeid);
		found.setPictures(newpic);
	}

	public static Intent makeRecipeIntent(Recipe sourceRecipe) {
		Intent intentOut = new Intent();
		intentOut.putExtra("name", sourceRecipe.getName());
		intentOut.putExtra("author", sourceRecipe.getauthor());
		intentOut.putExtra("inst", sourceRecipe.getInst());
		intentOut.putExtra("desc", sourceRecipe.getDesc());
		intentOut.putStringArrayListExtra("category", sourceRecipe.getCategory());
		intentOut.putStringArrayListExtra("ingredients", sourceRecipe.getIngredients());
		intentOut.putExtra("recipeid", sourceRecipe.getRecipeid());
		intentOut.putExtra("userid", sourceRecipe.getUserid());

		/*
		 * This method is missing "pictures" attribute, because pictures slow application down. Request pictures only when needed.
		 */

		return intentOut;
	}

	public Intent makeRecipeIntentFromRecipeID(String recipeid) {
		Recipe found = searchById(recipeid);
		if (found != null) {
			return makeRecipeIntent(found);
		} else {
			return null;
		}
	}

	protected void publishRecipeById(String recipeid) {

		final Recipe targetRecipe = searchById(recipeid);

		targetRecipe.generateTags();
		
		final WebServiceClient wsb = new WebServiceClient();

		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {

				try {
					// WebServiceClient wsb = new WebServiceClient();
					Log.v("tests", "checkpoint " + "AsyncTask begin");
					wsb.insertRecipe(targetRecipe);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

		};

		task.execute();

	}
}