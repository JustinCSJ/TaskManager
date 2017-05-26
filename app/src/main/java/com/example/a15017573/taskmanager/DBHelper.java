package com.example.a15017573.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15017573 on 26/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESC = "description";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTaskTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT," + COLUMN_DESC + " TEXT)";
        db.execSQL(createTaskTableSql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_TASK);
    }

    public long insertTask(String taskName, String taskDesc) {
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(COLUMN_NAME, taskName);
        values.put(COLUMN_DESC, taskDesc);
        long result = db.insert(TABLE_TASK, null, values);
        db.close();
        Log.d("SQL Insert", "" + result);
        return result;
    }

    public ArrayList<String> getAllTasks() {
        ArrayList<String> tasks = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_ID + ", " +
                COLUMN_NAME + ", " + COLUMN_DESC + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                tasks.add(id + " " + name + "\n" + desc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

//    public int deleteTask(int id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String condition = COLUMN_ID + "=?";
//        String[] args = {String.valueOf(id)};
//        int result = db.delete(TABLE_TASK, condition, args);
//        db.close();
//        return result;
//    }


}
