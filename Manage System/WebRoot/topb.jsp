<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>餐饮系统---管理员登陆</title>
 <style type="text/css">
 
   a{
     text-decoration:none;
   }
   body{
     width:100%;
     height:100%;
    
   }
   
 </style>
</head>
  
  <body>
  
  	<table>
  		<tr>
  		  <td>&nbsp;&nbsp;<font color="black">[欢迎:&nbsp;<%=session.getAttribute("name")%>&nbsp;登录]&nbsp;&nbsp;|&nbsp;&nbsp;</font></td>
  		  <td><a href="javascript:target()" ><font color="black" >[退出系统]</font></a></td>
  		  <td width="700px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  		  <td><div id='jnkc'>
  		  		<script>setInterval("jnkc.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);</script>
  		  		</div></td>
  		  
  		</tr>
  	</table>
  
  </body>
  <style type="text/css">
	body{
		background-color: #B0D5F0;
	}
</style>


<script type="text/javascript"> 
function target(){ 
window.parent.location.href="login.jsp"; 
} 
</script> 
</html>
