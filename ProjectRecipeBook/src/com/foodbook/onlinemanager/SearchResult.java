package com.foodbook.onlinemanager;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.foodbook.foodbook.R;
import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.RecipeBook;
import com.foodbook.foodbook.RecipeDetailsActivity;
import com.foodbook.foodbook.ResultsBook;
import com.foodbook.foodbook.TitleBarOverride;
import com.foodbook.foodbook.UpdateListView;

/**
 * 
 * <p>
 * Queried recipes are displayed. A view within the MVC architecture. This class handles the results found from the
 * ElasticSearch and displays them to screen
 * </p>
 * 
 * 
 * @see Recipe
 * @see WebServiceClient
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */

public class SearchResult extends TitleBarOverride {

	private ArrayList<String> localResults;
	private ArrayList<String> localID;

	private ArrayList<String> onlineResults;
	private ArrayList<String> onlineID;

	private ArrayList<String> combinedResults;
	private ArrayList<String> combinedID;

	private ListView resultListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);
		resetAttributes();
		showSearchResults();
	}
	
	
	/**
	 * 
	 * Create new ArrayLists for local and online results and their IDs
	 * 
	 * 
	 */
	
	protected void resetAttributes() {
		localResults = new ArrayList<String>();
		localID = new ArrayList<String>();
		onlineResults = new ArrayList<String>();
		onlineID = new ArrayList<String>();
		combinedResults = new ArrayList<String>();
		combinedID = new ArrayList<String>();
	}

	/**
	 * 
	 * Take intents, combine them in a list, then show the list on screen.
	 * 
	 * A view with the MVC architecture.
	 * 
	 * 
	 * 
	 */
	
	
	
	private void showSearchResults() {

		readIntent();
		constructCombinedLists();
		setupListView();
		UpdateListView.updateListView(getApplicationContext(), combinedResults, resultListView);

	}

	
	/**
	 * 
	 * Initialise the listview for the results
	 * 
	 * 
	 */
	
	
	
	
	
	protected void setupListView() {
		resultListView = (ListView) findViewById(R.id.searchResult_listView);
		resultListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

				Log.v("mylog", "opening a recipe details activity");
				Intent recipeDetailsIntent = new Intent();

				String recipeid = combinedID.get(position);

				if (position < localResults.size()) {
					Log.v("", "this is a local recipe");
					recipeDetailsIntent = RecipeBook.getInstance().makeRecipeIntentFromRecipeID(recipeid);

				} else {
					Log.v("", "this is an online recipe");
					recipeDetailsIntent = ResultsBook.getInstance().makeRecipeIntentFromRecipeID(recipeid);
					recipeDetailsIntent.putExtra("onlineRecipe", true);
				}

				recipeDetailsIntent.setClass(getApplicationContext(), RecipeDetailsActivity.class);
				startActivity(recipeDetailsIntent);
			}
		});
	}

	

	/**
	 * 
	 * combine the results with their IDs
	 * 
	 * 
	 */
	
	
	
	protected void constructCombinedLists() {
		combinedResults.addAll(localResults);
		combinedResults.addAll(onlineResults);
		combinedID.addAll(localID);
		combinedID.addAll(onlineID);
	}

	
	/**
	 * 
	 * Read intents from the calling methods
	 * 
	 * 
	 */
	
	
	protected void readIntent() {
		Intent in = getIntent();
		if (in.hasExtra("localResults")) {
			localResults = in.getStringArrayListExtra("localResults");
			localID = in.getStringArrayListExtra("localID");
		}
		if (in.hasExtra("onlineResults")) {
			onlineResults = in.getStringArrayListExtra("onlineResults");
			onlineID = in.getStringArrayListExtra("onlineID");
		}
	}

	

	/**
	 * 
	 * New Intents 
	 * 
	 * 
	 */
	
	@Override
	public void onNewIntent(Intent newIntent) {
		super.onNewIntent(newIntent);
		setIntent(newIntent);
		resetAttributes();
		showSearchResults();
	}

	/**
	 * Part of the view in the model-view-controller architecture
	 * 
	 * 
	 * @param sourceList
	 * @param targetListView
	 */

	private void updateListView(ArrayList<String> sourceList, ListView targetListView) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mytextview, sourceList);
		targetListView.setAdapter(adapter);
	}

}
