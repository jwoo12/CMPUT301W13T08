package com.foodbook.foodbook;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
	
	private final int BOGO_REQUEST_CODE = 1;
	
	protected String name;
	protected String descriptions;
	protected String category;
	protected String ingredients;
	protected String instructions;
	protected String recipeid;
	protected String author;
	
	protected EditText recipeNameField;
	protected EditText descriptionField;
	protected EditText categoryField;
	protected EditText ingredientsField;
	protected EditText instructionsField;
	
	protected ArrayList<String> categoryArrayList;
	protected ArrayList<String> ingredientsArrayList;
	
	protected ArrayList<Bitmap> pictures;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_recipe);
		
		//###################
		pictures = new ArrayList<Bitmap>();
		
		// retrieve recipe object from intent
		recipeid = getIntent().getStringExtra("recipeid");
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
					Context context = getApplicationContext();
					CharSequence text = "Incompleted recipe";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					return;
				}
				saveButtonClicked();
				RecipeBook.getInstance().saveToFile(getApplicationContext());
				finish();
			}
		});
		
		Button photoManagerButton = (Button) findViewById(R.id.editRecipePhotoManagerButton);
		photoManagerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent photoManagerIntent = new Intent();
				photoManagerIntent.setClass(getApplicationContext(), PhotoManager.class);
				startActivityForResult(photoManagerIntent, BOGO_REQUEST_CODE);
			}
		});
		
		// update text fields
		updateTextFields();
	}
	
	/**
	 * make an intent for RecipeDetailsActivity. 
	 * info about the recipe will be displayed in RecipeDetailsActivity.
	 * 
	 * 
	 * 
	 */
	
	protected void makeNewIntent() {
		
		
		
		Intent recipeDetailsIntent = new Intent();
		recipeDetailsIntent.setClass(getApplicationContext(), RecipeDetailsActivity.class);
		recipeDetailsIntent.putExtra("recipeid", recipeid);
		recipeDetailsIntent.putExtra("name", name);
		recipeDetailsIntent.putExtra("descriptions", descriptions);
		recipeDetailsIntent.putExtra("instructions", instructions);
		recipeDetailsIntent.putExtra("ingredients", ingredients);
		recipeDetailsIntent.putExtra("category", category);
		recipeDetailsIntent.putExtra("author", RecipeBook.getInstance().getAuthor());
		recipeDetailsIntent.putExtra("userid", RecipeBook.getInstance().getUserid());
		
		// start a new activity
		startActivity(recipeDetailsIntent);

	}
	
	/**
	* This function is used to check whether or not required fields are empty.
	* If a recipe is missing important information (ex. name), then this function will
	* return false.
	* 
	* 
	* @return false if any required fields are empty
	*/
	private boolean requiredInfoCheckOK() {
		
		if (name.equals("") || descriptions.equals("") || ingredients.equals("") || instructions.equals("")) {
			return false;
		}
		
		return true;
	}
	/**
	* This function sets the contents of text fields according to the recipe.
	* If recipe has no information (ie. when creating a new recipe), everything will be blank.
	* 
	* 
	* 
	*/
	private void updateTextFields() {
		
		
		if (recipeid == null) {
			// recipeid == null means it's a new recipe
			return;
		}
		
		recipeNameField.setText(name);
		descriptionField.setText(descriptions);
		categoryField.setText(category);
		ingredientsField.setText(ingredients);
		instructionsField.setText(instructions);
	}
	/**
	* This function reads contents of the edited text fields and saves them in global variables.
	* 
	* @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
	* 
	*/
	protected void readTextfields() {
		
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
	/**
	* This function edits the existing recipe object in the RecipeBook using the User edited text.
	* 
	* @see readTextfields 
	* 
	*/
	public void saveButtonClicked() {
		RecipeBook.getInstance().editRecipe(name, descriptions, instructions, ingredientsArrayList, categoryArrayList, recipeid);
	}
	
}
