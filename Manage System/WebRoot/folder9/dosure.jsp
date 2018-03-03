<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="javax.swing.JOptionPane"%>
<jsp:useBean id="mi" class="com.lore.dao.MenutblImpl" scope="session"/>


<% 
   request.setCharacterEncoding("UTF-8");
   response.setCharacterEncoding("UTF-8");

   String name = request.getParameter("name");
 
   String names2 = new String(name.getBytes("ISO8859-1"),"UTF-8");
   
   System.out.println(names2+"==================");
   
   boolean flag = mi.findname(names2);
   System.out.println(flag+"------------------");
   if(flag){
       JOptionPane.showMessageDialog(null,"加菜成功!");
   }else{
       JOptionPane.showMessageDialog(null,"加菜失败!");
   }
   response.sendRedirect("vehicle_entry.jsp");
%>