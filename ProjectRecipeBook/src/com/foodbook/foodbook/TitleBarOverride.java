package com.foodbook.foodbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.foodbook.onlinemanager.OnlineSearch;

public class TitleBarOverride extends Activity {
	
	/**
	 * This class contains the implementation of title bar menu button.
	 * This button can be used to navigate through different activities within the app.
	 * Since this class extends Activity, all activities that need to have the button will
	 * extend this one, so that they will all have button and behave as activities.
	 */
	

	RecipeBook myRecipeBook;
	Fridge myFridge;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater menuI = getMenuInflater();
    	menuI.inflate(R.menu.menu, menu);
    	
		return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	
	    switch (item.getItemId()) {
	    case R.id.menu_myRecipe:
	    	Intent myRecipeBook = new Intent();
	    	myRecipeBook.setClass(getApplicationContext(), RecipeBookActivity.class);
	    	startActivity(myRecipeBook);
	    	break;
	    case R.id.menu_makeRecipe:
	    	Intent makeRecipe = new Intent();
	    	makeRecipe.setClass(getApplicationContext(), MakeRecipeActivity.class);
	    	startActivity(makeRecipe);
	    	break;
	    case R.id.menu_postRecipe:
	    	Intent postRecipe = new Intent();
	    	postRecipe.setClass(getApplicationContext(), RecipeBookActivity.class);
	    	postRecipe.putExtra("showMineOnly", true);
	    	startActivity(postRecipe);
	    	break;
	    case R.id.menu_searchOnline:
	    	Intent search = new Intent();
	    	search.setClass(getApplicationContext(), OnlineSearch.class);
	    	startActivity(search);
	    default:
	      break;
	    }
	    return true;
	  } 
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent) {
    	//TODO
    }
    
}