package com.guoyaohua.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 
 * 
 * 数据库工具类
 */
public class DBHelper extends SQLiteOpenHelper{
    // 数据库名称常量
    private static final String DATABASE_NAME = "Wireless.db";
    // 数据库版本常量
    private static final int DATABASE_VERSION = 2;
    // 表名称常量
    public static final String TABLES_TABLE_NAME = "TableTbl";
    public static final String TABLES_TABLE_NAME2 = "MenuTbl";
	// 构造方法
	public DBHelper(Context context) {
		// 创建数据库
		super(context, DATABASE_NAME,null, DATABASE_VERSION);
	}

	// 创建时调用
	public void onCreate(SQLiteDatabase db) {
		//将注释去掉---Bylee
        db.execSQL("CREATE TABLE " + TABLES_TABLE_NAME + " ("
                + Tables._ID + " INTEGER PRIMARY KEY,"
                + Tables.NUM + " TEXT,"
                + Tables.DESCRIPTION + " TEXT"
                + ");");
        
        db.execSQL("CREATE TABLE " + TABLES_TABLE_NAME2 + " ("
                + Menus._ID + " INTEGER PRIMARY KEY,"
                + Menus.TYPE_ID + " INTEGER,"
                + Menus.NAME + " TEXT,"
                + Menus.PRICE + " INTEGER,"
                + Menus.PIC + " TEXT,"
                + Menus.REMARK + " TEXT"
                + ");");
	}

	// 版本更新时调用
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 删除表
		db.execSQL("DROP TABLE IF EXISTS TableTbl");//将注释去掉---Bylee
		db.execSQL("DROP TABLE IF EXISTS MenuTbl");
        onCreate(db);
	}

}
