package de.pathmaperuma.weightlog;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class DataManipulator {
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "weightdatabase.db";
	private static final String TABLE_NAME = "weighttable";
	private final Context context;
	private SQLiteDatabase database;

	private SQLiteStatement insertStmt;

	private static final String INSERT = "insert into "
			+ TABLE_NAME
			+ " (date, weight, fat, water, muscle, kcal, bone) values (?,?,?,?,?,?,?)";


    public void read(DataManipulation manipulation){
        SQLiteDatabase database = openReadableDB();
        manipulation.manipulateIn(database);
        database.close();
    }

    public void write(DataManipulation manipulation){
        SQLiteDatabase database = openWritableDB();
        manipulation.manipulateIn(database);
        database.close();
    }

	public DataManipulator(Context context) {
		this.context = context;
	}
	
	private SQLiteDatabase openWritableDB(){
		OpenHelper openHelper = new OpenHelper(this.context);
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
        this.database = writableDatabase;
        return writableDatabase;
	}
	
	private SQLiteDatabase openReadableDB(){
		OpenHelper openHelper = new OpenHelper(this.context);
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        this.database = readableDatabase;
        return readableDatabase;

	}
	
	private void closeDB(){
		if (this.database.isOpen()){
			this.database.close();
		}
	}

	public long insertReading(DataPoint dp) {
		openWritableDB();
		this.insertStmt = database.compileStatement(INSERT);
		this.insertStmt.bindString(1, dp.getUnixTimeString());
		this.insertStmt.bindString(2, dp.getWeightString());
		this.insertStmt.bindString(3, dp.getFatString());
		this.insertStmt.bindString(4, dp.getWaterString());
		this.insertStmt.bindString(5, dp.getMuscleString());
		this.insertStmt.bindString(6, dp.getKcalString());
		this.insertStmt.bindString(7, dp.getBoneString());
		System.out.println("Query ready : "+insertStmt.toString());
		long result = this.insertStmt.executeInsert();
		System.out.println("Insert resulted in : "+result);
		closeDB();
		return result;
	}

	public void deleteAll() {
		openWritableDB();
		database.delete(TABLE_NAME, null, null);
		closeDB();
	}

	public List<String[]> selectLast() {
		List<String[]> list = new ArrayList<String[]>();
		openReadableDB();
		Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null,
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
		if (!cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();
		closeDB();
		return list;
	}

	public List<String[]> selectAll() {
		List<String[]> list = new ArrayList<String[]>();
		openReadableDB();
		Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null,
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
		if (!cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();
		closeDB();
		return list;
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