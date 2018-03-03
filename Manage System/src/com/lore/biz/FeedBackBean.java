package com.lore.biz;

import java.util.List;


import com.lore.dao.FeedBackImpl;

import com.lore.entity.FeedBack;


public class FeedBackBean {
	

	private int pagenum;
	private int pagesize = 13;
	private int totalpage;
	private int total;
	
	
	
	

	
	FeedBackImpl fbi = new FeedBackImpl();
	public int getTotal() {
		int total = fbi.findCount();
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<FeedBack> findPage(int pagenum, String firstname,String lastname){
		
		String sql = null;
		if (firstname != "")
			if (lastname != "")
				sql = "select * from feedbacktbl where orderId = '" + firstname+ "' or userId = " + lastname;
			else
				sql = "select * from feedbacktbl where orderId = '" + firstname+ "'";
		else if (lastname != "")
			sql = "select * from feedbacktbl where userId = " + lastname;
		else
			sql = "select * from feedbacktbl";
		return fbi.findByPage(sql, pagenum, this.pagesize);

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

	public int getTotalpage() {

		int total = fbi.findCount();

		if (total % pagesize == 0) {
			this.totalpage = total / pagesize;
		} else {
			this.totalpage = total / pagesize + 1;
		}

		return this.totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

}

