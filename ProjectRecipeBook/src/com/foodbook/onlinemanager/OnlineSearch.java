package com.foodbook.onlinemanager;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.foodbook.foodbook.R;
import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.TitleBarOverride;


/**
 * 
 * <p> Implementation for the ability to query recipes </p>
 * 
 * 
 * @see Recipe
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */

public class OnlineSearch extends TitleBarOverride {

	private EditText keywordField;
	private Button searchButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_search);
		
		keywordField = (EditText) findViewById(R.id.searchBar);
		searchButton = (Button) findViewById(R.id.searchButton);
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String keyword = keywordField.getText().toString();
				ArrayList<Recipe> onlineResults = OnlineDataBase.searchByKeyword(keyword);
				
				//				WebServiceClient wsc = new WebServiceClient();
//				try {
//					wsc.searchRecipes(keyword);
//				} catch (ClientProtocolException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
			
				
			}
		});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		keywordField.setText("");
	}
}

