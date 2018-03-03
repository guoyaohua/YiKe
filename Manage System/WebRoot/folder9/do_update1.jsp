<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="com.lore.dao.MenutblImpl"%>
<%@page import="com.lore.entity.Menutbl"%>
<%
    request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
      
   Menutbl m = new Menutbl();  
   m.setId((Integer)session.getAttribute("id"));
   m.setFilename(request.getParameter("name"));
   m.setTypeId(Integer.parseInt(request.getParameter("cname")));
   m.setPrice(Integer.parseInt(request.getParameter("price")));
   m.setRemark(request.getParameter("remark"));
  		
   
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
    
     MenutblImpl mi = new MenutblImpl();
	     
	     mi.updateBySql(m);
	      
	 
	     response.sendRedirect("../folder9/vehicle_modification.jsp");
   
     

 

%>