package com.amaker.entity;

public class Table {
	private int id;
	private int num;
	private int flag;
	private String description;
	private int orderId;
	private int long_orderId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getLong_orderId() {
		return long_orderId;
	}
	public void setLong_orderId(int long_orderId) {
		this.long_orderId = long_orderId;
	}


}
