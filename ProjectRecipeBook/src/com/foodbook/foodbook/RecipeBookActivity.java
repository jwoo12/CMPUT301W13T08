package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

/**
 * <p>
 * This class is an activity class for local recipes.
 * </p>
 * 
 * <p>
 * There are three tabs that users can select. "All", "Mine", and "Downloads" Upon clicking of each tab, the corresponding list of recipes will be displayed.
 * </p>
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

				
				Log.v("", "position/id: " + position + " / " + recipeid);
				
				Intent recipeDetailsIntent = RecipeBook.getInstance().makeRecipeIntentFromRecipeID(recipeid);
				recipeDetailsIntent.setClass(getApplicationContext(), RecipeDetailsActivity.class);
				startActivity(recipeDetailsIntent);
				
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

	/**
	 * Part of a view within a RecipeBook's model. Helper to update the view
	 * 
	 * 
	 * 
	 */

	private void reloadRecipeBook() {
		sourceAll = RecipeBook.getNames(RecipeBook.getInstance().getRecipeBook());
		sourceMine = RecipeBook.getNames(RecipeBook.getInstance().getMine());
		sourceDownloads = RecipeBook.getNames(RecipeBook.getInstance().getDownloads());
		idAll = RecipeBook.getRecipeid(RecipeBook.getInstance().getRecipeBook());
		idMine = RecipeBook.getRecipeid(RecipeBook.getInstance().getMine());
		idDownloads = RecipeBook.getRecipeid(RecipeBook.getInstance().getDownloads());
	}

	/**
	 * Part of a view within a RecipeBook's model. Helper to update view.
	 * 
	 * Sorts the recipes by user created, downloaded, and all
	 * 
	 * 
	 */

	private void refreshTabs() {
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
