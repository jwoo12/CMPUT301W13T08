package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Intent;

public class ResultsBook {

	public static final ResultsBook resultsBookInstance = new ResultsBook();

	protected ArrayList<Recipe> resultsbooklist;

	public ResultsBook() {
		this.resultsbooklist = new ArrayList<Recipe>();
	}

	public static ResultsBook getInstance() {
		return resultsBookInstance;
	}

	protected void reset() {
		this.resultsbooklist.clear();
	}

	protected Recipe searchById(String recipeid) {
		if (resultsBookInstance.resultsbooklist.size() == 0) {
			return null;
		}

		for (Recipe currentRecipe : resultsBookInstance.resultsbooklist) {
			if (currentRecipe.getRecipeid().equals(recipeid)) {
				return currentRecipe;
			}
		}

		return null;

	}

	public Intent makeRecipeIntentFromRecipeID(String recipeid) {
		Recipe found = this.searchById(recipeid);
		if (found != null) {
			return RecipeBook.makeRecipeIntent(found);
		}
		return null;
	}

	public void download(String targetRecipeId) {
		Recipe found = this.searchById(targetRecipeId);
		RecipeBook.getInstance().downloads.add(found);
	}

	public void setSearchResults(ArrayList<Recipe> newResults) {
		if (newResults != null) {
			this.resultsbooklist = newResults;
		}
	}

	public ArrayList<String> getPicturesById(String recipeid) {

		Recipe found = this.searchById(recipeid);

		if (found != null) {
			return found.getPictures();
		}

		return null;

	}

}
