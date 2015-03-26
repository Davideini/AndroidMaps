package com.sce3.thirdyear.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class SQLiteDB {
    private static final String SESSION_TABLE = "session";
    private static final String HISTORY_TABLE = "history";

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public SQLiteDB(Context context) {
        this.context = context;
    }

    public SQLiteDB open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long updateSession(String hash){
        ContentValues values=new ContentValues();
        values.put("hash",hash);
        open();
        long retval=database.update(SESSION_TABLE,values,null,null);
        close();
        return retval;
    }

    public String getSavedSession(){
       open();
       Cursor c=database.query(SESSION_TABLE,new String[] {"hash"},null,null,null,null,null,null);
       c.moveToFirst();
       String hash=c.getString(c.getColumnIndex("hash"));
       close();
       return hash;
    }

}

