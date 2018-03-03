package com.lore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.lore.entity.Order;

public class OrderImpl {
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet res;
	
	//查询需要的字段,已开桌
	public List<Order> findByPage(int pagenum,int pagesize){
		List<Order> list = new ArrayList();
		conn = ConnectionManager.getConn();
		try {
			
			psmt = conn.prepareStatement("select id,orderid,orderTime,tableId,personNum,isPay,remark from ordertbl limit ?,?");
			
			psmt.setInt(1, (pagenum-1)*pagesize);
			psmt.setInt(2, pagesize);
			
			res = psmt.executeQuery();
			
			while(res.next()){
				Order o = new Order();
				o.setId(res.getInt("id"));
				o.setOrderid(res.getString("orderid"));
				o.setTableId(res.getInt("tableId"));
				o.setPersonNum(res.getInt("personNum"));
				o.setIsPay(res.getInt("isPay"));
				o.setOrderTime(res.getString("orderTime"));
				o.setRemark(res.getString("remark"));
				
				
				list.add(o);
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
	//查询所有以开桌记录的总数和
	public int findCount(){
		conn = ConnectionManager.getConn();
		int count = 0;
		try {
			psmt = conn.prepareStatement("select count(*) from ordertbl");
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

	//查询需要的字段,未开桌
	public List<Order> findByPage0(int pagenum,int pagesize){
		List<Order> list = new ArrayList();
		conn = ConnectionManager.getConn();
		try {
			
			psmt = conn.prepareStatement("select id from tabletbl where flag=0 limit ?,?");
			
			psmt.setInt(1, (pagenum-1)*pagesize);
			psmt.setInt(2, pagesize);
			
			res = psmt.executeQuery();
			
			while(res.next()){
				Order o = new Order();
				o.setId(res.getInt("id"));
				
				list.add(o);
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
	//查询未开桌所有记录的总数和
	public int findCount0(){
		conn = ConnectionManager.getConn();
		int count = 0;
		try {
			psmt = conn.prepareStatement("select count(*) from tabletbl");
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
//通过id删除	
     public void deleteOrder(int id){
    	 System.out.println(id+"---------------s");
			conn = ConnectionManager.getConn();
			try {
				psmt = conn.prepareStatement("delete from ordertbl where id=?");
				psmt.setInt(1,id );
				
				psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	
	}		
//通过账单id查询findOrderBytableid
     public Order findOrderByfileid(int fileid){
     		List<Order> list = new ArrayList<Order>();
     		
     		conn = ConnectionManager.getConn();
     		try {
     			psmt = conn.prepareStatement("select id,orderTime,tableId,personNum,isPay,remark from ordertbl where id="+fileid);
     			
     			//psmt.setInt(1, fileid);
     			//psmt.setInt(2, typeId);
     			res = psmt.executeQuery();
     			
     			if(res.next()){
     				
     				Order o = new Order();
     				o.setId(res.getInt("id"));
    				o.setTableId(res.getInt("tableId"));
    				o.setPersonNum(res.getInt("personNum"));
    				o.setIsPay(res.getInt("isPay"));
    				o.setOrderTime(res.getString("orderTime"));
    				o.setRemark(res.getString("remark"));
     				//System.out.println(o.getIsPay()+"---------------");
     				return o;
     				
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
//通过桌台id查询
     public Order findOrderBytableid(int tableid){
     		List<Order> list = new ArrayList<Order>();
     		
     		conn = ConnectionManager.getConn();
     		try {
     			psmt = conn.prepareStatement("select id,orderTime,tableId,personNum,isPay,remark from ordertbl where tableid=？");
     			
     			psmt.setInt(1, tableid);
     			//psmt.setInt(2, typeId);
     			res = psmt.executeQuery();
     			
     			if(res.next()){
     				
     				Order o = new Order();
     				o.setId(res.getInt("id"));
    				o.setTableId(res.getInt("tableId"));
    				o.setPersonNum(res.getInt("personNum"));
    				o.setIsPay(res.getInt("isPay"));
    				o.setOrderTime(res.getString("orderTime"));
    				o.setRemark(res.getString("remark"));
     				
     				return o;
     				
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
     
 public Order findOrderBy(int fileid,int tableid){
  		List<Order> list = new ArrayList<Order>();
  		
  		conn = ConnectionManager.getConn();
  		try {
  			psmt = conn.prepareStatement("select id,orderTime,tableId,personNum,isPay,remark from ordertbl where fileid=? and tableid=？");
  			
  			psmt.setInt(1, fileid);
  			psmt.setInt(2, tableid);
  			
  			res = psmt.executeQuery();
  			
  			if(res.next()){
  				
  				Order o = new Order();
  				o.setId(res.getInt("id"));
 				o.setTableId(res.getInt("tableId"));
 				o.setPersonNum(res.getInt("personNum"));
 				o.setIsPay(res.getInt("isPay"));
 				o.setOrderTime(res.getString("orderTime"));
 				o.setRemark(res.getString("remark"));
  				
  				return o;
  				
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
}
