package com.vongvia.acitivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liqihan on 2017/6/18.
 */

public class RankDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ranklist.db";
    public static final int DATABASE_VERSION = 1;

    public RankDbHelper(Context context) {
        super(context,DATABASE_NAME ,null ,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_WAITLIST_TABLE = "CREATE TABLE " + RankDbContract.rankEntry.TABLE_NAME + " (" +
                RankDbContract.rankEntry._ID + "   INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                RankDbContract.rankEntry.COLUMN_TIME + " INTEGER NOT NULL, " +
                RankDbContract.rankEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        db.execSQL(CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + RankDbContract.rankEntry.TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
