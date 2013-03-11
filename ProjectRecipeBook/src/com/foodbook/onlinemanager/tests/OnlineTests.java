/**
 * 
 */
package com.foodbook.onlinemanager.tests;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import com.foodbook.foodbook.Recipe;

/**
 * 
 * J-unit testing for web server
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *
 */
public class OnlineTests extends TestCase {

	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();
/*	
	// JSON Utilities
		private Gson gson = new Gson();


	String rName = "name"; 
	String rDesc = "desc"; 
	String rInst = "inst";
	ArrayList<String> rIng = new ArrayList<String>(); 
	ArrayList<String> rCate = new ArrayList<String>(); 
	String rUserID = "123"; 
	String rAuth = "auth";
	
	
	private Recipe r = new Recipe(rName,rDesc,rInst,rIng,rCate,rUserID,rAuth);
	
	/**
	 * 
	 * Test the connection
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 
	
	@Test
	
	public void testConnection() throws ClientProtocolException, IOException {
	
		HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/CMPUT301W13T08/");
	
		HttpResponse response = httpclient.execute(getRequest);
		
		assert (response != null);
	}

	
	 * Test to insert recipe
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * 
	 * 
	 *//*
	
	@Test
	
	public void insert () throws ClientProtocolException, IOException{
		HttpPost httpPost = new HttpPost("http://cmput301.softwareprocess.es:8080/CMPUT301W13T08/"+r.getRecipeid());
		StringEntity stringentity = null;
		
		try {
			stringentity = new StringEntity(gson.toJson(r));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		httpPost.setHeader("Accept","application/json");

		httpPost.setEntity(stringentity);
		HttpResponse response = null;
		
		response = httpclient.execute(httpPost);
		
		assert (response != null);
	}
	
*/
	

	
}

