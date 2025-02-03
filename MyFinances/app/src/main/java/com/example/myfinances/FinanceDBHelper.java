package com.example.myfinances;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FinanceDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myfinances.db";
    private static final int DATABASE_VERSION = 1;

    //Database Creation SQL statement
    private static final String CREATE_TABLE_CONTACT =
            "create table finance (_id integer primary key autoincrement, "
                    + "accountnumber text not null,"
                    + "accounttype text,"
                    + "initialbalance text,"
                    + "currentbalance text,"
                    + "paymentamount text,"
                    + "interestrate text);";

    public FinanceDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(FinanceDBHelper.class.getName(),
                "Upgrading database from version" + oldVersion + "to"
                        + newVersion + ", Which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }

}
