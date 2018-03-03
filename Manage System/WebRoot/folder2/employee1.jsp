<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>餐饮系统---管理员登陆--员工录入</title>
    
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
  
<script language="javascript" type="text/javascript">
function check(){
		  
		  var name1 = document.getElementById("name1").value;
		  var age1 = document.getElementById("age1").value;
		  var sex1 = document.getElementById("sex1").value;
		  var nation1 = document.getElementById("nation1").value;
		  var position1 = document.getElementById("position1").value;
		  var degree1 = document.getElementById("degree1").value;
		  var idcard1 = document.getElementById("idcard1").value;
		  var wages1 = document.getElementById("wages1").value;
		  var telephone1 = document.getElementById("telephone1").value;
		  var date1 = document.getElementById("date1").value;
		  var remark1 = document.getElementById("remark1").value;
		  
    
    if (name1 == "") {
			alert("员工姓名不能为空");
			
		} else {
			if (age1 == "") {
				alert("员工年龄不能为空");
				
			} else {
				if (sex1 == "") {
					alert("员工性别不能为空");
				} else {
					if (nation1 == "") {
						alert("员工民族不能为空");
					} else {
						if (position1 == "") {
							alert("员工职位不能为空");
						} else {
							if (degree1 == "") {
								alert("员工学位不能为空");
							} else {
							    if(idcard1==""){
							       alert("员工身份证号不能为空");
							    }else{
							       if(wages1==""){
							          alert("员工薪水不能为空");
							       }else{
							           if(telephone1==""){
							              alert("员工联系电话不能为空");
							           }else{
							              if(date1==""){
							                   alert("员工入职电话不能为空");
							              }else{
							                  if(remark1==""){
							                     alert("员工备注不能为空");
							                  }else{
							                  	document.form1.submit();
							                  }
							                 
							              }
							           }
							       }
							    }
								
							}
						}
					}
				}

			}
		}
		

}
</script>

  </head>
  
  <body>
    <form action="doadd.jsp" method="post" name="form1">
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
			   <td colspan="7"></td>
			</tr>
			<tr>
				<td colspan="7">
					<table>
						<tr align="center">
							<td>
								&nbsp;&nbsp;
								<font id="font1">员工添加信息表</font>
							</td>
						</tr>
						<tr>
						  <td></td>
						</tr>
						<tr>
						  <td></td>
						</tr>
						<tr>
						  <td></td>
						</tr>
						<tr>
						  <td></td>
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
			      <input type="text" id="name1" name="name" size="27" maxlength="10"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">年龄:</font>
			   </td>
			   <td>
			      <input type="text" id="age1" name="age" size="27"/>
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
			      <input type="text" id="sex1" name="sex" size="27"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">民族:</font>
			   </td>
			   <td>
			      <input type="text" id="nation1" name="nation" size="27"/>
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
			      <input type="text" id="position1" name="position" size="27"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">学位:</font>
			   </td>
			   <td>
			      <select id="degree1" name="degree">
										
										<option value="00" <%="博士".equals(session.getAttribute("degree"))?"selected":""%>>
											博&nbsp;&nbsp;士
										</option>
										<option value="01" <%="硕士".equals(session.getAttribute("degree"))?"selected":""%>>
											硕&nbsp;&nbsp;士
										</option>
										<option value="02" <%="本科".equals(session.getAttribute("degree"))?"selected":""%>>
											本&nbsp;&nbsp;科
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
			      <input type="text" id="idcard1" name="idcard" size="27" maxlength="18"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">工资:</font>
			   </td>
			   <td>
			      <input type="text" id="wages1" name="wages" size="27"/>
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
			      <input type="text"id="telephone1" name="telephone" size="27" maxlength="11"/>
			   </td>
			   <td></td>
			   <td align="right">
			      <font id="font2">入职日期:</font>
			   </td>
			   <td>
			      <input type="text" id="date1" name="date" style="width:57%" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  />
			      
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
					<textarea rows="4" cols="50" id="remark1" name="remark"></textarea>
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
			    
				<td>
					&nbsp;
				</td>
				<td align="center">
					<input type="button" onclick="check();" value="添加"/>&nbsp;&nbsp;
				    
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
				<td>
					&nbsp;
				</td>
			</tr>

		</table>
      </form>
  </body>
</html>
