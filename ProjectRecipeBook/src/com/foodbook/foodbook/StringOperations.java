package com.foodbook.foodbook;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * Dealing with common string operations. 
 *
 */

public class StringOperations {

	/**
	 * 
	 * Given a original text and the splitter, returns arraylist of string after splitting.
	 * 
	 * @param text original string
	 * @param splitter
	 * @return arraylist after splitting
	 */
	
	public static ArrayList<String> SplitToArrayList(String text, String splitter) {
		String[] stringArray = text.split(splitter);
		ArrayList<String> outputArrayList = new ArrayList<String>(Arrays.asList(stringArray));
		return outputArrayList;
	}
	

	/**
	 * 
	 * Adds all elements of the given arraylist of string
	 * 
	 * @param input
	 * @param separator gets insterted in between each element
	 * @return output string after addition
	 */
	
	public static String intoOneString(ArrayList<String> input, String separator) {
		
		if (input.size() == 0) {
			return "";
		}
		
		String outputString = input.get(0);
		
		for (int i = 1; i < input.size(); i++) {
			outputString += separator + input.get(i);
		}
		
		return outputString;
	}
	
	/**
	 * 
	 * This function formats ArrayList of String so that no entry has duplicate whitespaces/newline charactesr/etc...
	 * Also, this function removes empty entries.
	 * 
	 * 
	 * @param input
	 *            The ArrayList to be formatted
	 * @return
	 * outputArray after formatting
	 */

	public static ArrayList<String> formatArray(ArrayList<String> input) {

		ArrayList<String> output = new ArrayList<String>();

		for (String inputEntry : input) {
			String outputEntry = inputEntry;
			outputEntry = outputEntry.replace("\n", "");
			outputEntry = replaceLoop(outputEntry, "  ", " ");
			outputEntry = outputEntry.trim();

			if (!outputEntry.isEmpty() && !outputEntry.equals(" ")) {
				output.add(outputEntry);
			}
		}

		return output;

	}
	
	/**
	 * 
	 * Keeps replacing oldStr with newStr until there is no occurence of oldStr in the text
	 * 
	 * @param input
	 * @param oldStr
	 * @param newStr
	 * @return string after replacing
	 */
	
	public static String replaceLoop(String input, String oldStr, String newStr) {
		while (input.contains(oldStr)) {
			input = input.replace(oldStr, newStr);
		}
		return input;
	}

}
