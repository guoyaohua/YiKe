package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.UpdateDao;
import com.amaker.entity.Menu;
import com.amaker.entity.Table;
import com.amaker.util.DBUtil;

/**
 * @author 郭耀华
 *	完成更新数据功能
 */
public class UpdateDaoImpl implements UpdateDao {
	// 获得菜单列表
	public List getMenuList() {
		// 查询SQL语句
		String sql =" select id,typeId,price,filename,filePath,remark from MenuTbl ";
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
				int typeId = rs.getInt(2);
				int price = rs.getInt(3);
				String name = rs.getString(4);
				String pic = rs.getString(5);
				String remark = rs.getString(6);
				
				Menu m = new Menu();
				m.setId(id);
				m.setName(name);
				m.setPic(pic);
				m.setPrice(price);
				m.setRemark(remark);
				m.setTypeId(typeId);
				
				list.add(m);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	// 获得餐桌列表
	public List getTableList() {
		// 查询SQL语句
				String sql =" select id,num,flag,description from TableTbl ";
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
						int flag = rs.getInt(3);
						String description = rs.getString(4);
						
						Table t = new Table();
						t.setId(id);
						t.setNum(num);
						t.setFlag(flag);
						t.setDescription(description);
						
						list.add(t);
					}
					return list;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					util.closeConn(conn);
				}
				return null;
	}

}
