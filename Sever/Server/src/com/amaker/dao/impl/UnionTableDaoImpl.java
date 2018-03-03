package com.amaker.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.UnionTableDao;
import com.amaker.entity.UnionTable;
import com.amaker.util.DBUtil;

/**
 * @author 郭耀华
 * 实现并台功能
 */
public class UnionTableDaoImpl implements UnionTableDao {
	
	public List getTableList() {
		// 查询SQL语句
		String sql =" select id,num from TableTbl where flag = 1 ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			Statement pstmt = conn.createStatement();
			// 执行查询
			ResultSet rs = pstmt.executeQuery(sql);
			// 判断订单详细
			List list = new ArrayList();
			while (rs.next()) {
				// 获得菜单信息
				
				int id = rs.getInt(1);
				int num = rs.getInt(2);
				
				UnionTable ut = new UnionTable();
				ut.setId(id);
				ut.setNum(num);
				
				list.add(ut);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}

	public void updateStatus(int tableId1,int tableId2) {
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		CallableStatement cstmt;
		try {
			cstmt = conn.prepareCall("{call new_proc(?,?)}");
			cstmt.setInt(1, tableId1);
			cstmt.setInt(2, tableId2);
			cstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
