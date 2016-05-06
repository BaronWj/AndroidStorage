package com.example.panweijie.sdcarddemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myandroid_bd.DbOpenHelper;

/**
 * Created by panweijie on 16/1/22.
 */
public class PerosnDemo2 implements PersonServiceTo {

    private DbOpenHelper helper = null;

    public PerosnDemo2(Context context) {
        helper = new DbOpenHelper(context);
    }

    @Override
    public boolean addPerson(ContentValues values) {
        boolean flag = false;
        SQLiteDatabase database = null;
        long id = -1;
        try {
            database = helper.getWritableDatabase();
            id = database.insert("person", null, values);
            flag = (id != -1 ? true : false);
        } catch (Exception ex) {

        } finally {
            if (database != null) {
                database.close();
            }

        }

        return flag;
    }

    @Override
    public boolean deletePerson(String whereClause, String[] whereArgs) {
        boolean flag = false;
        SQLiteDatabase database = null;
        int count = 0;
        try {
            database = helper.getWritableDatabase();
            count = database.delete("person", whereClause, whereArgs);
            flag = (count > 0 ? true : false);
        } catch (Exception ex) {

        } finally {
            if (database != null) {
                database.close();
            }
        }


        return flag;
    }

    @Override
    public boolean updatePerson(ContentValues values, String whereClause, String[] whereArgs) {
        boolean flag = false;
        SQLiteDatabase database = null;
        int count = 0;//影响数据的行数
        try {
            database = helper.getWritableDatabase();
            Log.i("Test_update","VALUE"+values);
            count = database.update("person", values, whereClause, whereArgs);
            flag = (count > 0) ? true : false;
        } catch (Exception ex) {

        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;
    }

    @Override
    public Map<String, String> viewPerson(String selection, String[] selectionArgs) {
        //投影查询
        SQLiteDatabase database = null;
        Cursor cursor = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            database = helper.getReadableDatabase();
            cursor = database.query(true, "person", null, selection, selectionArgs, null, null, null, null
            );
            int cols_len = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                for (int i = 0; i < cols_len; i++) {
                    String cols_name = cursor.getColumnName(i);
                    String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
                    if (cols_value == null) {
                        cols_value = "";
                    }
                    map.put(cols_name, cols_value);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }


        return map;
    }

    @Override
    public List<Map<String, String>> viewPersonMaps(String selection, String[] selectionArgs) {
        List<Map<String,String>>list = new ArrayList<Map<String,String>>();
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            database = helper.getReadableDatabase();
            cursor = database.query(false,"person",null,selection,selectionArgs,null,null,null,null);
            int cols_len = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                Map<String,String>map =new HashMap<String,String>();
                for (int i = 0; i < cols_len; i++) {
                    String cols_name = cursor.getColumnName(i);
                    String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
                    if (cols_value == null) {
                        cols_value = "";
                    }
                    map.put(cols_name, cols_value);
                }
                list.add(map);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (database != null) {
                database.close();
            }
        }
        return list;
    }
}
