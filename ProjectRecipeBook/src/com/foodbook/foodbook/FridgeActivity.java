package com.foodbook.foodbook;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.foodbook.onlinemanager.OnlineDataBase;
import com.foodbook.onlinemanager.SearchResult;

/**
 * 
 * <p>
 * This class is an activity class for Ingredients.
 * </p>
 * <p>
 * Once a User has created an ingredient, it is displayed.
 * </p>
 * 
 * <p>
 * The User has the option to :
 * </p>
 * 
 * <ul>
 * <li>Add an ingredient</li>
 * <li>Edit an ingredient</li>
 * <li>Remove an ingredient</li>
 * <li>Search for recipes based on ingredients</li>
 * </ul>
 * 
 * @see Fridge
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */

public class FridgeActivity extends TitleBarOverride {

	public static RecipeBook myRecipeBook;

	private Fridge myFridge;
	private Context context;

	private ListView listView;
	private String ingredient;
	private Button addButton;
	private Button showWhatICanMakeButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getApplicationContext();
		setContentView(R.layout.activity_main);
		context = getApplicationContext();

		myFridge = new Fridge();
		myFridge.loadFromFile(getApplicationContext());

		myRecipeBook = new RecipeBook();
		boolean recipeLoadResultOK = myRecipeBook
				.loadFromFile(getApplicationContext());
		if (!recipeLoadResultOK) {
			askForNewName();
		}

		// setup List View
		listView = (ListView) findViewById(R.id.fridgeList);
		updateListView(myFridge, listView);
		listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showOptionDialog(position);
			}

		});

		// setup add button
		addButton = (Button) findViewById(R.id.myFridgeAddButton);
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showInputDialog(true, "", -1);
			}
		});

		// setup 'Show what I can make' button
		showWhatICanMakeButton = (Button) findViewById(R.id.fridgeSeeWhatICanMake);
		showWhatICanMakeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// local results of 'see what I can make' search
				ArrayList<String> localResults, localID;
				ArrayList<ArrayList<String>> localNamesAndIDs = RecipeBook.getNamesAndIDs(myRecipeBook.searchByIngredientsLocal(myFridge.getIngredients())); 
				localResults = localNamesAndIDs.get(0);
				localID = localNamesAndIDs.get(1);
				
				// online results of 'see what I can make' search
				ArrayList<String> onlineResults, onlineID;
				ArrayList<ArrayList<String>> onlineNamesAndIDs = RecipeBook.getNamesAndIDs(OnlineDataBase.searchByIngredientsOnline(myFridge.getIngredients()));
				onlineResults = onlineNamesAndIDs.get(0);
				onlineID = onlineNamesAndIDs.get(1);
				
				// display results
				Intent ingredientSeach = new Intent();
				ingredientSeach.setClass(getApplicationContext(), SearchResult.class);
				ingredientSeach.putExtra("localResults", localResults);
				ingredientSeach.putExtra("localID", localID);
				ingredientSeach.putExtra("onlineResults", onlineResults);
				ingredientSeach.putExtra("onlineID", onlineID);
				startActivity(ingredientSeach);
			}
		});
	}

	/**
	 * 
	 * Create an alert dialog to handle ingredient modification
	 * 
	 * @see AlertDialog
	 * 
	 * 
	 * @param add
	 *            User wants to add an ingredient
	 * @param defaultString
	 *            if the user does not add text to an ingredient a blank string
	 *            is assigned
	 * @param position
	 *            element at which the ingredient is added
	 */
	private void showInputDialog(final boolean add, String defaultString,
			final int position) {
		AlertDialog.Builder alertdg = new AlertDialog.Builder(this);
		alertdg.setTitle("Add Ingredient ");
		final EditText addText = new EditText(this);
		if (!add) {
			addText.setText(defaultString);
		}
		LinearLayout layout = new LinearLayout(this);
		layout.addView(addText);
		alertdg.setView(layout);
		alertdg.setPositiveButton("Save",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ingredient = addText.getText().toString();
						// TODO: empty string check
						if (add) {
							myFridge.addIngredient(ingredient);
						} else {
							myFridge.editIngredient(position, ingredient);
						}
						updateListView(myFridge, listView);
					}
				});
		alertdg.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// do nothing for "cancel"
					}
				});
		alertdg.show();
	}

	/**
	 * 
	 * Alert dialog created to show the options of an ingredient. The user is
	 * able to "Edit" or "Delete" an ingredient
	 * 
	 * @see AlertDialog
	 * @param position
	 *            element at which the selected ingredient is located
	 */
	private void showOptionDialog(final int position) {
		AlertDialog.Builder alertdg = new AlertDialog.Builder(this);
		final String titleOfDialog = myFridge.getIngredientAtIndex(position);
		alertdg.setTitle(titleOfDialog);
		alertdg.setPositiveButton("Delete",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						myFridge.removeIngredientByIndex(position);
						updateListView(myFridge, listView);
					}
				});
		alertdg.setNegativeButton("Edit",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showInputDialog(false, titleOfDialog, position);
					}
				});
		alertdg.show();
	}

	/**
	 * A toast to handle the event of an invalid input. If a User enters no
	 * text, the toast is displayed.
	 * 
	 * @see Toast
	 * 
	 */
	public void showInValidInputMessage() {
		Context context = getApplicationContext();
		CharSequence text = "Invalid Input";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	/**
	 * 
	 * A view in the MVC architecture. Handles displaying of ingredients to
	 * screen
	 * 
	 * @see ArrayAdapter
	 * 
	 * @param myFridgeToBeDisplayed
	 *            Fridge object being displayed
	 * @param listView
	 *            Space in which ingredients are listed
	 */

	private void updateListView(Fridge myFridgeToBeDisplayed, ListView listView) {
		ArrayList<String> ingredients = myFridgeToBeDisplayed.getIngredients();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ingredients);
		listView.setAdapter(adapter);
	}

	@Override
	public void onPause() {
		super.onPause();
		myFridge.saveToFile(getApplicationContext());
		myRecipeBook.saveToFile(getApplicationContext());
	}

}