package com.example.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBComments extends SQLiteOpenHelper {
    public static final String DBNAME= "Suggestions.db";

    public DBComments(Context context) {
        super(context, "Suggestions.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE comments(rating INTEGER, comments TEXT, suggestions TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS comments");

    }

    public Boolean insertComments(String rating, String comments, String suggestions){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("rating",rating);
        contentValues.put("comments",comments);
        contentValues.put("suggestions",suggestions);
        long result=db.insert("comments",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
}