package com.example.poorwa.shantibanapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by poorwa on 29/1/16.
 */
public class IncomeDBInterface extends SQLiteOpenHelper {

    public long z;

    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE " + IncomeDBHelper.TableInfo.TABLE_NAME + " ( " +
        IncomeDBHelper.TableInfo.MEMBER_NAME + " TEXT, " +
        IncomeDBHelper.TableInfo.PLOT_NUMBER + " INTEGER PRIMARY KEY, " +
        IncomeDBHelper.TableInfo.PAYMENT_DATE + " TEXT, " +
        IncomeDBHelper.TableInfo.AMOUNT_PAID + " TEXT, " +
        IncomeDBHelper.TableInfo.LATE_FEE_FINE + " TEXT, " +
        IncomeDBHelper.TableInfo.TOTAL_AMOUNT + " TEXT" + ")";

    public IncomeDBInterface(Context context) {
        super(context, IncomeDBHelper.TableInfo.DATABASE_NAME, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int arg1, int arg2) {
        // TODO Auto-generated method stub
        sdb.execSQL("DROP TABLE IF EXISTS " + IncomeDBHelper.TableInfo.TABLE_NAME);
        onCreate(sdb);
    }

    public void insert(Income income) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(IncomeDBHelper.TableInfo.MEMBER_NAME, income.getMemberName());
        cv.put(IncomeDBHelper.TableInfo.PLOT_NUMBER, income.getPlotNumber());
        cv.put(IncomeDBHelper.TableInfo.PAYMENT_DATE, income.getPaymentDate());
        cv.put(IncomeDBHelper.TableInfo.AMOUNT_PAID, income.getAmountPaid());
        cv.put(IncomeDBHelper.TableInfo.LATE_FEE_FINE, income.getLateFeeFine());
        cv.put(IncomeDBHelper.TableInfo.TOTAL_AMOUNT, income.getTotalAmount());

        z = SQ.insert(IncomeDBHelper.TableInfo.TABLE_NAME, null, cv);
        Log.println(Log.ASSERT, "Rows inserted", String.valueOf(z));
        SQ.close();
    }

    public Income getInfo(String plotNumber) {
        SQLiteDatabase SQ = this.getReadableDatabase();
        Income income = new Income();
        Cursor c;
        c = SQ.rawQuery("SELECT * FROM " + IncomeDBHelper.TableInfo.TABLE_NAME + " WHERE " + IncomeDBHelper.TableInfo.PLOT_NUMBER + " = " + plotNumber, null);
        c.moveToFirst();
        int i = 0;
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setMemberName(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setPlotNumber(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setPaymentDate(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setAmountPaid(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setLateFeeFine(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setTotalAmount(c.getString(i++));

        return income;
    }

    public void update(Income income) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String selection = "plot_number = ?";
        String[] args = {income.getPlotNumber()};

        cv.put(IncomeDBHelper.TableInfo.MEMBER_NAME, income.getMemberName());
        cv.put(IncomeDBHelper.TableInfo.PLOT_NUMBER, income.getPlotNumber());
        cv.put(IncomeDBHelper.TableInfo.PAYMENT_DATE, income.getPaymentDate());
        cv.put(IncomeDBHelper.TableInfo.AMOUNT_PAID, income.getAmountPaid());
        cv.put(IncomeDBHelper.TableInfo.LATE_FEE_FINE, income.getLateFeeFine());
        cv.put(IncomeDBHelper.TableInfo.TOTAL_AMOUNT, income.getTotalAmount());

        SQ.update(IncomeDBHelper.TableInfo.TABLE_NAME, cv, selection, args);
        SQ.close();

    }

}
