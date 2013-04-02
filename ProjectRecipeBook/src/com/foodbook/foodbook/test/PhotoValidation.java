package com.foodbook.foodbook.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.RecipeBook;

/**
 * <p>
 * This class is testing the that the photo is inserted correctly into the recipe made as well
 * as the edit photo portion, that it allows user to edit photo and as well as choose photo 
 * from a photo gallery
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */
public class PhotoValidation extends
		ActivityInstrumentationTestCase2<FridgeActivity> {
	public PhotoValidation(Class<FridgeActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}


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
	 * method to create/setup testing
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testRecipeBook = RecipeBook.getInstance();

	}
	 /**
	 * method for testing the photo is taken/inserted into a a recipe
	 */
	 public void testPhotoTaken() {
	     try{
	    	 fail("Not yet implemented"); // TODO
			} catch (Exception e) {
	            fail("Exception occurred");
	        }
		 
	 }
	 /**
	 * method for testing the photo can be deleted
	 */
	 public void testPhotoDelete() {
	     try{
	    	 fail("Not yet implemented"); // TODO
			} catch (Exception e) {
	            fail("Exception occurred");
	        }
		 
	 }
	 /**
	  * method for testing the photo ID matches recipe ID it was inserted into
	 */
	 public void testPhotoID() {
		try{
		    fail("Not yet implemented"); // TODO
		} catch (Exception e) {
			fail("Exception occurred");
		}
	 }
	 public void testGetCompressedPhotos() {
		 fail("Not yet implemented"); // TODO
		}


	 public void testDecompressPhotos() {
				fail("Not yet implemented"); // TODO
		}



}
