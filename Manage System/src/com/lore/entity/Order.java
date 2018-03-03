package com.lore.entity;

public class Order {
	
	private int id;
	private int tableId;
	private int personNum;
	private int isPay;
	private String orderTime;
	private String remark;
	private String oo;
	private String longid;
	private String orderid;
	
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getLongid() {
		return longid;
	}
	public void setLongid(String longid) {
		this.longid = longid;
	}
	public String getOo() {
		return oo;
	}
	public void setOo(String oo) {
		this.oo = oo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getPersonNum() {
		return personNum;
	}
	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
