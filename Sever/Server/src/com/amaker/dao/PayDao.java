package com.amaker.dao;

import java.util.List;

import com.amaker.entity.QueryOrder;

public interface PayDao {
	// 查询订单信息
	public QueryOrder getOrderById(int id);
	// 查询订单详细
	public List getOrderDetailList(int id);
	// 结算
	public void pay(int id);
}
