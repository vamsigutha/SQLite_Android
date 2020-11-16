package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY=
            "CREATE TABLE "+UserContract.NewUserInfo.TABLE_NAME+"("+ UserContract.NewUserInfo.USER_NAME+" TEXT,"+
            UserContract.NewUserInfo.USER_MOB+" TEXT,"+ UserContract.NewUserInfo.USER_EMAIL+" TEXT);";

    public UserDBHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","Database Created/Opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //If the database is not already created system will call oncreate method
        //Here we create table

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created...");

    }

    public void addInformations(String name, String mob, String email, SQLiteDatabase db){

        /* To insert into database we need to create content values ,
        Content values maps key and value pairs, here keys are table names and
         values are data we want to insert */

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,name);
        contentValues.put(UserContract.NewUserInfo.USER_MOB,mob);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL,email);

        //In below line if we put null in nullColumn hack database won't insert row,
        // if content values are empty

        db.insert(UserContract.NewUserInfo.TABLE_NAME,null,contentValues);
        Log.e("DATABASE OPERATIONS","One row is inserted...");

    }

    public Cursor getInformations(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {UserContract.NewUserInfo.USER_NAME,
                UserContract.NewUserInfo.USER_MOB,
                UserContract.NewUserInfo.USER_EMAIL};

        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME, projections,
                null, null, null, null, null);
        return cursor;
    }

    public Cursor getContact(String user_name,SQLiteDatabase db){
        String[] projections = {UserContract.NewUserInfo.USER_MOB,UserContract.NewUserInfo.USER_EMAIL};
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {user_name};
        Cursor cursor = db.query(UserContract.NewUserInfo.TABLE_NAME, projections, selection,
                selection_args, null, null, null);
        return cursor;

    }

    public void deleteContact(String user_name, SQLiteDatabase db) {
        String selection = UserContract.NewUserInfo.USER_NAME + " LIKE ?";
        String[] selection_args = {user_name};
        db.delete(UserContract.NewUserInfo.TABLE_NAME,selection,selection_args);
    }

    public int updateContact(String old_name, String new_name, String new_mobile, String new_email, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,new_name);
        contentValues.put(UserContract.NewUserInfo.USER_MOB,new_mobile);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL,new_email);
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {old_name};
        //Below returns how many are rows updated
        int count = db.update(UserContract.NewUserInfo.TABLE_NAME,contentValues,selection,selection_args);
        return count;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
