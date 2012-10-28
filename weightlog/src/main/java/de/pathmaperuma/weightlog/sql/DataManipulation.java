package de.pathmaperuma.weightlog.sql;

import android.database.sqlite.SQLiteDatabase;

public interface DataManipulation {

    void manipulateIn(SQLiteDatabase database);
}
