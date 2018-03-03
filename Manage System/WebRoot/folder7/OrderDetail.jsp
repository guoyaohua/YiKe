<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.entity.MenuDet"%>
<%@page import="com.lore.biz.MenueDetBean"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
#font1 {
	color: blue;
	font-size: 28px;
}

#font2 {
	background-color: blue;
	color: white;
	font-size: 17px;
	font-weight: bold;
}
 a{
     text-decoration:none;
   }
.tian {
	font-size: 17px;
}

</style>


</head>

	<body>

		<%
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		%>

		<form action="OrderDetail.jsp" method="post">
			<table align="center" width="1000" cellpadding="3" cellspacing="0"
				border="1">
				<tr>
					<td colspan="8">
						<table>
						
							<tr align="center">
								<td>
									&nbsp;&nbsp;
									<font id="font1">订单详情</font>
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
							
							<tr height="20px">
							   <td colspan="8">&nbsp;订单ID:
							      <input type="text" name="firstname" size="10" value="<%=request.getParameter("firstname") == null ? "" : request
					.getParameter("firstname")%>"/>&nbsp;
					              
					              <input type="submit" value="查询"/>
							   </td>
							</tr>
							
							<tr>
								<td width="90" align="center">
									ID
								</td>
								<td width="120" align="center">
									订单号
								</td>
								<td width="180" align="center">
									菜名
								</td>
								<td width="150" align="center">
									数量
								</td>
								<td width="150" align="center">
									桌号
								</td>
								<td width="150" align="center">
									餐桌人数
								</td>
								<td width="200" align="center">
									下单时间
								</td>
								<td width="100" align="center">
									是否结账
								</td>

							</tr>


			<%
   			int pagenum = 1;
   			
   			if(request.getParameter("pagenum")!=null){
   				pagenum = Integer.parseInt(request.getParameter("pagenum"));
   			}
   			
   			String firstname = "";
			if (request.getParameter("firstname") != null) {
			
				firstname = request.getParameter("firstname");
			}
   			
   			MenueDetBean db = new MenueDetBean();
   			List<MenuDet> list = db.findPage(pagenum,firstname);
   			int t = db.getTotalpage();
   			for(int i=0;i<list.size();i++){
   				MenuDet c = list.get(i);
   			    int in = c.getispay();
   			    String str;
   			    if(in==0){
   			    
   			      str = "已付款";
   			    
   			    }else{
   			      str = "已付款";
   			    }
   			%>
							<tr onmouseout="this.style.backgroundColor=''"
									onmouseover="this.style.backgroundColor='#BFDFFF'">
								<td align="center"><%=(i+1)+11*(pagenum-1)%></td>
								<td align="center" ><%=c.getorderid()%></td>
								<td align="center" ><%=c.getfilename()%></td>
								<td align="center" ><%=c.getnum()%></td>								
								<td align="center"><%=c.gettableid()%></td>
								<td align="center" ><%=c.getpersonnum()+"&nbsp;人"%></td>
								<td align="center"><%=c.getordertime()%></td>
								<td align="center" ><%=str%></td>
								<%}%>
							</tr>

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
							    <a href="OrderDetail.jsp?pagenum=<%=1 %>">首页&nbsp;&nbsp;&nbsp;</a>
							     <%
							      }
							     %>  
							       
							<%if(pagenum<=1){%>
								<img src="../picture/111.gif"/>
								<%
							  }else{
							%>
								<a href="OrderDetail.jsp?pagenum=<%=pagenum-1 %>"><img src="../picture/111.gif"/></a>
								<%
							  }
							%>

								<%
   				              for(int i=1;i<=db.getTotalpage();i++){
   				            %>
								<a href="OrderDetail.jsp?pagenum=<%=i %>">&nbsp;<%= i %>&nbsp;</a>
							<%
							  }
							%>

								<%if(pagenum>=db.getTotalpage()){%>
								<img src="../picture/222.gif"/>
								<%}else{%>
								<a href="OrderDetail.jsp?pagenum=<%=pagenum+1%>"><img src="../picture/222.gif"/></a>
								<%}%>
								
								
								 <%
							      if(pagenum>=db.getTotalpage()){
   								%>
							        &nbsp;&nbsp;&nbsp;尾页
							    <%
							     }else{
							    %>
							    <a href="OrderDetail.jsp?pagenum=<%=db.getTotalpage() %>">&nbsp;&nbsp;&nbsp;尾页</a>
							     <%
							      }
							       pagenum = 1;
							       
							     %>  
								&nbsp;&nbsp;&nbsp;&nbsp;共<%=db.getTotal()%>条记录&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</p>
					</td>
				</tr>




			</table>
			</form>
	</body>
</html>
