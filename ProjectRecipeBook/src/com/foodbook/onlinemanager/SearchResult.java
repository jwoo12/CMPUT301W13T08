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

		in = getIntent();
		localResults = in.getStringArrayListExtra("localResults");
		localID = in.getStringArrayListExtra("localID");
		// TODO add online results (later)

		combinedResults = new ArrayList<String>();
		combinedID = new ArrayList<String>();
		
		combinedResults.addAll(localResults);
		combinedID.addAll(localID);
		// add online stuff too (later)
		
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
					// TODO distinguish online/offline recipies here by inserting "onlineRecipe = true" to intent
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
	
	private void updateListView(ArrayList<String> sourceList, ListView targetListView) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sourceList);
		targetListView.setAdapter(adapter);
	}
	
}
