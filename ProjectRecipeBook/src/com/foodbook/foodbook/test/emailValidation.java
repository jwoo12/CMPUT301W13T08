
package com.foodbook.foodbook.test;

import junit.framework.TestCase;
import org.junit.Test;

import android.content.Intent;


/**
 * 
 * J-unit testing for email validation
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */
public class emailValidation extends TestCase {


  
	/**
	 * test email intent is not valid
	 */
	@Test
	public void testDeleteRecipe() {
		try{
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			assertTrue(emailIntent!=null);
	
		} catch (Exception e) {
            fail("Exception occurred");
     	   }
	}

	
}
