<%@ page language="java" import="java.util.*,com.lore.entity.*,com.lore.dao.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.dao.OrderImpl"%>
<%@page import="javax.swing.JOptionPane"%>
<%  
     
	int id = Integer.parseInt(request.getParameter("id"));
	//int typeid = (Integer)session.getAttribute("typeId");
	
	 System.out.println(id+"-----========-----s");
	 OrderImpl oi = new OrderImpl();
      oi.deleteOrder(id);
      
      JOptionPane.showMessageDialog(null,"删除成功!");
      response.sendRedirect("add_repair_a_maintenance.jsp");

%>