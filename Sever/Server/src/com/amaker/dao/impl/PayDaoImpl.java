package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.PayDao;
import com.amaker.entity.QueryOrder;
import com.amaker.entity.QueryOrderDetail;
import com.amaker.util.DBUtil;
/**
 * @author 郭耀华
 * 结算DAO实现类
 */
public class PayDaoImpl implements PayDao {
	
	// 根据订单编号，查询订单信息
	public QueryOrder getOrderById(int id) {
		// 查询SQL语句
		String sql =" 	select ot.`orderTime`, "+
					" 	ut.`name`, "+
					" 	ot.`personNum`, "+
					" 	ot.`tableId` "+
					" 	from orderTbl as ot "+
					" 	left join userTbl as ut on ot.`userID` = ut.id "+
					" 	where ot.`id`=? ";

		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置查询参数
			pstmt.setInt(1, id);
			// 执行查询
			ResultSet rs = pstmt.executeQuery();
			// 判断订单是否存在
			if (rs.next()) {
				// 获得订单信息
				String orderTime = rs.getString(1);
				String userName = rs.getString(2);
				int personNum =rs.getInt(3);
				int tableId = rs.getInt(4);
				QueryOrder qo = new QueryOrder();
				qo.setName(userName);
				qo.setOrderTime(orderTime);
				qo.setPersonNum(personNum);
				qo.setTableId(tableId);
				return qo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		
		return null;
	}
	// 根据订单编号，查询订单详细列表
	public List getOrderDetailList(int id) {
		//	查询SQL语句
		String sql =" 	select mt.`filename`, "+
					" 	mt.`price`, "+
					" 	odt.`num`, "+
					" 	mt.price*odt.num as total, "+
					" 	odt.`remark` "+
					" 	from orderdetailTbl as odt "+
					" 	left join menuTbl as mt on odt.`menuId` = mt.id "+
					" 	where odt.`orderId`= ?";
//					+
		
//					" 	union "+
//					
//					" 	select          '',"+
//					" 	'',"+
//					" 	'',"+
//					" 	sum(mt.price*odt.num) as total1,"+
//					" 	'' "+
//					" 	from orderdetailTbl as odt"+
//					" 	left join menuTbl as mt on odt.`menuId` = mt.id"+
//					" 	where odt.`orderId`= ? ";
//		
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置查询参数
			pstmt.setInt(1, id);
		//	pstmt.setInt(2, id);
			// 执行查询
			ResultSet rs = pstmt.executeQuery();
			// 判断订单详细
			
			List list = new ArrayList();
			
			while (rs.next()) {
				// 获得订单详细信息
				String name = rs.getString(1);
				float price = rs.getFloat(2);
				int num = rs.getInt(3);
				int total = rs.getInt(4);
				String remark = rs.getString(5);
				
				QueryOrderDetail qod = new QueryOrderDetail();
				
				qod.setName(name);
				qod.setNum(num);
				qod.setPrice(price);
				qod.setTotal(total);
				qod.setRemark(remark);
				
				list.add(qod);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	
	// 结算
	public void pay(int id) {
		// 查询SQL语句
		String sql =" update OrderTbl set isPay=1 where id = ?";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置查询参数
			pstmt.setInt(1, id);
			// 执行更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
}
