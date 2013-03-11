package com.foodbook.onlinemanager;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	
	private ListView resultListView;
	
	private Intent in;
	
	private ArrayList<String> localResults;
	private ArrayList<String> localID;
	
	private ArrayList<String> onlineResults;
	private ArrayList<String> onlineID;
	
	private ArrayList<String> combinedResults;
	private ArrayList<String> combinedID;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_search_result);
		
		in = getIntent();
		localResults = in.getStringArrayListExtra("localResults");
		localID = in.getStringArrayListExtra("localID");
		
		resultListView = (ListView) findViewById(R.id.searchResults_listview);
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
				}
			}
		});
		
		
	}

	@Override
	public void onNewIntent(Intent newIntent) {
		super.onNewIntent(newIntent);
		setIntent(newIntent);
	}
	
	
	
}
