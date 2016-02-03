package com.example.poorwa.shantibanapp;

import android.provider.BaseColumns;

/**
 * Created by poorwa on 2/2/16.
 */
public class BankFdInterestDBHelper {

    public BankFdInterestDBHelper() {

    }

    public static abstract class TableInfo implements BaseColumns {
        public static final String BANK_NAME = "bank_name";
        public static final String AMOUNT = "amount";
        public static final String ACCOUNT_NUMBER = "account_number";
        public static final String INTEREST_RATE = "interest_rate";
        public static final String INTEREST = "interest";
        public static final String DURATION = "duration";
        public static final String TABLE_NAME = "BankFdInterest";
        public static final String DATABASE_NAME = "PARS";
    }

}
