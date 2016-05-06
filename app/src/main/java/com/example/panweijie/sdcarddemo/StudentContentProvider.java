package com.example.panweijie.sdcarddemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by panweijie on 16/1/25.
 */
public class StudentContentProvider extends ContentProvider {

    private final String TAG = "StudentPrivider";
    private DBHelp help = null;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int STUDENT = 1;//操作单条记录
    private static final int STUDENTs = 2;//操作多条记录

    static {
        URI_MATCHER.addURI("com.example.panweijie.sdcarddemo.StudentContentProvider", "student", STUDENTs);
        URI_MATCHER.addURI("com.example.panweijie.sdcarddemo.StudentContentProvider", "student/#", STUDENT);
    }

    public StudentContentProvider() {

    }

    @Override
    public boolean onCreate() {
        help = new DBHelp(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        try {
            SQLiteDatabase database = help.getWritableDatabase();
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case STUDENT:
                    long id = ContentUris.parseId(uri);
                    String where_value = "id =" + id;
                    if (selection != null && !selection.equals("")) {
                        where_value += "and" + selection;
                    }
                    cursor = database.query("student", null, where_value, selectionArgs, null, null, null, null);
                    break;
                case STUDENTs:
                    cursor = database.query("student", null, selection, selectionArgs, null, null, null, null);
                default:
            }
        } catch (Exception ex) {

        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case STUDENT:
                return "vnd.android.cursor.item/student";
            case STUDENTs:
                return "vnd.android.cursor.dir/students";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //insert into student()(?,?);
        Uri resultUri = null;
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case STUDENTs:
                SQLiteDatabase database = help.getWritableDatabase();
                long id = database.insert("student", null, values);//插入当前行的行号
                resultUri = ContentUris.withAppendedId(uri, id);
                break;
        }
        Log.i(TAG, "---->" + resultUri.toString());

        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = -1;//影响数据库的行数
        try {
            int flag = URI_MATCHER.match(uri);
            SQLiteDatabase database = help.getWritableDatabase();
            switch (flag) {
                case STUDENT:
                    long id = ContentUris.parseId(uri);
                    String whrer_value = "id =" + id;
                    if (selection != null && !selection.equals("")) {
                        whrer_value += "and" + selection;
                    }
                    count = database.delete("student", whrer_value, selectionArgs);
                    break;
                case STUDENTs:
                    count = database.delete("student", selection, selectionArgs);
                    break;
            }
        } catch (Exception e) {

        }

        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = -1;
        try {
            SQLiteDatabase database = help.getWritableDatabase();
            long id = ContentUris.parseId(uri);
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case STUDENT:
                    String where_value = "id =" + id;
                    if (selection != null && !selection.equals("")) {
                        where_value += "and" + selection;
                    }
                    count = database.update("student", values, where_value, selectionArgs);
                    break;
            }
        } catch (Exception ex) {

        }

        return count;
    }
}
