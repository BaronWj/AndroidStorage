package com.example.panweijie.sdcarddemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import myandroid_bd.DbOpenHelper;
//内容提供者

/**
 * Created by panweijie on 16/1/25.
 */
public class DBHelp extends SQLiteOpenHelper {
    private static String name = "mydbfirst.db";//数据库的名称
    private static int version = 1;//表示数据库的版本号码


    public DBHelp(Context context) {
        super(context, name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table student(id integer primary key autoincrement,name varchar(64),address varchar(64))";
        db.execSQL(sql);//对数据库的创建
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
