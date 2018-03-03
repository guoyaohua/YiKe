package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.dao.OrderDao;
import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
import com.amaker.entity.Table;
import com.amaker.util.DBUtil;

/**
 * @author 郭耀华 点餐功能DAO实现类
 */
public class OrderDaoImpl implements OrderDao {
	ResultSet res;

	// 保存点餐信息，放回订单ID
	/**
	 * 开桌方法 应该先查询ordertbl表中的最后一项的ordertime字段，与
	 * ordertime参数比较，如果小于今天，则remark字段为1。如果大于今天，则remark字段为 上个订单remark数值加1；
	 * 然后将这几个参数写入ordertbl表中
	 */
	public String saveOrder(Order o) {
		int orderId = 0;
		int lastId = 0;
		int id = 0;
		// 先判断该订单为今日的第几个订单
		String sql3 = " select max(orderId) as orderId from orderTbl ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql3);
			if (rs.next()) {
				lastId = rs.getInt(1);
//				id = rs.getInt(2);
				
			}
			// 得到今日日期
			java.util.Date ctime = new java.util.Date();
			String rTime = "";
			java.text.SimpleDateFormat cf = new java.text.SimpleDateFormat(
					"yyMMdd");
			rTime = cf.format(ctime);
			if (lastId == 0) {
				orderId = Integer.parseInt(rTime + "001");
			} else {
				if (lastId > Integer.parseInt(rTime + "000")) {
					orderId = lastId + 1;
				} else {
					orderId = Integer.parseInt(rTime + "001");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 添加SQL语句
		String sql = " insert into ordertbl(orderTime,userId,tableId,personNum,isPay,orderId)values(?,?,?,?,?,?) ";

		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, o.getOrderTime());
			pstmt.setInt(2, o.getUserId());
			pstmt.setInt(3, o.getTableId());
			pstmt.setInt(4, o.getPersonNum());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, orderId);
			// 执行更新
			pstmt.executeUpdate();
			// 返回订单编号
			 String sql2 = " select max(id) as id  from orderTbl ";
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql2);
			 if (rs.next()) {
			 id = rs.getInt(1);
			 
			 }
			System.out.println(lastId + " " + id);
			return orderId + " " + id;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return "";// return 0 ;
	}

	// 保存点餐列表
	public String saveOrderDetail(OrderDetail od) {
		// 添加SQL语句
		String sql = " insert into orderdetailtbl(orderId,menuId,num,remark)values(?,?,?,?) ";
		String sql2 = "select filename , price from menutbl where id=?";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, od.getOrderId());
			pstmt.setInt(2, od.getMenuId());
			pstmt.setInt(3, od.getNum());
			pstmt.setString(4, od.getRemark());
			// 执行更新
			pstmt.executeUpdate();
			// 获得预定义语句 用于返回菜品名称和价格
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, od.getMenuId());

			// 执行查询
			ResultSet rs = pstmt2.executeQuery();
			// 构建字符串用于存储返回信息 //
			// 前者为 菜品名称 后者为价格
			rs.next();
			String result = rs.getString(1) + " " + rs.getString(2);

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return "";

	}

	// 更新桌号状态，有人
	public void updateTableStatus(int tableId, int personNum) {
		// 更新SQL语句
		String sql = " update tableTbl set flag=1 , num=" + personNum
				+ " where id = ? ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, tableId);
			// 执行更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

	// 更新桌号状态，空桌
	public void updateTableStatus2(int orderId) {
		// 更新SQL语句
		String sql = " update TableTbl set flag = 0 , num=0 , orderId = null where id = "
				+ " ( select tableId from OrderTbl where id = ?) ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, orderId);
			// 执行更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

	// 查询
	public String get(Table t) {
		String orderId = null;
		String longid = null;
		DBUtil util = new DBUtil();
		// 获得连接

		String sql = "select orderId,longid from tabletbl where id=?";
		Connection conn = util.openConnection();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, t.getId());

			res = psmt.executeQuery();
			while (res.next()) {
				orderId = res.getString("orderId");
				longid = res.getString("longid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}

		return longid+" "+orderId;
	}

	// 删除
	public void delete(int orderId) {

		Table t = new Table();
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn
					.prepareStatement("delete from tabletbl where orderId=?");
			// 设置参数
			pstmt.setInt(1, orderId);

			// 执行更新
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}

	}

	// 添加
	public void insert(Table t) {

		String orderId = t.getOrderId() + "";
		String long_orderId = t.getLong_orderId() + "";
		String sql = " update tableTbl set orderId = " + orderId
				+ ", longid = " + long_orderId + " where id = ? ";
		// String sql = " update tableTbl set orderId = 12312 where id = 2 ";
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, t.getId());

			// 执行更新
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}

	}

	public void delete(Table t) {
		// TODO Auto-generated method stub

	}

}
