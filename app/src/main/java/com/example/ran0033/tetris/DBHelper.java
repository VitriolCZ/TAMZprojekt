package com.example.ran0033.tetris;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by radosek on 8/12/2015.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "TetrisDB.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table contacts " + "(id integer primary key, name text)");
        db.execSQL("CREATE TABLE stats " + "(id INTEGER PRIMARY KEY, nick TEXT, level INTEGER, score INTEGER, date TEXT, sync INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS stats");
        onCreate(db);
    }

    public boolean insertScore(String nick, Integer level, Integer score)
    {
        Date currentTime = Calendar.getInstance().getTime();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nick", nick);
        contentValues.put("level", level);
        contentValues.put("score", score);
        contentValues.put("date", currentTime.toString());
        contentValues.put("sync", 0);
        db.insert("stats", null, contentValues);
        return true;
    }

    //Cursor representuje vracena data
    public ArrayList<Entry> getStats(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from stats ORDER BY score DESC, level DESC", null);
        res.moveToFirst();

        ArrayList<Entry> arrayList = new ArrayList<>();

        while(res.isAfterLast() == false){
            Entry e = new Entry();

            e.nick = res.getString(res.getColumnIndex("nick"));
            e.date = res.getString(res.getColumnIndex("date"));
            e.level = res.getInt(res.getColumnIndex("level"));
            e.score = res.getInt(res.getColumnIndex("score"));

            arrayList.add(e);
            res.moveToNext();
        }

        return arrayList;
    }

    public void removeAllStats()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("stats", "1", null);
    }
}
