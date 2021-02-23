package com.syro.photonote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConnectionHelper extends SQLiteOpenHelper
{
    static final String CREATE_USER_TABLE =  "CREATE TABLE users (id INTEGER, name TEXT, secondName TEXT, email TEXT, password TEXT)";
    static final String CREATE_PHOTO_TABLE = "CREATE TABLE photos (id INTEGERS, fkUser INTEGER, photoReference TEXT, cameraModel TEXT, " +
                                             "iso TEXT, shutterSpeed TEXT, aperture TEXT, lightComments TEXT, lens TEXT, focalLenght TEXT, " +
                                             "numberofShoot TEXT, photoLocation TEXT)";

    public SQLiteConnectionHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PHOTO_TABLE);

        System.out.println("Database created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS photos");
        onCreate(db);
    }
}
