package com.foodbook.foodbook;

import java.io.IOException;

import android.widget.Toast;

public class PhotoManagerViewOnly extends PhotoManagerAddOnly {

	@Override
	public void addPhoto() throws IOException {
		Toast.makeText(this, "Not author: you can only view!", Toast.LENGTH_LONG).show();
	}

}