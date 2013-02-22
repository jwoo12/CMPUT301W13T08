package com.example.projectrecipebook;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
public class TitleBarOverride extends Activity {
 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater menuI = getMenuInflater();
    	menuI.inflate(R.menu.menu, menu);
		return true;
    }
 
}