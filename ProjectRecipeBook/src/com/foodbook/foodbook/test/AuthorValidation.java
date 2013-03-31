package com.foodbook.foodbook.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.RecipeBook;

/**
 * <p>
 * This class test the creation of author
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */

@SuppressWarnings("rawtypes")
public class AuthorValidation extends ActivityInstrumentationTestCase2 {

	private RecipeBook testRecipeBook;

	String rName = "name";
	String rDesc = "desc";
	String rInst = "inst";
	ArrayList<String> rIng = new ArrayList<String>();
	ArrayList<String> rCate = new ArrayList<String>();
	String rUserID = "123";
	String rAuth = "auth";

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
	 * method to test User/author
	 */
	public void testAuthor() {
		try {
			testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);

			// assertTrue(testRecipeBook.getRecipeInfo(rAuth).contains());

		} catch (Exception e) {
			fail("Exception occurred");
		}
	}

}
