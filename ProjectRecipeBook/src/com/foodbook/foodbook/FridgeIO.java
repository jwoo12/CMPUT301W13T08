package com.foodbook.foodbook;


import android.content.Context;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

/**
 * 
 * This class was made as a suggestion from JDeodorant. 
 * 
 * God Classes from the Fridge class, dealing with file IO, were moved to this class
 * 
 * 
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 *  
 *
 */




public class FridgeIO {
	private final String fridgeFilename = "fridge.sav";

	public String getFridgeFilename() {
		return fridgeFilename;
	}

	/**
	 * Load previous ingredients from file.
	 */
	@SuppressWarnings("unchecked")
	public void loadFromFile(Context context, Fridge fridge) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					context.openFileInput(fridgeFilename));
			ArrayList<String> myFridge = (ArrayList<String>) in.readObject();
			fridge.setIngredients(myFridge);
		} catch (EOFException eof) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save current ingredients to file.
	 * @param context the application's context
	 */
	public void saveToFile(Context context, ArrayList<String> ingredients) {
		try {
			@SuppressWarnings("static-access")
			ObjectOutputStream out = new ObjectOutputStream(
					context.openFileOutput(fridgeFilename, context.MODE_PRIVATE));
			out.writeObject(ingredients);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}