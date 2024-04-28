package com.example.login_signup_page_android_sqllite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "SignUp.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "SignUp.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create table allusers(name TEXT, email TEXT primary key, website TEXT, password Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("drop Table if exists allusers");

    }

    public boolean insertData(String name, String email, String website, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("website", website);
        contentValues.put("password", password);

        long result = MyDatabase.insert("allusers",null, contentValues);
        MyDatabase.close();

        return result != -1;

    }
    public boolean updatePassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Password", password);

        long result = MyDatabase.update("allusers",contentValues, "email = ?", new String[]{email});
        return result != -1;

    }

    public boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});

        return cursor.getCount() > 0;
    }

    public boolean checkCredentials(String email, String password) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM allusers WHERE email = ? AND password = ?", new String[]{email, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        MyDatabase.close();
        return isValid;
    }

    public void insertDemoAccount() {
        insertData("Zubair", "demo@example.com", "example.com", "demo123");
    }



}
