package com.foodbook.foodbook;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
/**
* <p> This activity is used to change details of a selected recipe.
* Only the owner of the recipe can access this recipe. </p>
* 
* 
* 
* @see Recipe
* 
* @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
* 
*/
public class EditRecipeActivity extends TitleBarOverride {
	
	

	protected int position;
	
	protected String name;
	protected String descriptions;
	protected String category;
	protected String ingredients;
	protected String instructions;
	
	protected EditText recipeNameField;
	protected EditText descriptionField;
	protected EditText categoryField;
	protected EditText ingredientsField;
	protected EditText instructionsField;
	
	protected ArrayList<String> categoryArrayList;
	protected ArrayList<String> ingredientsArrayList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_recipe);
		
		// retrieve recipe object from intent
		position = getIntent().getIntExtra("position", -1);
		name = getIntent().getStringExtra("name");
		descriptions = getIntent().getStringExtra("descriptions");
		category = getIntent().getStringExtra("category");
		ingredients = getIntent().getStringExtra("ingredients");
		instructions = getIntent().getStringExtra("instructions");
		
		// setup textfields
		recipeNameField = (EditText) findViewById(R.id.editRecipeName);
		descriptionField = (EditText) findViewById(R.id.editRecipeDesc);
		categoryField = (EditText) findViewById(R.id.editRecipeCategory);
		ingredientsField = (EditText) findViewById(R.id.editRecipeIngredients);
		instructionsField = (EditText) findViewById(R.id.editRecipeInst);
		
		// setup save button
		Button saveButton = (Button) findViewById(R.id.editRecipeSaveButton);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				readTextfields();
				if (!requiredInfoCheckOK()) {
					// tell user that recipe is missing some important info
					return;
				}
				saveButtonClicked();
				finish();
			}
		});
		
		// update text fields
		updateTextFields();
	}
	
	private boolean requiredInfoCheckOK() {
		
		/**
		 * This function is used to check whether or not required fields are empty.
		 * If a recipe is missing important information (ex. name), then this function will
		 * return false.
		 */
		
		// TODO write if statements to test the textfields
		
		return true;
	}
	
	private void updateTextFields() {
		
		/**
		 * This function sets the contents of text fields according to the recipe.
		 * If recipe has no information (ie. when creating a new recipe), everything will be blank.
		 */
		
		if (position == -1) {
			// position == -1 means "new recipe". do not need to set any text.
			return;
		}
		
		recipeNameField.setText(name);
		descriptionField.setText(descriptions);
		categoryField.setText(category);
		ingredientsField.setText(ingredients);
		instructionsField.setText(instructions);
	}
	
	protected void readTextfields() {
		
		/**
		 * This function reads contents of textfields.
		 */
		
		// these are regular strings
		name = recipeNameField.getText().toString();
		descriptions = descriptionField.getText().toString();
		instructions = instructionsField.getText().toString();
		
		// these are meant to be array of strings.
		category = categoryField.getText().toString();
		String[] categoryArray = category.split(",");
		categoryArrayList = new ArrayList<String>(Arrays.asList(categoryArray));
		ingredients = ingredientsField.getText().toString();
		String[] ingredientsArray = ingredients.split(",");
		ingredientsArrayList = new ArrayList<String>(Arrays.asList(ingredientsArray));
		
	}

	public void saveButtonClicked() {
		
		/**
		 * This function edits the existing recipe object in the RecipeBook
		 */
		
		// edit the recipe details
		// TODO right now we are putting "position" in because we are arraylist. when we get SQL ready, we will change this to recipe ID.
		myRecipeBook.editRecipe(name, descriptions, instructions, ingredientsArrayList, categoryArrayList, position);
	}
	
}
