package com.foodbook.foodbook;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditRecipeActivity extends TitleBarOverride {

	protected Recipe recipe;
	protected Intent returningIntent;
	protected int position;
	
	protected EditText recipeName;
	protected EditText description;
	protected EditText category;
	protected EditText ingredients;
	protected EditText instructions;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_recipe);
		
		// retrieve recipeobject from intent
		recipe = (Recipe) getIntent().getSerializableExtra("recipe");
		position = getIntent().getIntExtra("position", -1);
		
		// setup textfields
		recipeName = (EditText) findViewById(R.id.editRecipeName);
		description = (EditText) findViewById(R.id.editRecipeDesc);
		category = (EditText) findViewById(R.id.editRecipeCategory);
		ingredients = (EditText) findViewById(R.id.editRecipeIngredients);
		instructions = (EditText) findViewById(R.id.editRecipeInst);
		
		// setup save button
		Button saveButton = (Button) findViewById(R.id.editRecipeSaveButton);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveButtonClicked();
			}
		});
		
		// 
		updateTextFields();
	}
	
	private void updateTextFields() {
		/**
		 * This function sets the contents of text fields according to the recipe.
		 */
		recipeName.setText(recipe.getRecipename());
		description.setText(recipe.getRecipeDescriptions());
		category.setText(recipe.getCategoryString());
		ingredients.setText(recipe.getIngredientsString());
		instructions.setText(recipe.getRecipeinstructions());
	}
	
	protected void readTextfields() {
		/**
		 * This function reads contents of textfields, and then puts into the recipe.
		 */
	}

	public void saveButtonClicked() {
		
		// read contents of textfields
		readTextfields();
		
		// setup returning intent
		returningIntent = new Intent();
		returningIntent.putExtra("recipe", recipe);
		returningIntent.putExtra("position", position);
		setResult(2, returningIntent);
		/*
		Intent intent = new Intent(this, FridgeActivity.class);
		startActivity(intent);
		*/
		finish();
	}
	
}
