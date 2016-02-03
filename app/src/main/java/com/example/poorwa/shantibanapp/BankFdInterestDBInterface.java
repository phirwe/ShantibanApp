package com.example.poorwa.shantibanapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.poorwa.shantibanapp.BankFdInterestDBHelper.*;

/**
 * Created by poorwa on 29/1/16.
 */
public class BankFdInterestDBInterface extends SQLiteOpenHelper {

    public long z;

    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE " + TableInfo.TABLE_NAME + " ( " +
            TableInfo.BANK_NAME + " TEXT, " +
            TableInfo.ACCOUNT_NUMBER + " TEXT PRIMARY KEY, " +
            TableInfo.AMOUNT + " TEXT, " +
            TableInfo.INTEREST_RATE + " TEXT, " +
            TableInfo.INTEREST + " TEXT, " +
            TableInfo.DURATION + " TEXT" + ")";

    public BankFdInterestDBInterface(Context context) {
        super(context, TableInfo.DATABASE_NAME, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int arg1, int arg2) {
        // TODO Auto-generated method stub
        sdb.execSQL("DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME);
        onCreate(sdb);
    }

    public void insert(BankFdInterest income) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableInfo.BANK_NAME, income.getBankName());
        cv.put(TableInfo.ACCOUNT_NUMBER, income.getAccountNumber());
        cv.put(TableInfo.AMOUNT, income.getAmount());
        cv.put(TableInfo.INTEREST_RATE, income.getInterestRate());
        cv.put(TableInfo.INTEREST, income.getInterest());
        cv.put(TableInfo.DURATION, income.getDuration());

        z = SQ.insert(TableInfo.TABLE_NAME, null, cv);
        Log.println(Log.ASSERT, "Rows inserted", String.valueOf(z));
        SQ.close();
    }

    public BankFdInterest getInfo(String accountNumber) {
        SQLiteDatabase SQ = this.getReadableDatabase();
        BankFdInterest income = new BankFdInterest();
        Cursor c;
        c = SQ.rawQuery("SELECT * FROM " + TableInfo.TABLE_NAME + " WHERE " + TableInfo.ACCOUNT_NUMBER + " = " + accountNumber, null);
        c.moveToFirst();
        int i = 0;
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setBankName(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setAccountNumber(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setAmount(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setInterestRate(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setInterest(c.getString(i++));
        Log.println(Log.ASSERT, "Inserted Values", c.getString(i));
        income.setDuration(c.getString(i++));

        SQ.close();
        return income;
    }

    public void update(BankFdInterest income) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String selection = "account_number LIKE ?";
        String[] args = {income.getAccountNumber()};

        cv.put(TableInfo.BANK_NAME, income.getBankName());
        cv.put(TableInfo.ACCOUNT_NUMBER, income.getAccountNumber());
        cv.put(TableInfo.AMOUNT, income.getAmount());
        cv.put(TableInfo.INTEREST_RATE, income.getInterestRate());
        cv.put(TableInfo.INTEREST, income.getInterest());
        cv.put(TableInfo.DURATION, income.getDuration());

        SQ.update(TableInfo.TABLE_NAME, cv, selection, args);
        SQ.close();
    }

}
