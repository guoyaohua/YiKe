package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amaker.dao.UserDao;
import com.amaker.entity.User;
import com.amaker.util.DBUtil;
/**
 * 
 * @author 郭耀华
 * 用户登录DAO实现类
 */
public class UserDaoImpl implements UserDao {
	
	/**
	 * 通过用户名称和密码登录，登录成功返回User对象，登录失败返回null
	 */
	public User login(String account, String password) {
		// 查询SQL语句
		String sql = " select id,account,password,name,permission,remark "+
						" from usertbl "+
						" where account=? and password=? ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置查询参数
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			// 执行查询
			ResultSet rs = pstmt.executeQuery();
			// 判断用户是否存在
			if (rs.next()) {
				// 获得用户信息
				int id = rs.getInt(1);
				String name = rs.getString(4);
				int permission = rs.getInt(5);
				String remark = rs.getString(6);
				// 封装用户信息
				User u = new User();
				
				u.setId(id);
				u.setAccount(account);
				u.setPassword(password);
				u.setName(name);
				u.setPermission(permission);
				u.setRemark(remark);
				
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	
	public static void main(String[] args) {
		UserDao dao = new UserDaoImpl();
		User u = dao.login("admin", "123");
		System.out.println(u.getAccount());
	}

}
