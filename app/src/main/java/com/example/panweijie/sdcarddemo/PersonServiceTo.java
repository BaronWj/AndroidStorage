package com.example.panweijie.sdcarddemo;

import android.content.ContentValues;

import java.util.List;
import java.util.Map;

/**
 * Created by panweijie on 16/1/22.
 */
public interface PersonServiceTo {
    public boolean addPerson(ContentValues values);

    public boolean deletePerson(String whereClause,String[] whereArgs);

    public boolean updatePerson(ContentValues values, String whereClause, String[] whereArgs);

    public Map<String,String> viewPerson(String selection,String[] selectionArgs);

    public List<Map<String,String>> viewPersonMaps(String selection, String[] selectionArgs);

}
