package com.foodbook.foodbook.test;


import android.test.ActivityInstrumentationTestCase2;
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
	 * method to test the edit of an author but making sure the user id does not change
	 */
	public void testEditAuthor() {
		try{
			testRecipeBook.setAuthor(newAuth);
			String UserID = testRecipeBook.getUserid();
			assertTrue(testRecipeBook.getAuthor().equals(newAuth));	
			testRecipeBook.setAuthor(changeAuth);
			assertTrue(testRecipeBook.getAuthor().equals(changeAuth));	
			assertTrue(testRecipeBook.getUserid().equals(UserID));	
			} catch (Exception e) {
            fail("Exception occurred");
     	   }
   	 }
	
}




