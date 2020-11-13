package com.nuchwezi.xlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Nemesis Fixx
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "dictionary";
	public static final String ROWID = "_id";

	public static final String TAG = "XLDB";
	private static final int DATABASE_VERSION = 1;

	// SHOPPINGLIST TABLE
	public static final String DICTIONARY_DATABASE_TABLE = "main_table";
	private static final String DICTIONARY_DATABASE_TABLE_INDEX = "dictionary_index";

	// the column names for the table go here
	public static final String COL_KEY = "key";
	public static final String COL_VALUE = "value";

	// Data table creation sql statement -- other extra fields have to added in
	// case
	private static final String DICTIONARY_DATATABLE_CREATE_SQL = "CREATE TABLE "
			+ DICTIONARY_DATABASE_TABLE + " ("
			+ ROWID + " integer primary key autoincrement, "
			+ COL_KEY + " TEXT not null UNIQUE,"
			+ COL_VALUE + " TEXT not null" 
			+ ");";

	private static final String DICTIONARY_DATATABLE_INDEX_SQL = "CREATE INDEX "
			+ DICTIONARY_DATABASE_TABLE_INDEX + " ON " + DICTIONARY_DATABASE_TABLE
			+ " (" + COL_KEY +")";
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			// Enable foreign key constraints
			db.execSQL("PRAGMA foreign_keys=ON;");
			Log.i(TAG, "Enabled Foreign Keys on the Database");
		}
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {

		try {

			database.execSQL(DICTIONARY_DATATABLE_CREATE_SQL);
			database.execSQL(DICTIONARY_DATATABLE_INDEX_SQL);

			Log.i(TAG, "Created Database Tables...");

		} catch (Exception e) {
			Log.e(TAG, "On Create DB Error :" + e.getMessage());
		}
	}

	// Method is called during an upgrade of the database, e.g. if you increase
	// the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
		try {

			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");

			//chose to delete items first, since this is in accordance with foreign key constraints
			database.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_DATABASE_TABLE);

			onCreate(database);

		} catch (Exception e) {
			Log.e(TAG, "Upgrade DB Error :" + e.getMessage());
		}

	}
}
