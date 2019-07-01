package com.hussain.fixturewc18.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hussain.fixturewc18.activies.MainActivityMatchFixture;
import com.hussain.fixturewc18.model.Record;

import java.util.LinkedList;
import java.util.List;

public class MatchFixtureDatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "Student.db";

    //Table for MainActivityMatchFixture
    public static final String TableName = "MatchFixture";
    public static final String Col1 = "ID";
    public static final String Col2 = "TeamA";
    public static final String Col3 = "TeamB";
    public static final String Col4 = "MatchPlay";
    public static final String Col5 = "GroupName";
    public static final String Col6 = "Vanue";
    public static final String Col7 = "Result";
    public static final String Col8 = "Winner";
    //end of table


    public MatchFixtureDatabaseHelper(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TableName
                +"(ID integer primary key autoincrement,TeamA text,TeamB text,MatchPlay text,GroupName text,Vanue text,Result text,Winner text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public boolean insertData(String TeamA, String TeamB, String MatchPlay,String GroupName, String Vanue, String Result, String Winner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col2,TeamA);
        values.put(Col3,TeamB);
        values.put(Col4,MatchPlay);
        values.put(Col5,GroupName);
        values.put(Col6,Vanue);
        values.put(Col7,Result);
        values.put(Col8,Winner);
        long result = db.insert(TableName,null, values);
        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean updateData(String ID,String TeamA, String TeamB, String MatchPlay,String GroupName, String Vanue, String Result, String Winner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col1,ID);
        values.put(Col2,TeamA);
        values.put(Col3,TeamB);
        values.put(Col4,MatchPlay);
        values.put(Col5,GroupName);
        values.put(Col6,Vanue);
        values.put(Col7,Result);
        values.put(Col8,Winner);
        long result = db.update(TableName, values, "ID = ?", new String[]{ID});
        //Log.e("DB", String.valueOf(result));
        if (result==-1)
            return false;
        else
            return true;
    }

    public List<Record> getData(){
        List<Record> MatchFixtureRecord = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TableName,null);
        Record record;

        if (cursor.moveToFirst()){
            do {
                record = new Record();
                record.setTeamA(cursor.getString(cursor.getColumnIndex(Col2)));
                record.setTeamB(cursor.getString(cursor.getColumnIndex(Col3)));

                //String PlayTime = check(cursor.getString(cursor.getColumnIndex(Col4)));
                //int pos = todayMatchPos(cursor.getString(cursor.getColumnIndex(Col4)));
                //PlayTime = check(PlayTime);
                //record.setMatchPlay(PlayTime);
                record.setMatchPlay(cursor.getString(cursor.getColumnIndex(Col4)));
                record.setGroup(cursor.getString(cursor.getColumnIndex(Col5)));
                record.setVanue(cursor.getString(cursor.getColumnIndex(Col6)));
                record.setResult(cursor.getString(cursor.getColumnIndex(Col7)));
                MatchFixtureRecord.add(record);
            }while (cursor.moveToNext());
        }
        return MatchFixtureRecord;
    }

    public List<Record> getDataForFavTeam(String FavTeamName){
        List<Record> MatchFixtureRecord = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from Matchfixture where TeamA = '"+ FavTeamName +"' or TeamB = '"+ FavTeamName +"'" ,null);
        Record record;

        if (cursor.moveToFirst()){
            do {
                record = new Record();
                record.setTeamA(cursor.getString(cursor.getColumnIndex(Col2)));
                record.setTeamB(cursor.getString(cursor.getColumnIndex(Col3)));
                record.setMatchPlay(cursor.getString(cursor.getColumnIndex(Col4)));
                record.setGroup(cursor.getString(cursor.getColumnIndex(Col5)));
                record.setVanue(cursor.getString(cursor.getColumnIndex(Col6)));
                record.setResult(cursor.getString(cursor.getColumnIndex(Col7)));
                MatchFixtureRecord.add(record);
            }while (cursor.moveToNext());
        }
        return MatchFixtureRecord;
    }

    public boolean isEmpty(){
        boolean status;
        SQLiteDatabase database = this.getReadableDatabase();
        int NoOfRows = (int) DatabaseUtils.queryNumEntries(database,TableName);
        status = (NoOfRows==0)? true: false;
        return status;
    }

    private String check(String playTime) {
        MainActivityMatchFixture mainActivityMatchFixture;
        mainActivityMatchFixture = new MainActivityMatchFixture();
        String DateTime = mainActivityMatchFixture.DateTime();
        //String DateTime = "2018-11-18 22:55:44";
        String SystemDate = DateTime.substring(8,10);
        String PlayDate = playTime.substring(0,2);

        if (SystemDate.equalsIgnoreCase(PlayDate))
            return "Today";
        return playTime;
    }

    /*private int todayMatchPos(String playTime) {
        MainActivityMatchFixture mainActivityMatchFixture;
        mainActivityMatchFixture = new MainActivityMatchFixture();
        String DateTime = mainActivityMatchFixture.DateTime();
        //String DateTime = "2018-11-18 22:55:44";
        String SystemDate = DateTime.substring(8,10);
        String PlayDate = playTime.substring(0,2);

        SQLiteDatabase db = this.getWritableDatabase();

        if (SystemDate.equalsIgnoreCase(PlayDate)){
            Cursor cursor = db.rawQuery(
                    "select ID from Matchfixture where MatchPlay = '"+ playTime +"'" ,null);
            return Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col1)));
        }
        return 48;
    }*/

}