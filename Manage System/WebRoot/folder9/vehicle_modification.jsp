<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lore.entity.Menutbl"%>
<%@page import="com.lore.biz.MenutblBean"%>

<html>
	<head>


		<title>餐饮系统--管理员登陆--菜品修改</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<style>
A {
	text-decoration: NONE
}
</style>
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

.tian {
	font-size: 17px;
}
</style>
	</head>

	<script type="text/javascript">
	function dodelete(param) {

		if (confirm("您确定要删除吗?")) {

			location.href = "dodelete1.jsp?id=" + param;
		}
	}
</script>

	<body>

		<%
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		%>

		<form action="dodelete.jsp" method="post">
			<table align="center" width="1000" cellpadding="3" cellspacing="0"
				border="1">
				<tr>
					<td colspan="6">
						<table>

							<tr align="center">
								<td>
									&nbsp;&nbsp;
									<font id="font1">菜品信息修改</font>
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
								<td colspan="7">

									<select name="cname">

										<option value="1" checked="checked"
											<%="1".equals(session.getAttribute("typename")) ? "selected": ""%>>
											今&nbsp;日&nbsp;推&nbsp;荐
										</option>
										<option value="2"
											<%="2".equals(session.getAttribute("typename")) ? "selected": ""%>>
											凉&nbsp;菜
										</option>
										<option value="3"
											<%="3".equals(session.getAttribute("typename")) ? "selected": ""%>>
											热&nbsp;菜
										</option>
										<option value="4"
											<%="4".equals(session.getAttribute("typename")) ? "selected": ""%>>
											主&nbsp;食
										</option>
										<option value="5"
											<%="5".equals(session.getAttribute("typename")) ? "selected": ""%>>
											甜&nbsp;品
										</option>
										<option value="6"
											<%="6".equals(session.getAttribute("typename")) ? "selected": ""%>>
											汤
										</option>
										<option value="7"
											<%="7".equals(session.getAttribute("typename")) ? "selected": ""%>>
											水&nbsp;果&nbsp;拼&nbsp;盘
										</option>
										<option value="8"
											<%="8".equals(session.getAttribute("typename")) ? "selected": ""%>>
											酒&nbsp;水&nbsp;饮&nbsp;料
										</option>

									</select>
									&nbsp;&nbsp;
									<input type="hidden" name="pagenum" value="1">
									<input type="submit" name="chaxun" value="查询" />
								</td>
							</tr>

							<tr>
								<td width="90" align="center">
									ID
								</td>
								<td width="220" align="center">
									名称
								</td>
								<td width="150" align="center">
									价格
								</td>
								<td width="150" align="center">
									类别
								</td>
								<td width="230" align="center">
									备注
								</td>
								<td width="65" align="center">
									修改
								</td>
								<td width="65" align="center">
									删除
								</td>

							</tr>


							<%
								int pagenum = 1;

								if (request.getParameter("pagenum") != null) {
									pagenum = Integer.parseInt(request.getParameter("pagenum"));
								}

								int typeId = 1;
								if (session.getAttribute("typeId") != null) {
									typeId = (Integer) session.getAttribute("typeId");

								}

								MenutblBean pb = new MenutblBean();
								List<Menutbl> list = pb.findPage(pagenum, typeId);
								for (int i = 0; i < list.size(); i++) {
									Menutbl c = list.get(i);
									String str = c.getRemark();
									String s;
									if (str.length() < 9 || str == "") {

										s = str;
									} else {
										s = str.substring(0, 9) + "....";
									}
							%>
							<tr onmouseout="this.style.backgroundColor=''"
									onmouseover="this.style.backgroundColor='#BFDFFF'">
								<td align="center"><%=(i + 1) + 11 * (pagenum - 1)%></td>
								<td align="center"><%=c.getFilename()%></td>
								<td align="center"><%=c.getPrice() + "元"%></td>
								<td align="center"><%=c.getTypename()%></td>
								<td align="center"><%=s%></td>
								<td align="center">
									<a href="do_update.jsp?id=<%=c.getId()%>">更新</a>
								</td>
								<td align="center">
									<a href="javascript:dodelete('<%=c.getId()%>')">删除</a>
								</td>
								<%
									}
								%>
							</tr>

						</table>

						&nbsp;&nbsp;&nbsp;
						
							<div align="center">
								<%
									if (pagenum <= 1) {
								%>
								首页&nbsp;&nbsp;&nbsp;
								<%
									} else {
								%>
								<a href="vehicle_modification.jsp?pagenum=<%=1%>">首页&nbsp;&nbsp;&nbsp;</a>
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
								<a href="vehicle_modification.jsp?pagenum=<%=pagenum - 1%>"><img
										src="../picture/111.gif" />
								</a>
								<%
									}
								%>

								<%
									for (int i = 1; i <= pb.getTotalpage(); i++) {
								%>
								<a href="vehicle_modification.jsp?pagenum=<%=i%>">&nbsp;<%=i%>&nbsp;</a>
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
								<a href="vehicle_modification.jsp?pagenum=<%=pagenum + 1%>"><img
										src="../picture/222.gif" />
								</a>
								<%
									}
								%>


								<%
									if (pagenum >= pb.getTotalpage()) {
								%>
								&nbsp;&nbsp;&nbsp;尾页
								<%
									} else {
								%>
								<a
									href="vehicle_modification.jsp?pagenum=<%=pb.getTotalpage()%>">&nbsp;&nbsp;&nbsp;尾页</a>
								<%
									}
									pagenum = 1;
								%>
								&nbsp;&nbsp;&nbsp;&nbsp;共<%=pb.getTotal()%>条记录&nbsp;&nbsp;&nbsp;&nbsp;
							</div>	
							
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>




			</table>
		</form>
	</body>
</html>
