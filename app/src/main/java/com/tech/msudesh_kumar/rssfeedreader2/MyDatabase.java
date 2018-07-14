package com.tech.msudesh_kumar.rssfeedreader2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase {

    private MyHelper mh;
    private SQLiteDatabase sdb;

    public MyDatabase(Context c) {
        mh = new MyHelper(c,"rssfeed.db",null,1);
    }

    public void open()
    {
        sdb = mh.getWritableDatabase();
    }

    public class MyHelper extends SQLiteOpenHelper
    {

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table Categories(_id integer primary key, Categories text);");
            ContentValues c = new ContentValues();
            c.put("Categories","All");
            db.insert("Categories",null,c);
            db.execSQL("create table Sites_Links(_id integer primary key, Sites text, Links text, Categories text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }

    public void insertCategories(String category)
    {
        ContentValues cv = new ContentValues();
        cv.put("Categories",category);
        sdb.insert("Categories",null, cv);
    }

    public Cursor queryCategories()
    {
        Cursor c = sdb.query("Categories",new String[]{"Categories"}, null, null, null, null, null, null);
        return c;
    }

    public void insertSite(String siteName, String siteLink, String Category)
    {
        ContentValues cv = new ContentValues();
        cv.put("Sites",siteName);
        cv.put("Links",siteLink);
        cv.put("Categories",Category);
        sdb.insert("Sites_Links",null, cv);
    }

    public Cursor querySites(String category)
    {
        Cursor c;
        c = sdb.query("Sites_Links",new String[]{"Sites"}, "Categories = ?", new String[]{category}, null, null, null, null);
        /*if(category=="All")
        {
            c = sdb.query("Sites_Links",new String[]{"Sites"}, null, null, null, null, null, null);
        }
        else
        {
            c = sdb.query("Sites_Links",new String[]{"Sites"}, "Categories = ?", new String[]{category}, null, null, null, null);
        }*/
        return c;
    }

    public Cursor queryLinks(String site)
    {
        Cursor c = sdb.query("Sites_Links",new String[]{"Links"}, "Sites = ?", new String[]{site}, null, null, null, null);
        return c;
    }

    public void close()
    {
        if(sdb!=null)
        {
            sdb.close();
        }
    }

}
