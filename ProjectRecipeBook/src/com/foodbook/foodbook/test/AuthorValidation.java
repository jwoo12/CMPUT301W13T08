package com.foodbook.foodbook.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.RecipeBook;


/**
 * <p>
 * This class test the creation of author 
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */

@SuppressWarnings("rawtypes")
public class AuthorValidation extends ActivityInstrumentationTestCase2 {

	private RecipeBook testRecipeBook;
	private ArrayList<Recipe> downloadrecipe;
	
	Recipe downloadRecipe = null;

	String newAuth = "newauth";
	String changeAuth = "changeauth";
	String DownloadAuth = "Downloadauth";

	/**
	 * method to inherit from fridgeactivity
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public AuthorValidation() {
		super("com.foodbook.foodbook.test", FridgeActivity.class);
	}

	/**
	 * method create a new setup/activity
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testRecipeBook = RecipeBook.getInstance();
	}

	/**
	 * method to test the correct author
	 */
	public void testAuthor() {
		try{
			testRecipeBook.setAuthor(newAuth);
			assertTrue(testRecipeBook.getAuthor().equals(newAuth));	
			} catch (Exception e) {
            fail("Exception occurred");
     	   }
   	 }
	/**
	 * method to test the edit of an author
	 */
	public void testEditAuthor() {
		try{
			testRecipeBook.setAuthor(newAuth);
			assertTrue(testRecipeBook.getAuthor().equals(newAuth));	
			testRecipeBook.setAuthor(changeAuth);
			assertTrue(testRecipeBook.getAuthor().equals(changeAuth));	
			} catch (Exception e) {
            fail("Exception occurred");
     	   }
   	 }
	/**
	 * method to test that downloaded recipes kept the original author's name
	 */
	public void testdownloadedAuthor() {
		try{
			//set author
			downloadRecipe.setAuthor("somethong");
			Log.i("******", testRecipeBook.getDownloads().toString());
			assertTrue(downloadRecipe.getauthor().equals(DownloadAuth));	
			//TODO
			} catch (Exception e) {
            fail("Exception occurred");
        }
    }

	
}




