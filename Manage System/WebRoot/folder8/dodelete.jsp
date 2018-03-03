<%@ page language="java" import="java.util.*,com.lore.entity.*,com.lore.dao.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.dao.FeedBackImpl"%>
<%@page import="javax.swing.JOptionPane"%>
<%  
     
	int id = Integer.parseInt(request.getParameter("id"));
	
	
	 
	 FeedBackImpl fbi = new FeedBackImpl();
      fbi.deleteFeedBack(id);
      
      JOptionPane.showMessageDialog(null,"删除成功!");
      response.sendRedirect("add_oil.jsp");

%>