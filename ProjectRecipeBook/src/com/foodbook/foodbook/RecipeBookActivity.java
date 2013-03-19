package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
/**
	 * <p> This class is an activity class for local recipes. </p>
	 * 
	 * <p> There are three tabs that users can select. "All", "Mine", and "Downloads"
	 * 	   Upon clicking of each tab, the corresponding list of recipes will be displayed. </p>
	 * 
	 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
	 * 
	 */
public class RecipeBookActivity extends TitleBarOverride {

	private Intent in;

	private ListView listView;
	
	private ArrayList<String> sourceAll;
	private ArrayList<String> sourceMine;
	private ArrayList<String> sourceDownloads;
	
	private ArrayList<String> idAll;
	private ArrayList<String> idMine;
	private ArrayList<String> idDownloads;
	
	private Context context;
	
	private int currentTab;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_recipe_book);
		
		context = getApplicationContext();
		
		Button allButton = (Button) findViewById(R.id.recipeBookAllButton);
		Button mineButton = (Button) findViewById(R.id.recipeBookMineButton);
		Button downloadsButton = (Button) findViewById(R.id.recipeBookDownloadsButton);
		
		allButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				currentTab = 1;
				refreshTabs();
			}
		});
		mineButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				currentTab = 2;
				refreshTabs();
			}
		});
		downloadsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				currentTab = 3;
				refreshTabs();
			}
		});
		
		listView = (ListView) findViewById(R.id.recipeBookList);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				String recipeid = new String();
				switch (currentTab) {
				case 1:
					recipeid = idAll.get(position);
					break;
				case 2:
					recipeid = idMine.get(position);
					break;
				case 3:
					recipeid = idDownloads.get(position);
					break;
				default:
					break;
				}
				
				ArrayList<String> recipeInfo = FridgeActivity.myRecipeBook.getRecipeInfo(recipeid);
				if (recipeInfo != null) {
					Intent recipeDetailsIntent = new Intent();
					recipeDetailsIntent.setClass(getApplicationContext(), RecipeDetailsActivity.class);
					recipeDetailsIntent = putExtraFromRecipeInfo(recipeDetailsIntent, recipeInfo);
					startActivity(recipeDetailsIntent);
				}
			}

		});
	}
	
	/**
	 * 
	 * Takes an intent and a recipe's info, and it puts the info into intent then returns new intent
	 * 
	 * 
	 * @param recipeDetailsIntent intent to be updated
	 * @param recipeInfo list containing information 
	 * @return new intent with updated info
	 */
	
	public static Intent putExtraFromRecipeInfo(Intent recipeDetailsIntent,
			ArrayList<String> recipeInfo) {
		
		recipeDetailsIntent.putExtra("recipeid", recipeInfo.get(0));
		recipeDetailsIntent.putExtra("name", recipeInfo.get(1));
		recipeDetailsIntent.putExtra("author", recipeInfo.get(2));
		recipeDetailsIntent.putExtra("descriptions", recipeInfo.get(3));
		recipeDetailsIntent.putExtra("instructions", recipeInfo.get(4));
		recipeDetailsIntent.putExtra("ingredients", recipeInfo.get(5));
		recipeDetailsIntent.putExtra("category", recipeInfo.get(6));
		recipeDetailsIntent.putExtra("userid", recipeInfo.get(7));
		
		return recipeDetailsIntent;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		in = getIntent();
		currentTab = in.getIntExtra("currentTab", 1);
		refreshTabs();
	}
	
	@Override
	public void onNewIntent(Intent newIntent) {
		super.onNewIntent(newIntent);
		setIntent(newIntent);
	}
	
	
	/**
	 * Part of a view within a RecipeBook's model. Helper to update the view
	 * 
	 * 
	 * 
	 */
	
	
	private void reloadRecipeBook() {
		sourceAll = RecipeBook.getNames(FridgeActivity.myRecipeBook.getRecipeBook());
		sourceMine = RecipeBook.getNames(FridgeActivity.myRecipeBook.getMine());
		sourceDownloads = RecipeBook.getNames(FridgeActivity.myRecipeBook.getDownloads());
		idAll = RecipeBook.getRecipeid(FridgeActivity.myRecipeBook.getRecipeBook());
		idMine = RecipeBook.getRecipeid(FridgeActivity.myRecipeBook.getMine());
		idDownloads = RecipeBook.getRecipeid(FridgeActivity.myRecipeBook.getDownloads());
	}
	
	
	
	/**
	 * Part of a view within a RecipeBook's model. Helper to update view.
	 * 
	 * Sorts the recipes by user created, downloaded, and all
	 * 
	 * 
	 */
	
	private void refreshTabs(){
		reloadRecipeBook();
		switch (currentTab) {
		case 1:
			UpdateListView.updateListView(context, sourceAll, listView);
			break;
		case 2:
			UpdateListView.updateListView(context, sourceMine, listView);
			break;
		case 3:
			UpdateListView.updateListView(context, sourceDownloads, listView);
			break;
		default:
			break;
		}
	}
	
}
