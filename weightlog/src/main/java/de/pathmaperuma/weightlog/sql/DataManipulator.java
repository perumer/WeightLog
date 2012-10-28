package de.pathmaperuma.weightlog.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import de.pathmaperuma.weightlog.DataPoint;

import java.util.List;

public class DataManipulator {
    public static final String TABLE_NAME = "weighttable";
	private final Context context;

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
        return openHelper.getWritableDatabase();
	}
	
	private SQLiteDatabase openReadableDB(){
		OpenHelper openHelper = new OpenHelper(this.context);
        return openHelper.getReadableDatabase();
	}
	
	public void insertReading(final DataPoint dp) {
        write(new WriteDataPoint(dp));
	}

	public void deleteAll() {
        write(new DeleteAllData());
	}

	public List<String[]> selectLast() {
        return execute(ReadRawDataPoints.OrderedByDateDescending());
    }

    public List<String[]> selectAll() {
        return execute(ReadRawDataPoints.OrderedByDateAscending());
    }

    private List<String[]> execute(ReadRawDataPoints dataManipulation) {
        read(dataManipulation);
        return dataManipulation.result;
    }

    public static class OpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "weightdatabase.db";
        private static final int DATABASE_VERSION = 2;

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