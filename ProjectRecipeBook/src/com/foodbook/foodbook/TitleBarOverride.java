package com.foodbook.foodbook;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.foodbook.onlinemanager.OnlineSearch;

/*
 * Menu details. 
 * 
 * I've added some implementation to the "Actions" menu. Clicking the "My Recipes" option will 
 * bring the User to the (local) Recipe Book activity. Clicking the "Make Recipe" will 
 * go to the RecipeDetailsActivity. We can add the implementation for posting and searching
 * once we have the code for that :) Let me know if the way I coded this (will all the
 * intents and stuff) won't work with the rest of the code.  -Jasmine
 * 
 */

public class TitleBarOverride extends Activity {

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
	    	startActivityForResult(makeRecipe, 1);
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