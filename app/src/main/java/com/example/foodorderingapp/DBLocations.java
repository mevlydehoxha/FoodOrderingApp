package com.example.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

public class DBLocations extends SQLiteOpenHelper {

    public static final String DBNAME= "Locations.db";

    public DBLocations(Context context) {
        super(context, "Locations.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE locations(address TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS locations");

    }

    public Boolean insertLocations(String address){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("address",address);
        long result=db.insert("locations",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }


}
