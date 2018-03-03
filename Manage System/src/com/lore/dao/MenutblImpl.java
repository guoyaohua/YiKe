package com.lore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lore.entity.Employee;
import com.lore.entity.Menutbl;


public class MenutblImpl {
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet res;
	Menutbl m = new Menutbl();
	
	public int findCount(int typeid){
		conn = ConnectionManager.getConn();
		int count = 0;
		
		
		try {
			psmt = conn.prepareStatement("select count(*) from menutbl where typeId="+typeid);
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
		    
	public List<Menutbl> findByPage(int pagenum,int pagesize,int typeid){
		List<Menutbl> list = new ArrayList<Menutbl>();
		
		conn = ConnectionManager.getConn();
		
		try {
			psmt = conn.prepareStatement("select id,filename,price,typename,remark from menutbl where typeId=? limit ?,? ");
			
			psmt.setInt(1, typeid);
			psmt.setInt(2, (pagenum-1)*pagesize);
			psmt.setInt(3, pagesize);
			
			res = psmt.executeQuery();
			
			while(res.next()){
				Menutbl c = new Menutbl();
				c.setId(res.getInt("id"));
				c.setPrice(res.getInt("price"));
				c.setFilename(res.getString("filename"));
				c.setTypename(res.getString("typename"));
				c.setRemark(res.getString("remark"));
				
				list.add(c);
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
	
//通过id查询
	public Menutbl findMenutblById(int id,int typeId){
		List<Menutbl> list = new ArrayList<Menutbl>();
		
		conn = ConnectionManager.getConn();
		try {
			psmt = conn.prepareStatement("select id,filename,price,typeId,remark from menutbl where id=? and typeId=?");
			
			psmt.setInt(1, id);
			psmt.setInt(2, typeId);
			res = psmt.executeQuery();
			
			if(res.next()){
				Menutbl au = new Menutbl();
				
				au.setPrice(res.getInt("price"));
				au.setTypeId(res.getInt("typeId"));
				au.setRemark(res.getString("remark"));
				au.setFilename(res.getString("filename"));
				
				return au;
				
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
	

//通过id删除	
	public void deleteEmployee(int id,int typeId){
		
		conn = ConnectionManager.getConn();
		try {
			psmt = conn.prepareStatement("delete from menutbl where id=? and typeId=?");
			psmt.setInt(1,id );
			psmt.setInt(2,typeId );
			
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
//通过id更新--do-update1.jsp
	public void updateBySql(Menutbl au){
		conn = ConnectionManager.getConn();
		
		try {
			psmt = conn.prepareStatement("update menutbl set filename=?,price=?,typeId=?,remark=? where id=?");
			
			
			psmt.setString(1, au.getFilename());
			psmt.setInt(2, au.getPrice());
			psmt.setInt(3, au.getTypeId());
			psmt.setString(4, au.getRemark());
			psmt.setInt(5, au.getId());
			psmt.executeUpdate();
			
			
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
		
		
	}
	//通过查询name来判断菜品名是否已经被占用
	public boolean findByName(String name1,String name2) {
		
		boolean flag = true;
		//System.out.println("==========="+name1);
		//System.out.println("==========="+name2);
		try {
			conn = ConnectionManager.getConn();
			String sql = "select * from menutbl where filename=? or filename=?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name1);
			psmt.setString(2, name2);
			res = psmt.executeQuery();
			boolean isTure = res.next();
			
			if (isTure) {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	//通过查询name来判断菜品名是否已经被提交 
	public boolean findname(String name2) {
		
		boolean flag = false;
		//System.out.println("==========="+name1);
		//System.out.println("==========="+name2);
		try {
			conn = ConnectionManager.getConn();
			String sql = "select * from menutbl where filename=?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name2);
			//psmt.setString(2, name2);
			res = psmt.executeQuery();
			boolean isTure = res.next();
			
			if (isTure) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	

}

