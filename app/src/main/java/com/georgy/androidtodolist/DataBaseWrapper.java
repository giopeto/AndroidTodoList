package com.georgy.androidtodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {

    public static final String TODOS = "Todos";
    public static final String TODO_ID = "id";
    public static final String TODO_TITLE = "title";
    public static final String TODO_DESCRIPTION = "description";
    public static final String TODO_FOR_DATE = "for_date";

    private static final String DATABASE_NAME = "Todos.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + TODOS
            + "(" + TODO_ID + " integer primary key autoincrement, "
            + TODO_TITLE + " text not null, "
            + TODO_DESCRIPTION + " text not null, "
            + TODO_FOR_DATE + " text not null);";

    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

}