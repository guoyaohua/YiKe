package com.amaker.dao;

import com.amaker.entity.User;
// UesrDao 接口
public interface UserDao {
	// 登录方法
	public User login(String account,String password);
}
