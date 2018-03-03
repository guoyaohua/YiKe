<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lore.dao.MenutblImpl" %>
<%@ page import="com.lore.biz.*" %>
<%
   
   
   request.setCharacterEncoding("UTF-8");
   response.setCharacterEncoding("UTF-8");
   
   String name = request.getParameter("username");
   
   String names1 = new String(name.getBytes("ISO8859-1"),"GBK");
   String names2 = new String(name.getBytes("ISO8859-1"),"UTF-8");
   MenutblImpl mi = new MenutblImpl();
   //System.out.println("==========="+names1);
   //System.out.println("==========="+names2);
   boolean flag = mi.findByName(names1,names2);
   //boolean flag = false;
   //System.out.println("==========="+flag);
   out.print(flag);	
%>
