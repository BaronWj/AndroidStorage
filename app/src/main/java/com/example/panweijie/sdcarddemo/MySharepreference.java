package com.example.panweijie.sdcarddemo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by panweijie on 16/1/18.
 */
public class MySharepreference {
    private Context context;

    public MySharepreference(Context context){
        this.context =context;
    }

    boolean saveMessage(String name,String pswd){
        boolean flag =false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
    //对数据进行编辑
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",name);
        editor.putString("pswd",pswd);
        flag = editor.commit();
        return  flag;
    }

    public Map<String,Object>getMessage(){
        Map<String,Object> map = new HashMap<String,Object>();
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        String pswd = sharedPreferences.getString("pswd", "");
        map.put("name",name);
        map.put("pwd",pswd);
        return map;
    }
}
