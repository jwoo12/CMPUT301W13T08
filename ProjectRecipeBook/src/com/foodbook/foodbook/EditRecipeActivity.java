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

	/**
	 * Setting onClick method for the save button
	 * 
	 */

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

	/**
	 * 
	 * Setting onClick method for the photo manager button
	 * 
	 */

	protected void activatePhotoManagerButton() {
		Button photoManagerButton = (Button) findViewById(R.id.editRecipePhotoManagerButton);
		photoManagerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openPhotoManager();
			}

		});
	}

	/**
	 * 
	 * Retrieves data from the intent
	 * 
	 */

	protected void readIntent() {
		recipeid = getIntent().getStringExtra("recipeid");
		name = getIntent().getStringExtra("name");
		desc = getIntent().getStringExtra("desc");
		category = StringOperations.intoOneString(getIntent().getStringArrayListExtra("category"), ", ");
		ingredients = StringOperations.intoOneString(getIntent().getStringArrayListExtra("ingredients"), ", ");
		inst = getIntent().getStringExtra("inst");
		pictures = RecipeBook.getInstance().getPicturesById(recipeid);
	}

	/**
	 * 
	 * Opens PhotoManager activity.
	 * Pictures belonging to the corresponding recipe, if there is any, will be sent to PhotoManager through intent.
	 * 
	 */

	protected void openPhotoManager() {
		Intent photoManagerIntent = new Intent();
		photoManagerIntent.setClass(getApplicationContext(), PhotoManager.class);
		if (recipeid != null && !photoManagerOpened) {
			photoManagerIntent.putExtra("pictures", RecipeBook.getInstance().getPicturesById(recipeid));
		} else if (photoManagerOpened) {
			photoManagerIntent.putExtra("pictures", pictures);
		}
		startActivity(photoManagerIntent);
	}

	/**
	 *
	 * Checks to see if the user filled out the required fields.
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
	 * 
	 * Sets the contents of textfields after retrieving information from intent.
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
	 *
	 * Reads text from textfields and converts them to string.
	 * Strings are saved as private attribute within the class.
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
	 * 
	 * Saves all changes done on the recipe.
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
