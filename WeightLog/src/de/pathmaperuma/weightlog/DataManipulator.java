package de.pathmaperuma.weightlog;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DataManipulator {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "weightdatabase.db";
	private static final String TABLE_NAME = "weighttable";
	private final Context context;
	private SQLiteDatabase db;

	private SQLiteStatement insertStmt;

	private static final String INSERT = "insert into "
			+ TABLE_NAME
			+ " (date, weight, fat, water, muscle, kcal, bone) values (?,?,?,?,?,?,?)";

	public DataManipulator(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();
		this.insertStmt = db.compileStatement(INSERT);
	}

	public long insert_old(String name, String number, String skypeId,
			String address) {
		this.insertStmt.bindString(1, name);
		this.insertStmt.bindString(2, number);
		this.insertStmt.bindString(3, skypeId);
		this.insertStmt.bindString(4, address);
		return this.insertStmt.executeInsert();
	}

	public long insert(Date date, float weight) {
		this.insertStmt.bindString(1, Long.valueOf(date.getTime()).toString());
		this.insertStmt.bindString(2, Float.valueOf(weight).toString());
		return this.insertStmt.executeInsert();
	}

	public long insertReading(Date date, Float weight, Float fat, Float water,
			Float muscle, Integer kcal, Float bone) {
		this.insertStmt.bindString(1, Long.valueOf(date.getTime()).toString());
		this.insertStmt.bindString(2, weight.toString());
		this.insertStmt.bindString(3, fat.toString());
		this.insertStmt.bindString(4, water.toString());
		this.insertStmt.bindString(5, muscle.toString());
		this.insertStmt.bindString(6, kcal.toString());
		this.insertStmt.bindString(7, bone.toString());
		return this.insertStmt.executeInsert();
	}

	public void deleteAll() {
		db.delete(TABLE_NAME, null, null);
	}

	public List<String[]> selectLast() {
		List<String[]> list = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null,
				"date desc");
		if (cursor.moveToFirst()) {
			do {
				String[] row = new String[] { cursor.getString(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4),
						cursor.getString(5), cursor.getString(6),
						cursor.getString(7) };
				list.add(row);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();
		return list;
	}

	public List<String[]> selectAll() {
		List<String[]> list = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null,
				"date asc");
		if (cursor.moveToFirst()) {
			do {
				String[] row = new String[] { cursor.getString(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4),
						cursor.getString(5), cursor.getString(6),
						cursor.getString(7) };
				list.add(row);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();
		return list;
	}

	public void delete(int rowId) {
		db.delete(TABLE_NAME, null, null);
	}

	private static class OpenHelper extends SQLiteOpenHelper {
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "
					+ TABLE_NAME
					+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, date INTEGER, weight TEXT, fat TEXT, water TEXT, muscle TEXT, kcal INTEGER, bone TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}
}