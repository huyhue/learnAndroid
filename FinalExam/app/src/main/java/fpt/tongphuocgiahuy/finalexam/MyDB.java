package fpt.tongphuocgiahuy.finalexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "usermanagement";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "User";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AGE = "Nam";
    public static final String COLUMN_QUE = "Que";
    public static final String COLUMN_ID = "Id";

    public static String CREATE_TABLE = "create table "+TABLE_NAME+" ("+COLUMN_ID+" INTEGER PRIMARY KEY,"
            + COLUMN_NAME+" TEXT, "+COLUMN_QUE+" TEXT, "+COLUMN_AGE+" TEXT )";
    public static String DROP_TABLE = "drop table if exists " + TABLE_NAME;

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

    public void addUser(User user){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_QUE, user.getQueQuan());
        values.put(COLUMN_AGE, user.getNamSinh());

        db.insert(TABLE_NAME,null,values);
    }

    public void updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_QUE, user.getQueQuan());
        values.put(COLUMN_AGE, user.getNamSinh());

        String selection = COLUMN_ID +" = ";
        String[] selectArgs = {user.getId()+""};

        db.update(TABLE_NAME,values,selection,selectArgs);
    }
    public void updateName(User user){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE User SET Name = '" + user.getName() + "', Que = '"+ user.getQueQuan()+ "' WHERE Id = '" + user.getId() + "' ");

//        db.execSQL("UPDATE User SET Name = '" + user.getName() + "' WHERE Id = '" + user.getId() + "' ");
    }

    public void deleteUser(int id){
        String sql = "delete from "+TABLE_NAME+" where "+COLUMN_ID +" = "+id;

        SQLiteDatabase db = getWritableDatabase();
//        db.rawQuery(sql, null);
        db.execSQL(sql);
    }

    public void deleteNameUser(String name){
        String sql = "delete from "+TABLE_NAME+" where "+COLUMN_NAME +" = "+name;

        SQLiteDatabase db = getWritableDatabase();
        db.rawQuery(sql, null);
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> arrayList = new ArrayList<>();
        String sql = "select * from "+TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndexOrThrow(COLUMN_ID));
            String name = c.getString(c.getColumnIndexOrThrow(COLUMN_NAME));
            String que = c.getString(c.getColumnIndexOrThrow(COLUMN_QUE));
            String age = c.getString(c.getColumnIndexOrThrow(COLUMN_AGE));
            arrayList.add(new User(id, name, que, age));
        }
        return arrayList;
    }
}
