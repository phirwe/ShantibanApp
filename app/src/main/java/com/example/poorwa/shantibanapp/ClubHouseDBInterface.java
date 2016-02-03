package com.example.poorwa.shantibanapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.poorwa.shantibanapp.ClubHouseDBHelper.*;

/**
 * Created by poorwa on 2/2/16.
 */
public class ClubHouseDBInterface extends SQLiteOpenHelper {

    public long z;

    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE " + TableInfo.TABLE_NAME + " ( " +
            TableInfo.MEMBER_NAME + " TEXT, " +
            TableInfo.PLOT_NUMBER + " INTEGER PRIMARY KEY, " +
            TableInfo.HOURS_RENTED + " TEXT, " +
            TableInfo.COST + " TEXT, " +
            TableInfo.DATE_RENTED + " TEXT" + ")";

    public ClubHouseDBInterface(Context context) {
        super(context, TableInfo.DATABASE_NAME, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME);
        onCreate(db);
    }

    public void insert(ClubHouse income) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableInfo.MEMBER_NAME, income.getMemberName());
        cv.put(TableInfo.PLOT_NUMBER, income.getPlotNumber());
        cv.put(TableInfo.HOURS_RENTED, income.getHoursRented());
        cv.put(TableInfo.COST, income.getCost());
        cv.put(TableInfo.DATE_RENTED, income.getDateRented());

        z = SQ.insert(TableInfo.TABLE_NAME, null, cv);
        Log.println(Log.ASSERT, "Row inserted", String.valueOf(z));
        SQ.close();
    }

    public ClubHouse getInfo(String plotNumber) {
        SQLiteDatabase SQ = this.getReadableDatabase();
        ClubHouse income = new ClubHouse();
        Cursor c;
        c = SQ.rawQuery("SELECT * FROM " + TableInfo.TABLE_NAME + "WHERE " + TableInfo.PLOT_NUMBER + " = " + plotNumber, null);
        c.moveToFirst();
        int i = 0;
        income.setMemberName(c.getString(i++));
        income.setPlotNumber(c.getString(i++));
        income.setHoursRented(c.getString(i++));
        income.setCost(c.getString(i++));
        income.setDateRented(c.getString(i++));

        SQ.close();
        return income;

    }

    public void update(ClubHouse income) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        String selection = "plot_number = ?";
        String[] args = {income.getPlotNumber()};
        ContentValues cv = new ContentValues();

        cv.put(TableInfo.MEMBER_NAME, income.getMemberName());
        cv.put(TableInfo.PLOT_NUMBER, income.getPlotNumber());
        cv.put(TableInfo.HOURS_RENTED, income.getHoursRented());
        cv.put(TableInfo.COST, income.getCost());
        cv.put(TableInfo.DATE_RENTED, income.getDateRented());

        SQ.update(TableInfo.TABLE_NAME, cv, selection, args);
        SQ.close();
    }

}
