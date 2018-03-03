<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<title>餐饮系统---管理员登陆--增加菜品</title>

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
#msg{
  font-size:12px;
  color:red;
}
</style>
<script language="javascript" type="text/javascript">

	function check() {

		var name1 = document.getElementById("name1").value;
		var cname1 = document.getElementById("cname1").value;
		var price1 = document.getElementById("price1").value;
		var file1 = document.getElementById("file1").value;
		var remark = document.getElementById("remark").value;
		if (name1 == "") {
			alert("菜品名称不能为空");
		} else {
			if (cname1 == "") {
				alert("菜品类型不能为空");
			} else {
				if (price1 == "") {
					alert("菜品价格不能为空");
				} else {
					if (file1 == "") {
						   alert("菜品照片不能为空");
					} else {
					    if(remark == ""){
					         alert("菜品备注不能为空");
					    }else{
					         document.form1.submit();
					        // location.href = "dosure.jsp?name="+ name1;
					    }
						

					}
				}

			}
		}
	
    
	}
	
	//判断菜品名是否已被用

	var xhr;
	function createXhr() {
		if (window.ActiveXObject) {
			xhr = new ActiveXObject("Microsoft.XMLHTTP");
		} else {
			xhr = new XMLHttpRequest();
		}
	}

	function statechange() {

		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var sp = document.getElementById("msg");
				var txt = xhr.responseText;
				if (txt.match("true")) {
					sp.innerHTML = "菜品名可用";
					document.getElementById("btn").disabled = false;
				} else {
					sp.innerHTML = "抱歉，菜品名已被占用，请重输";
					document.getElementById("btn").disabled = true;
				}
			}
		}
	}

	function dorequest(u) {
		if (u.length > 0) {
			createXhr();
			xhr.onreadystatechange = statechange;
			var url = "checkname.jsp?username=" + u;
			xhr.open("get", url, true);
			xhr.send(null);
		}
	}
</script>

	</head>

	<body>
       <form action="do_insert.jsp" method="post" name="form1" enctype="multipart/form-data">
		<table align="center" width="1000" cellpadding="3" cellspacing="0">
			<tr>
				<td colspan="8">
					<table>
						<tr align="center">
							<td>
								&nbsp;&nbsp;
								<font id="font1">菜品添加信息表</font>
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
					<input type="text" id="name1" name="txtBookName" onblur="dorequest(this.value)"/>
					&nbsp;&nbsp;<span id="msg"></span>
				</td>
				

				<td align="right">
					<font id="font2">菜品类型:</font>
				</td>
				
				<td>
					<select id="cname1" name="cname">
     					              
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
						             
              		              </select>
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
					<input type="text" id="price1" name="price" />
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
							<input type="file" id="file1" name="txtPic"/>
				            
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
					<textarea rows="4" cols="60" id="remark" name="remark"></textarea>
				</td>

			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td align="center">
					<input type="button" onclick="check();" id="btn" disabled value="提交" />&nbsp;&nbsp;
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
