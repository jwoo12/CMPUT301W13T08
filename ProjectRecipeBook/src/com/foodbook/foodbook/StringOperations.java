package com.foodbook.foodbook;

import java.util.ArrayList;
import java.util.Arrays;

public class StringOperations {

	public static ArrayList<String> SplitToArrayList(String text, String splitter) {
		String[] stringArray = text.split(splitter);
		ArrayList<String> outputArrayList = new ArrayList<String>(Arrays.asList(stringArray));
		return outputArrayList;
	}
	

	
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
	
	public static String replaceLoop(String input, String oldStr, String newStr) {
		while (input.contains(oldStr)) {
			input = input.replace(oldStr, newStr);
		}
		return input;
	}

}
