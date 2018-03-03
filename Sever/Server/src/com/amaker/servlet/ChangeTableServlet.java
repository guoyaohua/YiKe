package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.ChangeTableDao;
import com.amaker.dao.impl.ChangeTableDaoImpl;
/**
 * @author 郭耀华
 * 完成转台功能
 */
public class ChangeTableServlet extends HttpServlet {
	// 构造方法
	public ChangeTableServlet() {
		super();
	}
	// 销毁方法
	public void destroy() {
		super.destroy(); 
	}
	// 响应Get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 获得请求参数
		String orderId = request.getParameter("orderId");
		String tableId = request.getParameter("tableId");
		// 实例化ChangeTableDao
		ChangeTableDao dao = new ChangeTableDaoImpl();
		// 调用转台方法
		dao.changeTable(Integer.parseInt(orderId), Integer.parseInt(tableId));
		// 返回客户端信息
		out.println("更换成功！");
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
