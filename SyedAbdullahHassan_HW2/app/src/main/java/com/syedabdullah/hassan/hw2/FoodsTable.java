package com.syedabdullah.hassan.hw2;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class FoodsTable {

    public static final String TABLE_NAME="foods";
    public static final String FIELD_ID="id";
    public static final String FIELD_FOODNAME="foodname";
    public static final String FIELD_COUNTRYNAME="countryName";
    public static final String FIELD_IMAGE="image";
    public static final String FIELD_DESCRIPTION="description";
    public static final String CREATE_TABLE = "CREATE TABLE  " + TABLE_NAME  +" (" + FIELD_ID  +" INTEGER," + FIELD_FOODNAME  +" TEXT NOT NULL," + FIELD_COUNTRYNAME  +" INTEGER NOT NULL," +FIELD_IMAGE + " BLOB," + FIELD_DESCRIPTION  + " TEXT NOT NULL,"  + " PRIMARY KEY( " + FIELD_ID  +" AUTOINCREMENT)" + ");";
    public static final String DROP_TABLE = "DROP TABLE if exists " + TABLE_NAME;


    public static List<Food> getAll(DatabaseHelper db){

        Cursor cursor = db.getAllRecords(TABLE_NAME, null);
        List<Food> data=new ArrayList<Food>();
        Food food = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String foodname = cursor.getString(1);
            String countryName = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            String description = cursor.getString(4);

            food = new Food(id, foodname, countryName, image, description);
            data.add(food);
        }
        return data;
    }

    public static List<Food> find(DatabaseHelper db, String key){

        String where = FIELD_FOODNAME+" like '%"+key+"%'";
        Cursor cursor = db.getSomeRecords(TABLE_NAME,null, where);
        List<Food> data = new ArrayList<>();
        Food food = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String foodname = cursor.getString(1);
            String countryName = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            String description = cursor.getString(4);

            food = new Food(id, foodname, countryName, image, description);
            data.add(food) ;
        }
        return data;
    }
    public static boolean insert(DatabaseHelper db,int id, String foodname, String countryName, byte[] image, String description){
        ContentValues contentValues = new ContentValues( );

        contentValues.put(FIELD_FOODNAME, foodname);
        contentValues.put(FIELD_COUNTRYNAME, countryName);
        contentValues.put(FIELD_IMAGE, image);
        contentValues.put(FIELD_DESCRIPTION, description);

        boolean res = db.insert(TABLE_NAME,contentValues);

        return res;
    }
    public static boolean delete(DatabaseHelper db, int id){
        String where = FIELD_ID +" = "+ id;
        boolean res = db.delete(TABLE_NAME,where);
        return res;
    }
}
