package de.pathmaperuma.weightlog.sql;

import android.database.sqlite.SQLiteDatabase;

class DeleteAllData implements DataManipulation {

    @Override
    public void manipulateIn(SQLiteDatabase database) {
        database.delete(DataManipulator.TABLE_NAME, null, null);
    }
}
