package com.example.root.readpermissions.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.root.readpermissions.model.User;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    public  SQLiteDatabase db;


    //company  table
    private static final String CREATE_TABLE_USER="create table "+ DBContract.TABLE_NAME_USER+
            " (id integer primary key autoincrement, "+ DBContract.NAME+" text,"+ DBContract.EMAIL+" text,"+
             DBContract.PASSWORD +" text);";

    //drop tables
    private static final String DROP_TABLE_USER="drop table if exists "+DBContract.TABLE_NAME_USER;

    public DbHelper(Context context){
        super(context,DBContract.DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USER);
    }

    //user tables
    public void saveCompanyToLocalDatabase(User user, SQLiteDatabase database){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBContract.NAME,user.getName());
        contentValues.put(DBContract.EMAIL,user.getEmail());
        contentValues.put(DBContract.PASSWORD,user.getPassword());
        database.insert(DBContract.TABLE_NAME_USER,null,contentValues);
    }

    public void updateCompanyLocalDatabase(User user, SQLiteDatabase database){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBContract.NAME,user.getName());
        contentValues.put(DBContract.PASSWORD,user.getPassword());
        String selection=DBContract.EMAIL+" LIKE ?";
        String[] selection_args={user.getEmail()};
        database.update(DBContract.TABLE_NAME_USER,contentValues,selection,selection_args);
    }

    public String login(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + DBContract.TABLE_NAME_USER + " WHERE " + DBContract.EMAIL + " LIKE '%" + email +"%'";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String userPassword = cursor.getString(cursor.getColumnIndex(DBContract.PASSWORD));
        cursor.close();
        return userPassword;
    }

    public boolean doesUserExist(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + DBContract.TABLE_NAME_USER + " WHERE " + DBContract.EMAIL + " LIKE '%" + email +"%'";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return false;
        }

        cursor.moveToFirst();
        cursor.close();
        return true;
    }

}
