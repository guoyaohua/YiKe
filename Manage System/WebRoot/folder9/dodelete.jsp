<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.entity.Menutbl"%>
<%@page import="com.lore.biz.MenutblBean"%>
<%@page import="com.lore.dao.MenutblImpl"%>
<%@page import="javax.swing.JOptionPane"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	

	
	
	int pagenum = Integer.parseInt(request.getParameter("pagenum"));
	
	int typeId ;
	
	String cname = request.getParameter("cname");
	if (cname.equals("1")) {

		typeId = 1;
		
	} else if (cname.equals("2")) {

		typeId = 2;
		
	} else if (cname.equals("3")) {

		typeId = 3;
		
	} else if (cname.equals("4")) {
		
		typeId = 4;
	} else if (cname.equals("5")) {
		
		typeId = 5;
	} else if (cname.equals("6")) {
		
		typeId = 6;
	} else if (cname.equals("7")) {
		
		typeId = 7;
	} else {
		
		typeId = 8;
	}
	
	
	
	request.setAttribute("pagenum",pagenum);
	session.setAttribute("typeId",typeId);
	session.setAttribute("typename",cname);
	
	response.sendRedirect("vehicle_modification.jsp");
%>