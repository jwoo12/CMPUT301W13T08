package com.foodbook.onlinemanager;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.util.Log;
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
			
			public void onClick(View v) {
				final String keyword = keywordField.getText().toString(); //had to change to final for try block
				//ArrayList<Recipe> onlineResults = OnlineDataBase.searchByKeyword(keyword)*;
				
				
//				AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void> (){
//					
//					@Override
//					protected Void doInBackground(Void... arg0) {
//						
//						Log.v("tests", "checkpoint doInBackground");
//						
//						//ArrayList<Recipe> searchResult = null;
//						ArrayList<Recipe> onlineResults;
//						WebServiceClient wsc = new WebServiceClient();
//						try {
//							Log.v("tests", "checkpoint before search");
//							onlineResults = wsc.searchRecipes(keyword);
//						} catch (ClientProtocolException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						return null;
//						
//					}
//						
//				}.execute();
				

			
				
			}
		});
	}
	@Override
	public void onResume() {
		super.onResume();
		keywordField.setText("");
	}
}

