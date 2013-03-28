package com.foodbook.onlinemanager;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;
import android.util.Log;

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
		
		
		//TODO call wsc to return an array of recipes then add to output array
		//need to parse ingredients list into one string
		
		
		
		
		
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void> (){
			
			@Override
			protected Void doInBackground(Void... arg0) {
				
				Log.v("tests", "checkpoint doInBackground");
				
				//ArrayList<Recipe> searchResult = null;
		
				WebServiceClient wsc = new WebServiceClient();
//				try {
//					ArrayList<Recipe> onlineResults = new ArrayList<Recipe>();
//					Log.v("tests", "checkpoint before search");
//					onlineResults = wsc.searchRecipes(keyword);
//				} catch (ClientProtocolException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				return null;
				
			}
				
		}.execute();
		
		return ouput;
	}

}
