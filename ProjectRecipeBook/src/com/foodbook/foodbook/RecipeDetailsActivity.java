package com.foodbook.foodbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class RecipeDetailsActivity extends TitleBarOverride {
	
	private Intent in;
	
	protected String recipeid;
	
	protected String name;
	protected String author;
	protected String descriptions;
	protected String category;
	protected String ingredients;
	protected String instructions;
	
	protected EditText nameField;
	protected EditText authorField;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_details);
	}
	
	@Override
	public void onResume() {
		super.onResume();

		in = getIntent();
		
		recipeid = in.getStringExtra("recipeid");
		
		name = in.getStringExtra("name");
		author = in.getStringExtra("author");
		descriptions = in.getStringExtra("descriptions");
		instructions = in.getStringExtra("instructions");
		ingredients = in.getStringExtra("ingredients");
		category = in.getStringExtra("category");
	}
}
