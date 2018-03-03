package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.UpdateDao;
import com.amaker.dao.impl.UpdateDaoImpl;
import com.amaker.entity.Table;

/**
 * @author 郭宏志
 * 完成数据更新功能
 */
public class UpdateTableTblServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 构造方法
	public UpdateTableTblServlet() {
		super();
	}
	// 销毁方法
	public void destroy() {
		super.destroy();
	}
	// 响应Get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		// 实例化dao
		UpdateDao dao = new UpdateDaoImpl();
		// 获得菜谱列表
		List list = dao.getTableList();
		
		// 拼XML格式数据
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		// 根节点
		out.println("<tablelist>");
			for (int i = 0; i <list.size(); i++) {
				Table t = (Table)list.get(i);
				out.println("<table>");
					// 餐桌编号
					out.print("<id>");
						out.print(t.getId());
					out.println("</id>");
					// 餐桌人数
					out.print("<num>");
						out.print(t.getNum());
					out.println("</num>");
					// 餐桌状态
					out.print("<flag>");
						out.print(t.getFlag());
					out.println("</flag>");
					//餐桌描述
					out.print("<description>");
						out.print(t.getDescription());
					out.println("</description>");
				out.println("</table>");
			}
		out.println("</tablelist>");
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
