<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>餐饮系统---管理员登陆</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="dtree/dtree.css">

		<script type="text/javascript" src="dtree/dtree.js"></script>

	</head>
	<body >

		<div>
			<script type="text/javascript">
	var d = new dTree('d');
	
	d.add(0, -1, "后勤业务管理");
	
	  d.add(1, 0, "餐饮业务管理", "left.jsp");
	  
		  d.add(4, 1, "菜品管理", "left.jsp");
			
			    d.add(12, 4, "菜品管理", "folder9/vehicle_modification.jsp", "菜品管理", "main");
			    
				d.add(11, 4, "增加菜品", "folder9/vehicle_entry.jsp", "增加菜品", "main");
				
				
		  d.add(6, 1, "桌位详情", "left.jsp");
				d.add(21, 6, "桌位信息", "folder6/add_repair_a_maintenance.jsp", "桌位信息","main");
				
		  d.add(7, 1, "订单详情", "left.jsp");
				d.add(24, 7, "订单详情", "folder7/OrderDetail.jsp", "订单详情", "main");
				
		   d.add(8, 1, "用户反馈", "left.jsp");
			d.add(32, 8, "信息反馈", "folder8/add_oil.jsp", "信息反馈", "main");
			

	  d.add(2, 0, "员工统计", "left.jsp");
	        
	        d.add(39, 2, "员工信息", "folder2/vehicle.jsp", "员工信息", "main");
	        d.add(42, 2, "员工管理", "folder2/employee3.jsp", "员工管理", "main");
			d.add(40, 2, "员工录入", "folder2/employee1.jsp", "员工录入", "main");
			
			
	document.write(d);
</script>
		</div>

	</body>
</html>
