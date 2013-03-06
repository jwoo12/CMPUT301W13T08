package com.foodbook.foodbook;

import java.util.ArrayList;

import android.content.Context;

public class Fridge {

	private ArrayList<String> ingredients;
	
	public Fridge(Context context) {
		// try to load from file
		// if file doesn't exist, make a new ArrayList and save to file.
		this.ingredients = new ArrayList<String>();
	}
	
	public void addIngredient(String newIngredient) {
		this.ingredients.add(newIngredient);
	}
	
	public void editIngredient (int ingredientIndex, String newName) {
		this.ingredients.set(ingredientIndex, newName);
	}
	
	public void removeIngredientByIndex (int ingrdIndex) {
		this.ingredients.remove(ingrdIndex);
	}
	
	public void loadFromFile() {
		
	}
	
	public void saveToFile(Context context) {
		
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	public String getIngredientAtIndex(int index) {
		return this.ingredients.get(index);
	}
}