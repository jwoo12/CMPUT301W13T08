package com.foodbook.foodbook;

import java.util.ArrayList;
import java.util.Arrays;

public class StringOperations {

	public static ArrayList<String> SplitToArrayList(String text, String splitter) {
		String[] stringArray = text.split(splitter);
		ArrayList<String> outputArrayList = new ArrayList<String>(Arrays.asList(stringArray));
		return outputArrayList;
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
			while (outputEntry.contains("  ")) {
				outputEntry = outputEntry.replace("  ", " ");
			}
			outputEntry = outputEntry.trim();

			if (!outputEntry.equals("")) {
				output.add(outputEntry);
			}
		}

		return output;

	}

}
