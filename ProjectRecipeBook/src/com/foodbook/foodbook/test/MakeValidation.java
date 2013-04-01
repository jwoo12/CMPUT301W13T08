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
	
	String rName = "RecipeName";
	String rDesc = "desc";
	String rInst = "inst";
	ArrayList<String> rIng = new ArrayList<String>();
	ArrayList<String> rIng2= new ArrayList<String>();
	ArrayList<String> rIng3= new ArrayList<String>();
	ArrayList<String> rCate = new ArrayList<String>();
	String rUserID = "123";
	String rAuth = "auth";
	
	String Ing1= "sugar";
	String Ingr2= "chicken";
	String Ingr3= "milk";
	String Ingr4= "bread";


	
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
		//add ingredient to test recipe 
		rIng.add(Ing1);
		rIng.add(Ingr2);
		rIng.add(Ingr3);
		rIng2.add(Ingr3);
		rIng3.add(Ingr4);
		testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
		testRecipeBook.addRecipe("Recipe", "description", "simpleinstruction", rIng2, rCate, null);
		testRecipeBook.addRecipe("cannotMakeRecipe", "thirddescription", "thirdinstruction", rIng3, rCate, null);

	}
	 /**
	 * method for testing the see what I can make button in fridgeActivity when 
	 * there are no ingredients
	 */
	 public void testNoMakeResult() {
	     try{
	    	 //fridge does not contain any ingredient to make a recipe
			 ArrayList<ArrayList<String>> testlocalNamesAndIDs = RecipeBook.getNamesAndIDs(testRecipeBook.searchByIngredientsLocal(TestFridge.getIngredients()));
			 testlocalResults = testlocalNamesAndIDs.get(0);
			 Log.i("******", testlocalResults.toString());
			 assertTrue(testlocalResults.isEmpty());
			 TestFridge.clearFridge();
			} catch (Exception e) {
	            fail("Exception occurred");
	        }
		 
	 }
	 /**
	 * method for testing the "see what I can make" button in fridgeActivity returns the correct results with 1 recipe added
	 */
	 public void testMakeResult() {
		 try{
			 //all ingredients required in a recipe is now testFridge 
	    	 TestFridge.addIngredient(Ing1);
	    	 TestFridge.addIngredient(Ingr2);
	    	 TestFridge.addIngredient(Ingr3);
			 testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
			 ArrayList<ArrayList<String>> testlocalNamesAndIDs = RecipeBook.getNamesAndIDs(testRecipeBook.searchByIngredientsLocal(TestFridge.getIngredients()));
			 testlocalResults = testlocalNamesAndIDs.get(0);
			 assertTrue(testlocalResults.contains(rName));
			 TestFridge.clearFridge();
			} catch (Exception e) {
	            fail("Exception occurred");
	        }
	 }
	 /**
	 * method for testing the "see what I can make" button in fridgeActivity returns the correct results with 3 recipe added.
	 * It should return 2 results. 
	 */
	 public void testMakeResult2() {
		 try{
			 //all ingredients required in a recipe is now testFridge 
	    	 TestFridge.addIngredient(Ing1);
	    	 TestFridge.addIngredient(Ingr2);
	    	 TestFridge.addIngredient(Ingr3);
			 ArrayList<ArrayList<String>> testlocalNamesAndIDs = RecipeBook.getNamesAndIDs(testRecipeBook.searchByIngredientsLocal(TestFridge.getIngredients()));
			 testlocalResults = testlocalNamesAndIDs.get(0);
			
			 //contains the first 2 recipe but not the third
			 assertTrue(testlocalResults.contains(rName));
			 assertTrue(testlocalResults.contains("Recipe"));
			 assertFalse(testlocalResults.contains("cannotMakeRecipe"));
			 TestFridge.clearFridge();
			} catch (Exception e) {
	            fail("Exception occurred");
	        }
	 }

}
