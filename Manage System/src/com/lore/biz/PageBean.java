package com.lore.biz;
import java.util.List;

import com.lore.dao.EmployeeImpl;
import com.lore.entity.Employee;
public class PageBean {
	
	private int pagenum;
	private int pagesize = 13;
	private int totalpage;
	private int total;
	EmployeeImpl adi = new EmployeeImpl();
	public int getTotal() {
		
		int total = adi.findCount();
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public List<Employee> findPage(int pagenum,String firstname,String lastname){
			String str ="";
		if(!"".equals(firstname)&&!"".equals(lastname)){
			str = str +"where firstname='"+firstname+"' and lastname='"+lastname+"'";
		}
		
		return adi.findByPage(pagenum, this.pagesize,str);
			
		}
		
	
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
	//获得总的页数
	public int getTotalpage() {
		
		int total = adi.findCount();
		
		if(total%pagesize==0){
			this.totalpage = total/pagesize;
		}else{
			this.totalpage = total/pagesize+1;
		}
		
		return this.totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	
	
}
