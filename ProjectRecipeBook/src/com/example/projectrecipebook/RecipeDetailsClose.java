package com.example.projectrecipebook;

import android.os.Bundle;

public class RecipeDetailsClose extends RecipeDetailsActivity {
	
	
	@Override
	public void onPause() {
		super.onPause();
		finish();
	}

}
