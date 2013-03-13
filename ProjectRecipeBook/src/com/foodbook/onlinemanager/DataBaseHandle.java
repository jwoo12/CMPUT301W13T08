package com.foodbook.onlinemanager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * DataBaseHandle extends SQLiteOpenHelper, this class will help open a writable
 * database in which data will be written to. This class will also contain
 * methods to get and set information in the database.
 * 
 * @author rjanes
 * 
 */
public class DataBaseHandle extends SQLiteOpenHelper {
	// Static Variables: used as final to make immutable.//

	// TO CREATE THE ACTUAL DATABASE THAT WILL CONTAIN ALL OF OUR TABLES
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "foodBookData";

	// TABLE 1: *RECIPE* (HAS title, user, description, category, ingredients,
	// instructions)
	private static final String RECIPE_TABLE = "Recipe"; // *****NAME OF TABLE
															// *****
	private static final String R_PRIMARY_KEY = "r_id";
	private static final String RECIPE_TITLE = "Recipe_title";
	private static final String RECIPE_USERNAME = "Username";
	private static final String RECIPE_DESCRIPTION = "Description";
	private static final String RECIPE_CATEGORY = "Category";
	private static final String RECIPE_INGREDIENTS = "Ingredients";
	private static final String RECIPE_INSTRUCTIONS = "Instructions";
	private static final String RECIPE_ID = "recipe_id";
	private static final String USER_ID = "user_id";

	// TABLE 2: *INGREDIENTS* (An array of items)
	private static final String INGREDIENT_TABLE = "Ingredient"; // not sure we
																	// need this
																	// table...
																	// we
																	// probably
																	// will to
																	// compare.
	private static final String INGREDIENT_KEY = "id";
	private static final String INGREDIENT_NAME = "name";
	// private static final String RECIPE_BELONG = "recipe";

	// TABLE 3: *SEARCH*
	private static final String SEARCH_RECIPE_INGREDIENTS_TABLE = "Index"; // This
																			// table
																			// will
																			// use
																			// the
																			// keys
																			// from
																			// recipe
																			// and
																			// ingredient
																			// to
																			// cross
																			// reference
	private static final String SEARCH_KEY = "search_key";

	// Global Variables
	private SQLiteDatabase db;
	DataBaseHandle databasehelper;

	/**
	 * This constructor will call sqlitehelper database name and version to
	 * prepare for commands
	 * 
	 * @param context
	 * @param db
	 */
	public DataBaseHandle(Context context, SQLiteDatabase db) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.i("TAGGG", "DO ON CREATE CALL HERE THEN!");
	}

	/**
	 * Creates a DataBase: NOTE: This method will only activate IF there is NO
	 * DATABASE ALREADY. If the database is purposely deleted, just running the
	 * program will create a new one. Also, this will check for updates to the
	 * database and deal with them "in the background." It just works.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("TAGGG",
				"Creating New DataBase NOW! *Will not appear again if database"
						+ " is created because this onCreate will not run");
		this.db = db;
		// Creates Recipe Table

		String CREATE_RECIPE_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ RECIPE_TABLE + " "
				+ "(r_id INTEGER PRIMARY KEY AUTOINCREMENT, " + RECIPE_TITLE
				+ " TEXT, " + RECIPE_USERNAME + " TEXT, " + RECIPE_DESCRIPTION
				+ " TEXT, " + RECIPE_CATEGORY + " TEXT, " + RECIPE_INGREDIENTS
				+ " TEXT, " + RECIPE_INSTRUCTIONS + " TEXT, " + RECIPE_ID + " TEXT " + USER_ID + " TEXT);";
		db.execSQL(CREATE_RECIPE_TABLE);

		// String CREATE_RECIPE_TABLE =
		// "create table Recipe(R_ID INTEGER PRIMARY KEY AUTOINCREMENT, RecipeTitle TEXT, Description TEXT, Category TEXT, Ingredients TEXT, Insttructions TEXT)";
		// db.execSQL(CREATE_RECIPE_TABLE);
		// Creates Ingredients Table
		String CREATE_INGREDIENT_TABLE = "CREATE TABLE " + INGREDIENT_TABLE
				+ "(I_ID INTEGER PRIMARY KEY AUTOINCREMENT, " + INGREDIENT_KEY
				+ "TEXT " + INGREDIENT_NAME + ")";
		// db.execSQL(CREATE_INGREDIENT_TABLE);
		String CREATE_SEARCH_TABLE = "CREATE TABLE "
				+ SEARCH_RECIPE_INGREDIENTS_TABLE
				+ "(S_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "FOREIGN KEY (r_id) REFERENCES " + INGREDIENT_TABLE
				+ "(I_id)";

		// String CREATE_SEARCH_RECIPE_INGREDIENTS_TABLE = "CREATE TABLE " +
		// SEARCH_RECIPE_INGREDIENTS_TABLE + "(FOREIGN KEY (" + INGREDIENT_KEY
		// +", " + PRIMARY_KEY +")";

		System.out.println("path of database is: " + db.getPath());
		System.out.println(db);
	}

	// ---opens the database---
	public DataBaseHandle open() throws SQLException {
		db = getWritableDatabase();
		return this;
	}

	/**
	 * Opens the database
	 * 
	 * @params none
	 */
	public void close() {
		db.close();
	}

	String getSingleRecipe(String recipeid) {
		Cursor cursor = db.query(RECIPE_TABLE, new String[] { "_id", "title",
				"title_raw" }, "title_raw like " + "'%Smith%'", null, null,
				null, null);
		return recipeid;

	}

	/**
	 * Gets a list<String> containing a string of each row's (objects) info
	 * NOTE: this method can easily be adjusted to send back strings in many
	 * ways
	 * 
	 * @param everythinglist
	 * @return
	 */
	public List<String> getAllRecipes(List<String> everythinglist) {

		// Select All Query
		String selectQuery = "SELECT  * FROM " + RECIPE_TABLE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {// title, user, description, category, ingredients,
				// instructions)
				String title = cursor.getString(0);
				String user = cursor.getString(1);
				String description = cursor.getString(2);
				String category = cursor.getString(3);
				String ingredients = cursor.getString(4);
				String instructions = cursor.getString(5);
				String recipecombined = title + ", " + user + ", "
						+ description + ", " + category + ", " + ingredients
						+ ", " + instructions;
				everythinglist.add(recipecombined);
			} while (cursor.moveToNext());
		}
		// return contact list
		return everythinglist;
	}

	public ArrayList<String> getSingle(String id) {

		ArrayList<String> output = new ArrayList<String>();
		
		// Select All Query
		String selectQuery = "SELECT  * FROM " + RECIPE_TABLE + " where "
				+ R_PRIMARY_KEY + "=" + id;

		db = getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			
			//Log.i("TAGGG", "JUST ABOUT TO PRINT SINGLE");
			//System.out.println(result);

			String name = cursor.getString(2);
			String author = cursor.getString(3);
			String desc = cursor.getString(4);
			String cate = cursor.getString(5);
			String ing = cursor.getString(6);
			String inst = cursor.getString(7);
			String recipeid = cursor.getString(8);
			String userid = cursor.getString(9);
			
			output.add(recipeid);
			output.add(name);
			output.add(author);
			output.add(desc);
			output.add(inst);
			output.add(ing);
			output.add(cate);
			output.add(userid);
			
		}
		db.close();
		// return contact list
		return output;

	}

	/**
	 * getAllRecipes will return a cursor with all values in the database (in a
	 * string array).
	 * 
	 * @return
	 */
	public Cursor getAllRecipes() {
		db = getWritableDatabase();
		return db.query(RECIPE_TABLE, new String[] { RECIPE_TITLE,
				RECIPE_USERNAME, RECIPE_DESCRIPTION, RECIPE_CATEGORY,
				RECIPE_INGREDIENTS, RECIPE_INSTRUCTIONS }, null, null, null,
				null, null);
		// need to close so maybe do query first then return it.
	}

	/**
	 * This getRecipe method will find a UNIQUE recipe by it's ID (long int).
	 * 
	 * @param primkey
	 * @return
	 * @throws SQLException
	 */
	public Cursor getRecipe(long primkey) throws SQLException {
		db = getWritableDatabase();
		Cursor mCursor = db.query(true, RECIPE_TABLE, new String[] {
				R_PRIMARY_KEY, RECIPE_TITLE, RECIPE_USERNAME,
				RECIPE_DESCRIPTION, RECIPE_CATEGORY, RECIPE_INGREDIENTS,
				RECIPE_INSTRUCTIONS }, R_PRIMARY_KEY + "=" + primkey, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		db.close();
		return mCursor;
	}

	/**
	 * UpdateRecipe is the method in which the user will be able to UPDATE a
	 * UNIQUE recipe.
	 * 
	 * @param id
	 * @param title
	 * @param user
	 * @param description
	 * @param category
	 * @param ingredients
	 * @param instructions
	 * @return boolean
	 */
	public boolean updateRecipe(long id, String title, String user,
			String description, String category, String ingredients,
			String instructions) {
		ContentValues args = new ContentValues();
		args.put(RECIPE_TITLE, title);
		args.put(RECIPE_USERNAME, user);
		args.put(RECIPE_DESCRIPTION, description);
		args.put(RECIPE_CATEGORY, category);
		args.put(RECIPE_INGREDIENTS, ingredients);
		args.put(RECIPE_INSTRUCTIONS, instructions);

		return db.update(RECIPE_TABLE, args, R_PRIMARY_KEY + "=" + id, null) > 0;
	}

	/**
	 * Main method used to insert a recipe.
	 * 
	 * @param rname
	 * @param user
	 * @param description
	 * @param category
	 * @param ingredients
	 * @param instructions
	 * @return none
	 */
	public long insertRecipe(String rname, String user, String description,
			String category, String ingredients, String instructions) {

		ContentValues values = new ContentValues();
		values.put(RECIPE_TITLE, rname);
		values.put(RECIPE_USERNAME, user);
		values.put(RECIPE_DESCRIPTION, description);
		values.put(RECIPE_CATEGORY, category);
		values.put(RECIPE_INGREDIENTS, ingredients);
		values.put(RECIPE_INSTRUCTIONS, instructions);
		return db.insert(RECIPE_TABLE, null, values);
	}

	/**
	 * If the tables need to be altered, reset, or changed this we drop the
	 * tables in the database then re-add the original tables again. (To start
	 * fresh, without users that we added in testing, for example).
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);
		onCreate(db);
	}

	/**
	 * used to delete the tables so we don't get errors while testing. REMOVE
	 * any calls to this method before submiting
	 */
	public void dropTables() {
		db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);
	}

	/**
	 * getNameandId will get a PAIR of list<String>. One has a list of
	 * recipe_titles and the other has a list of ids. Together, these can/will
	 * be used to search for clicked items being displayed on an activity.
	 * 
	 * @return List<List<String>> List of two lists
	 */
	public List<List<String>> getNameandId() {
		db = getReadableDatabase();
		List<List<String>> results = new ArrayList<List<String>>();
		List<String> names = new ArrayList<String>();
		List<String> ids = new ArrayList<String>();

		Cursor crsr = fetchAllRecipes();
		if (crsr != null) {
			if (crsr.moveToFirst()) {
				do {
					String recipename = crsr.getString(crsr
							.getColumnIndex(RECIPE_TABLE));
					String id = crsr.getString(crsr
							.getColumnIndex(R_PRIMARY_KEY));
					names.add(recipename);
					ids.add(id);
				} while (crsr.moveToNext());

				db.close();
				results.add(names);
				results.add(ids);
			}
		}
		return results;
	}

	/**
	 * Creating content values by passing in recipe info in the proper order.
	 * This will create a string that does not need to be parsed by the database
	 * and thus has greater performance.
	 * 
	 * @param rname
	 * @param user
	 * @param description
	 * @param category
	 * @param ingredients
	 * @param instructions
	 * @return ContentValues (used in sqlite as 'values' paramater)
	 */
	private ContentValues createContentValues(String rname, String user,
			String description, String category, String ingredients,
			String instructions) {
		ContentValues values = new ContentValues();
		values.put(RECIPE_TITLE, rname);
		values.put(RECIPE_USERNAME, user);
		values.put(RECIPE_DESCRIPTION, description);
		values.put(RECIPE_CATEGORY, category);
		values.put(RECIPE_INGREDIENTS, ingredients);
		values.put(RECIPE_INSTRUCTIONS, instructions);

		return values;
	}

	/**
	 * Will fetch all recipes (this method does not iterate through and send
	 * back a list). Rather, it sends back the cursor and the caller can do as
	 * they please with the results.
	 * 
	 * @return Cursor
	 */
	public Cursor fetchAllRecipes() {
		return db.query(RECIPE_TABLE, new String[] { R_PRIMARY_KEY,
				RECIPE_TITLE, RECIPE_USERNAME, RECIPE_DESCRIPTION,
				RECIPE_CATEGORY, RECIPE_INGREDIENTS, RECIPE_INSTRUCTIONS },
				null, null, null, null, null);
	}

	/**
	 * Deletes a UNIQUE recipe by it's ID. Will return true if successful and
	 * false if is unable.
	 * 
	 * @param rowId
	 * @return boolean
	 */
	public boolean deleteContact(long rowId) {
		return db.delete(RECIPE_TABLE, R_PRIMARY_KEY + "=" + rowId, null) > 0;
	}

	/**
	 * Inserts into a recipe the values. This is an extra insert method that is
	 * not currently being used.
	 */
	void insert() {

		db = getWritableDatabase();
		// values.put(PRIMARY_KEY, recipe.getRecipeid()); // Recipe ID
		// values.put(RECIPE_TITLE, recipe.getRecipename()); // Recipe Name
		// values.put(RECIPE_DESCRIPTION, recipe.getRecipeDescriptions()); //
		// Recipe Description
		// values.put(RECIPE_CATEGORY, recipe.getCategory()); // Recipe Category
		// NEEDS TO CHANGE WHEN HAVE getCategory****
		// values.put(RECIPE_INGREDIENTS, recipe.getIngredientsString()); //
		// Recipe Ingredients
		// values.put(RECIPE_INSTRUCTIONS, recipe.getRecipeinstructions()); //
		// Recipe Instructions

		/*
		 * DOES WORK ContentValues values = new ContentValues(); values =
		 * createContentValues("LASAG", "ROB", "noodles and sauce","PASTA",
		 * "THIS, THAT, OTHER", "EAT YUMMMM");
		 * db.insertWithOnConflict(RECIPE_TABLE, null, values,
		 * SQLiteDatabase.CONFLICT_REPLACE); db.close(); // Closing database
		 * connection
		 */
	}
}