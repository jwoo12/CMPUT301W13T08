package com.foodbook.onlinemanager;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.R;
import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.RecipeBookActivity;
import com.foodbook.foodbook.RecipeDetailsActivity;
import com.foodbook.foodbook.TitleBarOverride;

/**
 * 
 * <p> Queried recipes are displayed </p>
 * 
 * 
 * @see Recipe
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */

public class SearchResult extends TitleBarOverride {
	
	private Intent in;
	
	private ArrayList<String> localResults = new ArrayList<String>();
	private ArrayList<String> localID = new ArrayList<String>();
	
	private ArrayList<String> onlineResults = new ArrayList<String>();
	private ArrayList<String> onlineID = new ArrayList<String>();
	
	private ArrayList<String> combinedResults = new ArrayList<String>();
	private ArrayList<String> combinedID = new ArrayList<String>();
	
	private ListView resultListView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);

		in = getIntent();
		if (in.hasExtra("localResults")) {
			localResults = in.getStringArrayListExtra("localResults");
			localID = in.getStringArrayListExtra("localID");			
		}
		if (in.hasExtra("onlineResults")) {
			onlineResults = in.getStringArrayListExtra("onlineResults");
			onlineID = in.getStringArrayListExtra("onlineID");
		}

		combinedResults.addAll(localResults);
		combinedResults.addAll(onlineResults);
		combinedID.addAll(localID);
		combinedID.addAll(onlineID);
		
		resultListView = (ListView) findViewById(R.id.searchResult_listView);

		resultListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
				String recipeid = combinedID.get(position);
				
				ArrayList<String> recipeInfo = FridgeActivity.myRecipeBook.getRecipeInfo(recipeid);
				if (recipeInfo != null) {
					Intent recipeDetailsIntent = new Intent();
					recipeDetailsIntent.setClass(getApplicationContext(), RecipeDetailsActivity.class);
					recipeDetailsIntent = RecipeBookActivity.putExtraFromRecipeInfo(recipeDetailsIntent, recipeInfo);
					startActivity(recipeDetailsIntent);
					// TODO distinguish online/offline recipes here by inserting "onlineRecipe = true" to intent
				} 
			}
		});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		updateListView(combinedResults, resultListView);
	}

	@Override
	public void onNewIntent(Intent newIntent) {
		super.onNewIntent(newIntent);
		setIntent(newIntent);
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param sourceList
	 * @param targetListView
	 */
	
	private void updateListView(ArrayList<String> sourceList, ListView targetListView) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sourceList);
		targetListView.setAdapter(adapter);
	}
	
}
