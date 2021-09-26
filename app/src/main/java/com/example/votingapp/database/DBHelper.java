package com.example.votingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userInfo.db";

    public DBHelper(Context context) {super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        //users table
        MyDB.execSQL("create Table users(name TEXT, username TEXT primary key, email TEXT, phoneNumber TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        //users table
        MyDB.execSQL("drop Table if exists users");
    }

    //users table
    //insert
    public Boolean insertData(String name, String username, String email, String phoneNumber, String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("username", username);
        values.put("email", email);
        values.put("phoneNumber", phoneNumber);
        values.put("password", password);

        long result = MyDB.insert("users", null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    //view
    public Cursor getData() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users", null);
        return cursor;
    }

    //update
    public Boolean updateData(String name, String username, String email, String phoneNumber, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("email", email);
        values.put("phoneNumber", phoneNumber);
        values.put("password", password);

        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[] {username});

        if (cursor.getCount()>0) {
            long result = MyDB.update("users", values, "username = ?", new String[] {username});
            if (result == -1) {
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    //delete
    public Boolean deleteData(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0) {
            long result = MyDB.delete("users", "username = ?", new String[] {username});
            if (result == -1) {
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    //view one
    public Cursor getOneData(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[] {username});
        return cursor;
    }

    //user table
    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
