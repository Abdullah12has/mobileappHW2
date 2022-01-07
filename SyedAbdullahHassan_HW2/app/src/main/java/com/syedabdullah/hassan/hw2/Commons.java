package com.syedabdullah.hassan.hw2;

import android.database.Cursor;

import java.util.ArrayList;

public class Commons {
    public static Food food;
    public static Cursor cursor;
    public static ArrayList<Food> data;
    public static ArrayList<Student> dataStudents;

    public static Food getFood() {
        return food;
    }

    public static void setFood(Food food) {
        Commons.food = food;
    }

    public static Cursor getCursor() {
        return cursor;
    }

    public static void setCursor(Cursor cursor) {
        Commons.cursor = cursor;
    }

    public static ArrayList<Food> getData() {
        return data;
    }

    public static void setData(ArrayList<Food> data) {
        Commons.data = data;
    }


    public static ArrayList<Student> getDataStudents() {
        return dataStudents;
    }

    public static void setDataStudents(ArrayList<Student> dataStudents) {
        Commons.dataStudents = dataStudents;
    }
}
