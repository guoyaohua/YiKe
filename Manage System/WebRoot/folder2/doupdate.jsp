<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.lore.entity.Employee"%>
<%@page import="com.lore.dao.EmployeeImpl"%>



<%
	 request.setCharacterEncoding("UTF-8");
     response.setCharacterEncoding("UTF-8");
     
     Employee au = new Employee();
     //int id = Integer.parseInt(request.getParameter("id"));
     String degree = request.getParameter("degree");
     if(degree.equals("00")){
        degree = "博士";
     }else if(degree.equals("01")){
     	degree = "硕士";
     }else if(degree.equals("02")){
     	degree = "本科";
     }else if(degree.equals("03")){
     	degree = "大专";
     }else if(degree.equals("04")){
     	degree = "中专";
     }
     //System.out.println("----------------"+session.getAttribute("id"));
    
     //String temp = "111";
    // int n = Integer.parseInt(temp);
    // System.out.println(n);
     
    // String ii = (String)session.getAttribute("id");
     //System.out.println("eftsrtgftsdg"+ii);
     
     //int id = Integer.parseInt(ii);
     //int id = 14;
     
     au.setId((Integer)session.getAttribute("id"));
     
     au.setName(request.getParameter("name"));
	 au.setSex(request.getParameter("sex"));
	 au.setAge(Integer.parseInt(request.getParameter("age")));
	 au.setPosition(request.getParameter("position"));
	 au.setWages(request.getParameter("wages"));
	 au.setDegree(degree);
	 au.setNation(request.getParameter("nation"));
	 au.setIdNumber(request.getParameter("idcard"));
	 au.setTelephone(request.getParameter("telephone"));
	 au.setDate(request.getParameter("date"));
	 au.setRemark(request.getParameter("remark"));
	 
	 //session.setAttribute("dd",degree);
	 //System.out.println(request.getParameter("id"));
	  //System.out.println(session.getAttribute("degree"));
	 EmployeeImpl adi = new EmployeeImpl();
	 //adi.saveEmployee(em);
     adi.updateAuthor(au);
     response.sendRedirect("employee3.jsp");
 %>