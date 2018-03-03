package com.lore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.lore.entity.FeedBack;

public class FeedBackImpl {

	Connection conn;
	PreparedStatement psmt;
	ResultSet res;
	FeedBack au = new FeedBack();
//获得总的条数
  public int findCount(){
		int count = 0;
		conn = ConnectionManager.getConn();
		try {
			psmt = conn.prepareStatement("select count(*) from feedbacktbl");
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
	
	
  public List<FeedBack> findByPage(String sql,int pagenum,int pagesize){
		List<FeedBack> list = new ArrayList();
		conn = ConnectionManager.getConn();
		try {
			
			psmt = conn.prepareStatement(sql+" limit ?,?");
			psmt.setInt(1, (pagenum-1)*pagesize);
			psmt.setInt(2, pagesize);
			
			res = psmt.executeQuery();
			
			while(res.next()){
				FeedBack fb = new FeedBack();
				fb.setId(res.getInt("id"));
				fb.setFeedback(res.getString("feedback"));
				fb.setMark1(res.getString("mark1"));
				fb.setMark2(res.getString("mark2"));
				fb.setMark3(res.getString("mark3"));
				fb.setMark4(res.getString("mark4"));
				fb.setOrderid(res.getInt("orderId"));
				fb.setOrdertime(res.getString("orderTime"));
				fb.setUserid(res.getInt("userId"));
				
				list.add(fb);
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
//通过id删除	
  public void deleteFeedBack(int id){
			
			conn = ConnectionManager.getConn();
			try {
				psmt = conn.prepareStatement("delete from feedbacktbl where id=?");
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
}
