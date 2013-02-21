package com.example.projectrecipebook;

import android.R.color;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class TitleBarOverride extends Activity {

	public class AndroidTitleBarActivity extends Activity {
		Color color;

		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			View title = getWindow().findViewById(android.R.id.title);
			View titleBar = (View) title.getParent();
			titleBar.setBackgroundColor(R.style.AppTheme);

		}
	}
}
