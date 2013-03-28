package com.foodbook.foodbook;

import android.content.Context;
import android.widget.Toast;

public class PhotoManagerView extends PhotoManager {

	@Override
	protected void deletePic(int position, Context applicationContext) {
		Toast.makeText(this, "Not author: you can only add!", Toast.LENGTH_LONG).show();
	}

}