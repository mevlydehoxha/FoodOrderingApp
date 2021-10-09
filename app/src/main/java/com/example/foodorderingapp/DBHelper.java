package com.example.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME= "Users.db";

    public DBHelper(Context context) {
        super(context, "Users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(user TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("DROP TABLE IF EXISTS users");
    }
    public Boolean insertData(String user, String password){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("user", user);
        contentValues.put("password", password);
        long result= MyDB.insert("users",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String user){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("SELECT * FROM users where user=?", new String[] {user});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Boolean checkusernamepassword(String user,String password)
    {
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("SELECT * FROM users WHERE user=? and password=?", new String[] {user,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


}
