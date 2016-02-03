package com.example.poorwa.shantibanapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.poorwa.shantibanapp.PlotSaleDBHelper.*;

/**
 * Created by poorwa on 2/2/16.
 */
public class PlotSaleDBInterface extends SQLiteOpenHelper {

    public long z;
    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE " + TableInfo.TABLE_NAME + "( " +
            TableInfo.MEMBER_NAME + " TEXT, " +
            TableInfo.PLOT_NUMBER + " INTEGER PRIMARY KEY, " +
            TableInfo.DATE + " TEXT, " +
            TableInfo.AMOUNT + " TEXT, " +
            TableInfo.TO_WHOM + " TEXT, " +
            TableInfo.PERCENTAGE_AMOUNT + " TEXT" + ")";


    public PlotSaleDBInterface(Context context) {
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

    public void insert(PlotSale income) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableInfo.MEMBER_NAME, income.getMemberName());
        cv.put(TableInfo.PLOT_NUMBER, income.getPlotNumber());
        cv.put(TableInfo.DATE, income.getDate());
        cv.put(TableInfo.AMOUNT, income.getAmount());
        cv.put(TableInfo.TO_WHOM, income.getToWhom());
        cv.put(TableInfo.PERCENTAGE_AMOUNT, income.getPercentageAmount());

        z = SQ.insert(TableInfo.TABLE_NAME, null, cv);
        Log.println(Log.ASSERT, "Row inserted", String.valueOf(z));
        SQ.close();
    }

    public PlotSale getInfo(String plotNumber) {
        SQLiteDatabase SQ = this.getReadableDatabase();
        PlotSale income = new PlotSale();
        Cursor c;
        c = SQ.rawQuery("SELECT * FROM " + TableInfo.TABLE_NAME + "WHERE " + TableInfo.PLOT_NUMBER + " = " + plotNumber, null);
        c.moveToFirst();
        int i = 0;

        income.setMemberName(c.getString(i++));
        income.setPlotNumber(c.getString(i++));
        income.setDate(c.getString(i++));
        income.setAmount(c.getString(i++));
        income.setToWhom(c.getString(i++));
        income.setPercentageAmount(c.getString(i++));

        SQ.close();
        return income;
    }

    public void update(PlotSale income) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        String selection = "plot_number = ?";
        String[] args = {income.getPlotNumber()};
        ContentValues cv = new ContentValues();

        cv.put(TableInfo.MEMBER_NAME, income.getMemberName());
        cv.put(TableInfo.PLOT_NUMBER, income.getPlotNumber());
        cv.put(TableInfo.DATE, income.getDate());
        cv.put(TableInfo.AMOUNT, income.getAmount());
        cv.put(TableInfo.TO_WHOM, income.getToWhom());
        cv.put(TableInfo.PERCENTAGE_AMOUNT, income.getPercentageAmount());

        SQ.update(TableInfo.TABLE_NAME, cv, selection, args);
        SQ.close();

    }

}
