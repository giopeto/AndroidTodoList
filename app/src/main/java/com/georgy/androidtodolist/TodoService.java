package com.georgy.androidtodolist;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TodoService {

    private DataBaseWrapper dbHelper;
    private SQLiteDatabase database;
    private String[] STUDENT_TABLE_COLUMNS = { DataBaseWrapper.TODO_ID, DataBaseWrapper.TODO_TITLE, DataBaseWrapper.TODO_DESCRIPTION, DataBaseWrapper.TODO_FOR_DATE };

    public TodoService(Context context) {

        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Todo set (String title, String description, String forDate) {

        Todo todo = new Todo(title, description, forDate);
        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.TODO_TITLE, title);
        values.put(DataBaseWrapper.TODO_DESCRIPTION, description);
        values.put(DataBaseWrapper.TODO_FOR_DATE, forDate);

        long todoID = database.insert(DataBaseWrapper.TODOS, null, values);

        Cursor cursor = database.query(DataBaseWrapper.TODOS,
                STUDENT_TABLE_COLUMNS, DataBaseWrapper.TODO_ID + " = "
                        + todoID, null, null, null, null);

        cursor.moveToFirst();
        Todo thisTodo = parseTodo(cursor);
        cursor.close();
        return thisTodo;
    }

    public void delete (int id) {
        if (id > 0) {
            database.execSQL("DELETE FROM Todos WHERE id = " + id );
        }
    }

    public List getAllTodos() {
        List todos = new ArrayList();

       /* String TODOS = "Todos";
        String TODO_ID = "id";
        String TODO_TITLE = "title";
        String TODO_DESCRIPTION = "description";
        String TODO_FOR_DATE = "for_date";

        String DATABASE_NAME = "Todos.db";
        int DATABASE_VERSION = 1;

        String DATABASE_CREATE = "create table " + TODOS
                + "(" + TODO_ID + " integer primary key autoincrement, "
                + TODO_TITLE + " text not null, "
                + TODO_DESCRIPTION + " text not null, "
                + TODO_FOR_DATE + " text not null);";
        database.execSQL(DATABASE_CREATE);*/

        Cursor cursor = database.query(DataBaseWrapper.TODOS,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Todo todo = parseTodo(cursor);
            todos.add(todo);
            cursor.moveToNext();
        }

        cursor.close();
        return todos;
    }

    private Todo parseTodo(Cursor cursor) {
        Todo todo = new Todo();
        todo.setId((cursor.getInt(0)));
        todo.setTitle(cursor.getString(1));
        todo.setDescription(cursor.getString(2));
        todo.setForDate(cursor.getString(3));
        return todo;
    }


}
