<%@ page language="java" import="java.util.*,com.lore.entity.*,com.lore.dao.*" pageEncoding="UTF-8"%>
<%@ page import="com.lore.dao.EmployeeImpl"%>


<%
	 request.setCharacterEncoding("UTF-8");
     response.setCharacterEncoding("UTF-8");
     
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
     Employee em = new Employee();
     
     em.setName(request.getParameter("name"));
	 em.setSex(request.getParameter("sex"));
	 em.setAge(Integer.parseInt(request.getParameter("age")));
	 em.setPosition(request.getParameter("position"));
	 em.setWages(request.getParameter("wages"));
	 em.setDegree(degree);
	 em.setNation(request.getParameter("nation"));
	 em.setIdNumber(request.getParameter("idcard"));
	 em.setTelephone(request.getParameter("telephone"));
	 em.setDate(request.getParameter("date"));
	 em.setRemark(request.getParameter("remark"));
	 
	 EmployeeImpl adi = new EmployeeImpl();
	 session.setAttribute("degree",degree);
     adi.saveEmployee(em);
     System.out.println(session.getAttribute("degree"));
     response.sendRedirect("employee1.jsp");
 %>