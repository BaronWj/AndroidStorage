package myandroid_bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by panweijie on 16/1/18.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static  String name = "mydb.db";//数据库的名称
    private static int version = 2;//表示数据库的版本号码

//支持的数据类型：整型数据，字符串类型，日期类型，二进制的数据类型
    final String CREATE_TABLE_SQL ="create table person(id integer primary key autoincrement,name varchar(64),address varchar(64))";

    public DbOpenHelper(Context context){
        super(context,name,null,version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("------------onUpdate Called--------------"+oldVersion+"---------"+newVersion);
       String sql = "alter table person add sex varchar(8)";
        db.execSQL(sql);

    }

    /**
     * 当数据库创建的时候，是第一次被执行，完成对数据库的表的创建
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

}
