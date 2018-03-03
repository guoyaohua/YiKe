package com.lore.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SimpleDBSource implements DBSource{
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/wireless_db?useUnicode=true&characterEncoding=gbk";
	private String username = "root";
	private String password = "root";
	
	public SimpleDBSource(){
		
		
		try {
			
			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	public Connection getConn() throws SQLException{
		
		return DriverManager.getConnection(url,username,password);
	}
	public void closeConn(Connection conn) throws SQLException{
		
		if(conn!=null && !conn.isClosed()){
			conn.close();
		}
		
	}

}
