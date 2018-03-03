<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="javax.swing.JOptionPane"%>
<jsp:useBean id="mi" class="com.lore.dao.MenutblImpl" scope="session"/>

<%@page import="com.lore.dao.MenutblImpl"%>
<%@page import="com.lore.biz.UploadBean"%>
<jsp:useBean id="upload" class="com.lore.upload.SmartUpload" scope="page"></jsp:useBean>
<%
    request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
    
    
    upload.initialize(pageContext);
    upload.setMaxFileSize(1024*1024*10);
    upload.setTotalMaxFileSize(1024*1024*50);
    upload.setAllowedFilesList("jpg,jpeg,png");
    upload.setDeniedFilesList("ext,bat");
    
    try{
    
       upload.upload();
       UploadBean bean = new UploadBean(); 
       String bookName = upload.getRequest().getParameter("txtBookName");
       int price = Integer.parseInt(upload.getRequest().getParameter("price"));
       String remark = upload.getRequest().getParameter("remark");
       
       
  		
     String typename;
     int typeId ;
	 String cname = upload.getRequest().getParameter("cname");
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
   
   if(typeId == 1){
		typename = "今日推荐";
	}else if(typeId == 2){
		typename = "凉菜";
	}else if(typeId == 3){
		typename = "热菜";
	}else if(typeId == 4){
		typename = "主食";
	}else if(typeId == 5){
		typename = "甜品";
	}else if(typeId == 6){
		typename = "汤";
	}else if(typeId == 7){
		typename = "水果拼盘";
	}else{
		typename = "酒水饮料";
	}
	
	
	  int i = upload.save("/upload",bookName,price,remark,typename,typeId);
	 
	 
	 
	  
   //String name = request.getParameter("txtBookName");
 
   //String names2 = new String(name.getBytes("ISO8859-1"),"UTF-8");
   
   //System.out.println(name+"==================");
   
   boolean flag = mi.findname(bookName);
   System.out.println(flag+"------------------");
   if(flag){
       JOptionPane.showMessageDialog(null,"加菜成功!");
   }else{
       JOptionPane.showMessageDialog(null,"加菜失败!");
   }
	  
       
       response.sendRedirect("vehicle_entry.jsp");
       
     
    }catch(Exception ex){
    
       ex.printStackTrace();
    }

%>