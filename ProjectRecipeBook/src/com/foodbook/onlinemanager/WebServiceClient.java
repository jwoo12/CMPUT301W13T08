package com.foodbook.onlinemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.foodbook.foodbook.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * 
 * Adapted from ESDemo by Chenlei Zhang.
 * 
 * @see https://github.com/rayzhangcl/ESDemo
 * 
 * This class is a model within the MVC architecture. It holds the methods used for a user's interaction with
 * the web service. 
 * 
 * Elastic Search is used to search through the web service. Gson is used to handle recipe objects
 * 
 * 
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes), and Chenlei Zhang
 *
 * @param <T> 
 */




public class WebServiceClient {

	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();

	// JSON Utilities
	private Gson gson = new Gson();

	// ERROR : java.lang.NoClassDefFoundError: com.google.gson.Gson

	private String URL = "http://cmput301.softwareprocess.es:8080/cmput301w13t08/";

	private String test_URL = "http://cmput301.softwareprocess.es:8080/testing/lab33844/";

	
	/**
	 * 
	 * Recieves a recipe to be published on the web service. 
	 * 
	 * 
	 * 
	 * @param recipe The recipe to be published
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	
	
	
	
	public void insertRecipe(Recipe recipe) throws IllegalStateException, IOException {

		Log.v("tests", "checkpoint insertRecipe");

		HttpPost httpPost = new HttpPost(URL + recipe.getRecipeid());
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(recipe));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringentity);
		HttpResponse response = null;
		try {

			Log.v("tests", "checkpoint trying response");

			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String status = response.getStatusLine().toString();
		// System.out.println(status);

		Log.v("tests", "status " + status);

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		String output;
		// System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			// System.err.println(output);

			Log.v("tests", "Output from Server -> " + output);
		}

		try {
			entity.consumeContent();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TODO Release connection not working

		// httpPost.releaseConnection();
	}

	
	/**
	 * 
	 * Downloads a recipe from the web service. 
	 * 
	 * 
	 */
	
	
	
	
	public void getRecipe() {
		try {
			HttpGet getRequest = new HttpGet(URL + "?pretty=1");// S4bRPFsuSwKUDSJImbCE2g?pretty=1

			getRequest.addHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Recipe>>() {
			}.getType();
			// Now we expect to get a Recipe response
			ElasticSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchResponseType);
			// We get the recipe from it!
			Recipe recipe = esResponse.getSource();
			System.out.println(recipe.toString());

			// TODO Release connection not working
			// getRequest.releaseConnection();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	
	
	/**
	 * 
	 * Searches for recipes on the web service by keywords or ingredients. 
	 * 
	 * The query string is based on whether or not an ingredient search is used. This variable will differ
	 * if searching by keyword instead. 
	 * 
	 * Keywords are based on a Recipe's "tag" array. The tag array includes a recipe's category, author name, and title. 
	 * By using this tag array, we were able to implement a universal search for a recipe.
	 * 
	 * 
	 * @see FridgeActivity search by ingredients using "See What I Can Make" button
	 * @see OnlineDataBase search by keyword using the edit box in activity
	 * 
	 * @param str the ingredients or keyword to be queried
	 * @param ingredient if we are searching by ingredients then true. False if search by keyword
	 * @return an ArrayList of found recipes 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	
	
	public ArrayList<Recipe> searchRecipes(String str, boolean ingredient) throws ClientProtocolException, IOException {

		Log.v("tests", "keyword " + str);
		
		String query = query(str, ingredient);
		Log.v("tests", "query " + query);
		
		
		ArrayList<Recipe> results = new ArrayList<Recipe>();

		HttpPost searchRequest = new HttpPost(URL + "_search?pretty=1");
		

		// do 2 queries: one for title and one for category then combine array

		StringEntity stringentity = new StringEntity(query);

		searchRequest.setHeader("Accept", "application/json");
		searchRequest.setEntity(stringentity);
		
		
		Log.v("tests", "executing....");

		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		// System.out.println(status);

		Log.v("tests", "search status " + status);

		String json = getEntityContent(response);

		// Log.v("tests", "json string after response " + json);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>() {
		}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		// System.err.println(esResponse);

		Log.v("tests", "search esResponse " + esResponse);

		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource();
			Log.v("tests", "recipe found " + recipe.getName());
			results.add(recipe);

			// System.err.println(recipe);
		}

		
		Log.v("tests", "size of results " + results.size());
		
		return results;
		// TODO Release Connection not working

		// searchRequest.releaseConnection();
	}

	
	/**

 * 
 * This class was made as a suggestion from JDeodorant. 
 * 
 * Long methods from this class, dealing with the selection of queries, were moved to this class
 * 
 * @param str keyword to search
 * @param ingredient true if searching by ingredients. false if searching by tags
 * @return query string to search
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *  
 *
 */
	 
	
	
	private String query(String str, boolean ingredient) {
		String query;
		if (ingredient) {
			query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"ingredients\",\"query\" : \""
					+ str + "\"}}}";
		} else {
			query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"tags\",\"query\" : \""
					+ str + "\"}}}";
		}
		return query;
	}

	/**
	 * update a field in a recipe
	 * 
	 * @param str the field to be updated
	 * 
	 * 
	 */
	public void updateRecipes(String str, Recipe toUpdate) throws ClientProtocolException, IOException {
		HttpPost updateRequest = new HttpPost(URL + toUpdate.getRecipeid() + "/_update");
		String query = "{\"script\" : \"ctx._source." + str + "}";
		StringEntity stringentity = new StringEntity(query);

		updateRequest.setHeader("Accept", "application/json");
		updateRequest.setEntity(stringentity);

		HttpResponse response = httpclient.execute(updateRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);

		// TODO Release Connection

		// updateRequest.releaseConnection();
	}

	/**
	 * delete an entry specified by the recipe's id
	 * 
	 * 
	 * @param toDelete the recipe to be deleted. 
	 * 
	 */
	public void deleteRecipe(Recipe toDelete) throws IOException {
		HttpDelete httpDelete = new HttpDelete("URL" + toDelete.getRecipeid());
		httpDelete.addHeader("Accept", "application/json");

		HttpResponse response = httpclient.execute(httpDelete);

		String status = response.getStatusLine().toString();
		//System.out.println(status);
		
		Log.v("tests", "delete status " +status);
		

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		String output;
		//System.err.println("Output from Server -> ");
		
		Log.v("tests", "DELETE Output from Server ->");
		
		while ((output = br.readLine()) != null) {
			//System.err.println(output);
			
			Log.v("tests", "output" + output);
			
		}

		// EntityUtils.consume(entity);
		entity.consumeContent();

		// TODO Release Connection
		// httpDelete.releaseConnection();
	}

	
	/**
	 * 
	 * reads from the http resonse and Json 
	 * 
	 * @param response the Http web service response
	 * @return a String representation of the entity
	 * @throws IOException
	 */
	
	
	String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		String output;
		// System.err.println("Output from Server -> ");

		Log.v("tests", "Output from Server -> ");

		String json = "";
		while ((output = br.readLine()) != null) {
			// System.err.println(output);

			Log.v("tests", "output" + output);

			json += output;
		}
		// System.err.println("JSON:"+json);

		Log.v("tests", "JSON: " + json);

		return json;
	}

}
