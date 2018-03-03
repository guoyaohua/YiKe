<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.biz.FeedBackBean"%>
<%@page import="com.lore.entity.FeedBack"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>餐饮系统---管理员登陆--评分反馈</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style>
A {
	text-decoration: NONE
}
</style>
		<style type="text/css">
#font1 {
	color: blue;
	font-size: 23px;
}

#font2 {
	background-color: blue;
	color: white;
	font-size: 17px;
	font-weight: bold;
}

.tian {
	font-size: 17px;
}
</style>

		<script type="text/javascript">
	function dodelete(param) {
  
		if (confirm("您确定要删除吗?")) {

			location.href = "dodelete.jsp?id="+ param;
		} 
	}
</script>

	</head>

	<body>

		<%
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		%>

		<form action="add_oil.jsp" method="post">
			<table align="center" width="1000" cellpadding="3" cellspacing="0"
				border="1">
				<tr>
					<td colspan="6">
						<table>

							<tr align="center">
								<td>
									&nbsp;&nbsp;
									<font id="font1">信息反馈查询</font>
								</td>
							</tr>

							<tr>
								<td>
									<hr color="red" size="2" width="990" align="left" />
								</td>
							</tr>

						</table>
					</td>
				</tr>

				<tr>
					<td colspan="6">
						<table border="1">

							<tr heigth="150" width="1000">
								<td colspan="9">

									&nbsp;订单ID:
									<input type="text" name="firstname" size="10"
										value="<%=request.getParameter("firstname") == null ? "" : request
					.getParameter("firstname")%>" />
									&nbsp; 服务员ID:
									<input type="text" name="lastname" size="10"
										value="<%=request.getParameter("lastname") == null ? "" : request
					.getParameter("lastname")%>" />
									&nbsp;
									<input type="submit" value="查询" />

									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>


							</tr>
							<tr>
								<td width="70" align="center">
									订单号
								</td>
								<td width="70" align="center">
									服务员ID
								</td>
								<td width="145" align="center">
									菜品口味
								</td>
								<td width="120" align="center">
									饭店环境
								</td>
								<td width="120" align="center">
									服务态度
								</td>
								<td width="120" align="center">
									总体评价
								</td>
								<td width="120" align="center">
									反馈内容
								</td>
								<td width="125" align="center">
									评价时间
								</td>
								<td width="70" align="center">
									删除
								</td>
							</tr>
							<%
				int pagenum = 1;

				if (request.getParameter("pagenum") != null) {
					pagenum = Integer.parseInt(request.getParameter("pagenum"));
				}
				String firstname = "";
				if (request.getParameter("firstname") != null) {
					firstname = request.getParameter("firstname");
				}
				String lastname = "";
				if (request.getParameter("lastname") != null
						&& request.getParameter("lastname") != "") {
					lastname = request.getParameter("lastname");
				}

				FeedBackBean pb = new FeedBackBean();

				List<FeedBack> list = pb.findPage(pagenum, firstname, lastname);

				for (int i = 0; i < list.size(); i++) {
					FeedBack f = list.get(i);
					
					String str1 = f.getMark1();
					String str2 = f.getMark2();
					String str3 = f.getMark3();
					String str4 = f.getMark4();
					String str5= f.getFeedback();
					
					
   			    	String s1;
   			    	String s2;
   			    	String s3;
   			    	String s4;
   			    	String s5;
   			    	
   			    	if(str1.length()<7||str1==""){
   			    
   			       		  s1=str1;
   			   	    }else{
   			    		  s1 = str1.substring(0,6)+"....";
   			       }
   			       
   			       if(str2.length()<7||str1==""){
   			    
   			       		 s2=str2;
   			   	    }else{
   			    		 s2 = str2.substring(0,6)+"....";
   			       }
   			       if(str3.length()<7||str1==""){
   			    
   			       		 s3=str3;
   			   	    }else{
   			    		 s3 = str3.substring(0,6)+"....";
   			       }
   			       if(str4.length()<7||str1==""){
   			    
   			       		 s4=str4;
   			   	    }else{
   			    		 s4 = str4.substring(0,6)+"....";
   			       }
   			       if(str5.length()<7||str1==""){
   			    
   			       		 s5=str5;
   			   	    }else{
   			    		 s5 = str5.substring(0,6)+"....";
   			       }
   			      
			%>
							<tr onmouseout="this.style.backgroundColor=''"
								onmouseover="this.style.backgroundColor='#BFDFFF'">
								<td align="center"><%=f.getOrderid()%></td>
								<td align="center"><%=f.getUserid()%></td>
								<td align="center"><%=s1%></td>
								<td align="center"><%=s2%></td>
								<td align="center"><%=s3%></td>
								<td align="center"><%=s4%></td>
								<td align="center"><%=s5%></td>
								<td align="center"><%=f.getOrdertime()%></td>
								<td align="center">
									<a href="javascript:dodelete('<%=f.getId()%>')">删除</a>
								</td>
							</tr>


							<%
																						  	}
																						  %>




						</table>

						<p>
							<div align="center">

								<%
									if (pagenum <= 1) {
							    %>
								首页&nbsp;&nbsp;&nbsp;
								<%
							    	} else {
							    %>
								<a href="add_oil.jsp?pagenum=<%=1%>">首页&nbsp;&nbsp;&nbsp;</a>
								<%
							     	}
							     %>

								<%
  							       if (pagenum <= 1) {
  							    %>
								<img src="../picture/111.gif" />
								<%
									} else {
								%>
								<a href="add_oil.jsp?pagenum=<%=pagenum - 1%>"><img
										src="../picture/111.gif" />
								</a>
								<%
									}
								%>

								<%
									for (int i = 1; i <= pb.getTotalpage(); i++) {
								%>
								<a href="add_oil.jsp?pagenum=<%=i%>">&nbsp;<%=i%>&nbsp;</a>
								<%
								}
							%>

								<%
									if (pagenum >= pb.getTotalpage()) {
								%>
								<img src="../picture/222.gif" />
								<%
									} else {
								%>
								<a href="add_oil.jsp?pagenum=<%=pagenum + 1%>"><img
										src="../picture/222.gif" />
								</a>
								<%
									}
								%>


								<%
																								 	if (pagenum >= pb.getTotalpage()) {
																								 %>
								&nbsp;&nbsp; &nbsp;尾页
								<%
 	} else {
 %>
								<a
									href="add_oil.jsp?pagenum=<%=pb.getTotalpage()%>">&nbsp;&nbsp;&nbsp;尾页</a>
								<%
							     	}
							     	pagenum = 1;
							     %>
								&nbsp;&nbsp;&nbsp;&nbsp;共<%=pb.getTotal()%>条记录&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</p>
					</td>
				</tr>


			</table>
		</form>
	</body>
</html>
