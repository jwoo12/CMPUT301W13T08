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

public class WebServiceClient {

	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();

	// JSON Utilities
	private Gson gson = new Gson();

	// ERROR : java.lang.NoClassDefFoundError: com.google.gson.Gson

	private String URL = "http://cmput301.softwareprocess.es:8080/CMPUT301W13T08/testing/";

	private String test_URL = "http://cmput301.softwareprocess.es:8080/testing/lab9901208/";

	public void insertRecipe(Recipe recipe) throws IllegalStateException, IOException {

		Log.v("tests", "checkpoint insertRecipe");

		HttpPost httpPost = new HttpPost(test_URL + recipe.getRecipeid());
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

	public void getRecipe() {
		try {
			HttpGet getRequest = new HttpGet(test_URL + "?pretty=1");// S4bRPFsuSwKUDSJImbCE2g?pretty=1

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

	public ArrayList<Recipe> searchRecipes(String str, boolean ingredient) throws ClientProtocolException, IOException {

		Log.v("tests", "keyword " + str);
		
		String query;
		
		if(ingredient){
			
			query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"ingredient\",\"query\" : \"" + str + "\"}}}";
			
		}else{

		query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"tags\",\"query\" : \"" + str + "\"}}}";
			
		}
		

		ArrayList<Recipe> results = new ArrayList<Recipe>();

		HttpPost searchRequest = new HttpPost(test_URL + "_search?pretty=1");
		

		// do 2 queries: one for title and one for category then combine array

		StringEntity stringentity = new StringEntity(query);

		searchRequest.setHeader("Accept", "application/json");
		searchRequest.setEntity(stringentity);

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

		return results;
		// TODO Release Connection not working

		// searchRequest.releaseConnection();
	}

	/**
	 * update a field in a recipe
	 */
	public void updateRecipes(String str) throws ClientProtocolException, IOException {
		HttpPost updateRequest = new HttpPost("http://cmput301.softwareprocess.es:8080/testing/lab02/1/_update");
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
	 * delete an entry specified by the id
	 */
	public void deleteRecipe(Recipe toDelete) throws IOException {
		HttpDelete httpDelete = new HttpDelete("test_URL" + toDelete.getRecipeid());
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
