package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.CheckTableDao;
import com.amaker.dao.impl.CheckTableDaoImpl;
import com.amaker.entity.CheckTable;
/**
 * @author 郭耀华
 * 实现查台功能
 */
public class CheckTableServlet extends HttpServlet {
	// 构造方法
	public CheckTableServlet() {
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
		// 实例化CheckTableDao
		CheckTableDao dao = new CheckTableDaoImpl();
		// 获得餐桌信息列表
		List list = dao.getTableList();
		// 转换为字符串
		String msg = build(list);
		// 返回给客户端
		out.print(msg);
		out.flush();
		out.close();
	}
	// 将List列表转化为字符串
	private String build(List list){
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			CheckTable ct = (CheckTable) list.get(i);
			int num = ct.getNum();
			int flag = ct.getFlag();
			msg+=num+","+flag;
			if(i<(list.size()-1))msg+=";";
		}
		return msg;
	}
	// 响应Post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	// 初始化
	public void init() throws ServletException {
	}

}
