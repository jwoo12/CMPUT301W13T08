package com.foodbook.foodbook.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.foodbook.foodbook.Fridge;
import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.RecipeBook;

/**
 * <p>
 * This class is testing the ingredients in fridgeactivity test and then test the see what i
 * can make and it will show what they can make
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */
public class MakeValidation extends
		ActivityInstrumentationTestCase2<FridgeActivity> {
	private RecipeBook testRecipeBook;
	
	String rName = "name";
	String rDesc = "desc";
	String rInst = "inst";
	ArrayList<String> rIng = new ArrayList<String>();
	ArrayList<String> rCate = new ArrayList<String>();
	String rUserID = "123";
	String rAuth = "auth";
	
	String Ing1= "sugar";
	String Ingr2= "chicken";

	
	/**
	 * method for creating super for FridgeActivity
	 */
	public MakeValidation() {
		super("com.foodbook.foodbook.test", FridgeActivity.class);
	}

	/**
	 * method to create/setup testing
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testRecipeBook = new RecipeBook();

	}
	 /**
	 * method for testing the see what I can make button in fridgeActivity when 
	 * there are no ingredients
	 */
	 public void testNoMakeResult() {
	     try{
	    	//add how to check the result
			} catch (Exception e) {
	            fail("Exception occurred");
	        }
		 
	 }
	 /**
	 * method for testing the see what I can make button in fridgeActivity
	 */
	 public void testMakeResult() {
		 try{
			 rIng.add(Ing1);
			 rIng.add(Ingr2);
			 testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
			 
			 //add how to check the result
			} catch (Exception e) {
	            fail("Exception occurred");
	        }
		 
	 }

}
