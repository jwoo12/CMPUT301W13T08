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

	private RecipeBookRecipeID recipeBookRecipeID = new RecipeBookRecipeID();
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
	 * Extracts names only from the given list of Recipes
	 * 
	 * @param inputArray
	 *            a list of recipes to be converted to strings
	 * @return outputArray an ArrayList of strings representing recipes
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
	 * Extracts ID's only from the given list of Recipes 
	 * 
	 * @param inputArray
	 *            recipe list
	 * @return a list of recipes
	 */

	public static ArrayList<String> getRecipeid(ArrayList<Recipe> inputArray) {
		return RecipeBookRecipeID.getRecipeid(inputArray);
	}

	/**
	 *
	 * A setter method for the downloads list
	 * 
	 * @param downloads
	 */

	public void setDownloads(ArrayList<Recipe> downloads) {
		this.downloads = downloads;
	}

	/**
	 * This is a getter for the entire RecipeBook.
	 * Combines mine and downloads and returns the resulting list.
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

		// TODO this should be re-written with intent

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
	 * gets the picture of the recipe of the given recipeID
	 * 
	 * @param recipeid
	 * @return pictures
	 */
	public ArrayList<String> getPicturesById(String recipeid) {
		return recipeBookRecipeID.getPicturesById(recipeid, this);
	}

	/**
	 * Remove a recipe by finding it with its ID
	 * 
	 * @param recipeid
	 *            recipe to be deleted
	 */

	public void deleteById(String recipeid) {

		recipeBookRecipeID.deleteById(recipeid, mine, downloads, this);

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
	
	/**
	 * 
	 * Saves RecipeBook instance to a file
	 * 
	 * @param context
	 * @return boolean value, denoting result
	 */

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
	 * Given a list of Recipe objects, extracts names and recipeID for each element, and return them.
	 * 
	 * @param recipeArray
	 * @return ArrayList<ArrayList<String>> namesAndIDs
	 */

	public static ArrayList<ArrayList<String>> getNamesAndIDs(ArrayList<Recipe> recipeArray) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		output.add(getNames(recipeArray));
		output.add(RecipeBookRecipeID.getRecipeid(recipeArray));
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
		return recipeBookRecipeID.containsRecipeOfID(recipeidIn, this);
	}
	
	/**
	 * 
	 * Find the recipe with given recipeID and replaces the picture swith the new set of pictures
	 * This method is missing "pictures" attribute, because pictures slow application down. Request pictures only when needed.
	 * 
	 * @param recipeid
	 * @param newpic
	 */

	public void updatePic(String recipeid, ArrayList<String> newpic) {
		recipeBookRecipeID.updatePic(recipeid, newpic, this);
	}

	/**
	 * 
	 * Parses intent for the recipe with the given recipeID
	 * 
	 * @param sourceRecipe
	 * @return intent, containing the recipe info of the Recipe specified by the given recipeID
	 */
	
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
	
	/**
	 * 
	 * Calls makeRecipeIntentFromRecipeID after finding the recipe by recipeID
	 * 
	 * @param recipeid
	 * @return intent
	 */

	public Intent makeRecipeIntentFromRecipeID(String recipeid) {
		return recipeBookRecipeID.makeRecipeIntentFromRecipeID(recipeid, this);
	}

	/**
	 * 
	 * Publishes Recipe to the webserver
	 * 
	 * @param recipeid
	 */
	
	protected void publishRecipeById(String recipeid) {

		final Recipe targetRecipe = recipeBookRecipeID.searchById(recipeid, this);
		targetRecipe.generateTags();

		final WebServiceClient wsb = new WebServiceClient();

		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {

				try {
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