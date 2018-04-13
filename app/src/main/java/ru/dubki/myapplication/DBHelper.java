package ru.dubki.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table employee (" + " id integer primary key autoincrement," + "fname text," + "sname text," + "otch text,"  + "date text" + " ); " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
