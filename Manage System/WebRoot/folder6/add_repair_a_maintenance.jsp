<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.biz.OrderBean"%>
<%@page import="com.lore.entity.Order"%>
<%@page import="com.lore.dao.OrderImpl"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>餐饮系统---管理员登陆--桌位信息</title>

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

		<form action="do_select.jsp" method="post">
			<table align="center" width="1000" cellpadding="3" cellspacing="0"
				border="1">
				<tr>
					<td colspan="6">
						<table>

							<tr align="center">
								<td>
									&nbsp;&nbsp;
									<font id="font1">桌位信息查询</font>
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
								<td colspan="7">
									
									<select name="cname">

										<option value="1" <%="1".equals(session.getAttribute("typename")) ? "selected": ""%>>
											现&nbsp;已&nbsp;开&nbsp;桌
										</option>
										<option value="0" <%="0".equals(session.getAttribute("typename")) ? "selected": ""%>>
											尚&nbsp;未&nbsp;开&nbsp;桌
										</option>


									</select>
								  	
									<input type="hidden" name="pagenum" value="1" />
									<input type="submit" name="chaxun" value="查询" />
									
								</td>
								
								
							</tr>
							<tr>
								<td width="150" align="center">
									桌台号
								</td>
								<td width="150" align="center">
									订单号
								</td>
								<td width="170" align="center">
									开桌人数
								</td>
								<td width="115" align="center">
									结账状态
								</td>
								<td width="170" align="center">
									开桌时间
								</td>
								<td width="125" align="center">
									结账备注
								</td>
								<td width="90" align="center">
									删除
								</td>
							</tr>
			<%
   			int pagenum = 1;
   			
   			if(request.getParameter("pagenum")!=null){
   				pagenum = Integer.parseInt(request.getParameter("pagenum"));
   			}
   			
   			int typeId = 1;
   			if(session.getAttribute("typeId")!=null){
   				typeId = (Integer)session.getAttribute("typeId");
   				
   			}
   		   OrderBean pb = new OrderBean();	
   		//if(request.getParameter("fileid")==null){
   				
   			
   			
   			//if(request.getParameter("fileid")==null){
   			   List<Order> list = pb.findPage(pagenum,typeId);
   			//}else{
   			    //Order o = new Order();
				//OrderImpl oi = new OrderImpl();
				//o =  oi.findOrderByfileid(fileid);
				//int fi = Integer.parseInt(request.getParameter("fileid"));
   			   //List<Order> list = oi.findOrderByfileid(fi);
   			//}
   			
   			
   			for(int i=0;i<list.size();i++){
   				Order o = list.get(i);
   				
   				
   				 if(o.getIsPay()==1){
   			         o.setOo("已付款");
   			     }else{
   			            o.setOo("未付款");
   			          }
   			      
   			     if(typeId==1){
   			     
   			%>
							<tr onmouseout="this.style.backgroundColor=''"
									onmouseover="this.style.backgroundColor='#BFDFFF'">
								<td align="center"><%="T"+o.getTableId()%></td>
								<td align="center"><%=o.getOrderid()%></td>
								<td align="center"><%=o.getPersonNum()+"&nbsp;位"%></td>
								<td align="center"><%=o.getOo()%></td>
								<td align="center"><%=o.getOrderTime()%></td>
								<td align="center"><%=o.getRemark()%></td>
								<td align="center"><a href="javascript:dodelete('<%=o.getId()%>')">删除</a></td>
							</tr>	
							<% }else{%>
							  <tr onmouseout="this.style.backgroundColor=''"
									onmouseover="this.style.backgroundColor='#BFDFFF'">
								<td align="center"><%="T"+o.getId()%></td>
								<td align="center"><%="空"%></td>
								<td align="center"><%="空"%></td>
								<td align="center"><%="空"%></td>
								<td align="center"><%="空"%></td>
								<td align="center"><%="空"%></td>
								<td align="center">删除</td>
							</tr>	
							
							  <%}%>
							
						
			 <%}%>
			
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
							    <a href="add_repair_a_maintenance.jsp?pagenum=<%=1 %>">首页&nbsp;&nbsp;&nbsp;</a>
							     <%
							      }
							     %>  
							       
							<%if(pagenum<=1){%>
								<img src="../picture/111.gif"/>
								<%
							  }else{
							%>
								<a href="add_repair_a_maintenance.jsp?pagenum=<%=pagenum-1 %>"><img src="../picture/111.gif"/></a>
								<%
							  }
							%>

								<%
   				              for(int i=1;i<=pb.getTotalpage();i++){
   				            %>
								<a href="add_repair_a_maintenance.jsp?pagenum=<%=i %>">&nbsp;<%= i %>&nbsp;</a>
							<%
							  }
							%>

								<%if(pagenum>=pb.getTotalpage()){%>
								<img src="../picture/222.gif"/>
								<%}else{%>
								<a href="add_repair_a_maintenance.jsp?pagenum=<%=pagenum+1%>"><img src="../picture/222.gif"/></a>
								<%}%>
								
								
								 <%
							      if(pagenum>=pb.getTotalpage()){
   								%>
							        &nbsp;&nbsp; &nbsp;尾页
							    <%
							     }else{
							    %>
							    <a href="add_repair_a_maintenance.jsp?pagenum=<%=pb.getTotalpage() %>">&nbsp;&nbsp;&nbsp;尾页</a>
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
