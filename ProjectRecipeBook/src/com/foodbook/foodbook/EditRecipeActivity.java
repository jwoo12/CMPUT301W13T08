package com.foodbook.foodbook;



import com.foodbook.foodbook.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//No comment
public class EditRecipeActivity extends TitleBarOverride {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_recipe);
	}
	
	public void saveButtonClicked(View view) {
		Intent intent = new Intent(this, FridgeActivity.class);
		
		startActivity(intent);
		finish();
	}
	
}
