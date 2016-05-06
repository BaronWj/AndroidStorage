package com.example.panweijie.sdcarddemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import myandroid_bd.DbOpenHelper;

/**
 * Created by panweijie on 16/1/21.
 */
public class PersonDemo implements PersonService {
    private DbOpenHelper helper = null;

    public PersonDemo(Context context) {
        helper = new DbOpenHelper(context);
    }

    @Override
    public boolean addPerson(Object[] params) {
        boolean flag = false;
        //实现对数据哭的添加删除和修改查询功能
        SQLiteDatabase database = null;
        try {
            String sql = "insert into person(name,address,sex) values(?,?,?)";
            database = helper.getWritableDatabase();//实现对数据库写的操作
            database.execSQL(sql,params);
            flag =true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;
    }

    @Override
    public boolean deletePerson(Object[] params) {
        boolean flag = false;
        SQLiteDatabase database = null;
        try {
            String sql = "delete from person where id = ?";
            database = helper.getWritableDatabase();
            database.execSQL(sql,params);
            flag = true;
        }catch (Exception e){

        }finally {
            if (database != null) {
                database.close();
            }
        }

        return flag;
    }

    @Override
    public boolean updatePerson(Object[] params) {
        boolean flag = false;
        SQLiteDatabase database = null;
        try {
            String sql = "update person set name = ?,address =?,sex= ? where id = ?";
            database = helper.getWritableDatabase();
            database.execSQL(sql,params);
            flag = true;
        }catch (Exception e){

        }finally {
            if (database != null) {
                database.close();
            }
        }

        return flag;

    }

    @Override
    public Map<String, String> viewPerson(String[] selectionArgs) {
        Map<String,String> map = new HashMap<String,String>();
        SQLiteDatabase database = null;
        try {
            String sql = "select * from person where id = ?";
            database = helper.getReadableDatabase();
            Cursor cursor =database.rawQuery(sql,selectionArgs);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                for (int i =0;i<colums;i++){
                    String cols_name =cursor.getColumnName(i);
                    String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
                    if (cols_value == null){
                        cols_value = "";
                    }
                    map.put(cols_name,cols_value);
                }
            }
        }catch (Exception ex){

        }finally {
            if (database!=null){
                database.close();
            }

        }

        return map;
    }

    @Override
    public List<Map<String, String>> viewPersonMaps(String[] selectionArgs) {
        List<Map<String,String>>list = new ArrayList<Map<String,String>>();
        String sql = "select *from person";
        SQLiteDatabase database = null;
        try {
            database = helper.getReadableDatabase();
            Cursor cursor = database.rawQuery(sql,selectionArgs);
            int conlums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                Map<String,String> map = new HashMap<String,String>();
                for (int i = 0;i<conlums;i++){
                    String cols_name = cursor.getColumnName(i);
                    String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
                    if (cols_value == null){
                        cols_value = "";
                    }
                    map.put(cols_name,cols_value);
                }
                list.add(map);
            }
        }catch (Exception ex){

        }finally {

        }
        return list;
    }
}
