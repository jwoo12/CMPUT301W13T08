package com.foodbook.foodbook.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.RecipeBook;

/**
 * <p>
 * This class when recipe is placed in the correct category/section, ie. all, mine, and download
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */

public class RecipeCategoryValidation extends ActivityInstrumentationTestCase2 {

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
	public RecipeCategoryValidation() {
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
	 * method to test that recipe created locally is saved in mine category
	 */
	public void testMineCategory() {
		try {
			int oldSize = testRecipeBook.getMine().size();
			testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
			int newSize = testRecipeBook.getMine().size();
			assertTrue(oldSize + 1 == newSize);

		} catch (Exception e) {
			fail("Exception occurred");
		}
	}

	/**
	 * method to test that recipe downloaded is saved in downloaded category
	 */
	public void testDownloadCategory() {
		try {
			// creating recipe should not go into download category test
			int oldSize = testRecipeBook.getDownloads().size();
			testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
			int newSize = testRecipeBook.getDownloads().size();
			assertFalse(oldSize + 1 == newSize);

		} catch (Exception e) {
			fail("Exception occurred");
		}
	}

	/**
	 * method to test that recipe downloaded/created locally is also saved in all category
	 */
	public void testAllCategory() {
		try {
			// creating recipe should not go into download category test
			int oldSize = testRecipeBook.getRecipeBook().size();
			testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
			int newSize = testRecipeBook.getRecipeBook().size();
			assertTrue(oldSize + 1 == newSize);

		} catch (Exception e) {
			fail("Exception occurred");
		}
	}

}
