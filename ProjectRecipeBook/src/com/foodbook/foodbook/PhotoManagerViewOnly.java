package com.foodbook.foodbook;

import java.io.IOException;

import android.content.Context;
import android.widget.Toast;

public class PhotoManagerViewOnly extends PhotoManagerAddOnly {

	@Override
	public void addPhoto() throws IOException {
		Toast.makeText(this, "Online recipe: you can only view!", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void deletePic(int position, Context applicationContext) {
		Toast.makeText(this, "Online recipe: you can only view!", Toast.LENGTH_SHORT).show();
	}

}