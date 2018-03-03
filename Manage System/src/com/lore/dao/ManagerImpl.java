package com.lore.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.lore.entity.Employee;
import com.lore.entity.Manager;





public class ManagerImpl{
	Connection conn;
	PreparedStatement psmt;
	ResultSet res;
	Manager ma = new Manager();
	
	
      /*  public int findCount(){
		int count = 0;
		conn = ConnectionManager.getConn();
		try {
			psmt = conn.prepareStatement("select count(*) from employees");
			res = psmt.executeQuery();
			
			if(res.next()){
			   count = res.getInt(1);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}

        public List<Employee> findByPage(int pagenum,int pagesize,String str){
		List<Employee> list = new ArrayList<Employee>();
		conn = ConnectionManager.getConn();
		
		try {
			
			psmt = conn.prepareStatement("select * from employees "+str+"limit ?,?");
			
			psmt.setInt(1, (pagenum-1)*pagesize);
			psmt.setInt(2, pagesize);
			
			res = psmt.executeQuery();
			
			while(res.next()){
				Employee sc = new Employee();
				//sc.setId(res.getInt("id"));
				sc.setName(res.getString("name"));
				sc.setSex(res.getString("sex"));
				sc.setAge(res.getInt("age"));
				sc.setNation(res.getString("nation"));
				sc.setIdNumber(res.getString("idNumber"));
				sc.setDegree(res.getString("degree"));
				sc.setPosition(res.getString("position"));
				sc.setWages(res.getString("wages"));
				sc.setTelephone(res.getString("telephone"));
				sc.setDate(res.getDate("date"));
				sc.setRemark(res.getString("remark"));
				list.add(sc);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	*/
	//查询登录名
	public boolean find(Manager m){
		
		boolean flag = false;
		conn = ConnectionManager.getConn();
		try {
			psmt = conn.prepareStatement("select account,password from usertbl where account = ? and password = ?");
			psmt.setString(1, m.getAccount());
			psmt.setString(2, m.getPassword());
			res = psmt.executeQuery();
			if(res.next()){
				
				flag = true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return flag;
		
		
	}
	/*
	public List<Employee> findAll(){
		//System.out.println("dstgsdg");
		List<Employee> list = new ArrayList<Employee>();
		//System.out.println("dstgsdg");
		conn = ConnectionManager.getConn();
		//System.out.println("dstgsdg");
		try {
			psmt = conn.prepareStatement("select account,password from usertbl");
			
			res = psmt.executeQuery();
			System.out.println("dthg");
			while(res.next()){
				
				Manager ma = new Manager();
				
				//sc.setId(res.getInt("id"));
				ma.setAccount(res.getString("account"));
				ma.setPassword(res.getString("password"));
				//ma.setInfo(res.getString("info"));
				
				list.add(ma);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
		
		
		
	}
*/	
/*	
//	public List<Schools> findAllBySql(String sql){
//		List<Schools> list = new ArrayList<Schools>();
//		conn = ConnectionManager.getConn();
//		try {
//			psmt = conn.prepareStatement(sql);
//			
//			res = psmt.executeQuery();
//			
//			while(res.next()){
//				Schools sc = new Schools();
//				
//				//sc.setId(res.getInt("id"));
//				sc.setName(res.getString("name"));
//				sc.setTelephone(res.getInt("telephone"));
//				sc.setInfo(res.getString("info"));
//				
//				list.add(sc);
//			}
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		
//		return list;
//	}

*/

}
