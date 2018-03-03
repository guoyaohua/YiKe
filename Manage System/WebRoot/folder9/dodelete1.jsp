<%@ page language="java" import="java.util.*,com.lore.entity.*,com.lore.dao.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.dao.MenutblImpl"%>
<%@page import="javax.swing.JOptionPane"%>
<%  
     
	int id = Integer.parseInt(request.getParameter("id"));
	//int typeid = (Integer)session.getAttribute("typeId");
	int typeid;
    		if(session.getAttribute("typeId")==null){
    		   typeid=1;
    		}else{
    		   typeid = (Integer)session.getAttribute("typeId");
    		}
	 
	 MenutblImpl ei = new MenutblImpl();
      ei.deleteEmployee(id ,typeid);
      
      JOptionPane.showMessageDialog(null,"删除成功!");
      response.sendRedirect("vehicle_modification.jsp");

%>