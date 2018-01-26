package com.example.ather.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Notes.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS USER" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT, DOB TEXT, GENDER NUMERIC );");
        db.execSQL("CREATE TABLE IF NOT EXISTS NOTES" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOTE TEXT, EMAIL TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS NOTES");
        onCreate(db);
    }

    public Cursor get_users()
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM USER",null);
        return cursor;
    }

    public void insert_user(String name, String email,String password,String DOB,int gender)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("EMAIL",email);
        contentValues.put("PASSWORD",password);
        contentValues.put("DOB",DOB);
        contentValues.put("GENDER",gender);
        this.getWritableDatabase().insertOrThrow("USER","",contentValues);
    }

    public void insert_note(String note, String email)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOTE",note);
        contentValues.put("EMAIL",email);
        this.getWritableDatabase().insertOrThrow("NOTES","",contentValues);
    }
}
