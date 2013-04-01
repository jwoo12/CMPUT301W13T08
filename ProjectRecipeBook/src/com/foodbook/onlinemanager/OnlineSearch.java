package com.foodbook.onlinemanager;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.foodbook.foodbook.R;
import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.RecipeBook;
import com.foodbook.foodbook.TitleBarOverride;

/**
 * 
 * <p>
 * Implementation for the ability to query recipes
 * </p>
 * 
 * 
 * @see Recipe
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */

public class OnlineSearch extends TitleBarOverride {

	private EditText keywordField;
	private Button searchButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_search);

		keywordField = (EditText) findViewById(R.id.searchBar);
		searchButton = (Button) findViewById(R.id.searchButton);

		searchButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final String keyword = keywordField.getText().toString();
				ArrayList<Recipe> onlineResults = OnlineDataBase.searchByKeyword(keyword);
				ArrayList<String> onlineNames = RecipeBook.getNamesAndIDs(onlineResults).get(0);
				ArrayList<String> onlineID = RecipeBook.getNamesAndIDs(onlineResults).get(1);
				
				Intent ingredientSeach = new Intent();
				ingredientSeach.setClass(getApplicationContext(), SearchResult.class);
				ingredientSeach.putExtra("localResults", new ArrayList<String>());
				ingredientSeach.putExtra("localID", new ArrayList<String>());
				ingredientSeach.putExtra("onlineResults", onlineNames);
				ingredientSeach.putExtra("onlineID", onlineID);
				startActivity(ingredientSeach);
				
			}
		});
		
	}

	@Override
	public void onResume() {
		super.onResume();
		keywordField.setText("");
	}
}
