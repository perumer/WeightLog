package de.pathmaperuma.weightlog;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReadRawDataPoints implements DataManipulation {

    public static ReadRawDataPoints OrderedByDateDescending(){
        return new ReadRawDataPoints("date desc");
    }

    public static ReadRawDataPoints OrderedByDateAscending() {
        return new ReadRawDataPoints("date asc");
    }

    private final String orderBy;
    public List<String[]> result = new ArrayList<String[]>();

    public ReadRawDataPoints(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public void manipulateIn(SQLiteDatabase database) {
        Cursor cursor = database.query(DataManipulator.TABLE_NAME, null, null, null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            do {
                String[] row = new String[] { cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6),
                        cursor.getString(7) };
                result.add(row);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        cursor.close();

    }
}
