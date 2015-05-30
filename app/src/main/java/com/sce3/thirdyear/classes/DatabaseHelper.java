package com.sce3.thirdyear.classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class creates the relation with the SQLite Database Helper
 * through which queries can be SQL called.
 *
 * @author Andrei
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // The database name and version
    private static final String DB_NAME = "weepi.db";
    private static final int DB_VERSION = 3;
    // The database user table
    private static final String SESSION_TABLE = "CREATE TABLE session(hash text);";
    private static final String DB_INSERT_NULL = "INSERT INTO session values('');";

    /**
     * Database Helper constructor.
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Creates the database tables.
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SESSION_TABLE);
        database.execSQL(DB_INSERT_NULL);

    }

    /**
     * Handles the table version and the drop of a table.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading databse from version" + oldVersion + "to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS user");
        onCreate(database);
    }
}
