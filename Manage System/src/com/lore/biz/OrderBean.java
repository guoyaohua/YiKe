package com.lore.biz;

import java.util.List;

import com.lore.entity.Order;
import com.lore.dao.OrderImpl;

public class OrderBean {

	private int pagenum;
	private int pagesize = 13;
	private int totalpage;
	private int total;
	private int typeId;
	OrderImpl dao = new OrderImpl();
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	

	public int getTotal() {
		int total = dao.findCount();
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Order> findPage(int pagenum, int typeId) {
		this.setTypeId(typeId);
		if(typeId==1){
			
			return dao.findByPage(pagenum, this.pagesize);
		}else{
			return dao.findByPage0(pagenum, this.pagesize);
			
		}
		

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

		int total = dao.findCount();

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
