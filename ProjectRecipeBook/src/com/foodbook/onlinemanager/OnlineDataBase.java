package com.foodbook.onlinemanager;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.foodbook.foodbook.Fridge;
import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.ResultsBook;

/**
 * 
 * HTTP and web service implementation
 * 
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */

public class OnlineDataBase extends Activity {

	private static String keyword;

	/**
	 * Given the keyword, returns a list of recipes that match
	 * 
	 * @param keyword
	 *            searchable term
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

	public static ArrayList<Recipe> searchByIngredientsOnline(ArrayList<String> ingredients) {

		if (ingredients.size() == 0) {
			return new ArrayList<Recipe>();
		}

		keyword = ingredients.get(0);

		for (int i = 1; i < ingredients.size(); i++) {

			keyword += " OR " + ingredients.get(i);
		}

		final ArrayList<Recipe> searchResult = new ArrayList<Recipe>();

		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {

				WebServiceClient wsc = new WebServiceClient();
				try {

					Log.v("tests", "checkpoint before search");
					ArrayList<Recipe> onlineResults = wsc.searchRecipes(keyword);
					Log.v("tests", "test size " + onlineResults.size());
					searchResult.addAll(onlineResults);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};

		task.execute();

		Log.v("tests", "test searchresults size " + searchResult.size());

		while (searchResult.size() == 0) {

		}
		
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
