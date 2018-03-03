package com.amaker.dao;

import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
import com.amaker.entity.Table;
public interface OrderDao {
	// 保存开桌信息
	public String saveOrder(Order o );
	// 保存点菜列表信息
	public String saveOrderDetail(OrderDetail od);
	
	// 更新桌号状态，有人
	public void updateTableStatus(int tableId,int personNum);
	// 更新桌号状态，空位
	public void updateTableStatus2(int orderId);
	// 绑定桌位与订单号
	public void insert(Table t);
	//得到该桌位此时的订单号
	public String get(Table t);
	//删除该桌位订单号
	public void delete(Table t);
	
}
