package com.guoyaohua.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 
 * 桌号常量类
 */
public final class Menus  implements BaseColumns{
		// 授权常量
	    public static final String AUTHORITY = "com.amaker.provider.MENUS";
        // 访问Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/menu1");
        // 默认排序常量
        public static final String DEFAULT_SORT_ORDER = "typeId DESC";// 按桌号排序
        // 表字段常量
        public static final String TYPE_ID = "typeId";			//类型
        public static final String NAME= "name";				// 名称
        public static final String PRICE= "price";				// 价格
        public static final String PIC= "pic";					// 图片
        public static final String REMARK= "remark";			// 备注
}
