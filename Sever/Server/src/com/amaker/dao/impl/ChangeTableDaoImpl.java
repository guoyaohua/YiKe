package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amaker.dao.ChangeTableDao;
import com.amaker.util.DBUtil;
/**
 * @author 郭耀华
 * 完成转台功能
 */
public class ChangeTableDaoImpl implements ChangeTableDao {

	public void changeTable(int orderId, int tableId) {
		// 更新SQL语句
		
		String sql = " update TableTbl set flag = 0 where id = "+
		  " (select tableId from OrderTbl  as ot where ot.id = ?)";
		String sql2 = " update OrderTbl set tableId = ? where id = ? ";
		String sql3 = " update TableTbl set flag = 1 where id = ?";
		
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		
		try {
			conn.setAutoCommit(false);
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			// 更新桌位状态
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			
			// 设置参数
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, orderId);
			// 更新订单表
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, tableId);
			// 更新桌位状态
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			util.closeConn(conn);
		}
	}
}
