package com.foodbook.foodbook;

import android.content.Context;
import android.widget.Toast;

/**
 *
 * Subclass of PhotoManager.
 * Limits user from deleting pictures.
 * For downloaded recipes.
 * 
 */

public class PhotoManagerAddOnly extends PhotoManager {

	/**
	 *
	 * Called when user long-clicks on a picture.
	 * shows a toast saying that the user cannot do the action.
	 * 
	 */
	
	@Override
	protected void deletePic(int position, Context applicationContext) {
		Toast.makeText(this, "Not author: you can only view!", Toast.LENGTH_SHORT).show();
	}

}
