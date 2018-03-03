package com.lore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lore.entity.Employee;
import com.lore.entity.MenuDet;
import com.lore.entity.Menutbl;
import com.lore.entity.Order;

public class MenueDetImpl {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	int pageSize = 10;

	public int count(){
		int count = 0;
		conn = ConnectionManager.getConn();
		
		try{
			psmt = conn.prepareStatement("select count(*) from orderdetailtbl ");
			rs = psmt.executeQuery();

			if(rs.next()){
				count = rs.getInt(1);
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
	//ajax技术来查询
	public List<String> findNames(String key){
		List<String> names = new ArrayList<String>();
		try{
					
			
			
		   String sql = "select orderid from ordertbl where orderid like '"+key+"%'";
		   conn = ConnectionManager.getConn();
		   psmt = conn.prepareStatement(sql);
	           rs = psmt.executeQuery();
		   while(rs.next()){
			names.add(rs.getString(1));
	           }	
		}catch(Exception ex){
		   System.out.println(ex.getMessage());
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return names;
	    }
	
	
	
	public List<MenuDet> findByPage(String sql,int pagenum,int pagesize){
		List<MenuDet> list = new ArrayList<MenuDet>();
		
		conn = ConnectionManager.getConn();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, (pagenum-1)*pagesize);
			psmt.setInt(2, pagesize);
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				MenuDet m = new MenuDet();
				m.setorderid(rs.getInt("orderId"));
				m.setmenueid(rs.getInt("menuId"));
				m.setnum(rs.getInt("num"));
				m.setfilename(rs.getString("filename"));
				m.setordertime(rs.getString("orderTime"));
				m.settableid(rs.getInt("tableId"));
				m.setpersonnum(rs.getInt("personNum"));
				m.setispay(rs.getInt("isPay"));
				
				list.add(m);
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
	//通过orderid来查询
/*	public MenuDet findOrderById(String orderid){
 		List<Order> list = new ArrayList<Order>();
 		
 		conn = ConnectionManager.getConn();
 		try {
 			psmt = conn.prepareStatement("select table1.menuId,table1.num," +
					                     "table2.price,table2.filename,table3.orderid,table3.orderTime,table3.tableId,table3.personNum,table3.isPay " +
					                     "from orderdetailtbl table1 " +
					                     "left join  menutbl table2 on table1.menuId = table2.id" +
					                     " left join ordertbl table3 on table1.id = table3.id where table3.orderid="+orderid);
 			
 			rs = psmt.executeQuery();
 			
 			if(rs.next()){
 				
 				
 				MenuDet m = new MenuDet();
				m.setorderid(rs.getInt("orderId"));
				m.setmenueid(rs.getInt("menuId"));
				m.setnum(rs.getInt("num"));
				m.setfilename(rs.getString("filename"));
				m.setordertime(rs.getString("orderTime"));
				m.settableid(rs.getInt("tableId"));
				m.setpersonnum(rs.getInt("personNum"));
				m.setispay(rs.getInt("isPay"));
 				//System.out.println(o.getIsPay()+"---------------");
 				return m;
 				
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
 		return null;
 		
 	}
*/	
}
