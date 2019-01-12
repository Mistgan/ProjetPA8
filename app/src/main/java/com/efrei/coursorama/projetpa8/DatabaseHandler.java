package com.efrei.coursorama.projetpa8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String SHOP_KEY = "id";
    public static final String SHOP_USER = "user";
    public static final String SHOP_BUDGET = "budget";

    public static final String SHOP_TABLE_NAME = "Shop";
    public static final String SHOP_TABLE_CREATE =
            "CREATE TABLE " + SHOP_TABLE_NAME + " (" +
                    SHOP_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SHOP_USER + " TEXT, " +
                    SHOP_BUDGET + " REAL);";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SHOP_TABLE_CREATE);
    }
    public static final String METIER_TABLE_DROP = "DROP TABLE IF EXISTS " + SHOP_TABLE_NAME + ";";
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(METIER_TABLE_DROP);
        onCreate(db);
    }
}