package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * <p>
 * This activity is used to change details of a selected recipe. Only the owner of the recipe can access this recipe.
 * </p>
 * 
 * 
 * 
 * @see Recipe
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */
public class EditRecipeActivity extends TitleBarOverride {

	protected String name;
	protected String desc;
	protected String category;
	protected String ingredients;
	protected String inst;
	protected String recipeid;
	protected String author;
	protected ArrayList<String> pictures;
	
	protected boolean photoManagerOpened = false;

	protected ArrayList<String> categoryArrayList;
	protected ArrayList<String> ingredientsArrayList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_recipe);

		readIntent();
		updateTextFields();
		activateSaveButton();
		activatePhotoManagerButton();
		
	}

	protected void activateSaveButton() {
		Button saveButton = (Button) findViewById(R.id.editRecipeSaveButton);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				readTextfields();
				if (!requiredInfoCheckOK()) {
					// tell user that recipe is incomplete
					Toast.makeText(getApplicationContext(), "Incomplete recipe", Toast.LENGTH_SHORT).show();
					return;
				}
				sendRecipeInfoToRecipeBook();
				RecipeBook.getInstance().saveToFile(getApplicationContext());
				openRecipeDetail();
				finish();
			}
		});
	}

	protected void activatePhotoManagerButton() {
		Button photoManagerButton = (Button) findViewById(R.id.editRecipePhotoManagerButton);
		photoManagerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openPhotoManager();
			}

		});
	}

	protected void readIntent() {
		recipeid = getIntent().getStringExtra("recipeid");
		name = getIntent().getStringExtra("name");
		desc = getIntent().getStringExtra("desc");
		category = StringOperations.intoOneString(getIntent().getStringArrayListExtra("category"), ", ");
		ingredients = StringOperations.intoOneString(getIntent().getStringArrayListExtra("ingredients"), ", ");
		inst = getIntent().getStringExtra("inst");
		pictures = RecipeBook.getInstance().getPicturesById(recipeid);
	}

	protected void openPhotoManager() {
		Intent photoManagerIntent = new Intent();
		photoManagerIntent.setClass(getApplicationContext(), PhotoManager.class);
		if (recipeid != null && !photoManagerOpened) {
			photoManagerIntent.putExtra("pictures", RecipeBook.getInstance().getPicturesById(recipeid));
		}
		else if (photoManagerOpened) {
			photoManagerIntent.putExtra("pictures", pictures);
		}
		startActivity(photoManagerIntent);
	}
	
	/**
	 * This function is used to check whether or not required fields are empty. If a recipe is missing important information (ex. name), then this function will return false.
	 * 
	 * 
	 * @return false if any required fields are empty
	 */
	protected boolean requiredInfoCheckOK() {

		if (name.equals("") || desc.equals("") || ingredients.equals("") || inst.equals("")) {
			return false;
		}

		return true;
	}

	/**
	 * This function sets the contents of text fields according to the recipe. If recipe has no information (ie. when creating a new recipe), everything will be blank.
	 * 
	 * 
	 * 
	 */
	protected void updateTextFields() {
		
		
		EditText recipeNameField = (EditText) findViewById(R.id.editRecipeName);
		EditText descField = (EditText) findViewById(R.id.editRecipeDesc);
		EditText categoryField = (EditText) findViewById(R.id.editRecipeCategory);
		EditText ingredientsField = (EditText) findViewById(R.id.editRecipeIngredients);
		EditText instField = (EditText) findViewById(R.id.editRecipeInst);
		
		recipeNameField.setText(name);
		descField.setText(desc);
		categoryField.setText(category);
		ingredientsField.setText(ingredients);
		instField.setText(inst);
		
	}

	/**
	 * This function reads contents of the edited text fields and saves them in global variables.
	 * 
	 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
	 * 
	 */
	protected void readTextfields() {

		EditText recipeNameField = (EditText) findViewById(R.id.editRecipeName);
		EditText descField = (EditText) findViewById(R.id.editRecipeDesc);
		EditText categoryField = (EditText) findViewById(R.id.editRecipeCategory);
		EditText ingredientsField = (EditText) findViewById(R.id.editRecipeIngredients);
		EditText instField = (EditText) findViewById(R.id.editRecipeInst);
		
		// these are regular strings
		name = recipeNameField.getText().toString();
		desc = descField.getText().toString();
		inst = instField.getText().toString();

		// these are arraylists
		category = categoryField.getText().toString();
		categoryArrayList = StringOperations.formatArray(StringOperations.SplitToArrayList(category, ","));
		ingredients = ingredientsField.getText().toString();
		ingredientsArrayList = StringOperations.formatArray(StringOperations.SplitToArrayList(ingredients, ","));

	}

	/**
	 * This function edits the existing recipe object in the RecipeBook using the User edited text.
	 * 
	 * @see readTextfields
	 * 
	 */

	public void sendRecipeInfoToRecipeBook() {
		RecipeBook.getInstance().editRecipe(name, desc, inst, ingredientsArrayList, categoryArrayList, recipeid, pictures);
	}

	@Override
	public void onResume() {
		super.onResume();
		PictureContainer container = PictureContainer.getInstance();
		ArrayList<String> newPictures = container.getPics();
		if (newPictures != null) {
			Log.v("mylog", "size: " + newPictures.size());
			pictures = newPictures;
			photoManagerOpened = true;
			container.reset();
		}
	}
	
	/**
	 * makes an intent for RecipeDetailsActivity. info about the recipe will be displayed in RecipeDetailsActivity.
	 * 
	 * 
	 */

	protected void openRecipeDetail() {

		Intent recipeDetailsIntent = RecipeBook.getInstance().makeRecipeIntentFromRecipeID(recipeid);
		recipeDetailsIntent.setClass(getApplicationContext(), RecipeDetailsActivity.class);
		startActivity(recipeDetailsIntent);

	}
}
