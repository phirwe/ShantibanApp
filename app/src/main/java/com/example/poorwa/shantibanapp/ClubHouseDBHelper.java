package com.example.poorwa.shantibanapp;

import android.provider.BaseColumns;

/**
 * Created by poorwa on 2/2/16.
 */
public class ClubHouseDBHelper {

    public ClubHouseDBHelper() {
    }

    public static abstract class TableInfo implements BaseColumns {
        public static final String MEMBER_NAME = "member_name";
        public static final String PLOT_NUMBER = "plot_number";
        public static final String HOURS_RENTED = "hours_rented";
        public static final String COST = "cost";
        public static final String DATE_RENTED = "date_rented";
        public static final String TABLE_NAME = "ClubHouse";
        public static final String DATABASE_NAME = "PARS";
    }

}
