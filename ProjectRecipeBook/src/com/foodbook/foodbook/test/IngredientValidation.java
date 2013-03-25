package com.foodbook.foodbook.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.foodbook.foodbook.Fridge;
import com.foodbook.foodbook.FridgeActivity;

/**
 * <p>
 * This class is testing the ingredients in fridgeactivity test add an
 * ingredient test edit an ingredient test delete an ingredient test see what i
 * can make and it will show what they can make
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */
public class IngredientValidation extends
		ActivityInstrumentationTestCase2<FridgeActivity> {
	private Fridge testFridge;

	
	/**
	 * method for creating super for FridgeActivity
	 */
	public IngredientValidation() {
		super("com.foodbook.foodbook.test", FridgeActivity.class);
	}

	/**
	 * method to create/setup testing
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testFridge = new Fridge();

	}


	/**
	 * method for testing adding ingredients in fridgeActivity.
	 * 
	 * If the app is running for the first time, it prompts for username, and
	 * this will interfere with the test. So the test should not be ran when the
	 * app hasn't been opened after installation.
	 * 
	 */
	public void testAddIngredients() {
		try {

			// add a dummy entry into the testFridge
			testFridge.addIngredient("dummy entry");

			// get result
			ArrayList<String> temp = new ArrayList<String>();

			// check if the dummy entry is in fridge
			assertTrue("added ingredient should ", testFridge.getIngredients()
					.contains("dummy entry"));
		} catch (Exception e) {
			fail("Exception occurred");
		}
	}

	/**
	 * method for testing editing ingredients in fridgeActivity
	 */
	public void testEditIngredients() {
		try {// we use sendKeys instead of setText so it goes through entry
				// click on first ingredient
				// click on add tab
			String oldName = "oldoldoldname";
			String newName = "newnewnewname";
			testFridge.addIngredient(oldName);
			testFridge.editIngredientByName(oldName, newName);

			assertTrue("the fridge now contains newnewnewname", testFridge
					.getIngredients().contains(newName));
		} catch (Exception e) {
			fail("Exception occurred");
		}
	}

	/**
	 * method for testing deleting ingredients in fridgeActivity
	 */
	public void testDeleteIngredients() {
		try {
			String toBeDeleted = "this should be deleted";
			testFridge.addIngredient(toBeDeleted);
			assertTrue(testFridge.getIngredients().contains(toBeDeleted));
			
			testFridge.removeIngredientByName(toBeDeleted);
			assertFalse(testFridge.getIngredients().contains(toBeDeleted));
			
		} catch (Exception e) {
			fail("Exception occurred");
		}
	}


}
