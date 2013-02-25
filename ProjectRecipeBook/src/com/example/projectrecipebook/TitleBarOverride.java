package com.example.projectrecipebook;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

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
 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater menuI = getMenuInflater();
    	menuI.inflate(R.menu.menu, menu);
    	
    	//Log.v("Tests", "menu size" + menu.size() );
    	
		return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
		
    	Intent recipebook = new Intent();
    	recipebook.setClass(getApplicationContext(), LocalRecipeBook.class);
    	
    	Intent makerecipe = new Intent();
    	makerecipe.setClass(getApplicationContext(), EditRecipeActivity.class);
    	
    	Intent postRecipe = new Intent();
    	postRecipe.setClass(getApplicationContext(), RecipeDetailsActivity.class);
		
	    switch (item.getItemId()) {
	    case R.id.menu_myRecipe:
	    	startActivity(recipebook);
	    	break;
	    case R.id.menu_makeRecipe:
	    	// make a new recipe object and feed in (TODO)
	    	startActivity(makerecipe);
	    	break;
	    case R.id.menu_postRecipe:
	    	// this is temporary. to be deleted. (TODO)
	    	startActivity(postRecipe);
	    	break;
	    default:
	      break;
	    }
	    return true;
	  } 
    
}