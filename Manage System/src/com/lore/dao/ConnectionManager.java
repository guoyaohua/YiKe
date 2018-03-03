package com.lore.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	
	public static Connection getConn(){
		Connection conn = null;
		InputStream in = ConnectionManager.class.getResourceAsStream("/db.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			Class.forName(p.getProperty("driverclass"));
			conn = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("pwd"));
			
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
