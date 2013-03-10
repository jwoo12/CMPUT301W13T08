package com.foodbook.foodbook;

import java.util.ArrayList;

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
	
	private int currentTab;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_recipe_book);
		
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
				/*
				Intent recipeDetailsIntent = new Intent();
				recipeDetailsIntent.setClass(getApplicationContext(), RecipeDetailsActivity.class);
				
				recipeDetailsIntent.putExtra("recipeid", recipeInfo.get(0));
				recipeDetailsIntent.putExtra("name", recipeInfo.get(1));
				recipeDetailsIntent.putExtra("author", recipeInfo.get(2));
				recipeDetailsIntent.putExtra("descriptions", recipeInfo.get(3));
				recipeDetailsIntent.putExtra("instructions", recipeInfo.get(4));
				recipeDetailsIntent.putExtra("ingredients", recipeInfo.get(5));
				recipeDetailsIntent.putExtra("category", recipeInfo.get(6));
				startActivity(recipeDetailsIntent);
				*/
			}

		});
		
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
	
	private void reloadRecipeBook() {
		sourceAll = RecipeBook.convertRecipeBookToStringArray(FridgeActivity.myRecipeBook.getRecipeBook());
		sourceMine = RecipeBook.convertRecipeBookToStringArray(FridgeActivity.myRecipeBook.getMine());
		sourceDownloads = RecipeBook.convertRecipeBookToStringArray(FridgeActivity.myRecipeBook.getDownloads());
		idAll = RecipeBook.getAllRecipeid(FridgeActivity.myRecipeBook.getRecipeBook());
		idMine = RecipeBook.getAllRecipeid(FridgeActivity.myRecipeBook.getMine());
		idDownloads = RecipeBook.getAllRecipeid(FridgeActivity.myRecipeBook.getDownloads());
	}
	
	private void updateListView(ArrayList<String> sourceList, ListView targetListView) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sourceList);
		targetListView.setAdapter(adapter);
	}
	
	private void refreshTabs(){
		reloadRecipeBook();
		switch (currentTab) {
		case 1:
			updateListView(sourceAll, listView);
			break;
		case 2:
			updateListView(sourceMine, listView);
			break;
		case 3:
			updateListView(sourceDownloads, listView);
			break;
		default:
			break;
		}
	}
	
}
