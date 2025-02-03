package com.example.myfinances;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class FinanceDataSource {

    private SQLiteDatabase database;
    private FinanceDBHelper dbHelper;

    public FinanceDataSource(Context context) {
        dbHelper = new FinanceDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertFinance(Finance f) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("accountnumber", f.getAccountnumber());
            initialValues.put("accounttype", f.getAccounttype());
            initialValues.put("initialbalance", f.getInitialbalance());
            initialValues.put("currentbalance", f.getCurrentbalance());
            initialValues.put("paymentamount", f.getPaymentamount());
            initialValues.put("interestrate", f.getInterestrate());


            didSucceed = database.insert("finance", null, initialValues) > 0;
        }
        catch (Exception e) {
            // do nothing -will rerun false if there is an exception
        }
        return didSucceed;
    }

    public int getLastFinanceID() {
        int lastId;
        try{
            String query = "Select Max(_id) from finance";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

}
