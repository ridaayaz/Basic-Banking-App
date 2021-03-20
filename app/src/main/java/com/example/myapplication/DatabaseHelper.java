package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(0324567890,'Ali',9472,'aliaaz@gmail.com','XXXXXXXXXXXX1111','BBD0987DEFO')");
        db.execSQL("insert into user_table values(0345678901,'Moiz',6582,'moizali06@gmail.com','XXXXXXXXXXXX2222','OOG987DFF32')");
        db.execSQL("insert into user_table values(0346789012,'Azlan',71359,'azlanshah4@gmail.com','XXXXXXXXXXXX3333','MMP87654FGS1')");
        db.execSQL("insert into user_table values(0356789013,'Mustafa',1500,'mustafa95@gmail.com','XXXXXXXXXXXX4444','AB76543EEK0')");
        db.execSQL("insert into user_table values(0378901234,'Arib',2603,'aribmir3@gmail.com','XXXXXXXXXXXX5555','BDD65DOO2109')");
        db.execSQL("insert into user_table values(0389012345,'Ayaz',945,'ayazhassan@gmail.com','XXXXXXXXXXXX6666','BAC54OO1KG8')");
        db.execSQL("insert into user_table values(0390123456,'Samia',5936,'samiabano.08@gmail.com','XXXXXXXXXXXX7777','DBE432DRT87')");
        db.execSQL("insert into user_table values(0301234567,'Alisha',1857,'alisha005.09@gmail.com','XXXXXXXXXXXX8888','OFA321TEK876')");
        db.execSQL("insert into user_table values(0312345678,'Dua',4398,'duaali.10@gmail.com','XXXXXXXXXXXX9999','GDB2109TYL65')");
        db.execSQL("insert into user_table values(0334567809,'Muniza',2173,'muniza.01@gmail.com','XXXXXXXXXXXX1000','AGD109UYT654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
