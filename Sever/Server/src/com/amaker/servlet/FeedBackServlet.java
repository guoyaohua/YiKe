package com.amaker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.FeedBackDao;
import com.amaker.dao.impl.FeedBackDaoImpl;

public class FeedBackServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
System.out.println("doGet");
		// 点餐时间
		String orderTime = request.getParameter("time");
		// 操作员编号
		String userId = request.getParameter("userId");
		// 菜品口味
		String mark1 = request.getParameter("mark1");
		// 服务态度
		String mark2 = request.getParameter("mark2");
		// 饭店环境
		String mark3 = request.getParameter("mark3");
		// 总体评价
		String mark4 = request.getParameter("mark4");
		// 反馈内容
		String feedBack = request.getParameter("feedback");
		// 订单号
		String orderId = request.getParameter("orderId");

		// 获得DAO接口
		FeedBackDao dao = new FeedBackDaoImpl();
		dao.saveFeedBack(mark1, mark2, mark3, mark4, feedBack, orderId, userId, orderTime);

		
	//super.doGet(request, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doPost");
		doGet(req, resp);

	}

}
