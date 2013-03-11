package com.foodbook.foodbook.test;

import java.util.ArrayList;

import org.junit.Test;

import android.widget.EditText;
import android.widget.ListView;

import com.foodbook.foodbook.Fridge;
import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.R;  
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

/**
 * <p> This class is testing the ingredients in fridgeactivity
 * test add an ingredient
 * test edit an ingredient
 * test delete an ingredient
 * test see what i can make and it will show what they can make
 *  </p>
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */
public class IngredientValidation extends ActivityInstrumentationTestCase2 <FridgeActivity> {
	private ListView testlistView;  
	private ListView results;
	private Fridge testFridge;
	private Context testcontext;
	private String ingredient;
	
	
	
	/**
	 * method for creating super for FridgeActivity
	 */
	public IngredientValidation() {
		super("com.foodbook.foodbook.test",FridgeActivity.class);
	}
	/**
	 * method to create/setup testing
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		FridgeActivity fridgeActivity = getActivity(); 
		testFridge = new Fridge(testcontext);
		EditText addText = new EditText(getActivity());
		testlistView = (ListView) fridgeActivity.findViewById(R.id.fridgeList);
		results = (ListView) fridgeActivity.findViewById(R.id.fridgeList);
		
	}

	private static final String INGRED_TEA = "T E A";
    private static final String INGRED_SUGAR = "S U G A R";
    private static final String INGRED_GREEN = "G R E E N SPACE";
    private static final String INGRED_GREENTEA = "G R E E N SPACE T E A";
    
    /**
     * method for testing adding ingredients in fridgeActivity
     */
    public void testAddIngredients() {
    	//testFridge = new Fridge(INGRED_TEA);
        // we use sendKeys instead of setText so it goes through entry
        // click on add tab
    	ingredient =INGRED_TEA;
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	//add ingredient
        sendKeys(INGRED_TEA);
        testFridge.addIngredient(ingredient);
        //click on save tab
        sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	
        // get result	
    	ArrayList<String> temp = new ArrayList<String>();
    	temp.addAll(testFridge.getIngredients());

    	assertTrue("added ingredient should be "+INGRED_TEA, temp.get(0).contains(INGRED_TEA)); 
    	assertTrue(testlistView.getChildCount()==1); 

    }
    /**
     * method for testing editing ingredients in fridgeActivity
     */
    public void testEditIngredients() {
        // we use sendKeys instead of setText so it goes through entry
        // click on first ingredient
    	// click on add tab
    	ingredient =INGRED_GREENTEA;
    	testFridge.addIngredient(ingredient);
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	//add ingredient
        sendKeys(INGRED_TEA);
        //click on save tab
        sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	//click on edit
    	sendKeys("ENTER");
    	//EDIT ingredient
        sendKeys(INGRED_GREEN);
        //click on save tab
        sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");

    	 // get result
    	ArrayList<String> temp = new ArrayList<String>();
    	temp.addAll(testFridge.getIngredients());
    	
    	//***delete later
    	Log.v("tagd", "temp itemd " + temp.get(0));
    	Log.v("tag", "temp item " + temp.size());
    	Log.v("tag", "jhlsdf item " + testlistView.getChildCount());

    	assertTrue("added ingredient should be "+INGRED_TEA, temp.get(0).contains(INGRED_GREENTEA)); 
    	assertTrue("added ingredient list should be 1", testlistView.getChildCount()==1); 
    }
    /**
     * method for testing deleting ingredients in fridgeActivity
     */
    public void testDeleteIngredients() {
        // we use sendKeys instead of setText so it goes through entry
        // click on first ingredient
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	//add ingredient
        sendKeys(INGRED_TEA);
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	//click on delete
    	sendKeys("TAB");
    	sendKeys("TAB");    
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	 // get result
    	ArrayList<String> temp = new ArrayList<String>();
    	temp.addAll(testFridge.getIngredients());

    	assertTrue("added ingredient list should be 1", testlistView.getChildCount()==0); 
    }

    /**
     * method for testing the see what I can make button in fridgeActivity
     */
    public void testMakeResult() {
        // we use sendKeys instead of setText so it goes through entry
        // click on see what i can make button
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	//add ingredient
        sendKeys(INGRED_TEA);
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");
    	//click on see what i can make button
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("TAB");
    	sendKeys("ENTER");

    	// get result
    	assertTrue("list result contains only the ingredient in the fridge", results.getChildCount()==2); 
 
    }
}

