package de.pathmaperuma.weightlog.sql;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.pathmaperuma.weightlog.DataPoint;

public class WriteDataPoint implements DataManipulation {
    private static final String INSERT = "insert into "
            + DataManipulator.TABLE_NAME
            + " (date, weight, fat, water, muscle, kcal, bone) values (?,?,?,?,?,?,?)";
    private final DataPoint dataPoint;

    public WriteDataPoint(DataPoint dataPoint) {
        this.dataPoint = dataPoint;
    }

    @Override
    public void manipulateIn(SQLiteDatabase database) {
        SQLiteStatement insertStmt = database.compileStatement(INSERT);
        insertStmt.bindString(1, Long.valueOf(dataPoint.getTimeTaken().toDate().getTime()).toString());
        insertStmt.bindString(2, Float.valueOf(dataPoint.getWeight()).toString());
        insertStmt.bindString(3, Float.valueOf(dataPoint.getPercentBodyFat()).toString());
        insertStmt.bindString(4, Float.valueOf(dataPoint.getPercentBodyWater()).toString());
        insertStmt.bindString(5, Float.valueOf(dataPoint.getPercentBodyMuscle()).toString());
        insertStmt.bindString(6, Integer.valueOf(dataPoint.getKilokalorien()).toString());
        insertStmt.bindString(7, Float.valueOf(dataPoint.getBoneWeight()).toString());
        System.out.println("Query ready : "+ insertStmt.toString());
        long result = insertStmt.executeInsert();
        System.out.println("Insert resulted in : "+result);
    }
}