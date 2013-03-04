package com.foodbook.foodbook;

import com.foodbook.foodbook.R;

import android.os.Bundle;

public class FridgeActivity extends TitleBarOverride {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getApplicationContext();
		setContentView(R.layout.activity_main);

	}
}