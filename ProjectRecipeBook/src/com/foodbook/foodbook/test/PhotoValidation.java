package com.foodbook.foodbook.test;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Gallery;

import com.foodbook.foodbook.BogoPicGen;
import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.ImageConverter;
import com.foodbook.foodbook.PhotoManager;
import com.foodbook.foodbook.RecipeBook;
import com.foodbook.foodbook.RecipeDetailsActivity;

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
	private RecipeBook testRecipeBook;
	protected ArrayList<String> testpictures;
	
	String rName = "name";
	String rDesc = "desc";
	String rInst = "inst";
	ArrayList<String> rIng = new ArrayList<String>();
	ArrayList<String> rCate = new ArrayList<String>();
	String rUserID = "123";
	String rAuth = "auth";
	
	String Ing1= "sugar";
	String Ingr2= "chicken";
	protected ArrayList<String> testpict = new ArrayList<String>();
	protected final int PICTURE_SIZE = 200;

	
	
	
	/**
	 * method for creating super for FridgeActivity
	 */
	public PhotoValidation() {
		super("com.foodbook.foodbook.test", FridgeActivity.class);
	}

	/**
	 * method to create/setup testing
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testRecipeBook = RecipeBook.getInstance();
		//testpictures = PhotoManager.pictures;

	}
	 /**
	 * method for testing the photo is taken/inserted into a a recipe
	 */
	 public void testPhotoTaken() {
	     try{
	    	 
	    	 int oldSize= testpict.size();
	    	 Bitmap newBMP = BogoPicGen.generateBitmap(PICTURE_SIZE, PICTURE_SIZE);
	 		 String newBMPString = ImageConverter.getJsonString(newBMP);
	 		 testpict.add(newBMPString);
	 		 int newSize= testpict.size();
	 		 assertTrue(oldSize+1==newSize);
			} catch (Exception e) {
				System.out.println("ERROR IS: " + e);
	            fail("Exception occurred");
	        }
		 
	 }
	 /**
	 * method for testing the photo can be deleted
	 */
	 public void testPhotoDelete() {
		 try{
			 int oldSize= testpict.size();
	    	 Bitmap newBMP = BogoPicGen.generateBitmap(PICTURE_SIZE, PICTURE_SIZE);
	 		 String newBMPString = ImageConverter.getJsonString(newBMP);
	 		 testpict.add(newBMPString);
	 		 
	 		 //deletepic
	 		 testpict.remove(0);
	 		 int newSize= testpict.size();
	 		 assertTrue(oldSize==newSize);
			} catch (Exception e) {
				System.out.println("ERROR IS IN testPHOTODELETE: " + e);
	            fail("Exception occurred");
	        }
		
	 }
	 /**
	  * method for testing the photo ID matches recipe ID it was inserted into
	 */
	 public void testPhotoID() {
		try{

		   // fail("Not yet implemented"); // TODO
		} catch (Exception e) {
			//fail("Exception occurred");
		}
	 }
	 public void testGetCompressedPhotos() {
		// fail("Not yet implemented"); // TODO
		}


	 public void testDecompressPhotos() {
		
				//fail("Not yet implemented"); // TODO
				//System.out.println("ERROR IS: " + e);
		}



}
