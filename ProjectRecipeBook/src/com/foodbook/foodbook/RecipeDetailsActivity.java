package com.foodbook.foodbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * Displays a recipe and its features on screen. 
 * 
 * Each recipe has the following features: share, publish, download, edit, and delete. Features are only
 * displayed when relevant. 
 * 
 * @see Recipe
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */


public class RecipeDetailsActivity extends TitleBarOverride {
	
	private Intent in;
	
	private String recipeid;
	private String name;
	private String author;
	private String descriptions;
	private String instructions;
	private String ingredients;
	private String category;
	private String userid;
	
	private boolean onlineRecipe;
	
	private TextView nameField;
	private TextView authorField;
	private TextView descriptionsField;
	private TextView instructionsField;
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
		
		TextView categoryLabel = (TextView) findViewById(R.id.recipeDetails_categoryLabel);
		categoryLabel.setText("Category");
		TextView ingredientsLabel = (TextView) findViewById(R.id.recipeDetails_ingredientsLabel);
		ingredientsLabel.setText("Ingredients");

		// read intent
		in = getIntent();
		recipeid = in.getStringExtra("recipeid");
		name = in.getStringExtra("name");
		author = in.getStringExtra("author");
		descriptions = in.getStringExtra("descriptions");
		instructions = in.getStringExtra("instructions");
		ingredients = in.getStringExtra("ingredients").replace(", ", "\n").replace(",", "\n");
		category = in.getStringExtra("category").replace(", ", "\n").replace(",", "\n");
		userid = in.getStringExtra("userid");
		onlineRecipe = in.getBooleanExtra("onlineRecipe", false);
		
		// bind views to variables
		nameField = (TextView) findViewById(R.id.recipeDetails_foodName);
		authorField = (TextView) findViewById(R.id.recipeDetails_author);
		descriptionsField = (TextView) findViewById(R.id.recipeDetails_desc);
		instructionsField = (TextView) findViewById(R.id.recipeDetails_instructions);
		ingredientsField = (TextView) findViewById(R.id.recipeDetails_ingredients);
		categoryField = (TextView) findViewById(R.id.recipeDetails_category);

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
				RecipeBook.getInstance().deleteById(recipeid);
				finish();
			}
		});
		
		publishButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//	WebServiceClient wsb = new WebServiceClient();
			//	ArrayList<String> ingredientsList = new ArrayList<String>(Arrays.asList(ingredients));
			//	ArrayList<String> categoryList = new ArrayList<String>(Arrays.asList(category));
				//Recipe publish = new Recipe(name, descriptions, instructions, ingredientsList, categoryList, userid, author);
				
//				try {
//					wsb.insertRecipe(publish);
//				} catch (IllegalStateException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
			
				
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
				Intent photoManagerIntent = new Intent();
				photoManagerIntent.setClass(getApplicationContext(), PhotoManager.class);
				startActivity(photoManagerIntent);
			}
		});
		
		shareButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String formatRecipe = "Recipe: " + name + "\n\n" + "Ingredients: " + "\n" 
								+ ingredients + "\n\n" + "Instructions: " + "\n" + instructions 
								+ "\n\n" + "Description: " + "\n" + descriptions + "\n" 
								+ "Category: " + category + "\n\n" +
								"Author: " + author + "\n";
				
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				String[] recipients = new String[]{"my@email.com", "",};
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, RecipeBook.getInstance().getAuthor() + " shared " + name + " with you");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, formatRecipe);
				emailIntent.setType("text/plain");
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
				
			}
		});
        
		// update the textviews
		hideUnnecessaryButtons();
		updateTextViews();
	}
	
	/**
	 * Used to create a new intent directing to the edit recipe activity
	 * 
	 * 
	 */
	
	public void editRecipe() {
		Intent editIntent = new Intent();
		editIntent.setClass(getApplicationContext(), EditRecipeActivity.class);
		editIntent.putExtra("recipeid", recipeid);
		editIntent.putExtra("name", name);
		editIntent.putExtra("descriptions", descriptions);
		editIntent.putExtra("category", category.replace("\n", ", "));
		editIntent.putExtra("ingredients", ingredients.replace("\n", ", "));
		editIntent.putExtra("instructions", instructions);
		startActivity(editIntent);
	}
	
	@Override
	public void onNewIntent(Intent newIntent) {
		super.onNewIntent(newIntent);
		setIntent(newIntent);
	}
	
	/**
	 * 
	 * Displays a recipe's details on screen
	 * 
	 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
	 */
	
	public void updateTextViews() {
		nameField.setText(name);
		authorField.setText(author);
		descriptionsField.setText(descriptions);
		instructionsField.setText(instructions);
		ingredientsField.setText(ingredients);
		categoryField.setText(category);
	}
	
	/**
	* This methods hides unnecessary buttons. For instance, removes "downloads" buttons for local (cached) recipes,
	* or removes "edit" button for recipes that the owner (author) is not the user himself/herself.
	* 
	* 
	* @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
	*/
	
	private void hideUnnecessaryButtons() {
		if (!RecipeBook.getInstance().getUserid().equals(userid)) {
			editLayout.setVisibility(View.GONE);
			deleteLayout.setVisibility(View.GONE);
			publishLayout.setVisibility(View.GONE);
		}
		if (onlineRecipe) {
			publishLayout.setVisibility(View.GONE);
			editLayout.setVisibility(View.GONE);
		}
		else {
			downloadLayout.setVisibility(View.GONE);
		}
	}
	
}
