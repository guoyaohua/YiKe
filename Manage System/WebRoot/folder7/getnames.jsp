<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<jsp:useBean id="mdi" class="com.lore.dao.MenueDetImpl" scope="page"/>
<%
   String key = request.getParameter("key");
   String name = new String(key.getBytes("ISO8859-1"),"GBK");
   List<String> names = mdi.findNames(name);
   String str="";
   for(String m : names){
	str+=m+",";
   }
   str = str.substring(0,str.length()-1);
   out.print(str);
%>