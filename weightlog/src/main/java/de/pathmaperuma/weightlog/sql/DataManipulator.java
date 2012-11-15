package de.pathmaperuma.weightlog.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import de.pathmaperuma.weightlog.DataPoint;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
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

    public List<DataPoint> selectAllDataPointsDescendingByDate() {
        List<String[]> rows = selectAllDescendingByDate();
        return createDataPointsFrom(rows);
    }

	public List<String[]> selectAllDescendingByDate() {
        return execute(ReadRawDataPoints.OrderedByDateDescending());
    }

    public List<String[]> selectAll() {
        return execute(ReadRawDataPoints.OrderedByDateAscending());
    }

    private List<String[]> execute(ReadRawDataPoints dataManipulation) {
        read(dataManipulation);
        return dataManipulation.result;
    }

    private List<DataPoint> createDataPointsFrom(List<String[]> rows) {
        List<DataPoint> dataPoints = new ArrayList<DataPoint>(rows.size());
        for (String[] s : rows) {
            Date date = new Date(Long.parseLong(s[1]));
            float weight = Float.parseFloat(s[2]);
            float fat = Float.parseFloat(s[3]);
            float water= Float.parseFloat(s[4]);
            float muscle = Float.parseFloat(s[5]);
            int kcal = Integer.parseInt(s[6]) / 100;
            float bone = Float.parseFloat(s[7]);
            dataPoints.add(new DataPoint(weight, fat, water, muscle, kcal, bone, new DateTime(date)));
        }
        return dataPoints;
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