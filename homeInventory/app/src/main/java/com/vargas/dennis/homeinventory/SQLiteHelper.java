package com.vargas.dennis.homeinventory;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.design.widget.Snackbar;
import android.util.Log;

import java.sql.Blob;

public class SQLiteHelper extends SQLiteOpenHelper{

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sqlQuery){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sqlQuery);
    }

    public void InsertData(String name, String price, String quantity, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sqlQuery = "INSERT INTO INVENTORYITEMS VALUES (null, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sqlQuery);

        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindString(3, quantity);
        Log.i("SQLHELPER","add-itmem: "+quantity);
        try{
            statement.bindBlob(4, image);
        }catch(Exception e){
            Log.i("BLOBIMAGE", "image is null");
//            byte [] byteArray = new byte[1];
//            statement.bindBlob(3,(Blob)null);
            statement.bindNull(4);
        }
        try{
            statement.executeInsert();
        }catch (Exception e){
        }

    }

    public void DeleteData(int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM INVENTORYITEMS WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1, id);
        statement.execute();
        database.close();
    }

    public Cursor GetData(String sqlQuery){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sqlQuery, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
