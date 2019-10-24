package com.example.planningpoker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Ppoker";
    public static final String TABLE_USER ="USER";
    public static final String COLU_1 ="U_ID";
    public static final String COLU_2 ="NAME";
    public static final String TABLE_TASK ="TASK";
    public static final String COLT_1 ="T_ID";
    public static final String COLT_2 ="QUESTION";
    public static final String TABLE_VOTING ="VOTING";
    public static final String COLV_1 ="V_ID";
    public static final String COLV_2 ="WHO";
    public static final String COLV_3 ="VOTE";
    public static final String COLV_4 ="WHAT";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_USER+" (U_ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT)");
        db.execSQL("create table "+TABLE_TASK+"( T_ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION TEXT)");
        db.execSQL("create table "+TABLE_VOTING+"( V_ID INTEGER PRIMARY KEY AUTOINCREMENT,WHO INTEGER,VOTE INTEGER,WHAT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TASK);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_VOTING);
        onCreate(db);

    }
    public boolean InsertData_User(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLU_2,name);
        long result = db.insert(TABLE_USER,null,contentValues);
        if (result == -1)
            return  false;
        else
            return true;
    }
    public boolean InsertData_TASK(String question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLT_2,question);
        long result = db.insert(TABLE_TASK,null,contentValues);
        if (result == -1)
            return  false;
        else
            return true;
    }
    public boolean InsertData_Voting(Integer who,Integer vote, String what){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLV_2,who);
        contentValues.put(COLV_3,vote);
        contentValues.put(COLV_4,what);
        long result = db.insert(TABLE_VOTING,null,contentValues);
        if (result == -1)
            return  false;
        else
            return true;
    }
}
