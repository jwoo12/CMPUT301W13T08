
package com.foodbook.foodbook.test;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import android.util.Log;

import com.foodbook.onlinemanager.*;

import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.RecipeBook;
import com.foodbook.foodbook.ResultsBook;

/**
 * 
 * J-unit testing for web server
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */
public class DatabaseValidation extends TestCase {

  
	
	private RecipeBook testRecipeBook;
	private ResultsBook testresults;
	
	String rName = "name"; 
	String rDesc = "desc"; 
	String rInst = "inst";
	ArrayList<String> rIng = new ArrayList<String>(); 
	ArrayList<String> rCate = new ArrayList<String>(); 
	String rUserID = "000"; 
	String rAuth = "auth";
	
	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();
	WebServiceClient client = new WebServiceClient();
	/**
	 * method create a new setup/activity
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testRecipeBook = RecipeBook.getInstance();
		rIng.add("egg");
		rCate.add("soup");
		testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
		
	}
	
	/**
	 * 
	 * Test the connection
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	
	@Test
	public void testConnection() throws ClientProtocolException, IOException {
		try{
		HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/CMPUT301W13T08/");
		
		HttpResponse response = httpclient.execute(getRequest);
		
		assert (response != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test to insert recipe
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * 
	 */
	@Test
	public void testinsert () throws ClientProtocolException, IOException{
		try{
		int Size = testRecipeBook.getRecipeBook().size();
		String recipeID = testRecipeBook.getMine().get(Size-1).getRecipeid();	
		//insert a recipe
		testRecipeBook.publishRecipeById(recipeID);
		//search by keywords to check if its inserted online
		ArrayList<Recipe> onlineResults = OnlineDataBase.searchByKeyword(rName);
		ArrayList<String> onlineNames = testRecipeBook.getNamesAndIDs(onlineResults).get(0);
		
		assertTrue(onlineNames.contains(rName));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * search by keyword recipe name
	 */
	@Test
	public void testSearchName() throws ClientProtocolException, IOException {
		try{
		//insert first
		int Size = testRecipeBook.getRecipeBook().size();
		String recipeID = testRecipeBook.getMine().get(Size-1).getRecipeid();	
		//insert a recipe
		testRecipeBook.publishRecipeById(recipeID);
		
		//search by keywords
		ArrayList<Recipe> onlineResults = OnlineDataBase.searchByKeyword(rName);
		ArrayList<String> onlineNames = testRecipeBook.getNamesAndIDs(onlineResults).get(0);
		
		assertTrue(onlineNames.contains(rName));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}	
	
	/**
	 * search by keyword ingredients
	 */
	@Test
	public void testSearchIngredients() throws ClientProtocolException, IOException {
		try{
		//insert first
		int Size = testRecipeBook.getRecipeBook().size();
		String recipeID = testRecipeBook.getMine().get(Size-1).getRecipeid();	
		//insert a recipe
		testRecipeBook.publishRecipeById(recipeID);
		
		//search by keywords
		ArrayList<Recipe> onlineResults = OnlineDataBase.searchByKeyword("egg");
		ArrayList<String> onlineNames = testRecipeBook.getNamesAndIDs(onlineResults).get(0);
		
		assertTrue(onlineNames.contains(rName));
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * search by keyword category
	 */
	@Test
	public void testSearchCategory() throws ClientProtocolException, IOException {
		try{
		//insert first
		int Size = testRecipeBook.getRecipeBook().size();
		String recipeID = testRecipeBook.getMine().get(Size-1).getRecipeid();	
		//insert a recipe
		testRecipeBook.publishRecipeById(recipeID);
		
		//search by keywords
		ArrayList<Recipe> onlineResults = OnlineDataBase.searchByKeyword("soup");
		ArrayList<String> onlineNames = testRecipeBook.getNamesAndIDs(onlineResults).get(0);
		
		assertTrue(onlineNames.contains(rName));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	
	/**
	 * Test to get a recipe
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * 
	 */
	@Test
	public void testGetRecipe() {
		try{
			
		//insert first
		int Size = testRecipeBook.getRecipeBook().size();
		String recipeID = testRecipeBook.getMine().get(Size-1).getRecipeid();	
		//insert a recipe
		testRecipeBook.publishRecipeById(recipeID);		
		
		//get a recipe
		testresults.download(recipeID);
		Log.i("something", "hello"+testRecipeBook.getDownloads().toString());
		assertTrue(testRecipeBook.getDownloads().contains(rName));
		
		} catch (Exception e) {
            fail("Exception occurred");
     	   }

	}
	
	
	/**
	 * update a field in a recipe//restricted to recipe creator only
	 */
	@Test
	public void testUpdateRecipes() throws ClientProtocolException, IOException {
		try{
		//update a new field//first download another user recipe
		testresults.download("llf0o4t320k45de1dtjqre3kbh1364928885260n");
		testRecipeBook.getDownloads();
		
		//then upload again if user ID on downloaded recipe matches user ID
		//insert a recipe
		testRecipeBook.publishRecipeById("6bpti8bdfjj2hgm9d0l8q14plo1364878274727w");
		//search by keywords to check if its inserted online
		ArrayList<Recipe> onlineResults = OnlineDataBase.searchByKeyword(rAuth);
		ArrayList<String> onlineNames = testRecipeBook.getNamesAndIDs(onlineResults).get(0);
		
		assertFalse(onlineNames.contains(rName));
		

		} catch (Exception e) {
            fail("Exception occurred");
     	   }
	}	
	
	/**
	 * delete an entry specified by the id
	 */
	@Test
	public void testDeleteRecipe() {
		try{
		//insert first
		int Size = testRecipeBook.getRecipeBook().size();
		String recipeID = testRecipeBook.getMine().get(Size-1).getRecipeid();	
		//insert a recipe
		testRecipeBook.publishRecipeById(recipeID);	

		// deleting the recipe
		testRecipeBook.deleteById(recipeID);

		int newSize = testRecipeBook.getRecipeBook().size();
		assertTrue(Size - 1 == newSize);
		
		//search by keywords to check if its deleted online
		ArrayList<Recipe> onlineResults = OnlineDataBase.searchByKeyword(rAuth);
		ArrayList<String> onlineNames = testRecipeBook.getNamesAndIDs(onlineResults).get(0);
		
		assertFalse(onlineNames.contains(rName));

		} catch (Exception e) {
            fail("Exception occurred");
     	   }
	}

	
}
