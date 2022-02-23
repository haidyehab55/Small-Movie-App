package com.example.movie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MovieDBhelper extends SQLiteOpenHelper {

    final static int DB_version = 1;
    final static String DB_Name = "MovieDataBase";
    SQLiteDatabase MovieDataBase;

    public MovieDBhelper(Context context) {
        super(context, DB_Name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table movie (id integer primary key," + " name text not null , description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists movie");
        onCreate(db);
    }

    public void createNewMovie (String name, String desc) {
        ContentValues row = new ContentValues();
        row.put("Name",name);
        row.put("Description", desc);
        MovieDataBase = getWritableDatabase();
        MovieDataBase.insert("movie",null,row);
        MovieDataBase.close();
    }
    public Cursor fetchAllMovies (){
        MovieDataBase = getReadableDatabase();
        String [] rowDetails = {"Name","Description","id"};
        Cursor cursor = MovieDataBase.query("movie", rowDetails,null,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
            MovieDataBase.close();
        }
            return cursor;
    }
    public String getMovieDesc (String name) {
        MovieDataBase = getReadableDatabase();
        String Desc ="";
        Cursor cursor = MovieDataBase.rawQuery("select description from movie where name = '"+name +"'",null);
        cursor.moveToFirst();
        Desc = cursor.getString(0);
        MovieDataBase.close();
        return Desc;
    }

}
