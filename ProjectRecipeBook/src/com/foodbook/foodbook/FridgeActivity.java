package com.foodbook.foodbook;

import java.util.Vector;

import com.foodbook.foodbook.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class FridgeActivity extends TitleBarOverride {

	
	final Context context = this;
	private String ingredient = "";
	private Button addButton;
	
	public static Vector <String> ingredientList = new Vector();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getApplicationContext();
		setContentView(R.layout.activity_main);

	}
	
	
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		addButton = (Button) findViewById(R.id.myFridgeAddButton);
		
		addButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				Log.v("tests", "ingredient String before " + ingredient);
				ingredientDialog();
				
				//Log.v("tests", "ingredientList " + ingredientList.get(0));
				Log.v("tests", "size of list before " + ingredientList.size());
				
			}
		});
				
		

		
	}
	
	
	private  void ingredientDialog(){
		
		
			AlertDialog.Builder alertdg = new AlertDialog.Builder(this); 
			alertdg.setTitle("Add Ingredient ");

			final EditText addText = new EditText(this); 
			//addText.setWidth(210); 
			

			LinearLayout layout = new LinearLayout(this); 

			
			layout.addView(addText); 
			alertdg.setView(layout); 

			alertdg.setPositiveButton("Save", new DialogInterface.OnClickListener() {

			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        // TODO Auto-generated method stub
			       
			        ingredient = addText.getText().toString(); 
			        Log.v("tests", "ingredient String: " + ingredient);
			        ingredientList.add(ingredient);
			        Log.v("tests", "ingredientList " + ingredientList.get(0));
			    }
			});

			alertdg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        // TODO Auto-generated method stub

			    }
			});
			alertdg.show(); 
			
		
	}
	
	
	
}