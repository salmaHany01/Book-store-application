package com.example.bsp3admoon;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import android.database.Cursor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DBname="Userdb";

    public DBHelper(Context context) {
        super(context,"Userdb",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB)
    {
        MyDB.execSQL("create Table users(username TEXT,email TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1)
    {
        MyDB.execSQL("drop Table if exists users");

    }

    public Boolean insertData(String username,String email,String password)
    {
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result=MyDB.insert("users",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String username)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkusermail(String usermail)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where email=?",new String[]{usermail});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkemailpassword(String email,String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }



}
