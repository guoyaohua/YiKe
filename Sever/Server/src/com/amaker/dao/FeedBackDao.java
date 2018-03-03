package com.amaker.dao;

public interface FeedBackDao {
	/**
	 * 
	 * @param mark1 菜品口味
	 * @param mark2 服务态度
	 * @param mark3 饭店环境
	 * @param mark4 总体评价
	 * @param feedBack 反馈信息
	 */
public void saveFeedBack(String mark1, String mark2, String mark3,
		String mark4, String feedBack,String orderId,String userId,String orderTime);
}
