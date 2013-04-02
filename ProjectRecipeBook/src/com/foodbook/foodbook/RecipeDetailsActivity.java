package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * Displays a recipe and its features on screen.
 * 
 * Each recipe has the following features: share, publish, download, edit, and delete. Features are only displayed when relevant.
 * 
 * @see Recipe
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */

public class RecipeDetailsActivity extends TitleBarOverride {

	private Context context;
	private boolean onlineRecipe;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_details);
		context = getApplicationContext();
		
		updateOnlineRecipeBoolean();
		setupButtons();
		hideUnnecessaryButtons();
		updateTextViews();

	}
	
	protected void updateOnlineRecipeBoolean() {
		onlineRecipe = getIntent().getBooleanExtra("onlineRecipe", false);
	}
	
	/**
	 * 
	 * Sets onClick methods for each button in the activity
	 * 
	 */

	protected void setupButtons() {

		Button editButton;
		Button deleteButton;
		Button publishButton;
		Button downloadButton;
		Button shareButton;
		Button photoManagerButton;

		photoManagerButton = (Button) findViewById(R.id.recipeDetails_photoButton);
		editButton = (Button) findViewById(R.id.recipeDetails_editButton);
		deleteButton = (Button) findViewById(R.id.recipeDetails_deleteButton);
		publishButton = (Button) findViewById(R.id.recipeDetails_publishButton);
		downloadButton = (Button) findViewById(R.id.recipeDetails_downloadButton);
		shareButton = (Button) findViewById(R.id.recipeDetails_shareButton);

		// set button listeners
		editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editRecipe();
			}
		});

		deleteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (onlineRecipe) {
					// TODO delete from server
				} else {
					RecipeBook.getInstance().deleteById(getIntent().getStringExtra("recipeid"));
				}
				finish();
			}
		});

		publishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				RecipeBook.getInstance().publishRecipeById(getIntent().getStringExtra("recipeid"));

				Toast.makeText(getApplicationContext(), "Recipe published!", Toast.LENGTH_SHORT).show();

			}

		});

		downloadButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ResultsBook.getInstance().download(getIntent().getStringExtra("recipeid"));
				Toast.makeText(getApplicationContext(), "Recipe downloaded!", Toast.LENGTH_SHORT).show();
			}
		});

		photoManagerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Pictures are separated from other attributes of recipe because the size is relatively large, and it slows down the application.

				Intent photoManagerIntent = new Intent();
				if (onlineRecipe) {
					// if the recipe is online recipe, view only
					photoManagerIntent.setClass(context, PhotoManagerViewOnly.class);
				} else {
					// if recipe is offline, check author.
					if (RecipeBook.getInstance().getUserid().equals(getIntent().getStringExtra("userid"))) {
						// if recipe is written by this user, full acess. (view/add/delete)
						photoManagerIntent.setClass(context, PhotoManager.class);
					} else {
						// if recipe is written by someone else, only add/view.
						photoManagerIntent.setClass(context, PhotoManagerAddOnly.class);
					}
				}

				ArrayList<String> pics = null;

				if (onlineRecipe) {
					// for online
					pics = ResultsBook.getInstance().getPicturesById(getIntent().getStringExtra("recipeid"));
				} else {
					// for offline
					pics = RecipeBook.getInstance().getPicturesById(getIntent().getStringExtra("recipeid"));
				}

				if (pics != null) {
					// pics is not null.. now check the size.
					if (pics.size() != 0) {
						// size isn't 0... now put it into the intent.
						photoManagerIntent.putStringArrayListExtra("pictures", pics);
					}
				}

				startActivity(photoManagerIntent);
			}
		});

		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String name = getIntent().getStringExtra("name");
				ArrayList<String> ingredientsArrayList = getIntent().getStringArrayListExtra("ingredients");
				String ingredients = StringOperations.intoOneString(ingredientsArrayList, ", ");
				String inst = getIntent().getStringExtra("inst");
				String desc = getIntent().getStringExtra("desc");
				ArrayList<String> categoryArrayList = getIntent().getStringArrayListExtra("category");
				String category = StringOperations.intoOneString(categoryArrayList, ", ");
				String author = getIntent().getStringExtra("author");
				String formatRecipe = "Recipe: " + name + "\n\n" + "Ingredients: " + "\n" + ingredients + "\n\n" + "Instructions: " + "\n" + inst + "\n\n" + "Description: " + "\n" + desc + "\n\n" + "Category: " + category + "\n\n" + "Author: " + author + "\n";

				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				String[] recipients = new String[] { "my@email.com", "", };
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, RecipeBook.getInstance().getAuthor() + " shared " + name + " with you");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, formatRecipe);
				emailIntent.setType("text/plain");
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));

			}
		});
	}

	/**
	 * 
	 * Opens EditRecipeActivity for the current recipe
	 * 
	 */
	
	public void editRecipe() {
		String recipeid = getIntent().getStringExtra("recipeid");
		Intent editActivityIntent = RecipeBook.getInstance().makeRecipeIntentFromRecipeID(recipeid);
		editActivityIntent.putExtra("pictures", RecipeBook.getInstance().getPicturesById(recipeid));
		editActivityIntent.setClass(getApplicationContext(), EditRecipeActivity.class);
		startActivity(editActivityIntent);
	}

	@Override
	public void onNewIntent(Intent newIntent) {
		super.onNewIntent(newIntent);
		setIntent(newIntent);
		updateOnlineRecipeBoolean();
		hideUnnecessaryButtons();
		updateTextViews();
	}

	/**
	 * 
	 * Displays a recipe's details on screen
	 * 
	 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
	 */

	public void updateTextViews() {

		TextView nameField = (TextView) findViewById(R.id.recipeDetails_foodName);
		TextView authorField = (TextView) findViewById(R.id.recipeDetails_author);
		TextView descField = (TextView) findViewById(R.id.recipeDetails_desc);
		TextView instField = (TextView) findViewById(R.id.recipeDetails_instructions);
		TextView ingredientsField = (TextView) findViewById(R.id.recipeDetails_ingredients);
		TextView categoryField = (TextView) findViewById(R.id.recipeDetails_category);

		Intent in = getIntent();
		nameField.setText(in.getStringExtra("name"));
		authorField.setText(in.getStringExtra("author"));
		descField.setText(in.getStringExtra("desc"));
		instField.setText(in.getStringExtra("inst"));
		ArrayList<String> ingredientsList = in.getStringArrayListExtra("ingredients");
		ingredientsField.setText(StringOperations.intoOneString(ingredientsList, "\n"));
		ArrayList<String> categoryList = in.getStringArrayListExtra("category");
		categoryField.setText(StringOperations.intoOneString(categoryList, "\n"));
	}

	/**
	 * This methods hides unnecessary buttons. For instance, removes "downloads" buttons for local (cached) recipes, or removes "edit" button for recipes that the owner (author) is not the user himself/herself.
	 * 
	 * 
	 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
	 */

	private void hideUnnecessaryButtons() {

		RelativeLayout editLayout = (RelativeLayout) findViewById(R.id.recipeDetails_editLayout);
		RelativeLayout deleteLayout = (RelativeLayout) findViewById(R.id.recipeDetails_deleteLayout);
		RelativeLayout publishLayout = (RelativeLayout) findViewById(R.id.recipeDetails_publishLayout);
		RelativeLayout downloadLayout = (RelativeLayout) findViewById(R.id.recipeDetails_downloadLayout);

		String useridOfThisRecipe = getIntent().getStringExtra("userid");
		
		if (!RecipeBook.getInstance().getUserid().equals(useridOfThisRecipe)) {
			editLayout.setVisibility(View.GONE);
		}
		if (onlineRecipe) {
			publishLayout.setVisibility(View.GONE);
			editLayout.setVisibility(View.GONE);
			if (!RecipeBook.getInstance().getUserid().equals(useridOfThisRecipe)) {
				// online recipe, and not yours, delete button invisible.
				deleteLayout.setVisibility(View.GONE);
			}
		} else {
			downloadLayout.setVisibility(View.GONE);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		PictureContainer container = PictureContainer.getInstance();
		ArrayList<String> pictures = container.getPics();
		if (pictures != null) {
			Log.v("mylog", "size: " + pictures.size());
			RecipeBook.getInstance().updatePic(getIntent().getStringExtra("recipeid"), pictures);
			container.reset();
		}
	}

}