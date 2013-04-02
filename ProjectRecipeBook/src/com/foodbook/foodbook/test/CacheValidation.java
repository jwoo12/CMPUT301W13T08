package com.foodbook.foodbook.test;

import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.RecipeBook;
import com.foodbook.foodbook.ResultsBook;

import android.test.ActivityInstrumentationTestCase2;

/**
 * <p>
 * This class test the caching of a recipe
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */
public class CacheValidation extends ActivityInstrumentationTestCase2
{
  private RecipeBook testRecipeBook;
	private ResultsBook testResults;

	/**
	 * method to inherit from fridgeactivity
	 */
	public CacheValidation(String name)
	{

		super("com.foodbook.foodbook.test", FridgeActivity.class);
	}

	/**
	 * method create a new setup/activity
	 */
	protected void setUp() throws Exception
	{

		super.setUp();
		testResults= ResultsBook.getInstance();
	}
	
	/**
	 * method to test caching of a recipe 
	 */
	public void testAddCache() {
		try{

			fail("Not yet implemented"); // TODO
			
			} catch (Exception e) {
            fail("Exception occurred");
        }
    }


}

