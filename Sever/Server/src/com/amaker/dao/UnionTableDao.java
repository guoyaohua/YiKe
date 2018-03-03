package com.amaker.dao;

import java.util.List;
// 并台功能接口
public interface UnionTableDao {
	// 获得桌位列表
	public  List getTableList();
	// 更新合并后的数据
	public void updateStatus(int tableId1,int tableId2);
}
