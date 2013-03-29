package com.foodbook.onlinemanager;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class OnlineDataBase extends Activity {

	static String keyword = "";
	static ArrayList<Recipe> onlineResults = new ArrayList<Recipe>();

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

		keyword += ingredients.get(0);

		for (int i = 1; i < ingredients.size(); i++) {

			keyword += " OR " + ingredients.get(i);
		}

		final ArrayList<Recipe> searchResult = new ArrayList<Recipe>();
		keyword = keyword.substring(0, keyword.length() - 4);

		Log.v("tests", "keyword ingredient search     " + keyword);

		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {

				WebServiceClient wsc = new WebServiceClient();
				try {

					Log.v("tests", "checkpoint before search");
					onlineResults = wsc.searchRecipes(keyword);
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

		return searchResult;
	}

}
