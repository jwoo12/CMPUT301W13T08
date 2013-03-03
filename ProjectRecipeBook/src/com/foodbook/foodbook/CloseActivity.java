package com.foodbook.foodbook;

import android.os.Bundle;

public class CloseActivity extends ViewRecipeBookActivity {
	
	
	@Override
	public void onPause() {
		super.onPause();
		finish();
	}

}
