package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
	private boolean showMineOnly;

	private ListView listView;
	
	private ArrayList<String> sourceAll;
	private ArrayList<String> sourceMine;
	private ArrayList<String> sourceDownloads;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_recipe_book);

		in = getIntent();
		showMineOnly = in.getBooleanExtra("showMineOnly", false);
		
		
		listView = (ListView) findViewById(R.id.recipeBookList);
		Button allButton = (Button) findViewById(R.id.recipeBookAllButton);
		Button mineButton = (Button) findViewById(R.id.recipeBookMineButton);
		Button downloadsButton = (Button) findViewById(R.id.recipeBookDownloadsButton);
		
		allButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				reloadRecipeBook();
				updateRecipeList(sourceAll, listView);
			}
		});
		mineButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				reloadRecipeBook();
				updateRecipeList(sourceMine, listView);
			}
		});
		downloadsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				reloadRecipeBook();
				updateRecipeList(sourceDownloads, listView);
			}
		});
		
		allButton.callOnClick();
		
		if (showMineOnly) {
			mineButton.callOnClick();
		}
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		reloadRecipeBook();
		// TODO update the list
	}
	
	private void reloadRecipeBook() {
		sourceAll = RecipeBook.convertRecipeBookToStringArray(FridgeActivity.myRecipeBook.getRecipeBook());
		sourceMine = RecipeBook.convertRecipeBookToStringArray(FridgeActivity.myRecipeBook.getMine());
		sourceDownloads = RecipeBook.convertRecipeBookToStringArray(FridgeActivity.myRecipeBook.getDownloads());
	}
	
	private void updateRecipeList(ArrayList<String> sourceList, ListView targetListView) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sourceList);
		targetListView.setAdapter(adapter);
	}
	
}
