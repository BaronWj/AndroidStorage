package com.example.panweijie.sdcarddemo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import myandroid_bd.DbOpenHelper;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MyTest";
    DbOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        saveFile();
//        readFile();
//        save();
//        find();


        // createDb();
//        insertDB();
//        deleteDB();
//        updateDB();
//        viewDB();
//        listDB();
//        addPerson2();
//        deletePerson();
//        updatePersonDB2();
//        viewPerson2();
//        listPerson2();
        contentInsert();
//          contentDelete();
//        contentUpdate();
        contentQuery();
    }


    public void saveFile() {

        Context context = getBaseContext();
        FileService fileService = new FileService(context);
        boolean flag = fileService.saveContentToSdcard("hello.text", "你好");
        Log.i(TAG, "---->" + flag);
    }

    public void readFile() {
        Context context = getBaseContext();
        FileService fileService = new FileService(context);
        String msgString = fileService.getFileFromSdcard("hello.text");
        Log.i(TAG, "---->" + msgString);
    }

    public void save() {
        Context context = getBaseContext();
        MySharepreference mySharepreference = new MySharepreference(context);
        boolean flag = mySharepreference.saveMessage("admin", "123");
        Log.i(TAG, "--->" + flag);
    }


    public void find() {
        Context context = getBaseContext();
        MySharepreference mySharepreference = new MySharepreference(context);
        Map<String, Object> map = mySharepreference.getMessage();
        Log.i(TAG, "--->" + map.toString());

    }

    public void createDb() {
        DbOpenHelper helper = new DbOpenHelper(getBaseContext());
        helper.getWritableDatabase();

    }

    public void insertDB() {
        PersonService service = new PersonDemo(getBaseContext());
        Object[] params = {"李强", "南宁", "女"};
        boolean flag = service.addPerson(params);
        System.out.print("---->" + flag);

    }

    public void deleteDB() {
        PersonService service = new PersonDemo(getBaseContext());
        Object[] params = {1};
        boolean flag = service.deletePerson(params);
        System.out.print("---->" + flag);

    }

    public void updateDB() {
        PersonService service = new PersonDemo(getBaseContext());
        Object[] params = {"王五", "上海", "不详", 3};
        boolean flag = service.updatePerson(params);
    }

    public void viewDB() {
        PersonService service = new PersonDemo(getBaseContext());
        String[] selectionArgs = {"3"};
        Map<String, String> map = service.viewPerson(selectionArgs);
        Log.i("Test", "--->" + map.toString());
    }

    public void listDB() {
        PersonService service = new PersonDemo(getBaseContext());
        List<Map<String, String>> list = service.viewPersonMaps(null);
        Log.i("Test", "--->" + list.toString());
    }


    //    --------------------------------------------------
    public void addPerson2() {
        PersonServiceTo serviceTo = new PerosnDemo2(getBaseContext());
        ContentValues values = new ContentValues();//类似map
        values.put("name", "杰克");
        values.put("address", "XX");
        values.put("sex", "男");
        boolean flag = serviceTo.addPerson(values);
        Log.i("Test_", "--->" + flag);

//        serviceTo.addPerson()
    }

    public void deletePerson() {
        PersonServiceTo serviceTo = new PerosnDemo2(getBaseContext());
        //delete from person where id =?
        //不包where语句
        boolean flag = serviceTo.deletePerson("id =?", new String[]{"2"});
        Log.i("Test_", "--->" + flag);
    }

    public void updatePersonDB2() {
        PersonServiceTo serviceTo = new PerosnDemo2(getBaseContext());
        ContentValues values = new ContentValues();//类似map
        values.put("name", "杰克");
        values.put("address", "不详");
        values.put("sex", "女");

        boolean flag = serviceTo.updatePerson(values, "id = ?", new String[]{"1"});
        Log.i("Test_update", "--->" + flag);

    }

    public void viewPerson2() {
        PersonServiceTo serviceTo = new PerosnDemo2(getBaseContext());
        Map<String, String> map = serviceTo.viewPerson("id = ?", new String[]{"3"});
        Log.i("Test_viewPerson1", "--->" + map.toString());

    }

    public void listPerson2() {
        PersonServiceTo serviceTo = new PerosnDemo2(getBaseContext());
        //select ＊from person
        List<Map<String, String>> mapList = serviceTo.viewPersonMaps(null, null);
        Log.i("Test_viewPerson2", "--->" + mapList.toString());

    }


    //内容提供类
    public void contentInsert(){


        //访问内容提供者的步骤
        //1.需要一个内容解析者
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        Uri uri = Uri.parse("content://com.example.panweijie.sdcarddemo.StudentContentProvider/student");
        ContentValues values = new ContentValues();
        values.put("name","张三");
        values.put("address","北京");
        contentResolver.insert(uri, values);
    }

    public void contentDelete(){
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        //删除单行的记录
        Uri uri = Uri.parse("content://com.example.panweijie.sdcarddemo.StudentContentProvider/student/4");
        contentResolver.delete(uri, null, null);
        //删除多行的记录  后面不带参数
    }

    public void contentUpdate(){
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        Uri uri = Uri.parse("content://com.example.panweijie.sdcarddemo.StudentContentProvider/student/3");
        ContentValues values = new ContentValues();
        values.put("name","李旺");
        values.put("address","潍坊");
        contentResolver.update(uri, values, null, null);
    }

    public void  contentQuery(){
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        //查询单条记录
//        Uri uri = Uri.parse("content://com.example.panweijie.sdcarddemo.StudentContentProvider/student/3");
        //查询多条记录
        Uri uri = Uri.parse("content://com.example.panweijie.sdcarddemo.StudentContentProvider/student");

        Cursor cursor = contentResolver.query(uri,null,null,null,null);
        while (cursor.moveToNext()){
            System.out.println("------>>"+cursor.getString(cursor.getColumnIndex("name")));
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
