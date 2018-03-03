<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.entity.Order"%>
<%@page import="com.lore.biz.OrderBean"%>
<%@page import="com.lore.dao.OrderImpl"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

    
	int fileid = Integer.parseInt(request.getParameter("fileid"));
	//int tableid = Integer.parseInt(request.getParameter("tableid"));
	
	System.out.println(fileid+"========++++++++++++============");
	
	
	Order o = new Order();
	OrderImpl oi = new OrderImpl();
	o =  oi.findOrderByfileid(fileid);
	
	//if(request.getParameter("fileid")==null&&request.getParameter("tableid")!=null){
	    //o =  oi.findOrderBytableid(tableid);
	//}
	//if(request.getParameter("fileid")!=null){
	    //o =  oi.findOrderByfileid(fileid);
	    //System.out.println(o.getId());
	    //System.out.println(o.getIsPay());
	    //System.out.println(o.getTableId());
	//}
	//if(request.getParameter("fileid")!=null&&request.getParameter("tableid")!=null){
	
		//o =  oi.findOrderBy(fileid,tableid);
	//}
	
	 response.sendRedirect("add_repair_a_maintenance.jsp");     
%>	