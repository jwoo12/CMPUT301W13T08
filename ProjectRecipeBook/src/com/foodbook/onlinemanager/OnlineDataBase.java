package com.foodbook.onlinemanager;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.foodbook.foodbook.Fridge;
import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.RecipeBook;
import com.foodbook.foodbook.ResultsBook;

/**
 * 
 * Web service and local implementation for searching recipes by ingredients or keywords.
 * 
 * A keyword can be a recipe's title, author, or category.
 * 
 * Searching is completed by ElasticSearch
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */


public class OnlineDataBase extends Activity {

	private static String queryString;
	private static boolean searchDone;

	/**
	 * Given the keyword, returns a list of recipes that match.
	 * 
	 * A keyword can be a recipe's category, author, or title. 
	 * 
	 * @see Recipe
	 * @see WebServiceClient
	 * 
	 * 
	 * @param keyword
	 *            searchable term
	 * @return list of relevant recipes
	 */



	public static ArrayList<Recipe> searchByKeyword(final String keyword) {
		
		ResultsBook.getInstance().reset();
		final ArrayList<Recipe> searchResult = new ArrayList<Recipe>();
		searchDone = false;
		
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {

				Log.v("tests", "checkpoint doInBackground");
				WebServiceClient wsc = new WebServiceClient();
				try {
					ArrayList<Recipe> onlineResults = wsc.searchRecipes(keyword, false);
					searchResult.addAll(onlineResults);
					searchDone = true;
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;

			}

		};
		
		task.execute();
		
		while (!searchDone) {
			//infinite loop
		}
		
		ResultsBook.getInstance().setSearchResults(searchResult);
		return searchResult;
		
	}

	/**
	 * Iterate through the web sevice to find recipes that can be made from what is in the fridge (ingredients).
	 * 
	 * 
	 * @param ingredients the list of ingredients to be searched. The list will be parsed into one string using logical operators. 
	 * @return an arraylist of recipes found on the web service
	 */
	public static ArrayList<Recipe> searchByIngredientsOnline(ArrayList<String> ingredients) {

		if (ingredients.size() == 0) {
			return new ArrayList<Recipe>();
		}

		queryString = ingredients.get(0);

		for (int i = 1; i < ingredients.size(); i++) {

			queryString += " OR " + ingredients.get(i);
		}

		ResultsBook.getInstance().reset();
		final ArrayList<Recipe> searchResult = new ArrayList<Recipe>();
		searchDone = false;

		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {

				WebServiceClient wsc = new WebServiceClient();
				try {

					Log.v("tests", "checkpoint before search");
					ArrayList<Recipe> onlineResults = wsc.searchRecipes(queryString, true);
					Log.v("tests", "test size " + onlineResults.size());
					searchResult.addAll(onlineResults);
					searchDone = true;
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};

		task.execute();

		while (!searchDone) {

		}

		Log.v("tests", "test searchresults size " + searchResult.size());
		
		// filtering
		ArrayList<Recipe> output = new ArrayList<Recipe>();
		boolean skip;
		for (Recipe recipe : searchResult) {
			skip = false;
			for (String ingInRecipe : recipe.getIngredients()) {
				if (!Fridge.getInstance().getIngredients().contains(ingInRecipe)) {
					skip = true;
					break;
				}
			}
			if (!skip) {
				output.add(recipe);
			}
		}

		ResultsBook.getInstance().setSearchResults(output);
		return output;
	}
}