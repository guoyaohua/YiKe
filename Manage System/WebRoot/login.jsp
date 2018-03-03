<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>餐饮系统---管理员登陆</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" href="imageslogin/login.css" />
	</head>

	<body>
	
		<form action="dologin.jsp" method="post">
			<table align="center" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<h1>
							无线点菜系统管理平台
						</h1>
					</td>
				</tr>
				<tr>
					<td>
						<img src="imageslogin/1111.jpg" border="0" hspace="0"
							vspace="0" align="middle" />
					</td>
					<td>

						<table background="imageslogin/login_2.gif" border="0"
							height="261" align="center">
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<font class="font">&nbsp;登录名称&nbsp;&nbsp;</font>
								</td>
								<td>
									<input type="text" size="15" id="username" name="account">
									&nbsp;&nbsp;
								</td>
							</tr>
							<tr height="16">
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<font class="font">&nbsp;登录密码&nbsp;&nbsp;</font>
								</td>
								<td>
									<input type="password" id="upwd" name="password" size="16">
									&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									
								</td>
							</tr>

							<tr>
								<td>
									<img src="imageslogin/tip.gif" align="right" />
								</td>
								<td align="left">

									<a href="index.jsp"><font class="font">忘记密码</font>
									</a> &nbsp;
									<a href="index.jsp"><font class="font">联系管理员</font>
									</a>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>

							<tr>
								<td align="center" colspan="2">
									<input type="submit" class="btn" value=" 登  录  ">
									<input type="reset" class="btn" value=" 重  置  ">
								</td>
							</tr>

							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
					<td>
						<img src="imageslogin/login_3.gif" border="0" hspace="0"
							vspace="0" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<img src="imageslogin/login_4.gif" border="0" hspace="0"
							vspace="0" />
					</td>
				</tr>

			</table>
		</form>
	</body>
</html>
