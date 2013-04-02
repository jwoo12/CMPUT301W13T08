package com.foodbook.foodbook;

import java.io.IOException;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * Subclass of PhotoManager.
 * Does not allow user to add or delete any of pictures.
 * Used for online recipes. 
 *
 */

public class PhotoManagerViewOnly extends PhotoManagerAddOnly {

	/**
	 *
	 * Called when user clicks on "add" button.
	 * shows a toast saying that the user cannot do the action.
	 * 
	 */
	
	@Override
	public void addPhoto() throws IOException {
		Toast.makeText(this, "Online recipe: you can only view!", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 *
	 * Called when user long-clicks on a picture.
	 * shows a toast saying that the user cannot do the action.
	 * 
	 */
	
	@Override
	protected void deletePic(int position, Context applicationContext) {
		Toast.makeText(this, "Online recipe: you can only view!", Toast.LENGTH_SHORT).show();
	}

}