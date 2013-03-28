package com.foodbook.foodbook;

import java.io.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

//http://mobile.cs.fsu.edu/converting-images-to-json-objects/  THIS is the site from which this code is derived from.

/**
 * 
 * The class has methods to achieve the following: 1) Bitmap image to a String. 2) Bitmap image to a Json String. 3) JSONObject (String) back to a Bitmap image.
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 */
public class ImageConverter {

	// THIS IS THE METHOD WE WILL CALL<<<<<<<
	/**
	 * Converting String to Json String
	 * 
	 * @param Bitmap
	 *            Object.
	 * @returns JSONObject
	 */
	public static String getJsonString(Bitmap bitmapPicture) {
		String encodedImage = getStringFromBitmap(bitmapPicture);
		JSONObject jsonObj = null;

		try {
			// getting the JSON object from String with the key as "image"
			jsonObj = new JSONObject("{\"image\":\"" + encodedImage + "\"}");
		} catch (JSONException e) {
			System.out.println("Error in method getJson: " + e);
			e.printStackTrace();
		}

		String jasonString = null;
		try {
			jasonString = (String) jsonObj.get("image");
		} catch (JSONException e) {
			System.out.println("Error in getJsonObjectFromString method: " + e);
			e.printStackTrace();
		}
		return jasonString;
	}

	/**
	 * Converts Bitmap image to a String.
	 * 
	 * @param bitmapPicture
	 * @return String encodedImage
	 */
	private static String getStringFromBitmap(Bitmap bitmapPicture) {
		/*
		 * This functions converts Bitmap picture to a string which can be JSONified.
		 */
		final int COMPRESSION_QUALITY = 100;
		String encodedImage;
		ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
		bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY, byteArrayBitmapStream);
		byte[] b = byteArrayBitmapStream.toByteArray();
		encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
		return encodedImage;
		// I left this method without the creation of a Json object, simply for
		// versatility. Use the String returned here
		// to call getJsonObjectFromString if a Json String is desired.
	}

	/**
	 * Will convert a jsonString that, has in it, an encoded Bitmap image, back to a Bitmap image.
	 * 
	 * @param jsonString
	 * @return decodedBye
	 */
	public static Bitmap getBitmapFromString(String jsonString) {
		/*
		 * This Function converts the String back to Bitmap
		 */
		byte[] decodedString = Base64.decode(jsonString, Base64.DEFAULT);

		// decoding bytes here.
		Bitmap decodedStringNowBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
		return decodedStringNowBitmap;
	}

}