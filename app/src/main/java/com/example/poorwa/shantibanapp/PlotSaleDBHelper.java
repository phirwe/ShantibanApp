package com.example.poorwa.shantibanapp;

import android.provider.BaseColumns;

/**
 * Created by poorwa on 2/2/16.
 */
public class PlotSaleDBHelper {

    public PlotSaleDBHelper() {
    }

    public static abstract class TableInfo implements BaseColumns {
        public static final String MEMBER_NAME = "member_name";
        public static final String PLOT_NUMBER = "plot_number";
        public static final String DATE = "date";
        public static final String AMOUNT = "amount";
        public static final String TO_WHOM = "to_whom";
        public static final String PERCENTAGE_AMOUNT = "percentage_amount";
        public static final String TABLE_NAME = "PlotSale";
        public static final String DATABASE_NAME = "PARS";
    }

}
