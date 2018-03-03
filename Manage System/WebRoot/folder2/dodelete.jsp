<%@ page language="java" import="java.util.*,com.lore.entity.*,com.lore.dao.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.dao.EmployeeImpl"%>
<%@page import="javax.swing.JOptionPane"%>
<%  
     //System.out.println("dfdfgdd");
	int id = Integer.parseInt(request.getParameter("id"));
	 EmployeeImpl ei = new EmployeeImpl();
      ei.deleteEmployee(id);
      
      JOptionPane.showMessageDialog(null,"删除成功!");
      response.sendRedirect("employee3.jsp");

%>