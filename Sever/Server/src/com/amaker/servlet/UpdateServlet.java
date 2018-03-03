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
import com.amaker.entity.Menu;

/**
 * @author 郭耀华
 * 完成数据更新功能
 */
public class UpdateServlet extends HttpServlet {
	// 构造方法
	public UpdateServlet() {
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
		List list = dao.getMenuList();
		
		// 拼XML格式数据
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		// 根节点
		out.println("<menulist>");
			for (int i = 0; i <list.size(); i++) {
				Menu m = (Menu)list.get(i);
				out.println("<menu>");
					// 菜谱编号
					out.print("<id>");
						out.print(m.getId());
					out.println("</id>");
					// 分类
					out.print("<typeId>");
						out.print(m.getTypeId());
					out.println("</typeId>");
					// 名称
					out.print("<name>");
						out.print(m.getName());
					out.println("</name>");
					// 图片路径
					out.print("<pic>");
						out.print(m.getPic());
					out.println("</pic>");
					// 价格
					out.print("<price>");
						out.print(m.getPrice());
					out.println("</price>");
					// 备注
					out.print("<remark>");
						out.print(m.getRemark());
					out.println("</remark>");
					
				out.println("</menu>");
			}
		out.println("</menulist>");
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
