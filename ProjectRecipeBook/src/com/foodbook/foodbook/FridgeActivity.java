package com.foodbook.foodbook;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class FridgeActivity extends TitleBarOverride {

	private Fridge myFridge;
	private Context context;
	private ListView listView;
	
	private String ingredient = "";
	private Button addButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		getApplicationContext();
		setContentView(R.layout.activity_main);
		context = getApplicationContext();
		myFridge = new Fridge(context);
		
		// setup List View
		listView = (ListView) findViewById(R.id.fridgeList);
		updateListView(myFridge, listView);
		listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
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
	}
	
	private void showInputDialog(final boolean add, String defaultString, final int position) {
		AlertDialog.Builder alertdg = new AlertDialog.Builder(this);
		alertdg.setTitle("Add Ingredient ");
		final EditText addText = new EditText(this);
		if (!add) {
			addText.setText(defaultString);
		}
		LinearLayout layout = new LinearLayout(this);
		layout.addView(addText);
		alertdg.setView(layout);
		alertdg.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ingredient = addText.getText().toString();
				if (add) {
					myFridge.addIngredient(ingredient);
				}
				else {
					myFridge.editIngredient(position, ingredient);
				}
				updateListView(myFridge, listView);
			}
		});
		alertdg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// do nothing for "cancel"
			}
		});
		alertdg.show(); 
	}
	
	private void showOptionDialog(final int position) {
		AlertDialog.Builder alertdg = new AlertDialog.Builder(this);
		final String titleOfDialog = myFridge.getIngredientAtIndex(position);
		alertdg.setTitle(titleOfDialog);
		alertdg.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				myFridge.removeIngredientByIndex(position);
				updateListView(myFridge, listView);
			}
		});
		alertdg.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				showInputDialog(false, titleOfDialog, position);
			}
		});
		alertdg.show(); 
	}
	
	private void updateListView(Fridge myFridgeToBeDisplayed, ListView listView) {
		ArrayList<String> ingredients = myFridgeToBeDisplayed.getIngredients();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredients);
		listView.setAdapter(adapter);
	}
	
}