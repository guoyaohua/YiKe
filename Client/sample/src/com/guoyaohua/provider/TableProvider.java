package com.guoyaohua.provider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class TableProvider extends ContentProvider{
	// 数据库帮助类
	private DBHelper dbHelper;
    // Uri工具类
    private static final UriMatcher sUriMatcher;
    // 查询、更新条件
    private static final int TABLES = 1;
    private static final int TABLES_ID = 2;
    // 查询列集合
    private static HashMap<String, String> tblProjectionMap;
    static {
    	// Uri匹配工具类
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Tables.AUTHORITY, "table", TABLES);
        sUriMatcher.addURI(Tables.AUTHORITY, "table/#", TABLES_ID);
        // 实例化查询列集合
        tblProjectionMap = new HashMap<String, String>();
        // 添加查询列
        tblProjectionMap.put(Tables._ID, Tables._ID);
        tblProjectionMap.put(Tables.NUM, Tables.NUM);
        tblProjectionMap.put(Tables.DESCRIPTION,Tables.DESCRIPTION);
    }

	// 创建是调用
	public boolean onCreate() {
		// 实例化数据库帮助类
		dbHelper = new DBHelper(getContext());
		return true;
	}
	// 添加方法
	public Uri insert(Uri uri, ContentValues values) {
		// 获得数据库实例
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 插入数据，返回行ID
		long rowId = db.insert(DBHelper.TABLES_TABLE_NAME,Tables.NUM, values);
		// 如果插入成功返回uri
        if (rowId > 0) {
            Uri empUri = ContentUris.withAppendedId(Tables.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(empUri, null);
            return empUri;
        }
		return null;
	}
	

	// 获得类型
	public String getType(Uri uri) {
		return null;
	}

	// 查询方法
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		 SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        switch (sUriMatcher.match(uri)) {
	        // 查询所有
	        case TABLES:
	            qb.setTables(DBHelper.TABLES_TABLE_NAME);
	            qb.setProjectionMap(tblProjectionMap);
	            break;
	        // 根据ID查询
	        case TABLES_ID:
	            qb.setTables(DBHelper.TABLES_TABLE_NAME);
	            qb.setProjectionMap(tblProjectionMap);
	            qb.appendWhere(Tables._ID + "=" + uri.getPathSegments().get(1));
	            break;
	        default:
	            throw new IllegalArgumentException("Uri错误！ " + uri);
	        }

	        // 使用默认排序
	        String orderBy;
	        if (TextUtils.isEmpty(sortOrder)) {
	            orderBy = Tables.DEFAULT_SORT_ORDER;
	        } else {
	            orderBy = sortOrder;
	        }

	        // 获得数据库实例
	        SQLiteDatabase db = dbHelper.getReadableDatabase();
	        // 返回游标集合
	        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
	        c.setNotificationUri(getContext().getContentResolver(), uri);
	        return c;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		return 0;
	}
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}
}
