package com.example.poorwa.shantibanapp;

import android.provider.BaseColumns;

/**
 * Created by poorwa on 29/1/16.
 */
public class AnnualMaintenanceDBHelper {
    public AnnualMaintenanceDBHelper() {

    }

    public static abstract class TableInfo implements BaseColumns {
        public static final String MEMBER_NAME = "member_name";
        public static final String PLOT_NUMBER = "plot_number";
        public static final String PAYMENT_DATE = "payment_date";
        public static final String AMOUNT_PAID = "amount_paid";
        public static final String LATE_FEE_FINE = "late_fee_fine";
        public static final String TOTAL_AMOUNT = "total_amount";
        public static final String TABLE_NAME = "AnnualMaintenance";
        public static final String DATABASE_NAME = "PARS";
    }


}
