<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.entity.Order"%>
<%@page import="com.lore.biz.OrderBean"%>
<%@page import="com.lore.dao.OrderImpl"%>
<%@page import="javax.swing.JOptionPane"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	

	
	int pagenum = Integer.parseInt(request.getParameter("pagenum"));
	
	int typeId ;
	
	String cname = request.getParameter("cname");
	if (cname.equals("1")) {

		typeId = 1;
		
	} else  {

		typeId = 0;
	}	
	
	
	request.setAttribute("pagenum",pagenum);
	session.setAttribute("typeId",typeId);
	session.setAttribute("typename",cname);
	
	response.sendRedirect("add_repair_a_maintenance.jsp");
%>