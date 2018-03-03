package com.lore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.lore.entity.Employee;


public class EmployeeImpl {
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet res;
	Employee em = new Employee();
	
	
//查询记录通过id
	public Employee findbyid(int id){
		
		List<Employee> list = new ArrayList<Employee>();
		//System.out.println("dstgsdg");
		conn = ConnectionManager.getConn();
		//System.out.println("dstgsdg");
		try {
			
			psmt = conn.prepareStatement("select * from employees where id=?");
			psmt.setInt(1, id);
			res = psmt.executeQuery();
			//System.out.println("++++++++++++++++++++++"+id);
			//System.out.println("dthg");
			while(res.next()){
				//System.out.println("++++++++++++++++++++++1111"+id);
				Employee em = new Employee();
				
				em.setName(res.getString("name"));
				em.setSex(res.getString("sex"));
				em.setAge(res.getInt("age"));
				em.setNation(res.getString("nation"));
				em.setIdNumber(res.getString("idNumber"));
				em.setDegree(res.getString("degree"));
				em.setPosition(res.getString("position"));
				em.setWages(res.getString("wages"));
				em.setTelephone(res.getString("telephone"));
				em.setDate(res.getString("date"));
				em.setRemark(res.getString("remark"));
				//ma.setInfo(res.getString("info"));
				
				return em;
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
	
    public int findCount(){
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
		List<Employee> list = new ArrayList();
		conn = ConnectionManager.getConn();
		try {
			psmt = conn.prepareStatement("select * from employees "+str+"limit ?,?");
			
			psmt.setInt(1, (pagenum-1)*pagesize);
			psmt.setInt(2, pagesize);
			
			res = psmt.executeQuery();
			while(res.next()){
				Employee sc = new Employee();
				sc.setId(res.getInt("Id"));
				sc.setName(res.getString("name"));
				sc.setSex(res.getString("sex"));
				sc.setAge(res.getInt("age"));
				sc.setNation(res.getString("nation"));
				sc.setIdNumber(res.getString("idNumber"));
				sc.setDegree(res.getString("degree"));
				sc.setPosition(res.getString("position"));
				sc.setWages(res.getString("wages"));
				sc.setTelephone(res.getString("telephone"));
				sc.setDate(res.getString("date"));
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
//通过id删除	
	public void deleteEmployee(int id){
		conn = ConnectionManager.getConn();
		try {
			psmt = conn.prepareStatement("delete from employees where id=?");
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
	//插入
	public void saveEmployee(Employee em) {
		// TODO Auto-generated method stub
		conn = ConnectionManager.getConn();
		try {
			psmt = conn.prepareStatement("insert into employees (name,sex,age,nation,idNumber,degree,position,wages,telephone,remark,date)values(?,?,?,?,?,?,?,?,?,?,?)");
			//psmt.setInt(1, au.getAuthorid());
			psmt.setString(1, em.getName());
			psmt.setString(2, em.getSex());
			psmt.setInt(3, em.getAge());
			psmt.setString(4, em.getNation());
			psmt.setString(5, em.getIdNumber());
			psmt.setString(6, em.getDegree());
			psmt.setString(7, em.getPosition());
			psmt.setString(8, em.getWages());
			psmt.setString(9, em.getTelephone());
			psmt.setString(10, em.getRemark());
			psmt.setString(11, em.getDate());
			
			
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
	//通过id更新--doupdate.jsp
	public void updateAuthor(Employee au){
		conn = ConnectionManager.getConn();
		
		try {
			psmt = conn.prepareStatement("update employees set name=?,sex=?,age=?,nation=?,idNumber=?,degree=?,position=?,wages=?,telephone=?,remark=?,date=? where id=?");
			
			
			
			psmt.setString(1, au.getName());
			psmt.setString(2, au.getSex());
			psmt.setInt(3, au.getAge());
			psmt.setString(4, au.getNation());
			psmt.setString(5, au.getIdNumber());
			psmt.setString(6, au.getDegree());
			psmt.setString(7, au.getPosition());
			psmt.setString(8, au.getWages());
			psmt.setString(9, au.getTelephone());
			psmt.setString(10, au.getRemark());
			psmt.setString(11, au.getDate());
			psmt.setInt(12, au.getId());
			psmt.executeUpdate();
			//System.out.println(au.getName());
			
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

}
