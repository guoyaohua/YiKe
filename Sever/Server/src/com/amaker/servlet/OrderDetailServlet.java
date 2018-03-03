package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.entity.OrderDetail;
import com.amaker.entity.Print;

public class OrderDetailServlet extends HttpServlet {
	// 用于暂时存放点菜信息 打印用
	public static List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	public OrderDetailServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String orderId = request.getParameter("orderId");
		String menuId = request.getParameter("menuId");
		String num = request.getParameter("num");
		String remark = request.getParameter("remark");
		String longOrderId = request.getParameter("longOrderId");
		// 得到是否为最后一道菜
		String isLast = request.getParameter("isLast");

		OrderDao dao = new OrderDaoImpl();

		OrderDetail od = new OrderDetail();

		od.setMenuId(Integer.parseInt(menuId));
		od.setOrderId(Integer.parseInt(orderId));
		od.setNum(Integer.parseInt(num));
		od.setRemark(remark);

		String result = dao.saveOrderDetail(od);
		// res[0]为菜品名称 res[1]为单价
		String[] res = result.split(" ");

		// 将每条菜品都加入到list中
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuId", menuId);
		map.put("price", res[1]);
		map.put("name", res[0]);
		map.put("num", num);
		list.add(map);
		// 判断是否打印
		if (isLast.equals("yes")) {
			int i;
			//map为菜品集合   orderId为订单号  remark为桌位名称
			Print.orderPrint(list,longOrderId,remark);
			for (i = 0; i < list.size(); i++) {
				// 获得其中点菜map
				Map listItem = (Map) list.get(i);
				String listItem_menuId = listItem.get("menuId") + "";
				String listItem_num = listItem.get("num") + "";
				String listItem_price = listItem.get("price") + "";
				String listItem_name = listItem.get("name") + "";
				System.out.println("menuId:" + listItem_menuId + "    数量:"
						+ listItem_num + "    菜名:" + listItem_name + "    单价:"
						+ listItem_price);
			}
			// 清空list中数据
			list.clear();
		}

		// 浼犻�缁欏鎴风鐨勬暟鎹細鍗抽【瀹㈢殑璇勪环鍐呭
		out.print(remark);

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {

	}

}
