<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.dao.EmployeeImpl"%>
<%@page import="com.lore.entity.Employee"%>
<%@page import="com.lore.biz.PageBean"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<title>餐饮系统--管理员登陆--员工修改</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style>
A {text-decoration: NONE} 
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
		} else {

		}
	}
</script>

		
	</head>

	<body>

		<%
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		%>

		
			<table align="center" width="1000" cellpadding="3" cellspacing="0"
				border="1">
				<tr>
					<td colspan="6">
						<table>
							<tr align="center">
								<td>
									&nbsp;&nbsp;
									<font id="font1">职员信息修改</font>
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
					<td>
						<table border="1">

							<tr>
								<td width="30" align="center">
									ID
								</td>
								<td width="70" align="center">
									姓名
								</td>
								<td width="35" align="center">
									性别
								</td>
								<td width="35" align="center">
									年龄
								</td>
								<td width="45" align="center">
									民族
								</td>
								<td width="110" align="center">
									身份证号
								</td>
								<td width="65" align="center">
									学历
								</td>
								<td width="80" align="center">
									职位
								</td>
								<td width="57" align="center">
									工资
								</td>
								<td width="108" align="center">
									电话
								</td>
								<td width="105" align="center">
									入职日期
								</td>
								<td width="90" align="center">
									备注
								</td>
								<td width="50" align="center">修改</td>
								<td width="50" align="center">删除</td>
							</tr>


							<%
							   
							       int pagenum = 1;
							   
								
								if (request.getParameter("pagenum") != null) {
									pagenum = Integer.parseInt(request.getParameter("pagenum"));
								}
								String firstname = "";
								String lastname = "";
								if (request.getParameter("firstname") != null) {
									firstname = request.getParameter("firstname");
								}
								if (request.getParameter("lastname") != null) {
									lastname = request.getParameter("lastname");
								}
								PageBean pb = new PageBean();
								List<Employee> list = pb.findPage(pagenum, firstname, lastname);

								for (int i = 0; i < list.size(); i++) {

									Employee ma = list.get(i);
									 String str = ma.getRemark();
   			    					 String s;
   			    					 if(str.length()<4||str==""){
   			    
   			       							 s=str;
   			   						  }else{
   			    							 s = str.substring(0,3)+"....";
   			    					 }
									
							%>
							<tr onmouseout="this.style.backgroundColor=''"
									onmouseover="this.style.backgroundColor='#BFDFFF'">
								<td align="center"><%=(i+1)+12*(pagenum-1)%></td>
								<td align="center"><%=ma.getName()%></td>
								<td align="center"><%=ma.getSex()%></td>
								<td align="center"><%=ma.getAge()%></td>
								<td align="center"><%=ma.getNation()%></td>
								<td align="center"><%=ma.getIdNumber()%></td>
								<td align="center"><%=ma.getDegree()%></td>
								<td align="center"><%=ma.getPosition()%></td>
								<td align="center"><%=ma.getWages()%></td>
								<td align="center"><%=ma.getTelephone()%></td>
								<td align="center"><%=ma.getDate()%></td>
								<td align="center"><%=s%></td>
								<td align="center"><a href="update.jsp?id=<%=ma.getId()%>">更新</a></td>
								<td align="center"><a href="javascript:dodelete('<%=ma.getId()%>')">删除</a></td>
							</tr>

							<%
								}
							%>
						</table>

						<p>
							<div align="center">
							  
							   <%
							      if(pagenum<=1){
							         
							     %>
							         首页&nbsp;&nbsp;&nbsp;
							    <%
							     }else{
							    %>
							    <a href="../folder2/employee3.jsp?pagenum=<%=1 %>">首页&nbsp;&nbsp;&nbsp;</a>
							     <%
							      }
							     %>  
							
								<%
									if (pagenum <= 1) {
								%>
								<img src="../picture/111.gif"/>
								<%
									} else {
								%>
								<a
									href="../folder2/employee3.jsp?pagenum=<%=pagenum - 1%>&firstname=<%=firstname%>&lastname=<%=lastname%>"><img src="../picture/111.gif"/></a>
								<%
									}
								%>

								<%
									for (int i = 1; i <= pb.getTotalpage(); i++) {
								%>
								<a
									href="../folder2/employee3.jsp?pagenum=<%=i%>&firstname=<%=firstname%>&lastname=<%=lastname%>">&nbsp;<%=i%>&nbsp;</a>
								<%
									}
								%>



								<%
									if (pagenum >= pb.getTotalpage()) {
								%>
								<img src="../picture/222.gif"/>
								<%
									} else {
								%>
								<a
									href="../folder2/employee3.jsp?pagenum=<%=pagenum + 1%>&firstname=<%=firstname%>&lastname=<%=lastname%>"><img src="../picture/222.gif"/></a>
								<%
									}
								%>
								
								 <%
							      if(pagenum>=pb.getTotalpage()){
   								%>
							       &nbsp;&nbsp;&nbsp;  尾页
							    <%
							     }else{
							    %>
							    <a href="../folder2/employee3.jsp?pagenum=<%=pb.getTotalpage() %>">&nbsp;&nbsp;&nbsp;尾页</a>
							     <%
							      }
							     %>  
							     
							      &nbsp;&nbsp;&nbsp;&nbsp;共<%=pb.getTotal()%>条记录&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</p>
					</td>
				</tr>

			</table>

	</body>
</html>
