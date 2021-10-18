package com.example.listviewsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "studentmanagement";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Student";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_ID = "Id";

    public static String CREATE_TABLE = "create table "+TABLE_NAME+" ("+COLUMN_ID+" INTEGER PRIMARY KEY,"
            + COLUMN_NAME+" TEXT, "+COLUMN_AGE+" INTERGER )";
    public static String DROP_TABLE = "drop table if exists "+TABLE_NAME;

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    public void addStudent(Student student){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());

        db.insert(TABLE_NAME,null,values);
    }

    public void updateStudent(Student student){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());

        String selection = COLUMN_ID +" = ";
        String[] selectArgs = {student.getId()+""};

        db.update(TABLE_NAME,values,selection,selectArgs);
    }

    public void deleteStudent(Student student){
        String sql = "delete from "+TABLE_NAME+" where "+COLUMN_ID +" = "+student.getId();

        SQLiteDatabase db = getWritableDatabase();
        db.rawQuery(sql, null);
    }

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> arrayList = new ArrayList<>();
        String sql = "select * from "+TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndexOrThrow(COLUMN_ID));
            String name = c.getString(c.getColumnIndexOrThrow(COLUMN_NAME));
            int age = c.getInt(c.getColumnIndexOrThrow(COLUMN_AGE));

            arrayList.add(new Student(id,name,age));

        }

        return arrayList;
    }
}
