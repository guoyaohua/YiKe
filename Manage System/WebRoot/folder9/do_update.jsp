<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.entity.Menutbl"%>
<%@page import="com.lore.biz.MenutblBean"%>
<%@page import="com.lore.dao.MenutblImpl"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>餐饮系统--管理员登陆--菜品修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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


	</head>

	<body>
	   <%   
	       
	        int id = Integer.parseInt(request.getParameter("id"));
	        
    		session.setAttribute("id",id);
    		//int typeid = (Integer)session.getAttribute("typeId");
    		//String typeid =(String)session.getAttribute("typeId");
    		int typeid;
    		if(session.getAttribute("typeId")==null){
    		   typeid=1;
    		}else{
    		   typeid = (Integer)session.getAttribute("typeId");
    		}
    		
    		MenutblImpl adi = new MenutblImpl();
    		Menutbl me = adi.findMenutblById(id,typeid);
    		
        %>
       <form action="do_update1.jsp" method="post" >
       <input type="hidden" name="id" value="<%=me.getId()%>" >
		<table align="center" width="1000" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="8">
					<table>
						<tr align="center">
							<td>
								&nbsp;&nbsp;
								<font id="font1">菜品信息修改</font>
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
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			
			
			<tr>
			
			   <td align="right" width="100px">
					<font id="font2">菜品名称:</font>
				</td>
				
				<td width="80">
					<input type="text" name="name" value="<%=me.getFilename()%>"/>
				</td>
				

				<td align="right">
					<font id="font2">菜品类型:</font>
				</td>
				
				<td>
					<select name="cname">
     					              
						              <option value="1" <%="1".equals(session.getAttribute("typename"))?"selected":""%>>
						                                              今&nbsp;日&nbsp;推&nbsp;荐
						              </option>
						              <option value="2" <%="2".equals(session.getAttribute("typename"))?"selected":""%>>
						                                              凉&nbsp;菜
						              </option>
						              <option value="3" <%="3".equals(session.getAttribute("typename"))?"selected":""%>>
						              		  热&nbsp;菜
						              </option>
						              <option value="4" <%="4".equals(session.getAttribute("typename"))?"selected":""%>>
						              		  主&nbsp;食
						              </option>
						              <option value="5" <%="5".equals(session.getAttribute("typename"))?"selected":""%>>
						              		 甜&nbsp;品 
						              </option>
						              <option value="6" <%="6".equals(session.getAttribute("typename"))?"selected":""%>>
						              		汤
						              </option>
						              <option value="7" <%="7".equals(session.getAttribute("typename"))?"selected":""%>>
						              		水&nbsp;果&nbsp;拼&nbsp;盘
						              </option>
						              <option value="8" <%="8".equals(session.getAttribute("typename"))?"selected":""%>>
						              		酒&nbsp;水&nbsp;饮&nbsp;料
						              </option>
						             
              		              </select>&nbsp;&nbsp;&nbsp;
				</td>
				<td width="80"></td>
				<td width="80"></td>
				<td width="80"></td>
			</tr>
			
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
				
				
				<td align="right">
					<font id="font2">菜品价格:</font>
				</td>
				<td>
					<input type="text" name="price" value="<%=me.getPrice()%>"/>
				</td>
			</tr>
			
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			<tr>
			   <td colspan="8"></td>
			</tr>
			
			<tr>
			    <td align="right">
			       <font id="font2">上传照片:</font>
			    </td>
			    
				<td align="left" width="400">
							<input type="file" name="file"/>
				            
				</td>
				
			</tr>
			
			<tr>
				<td align="right">
					
				</td>
				<td>
					
				</td>
				<td>
					&nbsp;
				</td>
				<td align="right">
					
				</td>
				<td>
					
				</td>
			</tr>
			
			
			<tr>
				<td align="right">
					<font id="font2">备注信息:</font>
				</td>
				<td colspan="6">
					<textarea rows="4" cols="60" name="remark"><%=me.getRemark()%></textarea>
				</td>

			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td align="center">
					<input type="submit" value="更新" />&nbsp;&nbsp;
				    <input type="reset"  value="取消 " />
				</td>
				
				<td align="center">
				
				</td>
				<td>
					&nbsp;
				</td>
			</tr>


			

		</table>
      </form>
	</body>
</html>
