package com.foodbook.onlinemanager;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.foodbook.foodbook.R;
import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.RecipeBook;
import com.foodbook.foodbook.RecipeBookActivity;
import com.foodbook.foodbook.RecipeDetailsActivity;
import com.foodbook.foodbook.TitleBarOverride;
import com.foodbook.foodbook.UpdateListView;

/**
 * 
 * <p>
 * Queried recipes are displayed
 * </p>
 * 
 * 
 * @see Recipe
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

	protected void resetAttributes() {
		localResults = new ArrayList<String>();
		localID = new ArrayList<String>();
		onlineResults = new ArrayList<String>();
		onlineID = new ArrayList<String>();
		combinedResults = new ArrayList<String>();
		combinedID = new ArrayList<String>();
	}

	private void showSearchResults() {
		
		readIntent();
		constructCombinedLists();
		setupListView();
		UpdateListView.updateListView(getApplicationContext(), combinedResults, resultListView);
		
	}

	protected void setupListView() {
		resultListView = (ListView) findViewById(R.id.searchResult_listView);
		resultListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

				Intent recipeDetailsIntent = new Intent();
				String recipeid = combinedID.get(position);
				
				if (position < localResults.size()) {
					ArrayList<String> recipeInfo = RecipeBook.getInstance().getRecipeInfo(recipeid);
					recipeDetailsIntent.setClass(getApplicationContext(), RecipeDetailsActivity.class);
					//recipeDetailsIntent = RecipeBookActivity.makeIntent(recipeDetailsIntent, recipeInfo);

				} else {
					int offset = combinedID.size() - localResults.size();
				}

				startActivity(recipeDetailsIntent);
			}
		});
	}

	protected void constructCombinedLists() {
		combinedResults.addAll(localResults);
		combinedResults.addAll(onlineResults);
		combinedID.addAll(localID);
		combinedID.addAll(onlineID);
	}

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
