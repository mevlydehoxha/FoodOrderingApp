package com.example.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOrders extends SQLiteOpenHelper {

    public static final String DBNAME= "Orders.db";

    public DBOrders(Context context) {
        super(context, "Orders.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE orders(ordername TEXT, quantity TEXT, extra TEXT, address TEXT, phonenumber TEXT, notes TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS orders");

    }

    public Boolean insertOrder(String ordername, String quantity, String extra, String phonenumber, String notes){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("ordername",ordername);
        contentValues.put("quantity",quantity);
        contentValues.put("extra",extra);
        contentValues.put("phonenumber", phonenumber);
        contentValues.put("notes",notes);
        long result=db.insert("orders",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }


}
