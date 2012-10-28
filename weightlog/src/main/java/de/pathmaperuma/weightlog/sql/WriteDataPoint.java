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
        insertStmt.bindString(1, dataPoint.getUnixTimeString());
        insertStmt.bindString(2, dataPoint.getWeightString());
        insertStmt.bindString(3, dataPoint.getFatString());
        insertStmt.bindString(4, dataPoint.getWaterString());
        insertStmt.bindString(5, dataPoint.getMuscleString());
        insertStmt.bindString(6, dataPoint.getKcalString());
        insertStmt.bindString(7, dataPoint.getBoneString());
        System.out.println("Query ready : "+ insertStmt.toString());
        long result = insertStmt.executeInsert();
        System.out.println("Insert resulted in : "+result);
    }
}