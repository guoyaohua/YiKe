package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.PayDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.dao.impl.PayDaoImpl;
/**
 * @author 郭耀华
 * 实现结算功能
 */
public class PayMoneyServlet extends HttpServlet {
	// 构造方法
	public PayMoneyServlet() {
		super();
	}
	// 销毁方法
	public void destroy() {
		super.destroy(); 
	}
	// 响应Get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 实例化PayDao
		PayDao dao = new PayDaoImpl();
		// 获得订单编号
		String id = request.getParameter("id");
		// 结算
		dao.pay(Integer.parseInt(id));
		// 实例化OrderDao
		OrderDao dao2 = new OrderDaoImpl();
		// 将餐桌状态更新为空位
		dao2.updateTableStatus2(Integer.parseInt(id));
		// 向客户端发送信息
		out.print("已结算！");
		out.flush();
		out.close();
	}
	// 响应Post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	// 初始化方法
	public void init() throws ServletException {
	}
}
