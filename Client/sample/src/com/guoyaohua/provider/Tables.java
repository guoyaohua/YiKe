package com.guoyaohua.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 
 * 桌号常量类
 */
public final class Tables  implements BaseColumns{
		// 授权常量
	    public static final String AUTHORITY = "com.amaker.provider.TABLES";
        // 访问Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/table");
        // 默认排序常量
        public static final String DEFAULT_SORT_ORDER = "num DESC";// 按桌号排序
        // 表字段常量
        public static final String NUM = "num";					//桌号
        public static final String DESCRIPTION= "description";	// 描述
}
