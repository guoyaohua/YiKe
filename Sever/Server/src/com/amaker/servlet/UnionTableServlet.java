package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.UnionTableDao;
import com.amaker.dao.impl.UnionTableDaoImpl;
import com.amaker.entity.Menu;
import com.amaker.entity.UnionTable;

public class UnionTableServlet extends HttpServlet {
	public UnionTableServlet() {
		super();
	}
	public void destroy() {
		super.destroy();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		UnionTableDao dao = new UnionTableDaoImpl();
		
		List list = dao.getTableList();
	
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		
		out.println("<tablelist>");
			for (int i = 0; i <list.size(); i++) {
				UnionTable ut = (UnionTable)list.get(i);
				out.println("<table>");
					out.print("<id>");
						out.print(ut.getId());
					out.println("</id>");
					
					out.print("<num>");
						out.print(ut.getNum());
					out.println("</num>");
				out.println("</table>");
			}
		out.println("</tablelist>");
		
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}
}
