<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.dao.EmployeeImpl"%>
<%@page import="com.lore.entity.Employee"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>餐饮系统---管理员登陆--员工修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<style type="text/css">
#font1 {
	color: blue;
	font-size: 28px;
}

#font2 {
	font-weight: bold;
	font-size: 18px;
}
.tian{
font-size: 17px;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js">
</script>

  </head>
  
<body>
   <%
    		int id = Integer.parseInt(request.getParameter("id"));
    		
    		EmployeeImpl adi = new EmployeeImpl();
    		session.setAttribute("id",id);
    		String degree = (String)session.getAttribute("degree");
    		Employee me = adi.findbyid(id);
    		
        %>
    <form action="doupdate.jsp" method="post" name="form1">
    <input type="hidden" name="id" value="<%=me.getId()%>" >
		<table align="center" width="1000" cellpadding="3" cellspacing="0">
		    <tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
				<td colspan="7">
					<table>
						<tr align="center">
						    
							<td>
								&nbsp;&nbsp;
								<font id="font1">员工修改信息表</font>
							</td>
						</tr>
						<tr>
						  <td>
						  </td>
						</tr>
						<tr>
						  <td>
						  </td>
						</tr>
						<tr>
						  <td>
						  </td>
						</tr>
						<tr>
							<td>
								<hr color="red" size="2" width="950" align="left" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			
			
			
			<tr>
			   <td align="right">
			      <font id="font2">姓名:</font>
			   </td>
			   <td>
			      <input type="text" name="name" value="<%=me.getName()%>"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">年龄:</font>
			   </td>
			   <td>
			      <input type="text" name="age" size="27" value="<%=me.getAge() %>"/>
			   </td>
			   <td></td>
			   <td></td>
			  
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td align="right">
			      <font id="font2">性别:</font>
			   </td>
			   <td>
			      <input type="text" name="sex" value="<%=me.getSex() %>"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">民族:</font>
			   </td>
			   <td>
			      <input type="text" name="nation" size="27" value="<%=me.getNation() %>"/>
			   </td>
			   <td></td>
			   <td></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td align="right">
			      <font id="font2">职位:</font>
			   </td>
			   <td>
			      <input type="text" name="position" value="<%=me.getPosition() %>"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">学位:</font>
			   </td>
			   <td>
			      <select name="degree">
										
										<option value="00" <%="博士".equals(session.getAttribute("degree"))?"selected":""%>>
											博&nbsp;&nbsp;士
										</option>
										<option value="01" <%="硕士".equals(session.getAttribute("degree"))?"selected":""%>>
											硕&nbsp;&nbsp;士
										</option>
										<option value="02" <%="本科".equals(session.getAttribute("degree"))?"selected":""%>>
											学&nbsp;&nbsp;士
										</option>
										<option value="03" <%="大专".equals(session.getAttribute("degree"))?"selected":""%>>
											大&nbsp;&nbsp;专
										</option>
										<option value="04" <%="中专".equals(session.getAttribute("degree"))?"selected":""%>>
											中&nbsp;&nbsp;专
										</option>
				  </select>
			     
			   </td>
			   <td></td>
			   <td></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td align="right">
			      <font id="font2">身份证号:</font>
			   </td>
			   
			   <td>
			      <input type="text" name="idcard" value="<%=me.getIdNumber()%>"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">工资:</font>
			   </td>
			   <td>
			      <input type="text" name="wages" size="27" value="<%=me.getWages()%>"/>
			   </td>
			   <td></td>
			   <td></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td align="right">
			      <font id="font2">联系电话:</font>
			   </td>
			   <td>
			      <input type="text" name="telephone" value="<%=me.getTelephone() %>"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">入职日期:</font>
			   </td>
			   <td>
			      <input type="text" name="date" value="<%=me.getDate() %>" style="width:55%" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  />
			      
			   </td>
			   <td></td>
			   <td></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
				<td align="right">
					<font id="font2">备注信息:</font>
				</td>
				<td colspan="6">
					<textarea rows="4" cols="50" name="remark"><%=me.getRemark()%></textarea>
				</td>

			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			   <td colspan="7"></td>
			</tr>
			<tr>
			    
				<td>
					&nbsp;
				</td>
				<td align="center">
					<input type="submit"  value="更新"/>&nbsp;&nbsp;
				    
				</td>
				
				<td>
				    <input type="reset" value="取消 "/>&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>

		</table>
      </form>
  </body>
</html>
