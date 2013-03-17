package com.foodbook.foodbook;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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
	
	Uri imageFileUri;
	
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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_recipe);
		
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
		
		//take picture
		ImageButton picturebutton = (ImageButton) findViewById(R.id.TakeAPhoto);
        OnClickListener listener = new OnClickListener() {
            public void onClick(View v){
                takeAPhotoClicked();
            }
        };
        picturebutton.setOnClickListener(listener);
        
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
				finish();
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
		recipeDetailsIntent.putExtra("author", FridgeActivity.myRecipeBook.getAuthor());
		recipeDetailsIntent.putExtra("userid", FridgeActivity.myRecipeBook.getUserid());
		
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
		FridgeActivity.myRecipeBook.editRecipe(name, descriptions, instructions, ingredientsArrayList, categoryArrayList, recipeid);
	}
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	public void takeAPhotoClicked() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        
        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
        File folderF = new File(folder);
        if (!folderF.exists()) {
            folderF.mkdir();
        }
        //store image for app
        String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
        File imageFile = new File(imageFilePath);
        imageFileUri = Uri.fromFile(imageFile);
        
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
           // TextView tv = (TextView) findViewById(R.id.status);
            if (resultCode == RESULT_OK) {
                //tv.setText("Photo OK!");
                ImageButton button = (ImageButton) findViewById(R.id.TakeAPhoto);
                button.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
            } else if (resultCode == RESULT_CANCELED) {
              //  tv.setText("Photo canceled");
            } else {
              //  tv.setText("Not sure what happened!" + resultCode);
            }
        }
    }
	
}
