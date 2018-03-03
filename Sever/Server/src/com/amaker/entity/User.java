package com.amaker.entity;

/**
 * 
 * @author hz.guo
 *	用于封装UserTbl表的实体类
 */
public class User {
	// 编号
	private int id;
	// 账号
	private String account;
	// 密码
	private String password;
	// 用户名称
	private String name;
	// 性别
	private String gender;
	// 权限
	private int permission;
	// 备注
	private String remark;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

}
