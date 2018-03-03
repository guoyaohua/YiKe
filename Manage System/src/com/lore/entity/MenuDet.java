package com.lore.entity;

public class MenuDet {
	private int orderid;
	private int menueid;
	private int num;
	private int personnum;
	private int ispay;
	private int tableid;
	private String remark;
	private String ordertime;
	private String filename;
	
	public int getorderid() {
		return orderid;
	}
	public void setorderid(int orderid) {
		this.orderid = orderid;
	}
	public int getmenueid() {
		return menueid;
	}
	public void setmenueid(int menueid) {
		this.menueid = menueid;
	}
	public int getnum() {
		return num;
	}
	public void setnum(int num) {
		this.num = num;
	}
	public int getpersonnum() {
		return personnum;
	}
	public void setpersonnum(int personnum) {
		this.personnum = personnum;
	}
	public int getispay()  {
		return ispay;
	}
	public void setispay(int ispay) {
		this.ispay = ispay;
	}
	public String getremark() {
		return remark;
	}
	public void setremark(String remark) {
		this.remark = remark;
	}
	public String getordertime() {
		return ordertime;
	}
	public void setordertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getfilename() {
		return filename;
	}
	public void setfilename(String filename) {
		this.filename = filename;
	}
	public int gettableid() {
		return tableid;
	}
	public void settableid(int tableid) {
		this.tableid = tableid;
	}
}
