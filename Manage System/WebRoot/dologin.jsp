<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.dao.ManagerImpl"%>
<%@page import="com.lore.entity.Manager"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
    
	String username = request.getParameter("account");
	String pwd = request.getParameter("password");

	ManagerImpl mi = new ManagerImpl();
	Manager m = new Manager();
	m.setAccount(username);
	m.setPassword(pwd);
	
	//List<Manager> list1 = mi.find(Manager ma);
    //System.out.println("dstgsdg");
    	
    boolean boo =  mi.find(m);
  
    if(boo==true){
      session.setAttribute("name",username);
      request.getRequestDispatcher("index.jsp").forward(request,response);
	 }else{
	  response.sendRedirect("login.jsp"); 
     }	
    	
	/*for (int i = 0; i < list1.size(); i++) {

		Manager mm = list1.get(i);

		if (username.equals(mm.getAccount())
				&& pwd.equals(mm.getPassword())) {
			session.setAttribute("name", username);

			 response.sendRedirect("index.jsp");
		
			request.getRequestDispatcher("index.jsp").forward(request,response);
		} else {
			response.sendRedirect("index.jsp");

		}
		*/
	
	
%>

