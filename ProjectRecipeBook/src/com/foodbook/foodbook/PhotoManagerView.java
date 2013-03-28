package com.foodbook.foodbook;

import java.io.IOException;

import android.content.Context;
import android.widget.Toast;

public class PhotoManagerView extends PhotoManager {

	@Override
	protected void deletePic(int position, Context applicationContext) {
		Toast.makeText(this, "Not author: you can only view!", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void addPhoto() throws IOException {
		Toast.makeText(this, "Not author: you can only view!", Toast.LENGTH_LONG).show();
	}

}