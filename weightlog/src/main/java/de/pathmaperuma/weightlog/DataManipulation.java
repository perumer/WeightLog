package de.pathmaperuma.weightlog;

import android.database.sqlite.SQLiteDatabase;

public interface DataManipulation {

    void manipulateIn(SQLiteDatabase database);
}
