package com.foodbook.foodbook;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

	private TextView nameField;
	private TextView authorField;
	private TextView descField;
	private TextView instField;
	private TextView ingredientsField;
	private TextView categoryField;

	private RelativeLayout editLayout;
	private Button editButton;
	private RelativeLayout deleteLayout;
	private Button deleteButton;
	private RelativeLayout publishLayout;
	private Button publishButton;
	private RelativeLayout downloadLayout;
	private Button downloadButton;
	private Button shareButton;
	private Button photoManagerButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_details);
		context = getApplicationContext();

		Log.v("", "setting up fields");
		setupTextFields();
		Log.v("", "setting up buttons");
		setupButtons();
		Log.v("", "hiding buttons");
		hideUnnecessaryButtons();
		Log.v("", "updating textview");
		updateTextViews();
		Log.v("", "recipe details oncreate done");

	}

	protected void setupButtons() {
		// bind buttons (and layouts containing buttons) to variables
		photoManagerButton = (Button) findViewById(R.id.recipeDetails_photoButton);
		editLayout = (RelativeLayout) findViewById(R.id.recipeDetails_editLayout);
		editButton = (Button) findViewById(R.id.recipeDetails_editButton);
		deleteLayout = (RelativeLayout) findViewById(R.id.recipeDetails_deleteLayout);
		deleteButton = (Button) findViewById(R.id.recipeDetails_deleteButton);
		publishLayout = (RelativeLayout) findViewById(R.id.recipeDetails_publishLayout);
		publishButton = (Button) findViewById(R.id.recipeDetails_publishButton);
		downloadLayout = (RelativeLayout) findViewById(R.id.recipeDetails_downloadLayout);
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
				RecipeBook.getInstance().deleteById(getIntent().getStringExtra("recipeid"));
				finish();
			}
		});

		publishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				/*
				 * 
				 * final WebServiceClient wsb = new WebServiceClient();
				 * 
				 * ArrayList<String> ingredientsList = new ArrayList<String>(Arrays.asList()); ArrayList<String> categoryList = new ArrayList<String>(Arrays.asList(category)); final Recipe publish = new Recipe(name, desc, inst, ingredientsList, categoryList, userid, author, null);
				 * 
				 * ArrayList<String> testIng = new ArrayList<String>();
				 * 
				 * testIng.add("ingredient");
				 * 
				 * ArrayList<String> testCateg = new ArrayList<String>(); testCateg.add("category");
				 * 
				 * AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
				 * 
				 * @Override protected Void doInBackground(Void... arg0) {
				 * 
				 * try { // WebServiceClient wsb = new WebServiceClient(); Log.v("tests", "checkpoint " + "AsyncTask begin"); wsb.insertRecipe(publish); } catch (IllegalStateException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
				 * 
				 * return null; }
				 * 
				 * };
				 * 
				 * task.execute();
				 * 
				 * // TODO add conditionals
				 * 
				 * Context context = getApplicationContext(); CharSequence text = "Recipe published!"; int duration = Toast.LENGTH_SHORT;
				 * 
				 * Toast toast = Toast.makeText(context, text, duration); toast.setGravity(Gravity.TOP | Gravity.LEFT, 300, 100); toast.show();
				 */
			}

		});

		downloadButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO download
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
					if (RecipeBook.getInstance().getUserid().equals(RecipeBook.getInstance().getUserid())) {
						// if recipe is written by this user, full acess. (view/add/delete)
						photoManagerIntent.setClass(context, PhotoManager.class);
					} else {
						// if recipe is written by someone else, only add/view.
						photoManagerIntent.setClass(context, PhotoManagerAddOnly.class);
					}
				}

				ArrayList<String> pics = RecipeBook.getInstance().getPicturesById(getIntent().getStringExtra("recipeid"));

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

	protected void setupTextFields() {
		nameField = (TextView) findViewById(R.id.recipeDetails_foodName);
		authorField = (TextView) findViewById(R.id.recipeDetails_author);
		descField = (TextView) findViewById(R.id.recipeDetails_desc);
		instField = (TextView) findViewById(R.id.recipeDetails_instructions);
		ingredientsField = (TextView) findViewById(R.id.recipeDetails_ingredients);
		categoryField = (TextView) findViewById(R.id.recipeDetails_category);
	}

	/**
	 * Used to create a new intent directing to the edit recipe activity
	 * 
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
		if (!RecipeBook.getInstance().getUserid().equals(RecipeBook.getInstance().getUserid())) {
			editLayout.setVisibility(View.GONE);
			deleteLayout.setVisibility(View.GONE);
			publishLayout.setVisibility(View.GONE);
		}
		if (getIntent().getBooleanExtra("onlineRecipe", false)) {
			publishLayout.setVisibility(View.GONE);
			editLayout.setVisibility(View.GONE);
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
