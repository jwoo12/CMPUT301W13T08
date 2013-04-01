package com.foodbook.foodbook.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.foodbook.foodbook.Fridge;
import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.Recipe;
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
	private Fridge TestFridge; 
	ArrayList<String> testlocalResults, testlocalID;
	
	String rName = "name";
	String rDesc = "desc";
	String rInst = "inst";
	ArrayList<String> rIng = new ArrayList<String>();
	ArrayList<String> rCate = new ArrayList<String>();
	String rUserID = "123";
	String rAuth = "auth";
	
	String Ing1= "sugar";
	String Ingr2= "chicken";
	String Ingr3= "milk";

	
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
		testRecipeBook = RecipeBook.getInstance();
		TestFridge = Fridge.getInstance();

	}
	 /**
	 * method for testing the see what I can make button in fridgeActivity when 
	 * there are no ingredients
	 */
	 public void testNoMakeResult() {
	     try{
	    	 //fridge does not contain Ingr3 to make a recipe
	    	 TestFridge.addIngredient(Ing1);
	    	 TestFridge.addIngredient(Ingr2);
	    	// rIng.add(Ing1);
			// rIng.add(Ingr2);
			// rIng.add(Ingr3);
			 testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
			 ArrayList<ArrayList<String>> testlocalNamesAndIDs = RecipeBook.getNamesAndIDs(testRecipeBook.searchByIngredientsLocal(TestFridge.getIngredients()));
			 testlocalResults = testlocalNamesAndIDs.get(0);
			 testlocalID = testlocalNamesAndIDs.get(1);
			 Log.i("****",testlocalResults.toString());
			 //TODO

			 TestFridge.clearFridge();
			} catch (Exception e) {
	            fail("Exception occurred");
	        }
		 
	 }
	 /**
	 * method for testing the see what I can make button in fridgeActivity
	 */
	 public void testMakeResult() {
		 try{
	    	 TestFridge.addIngredient(Ing1);
	    	 TestFridge.addIngredient(Ingr2);
	    	 TestFridge.addIngredient(Ingr3);
	    	 rIng.add(Ing1);
			 rIng.add(Ingr2);
			 rIng.add(Ingr3);
			 testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
			 //TODO
			 //add how to check the result
			 TestFridge.clearFridge();
			} catch (Exception e) {
	            fail("Exception occurred");
	        }
		 
	 }

}
