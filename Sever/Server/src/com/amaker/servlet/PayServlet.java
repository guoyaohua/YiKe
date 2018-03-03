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

import com.amaker.dao.PayDao;
import com.amaker.dao.impl.PayDaoImpl;
import com.amaker.entity.Menu;
import com.amaker.entity.Print;
import com.amaker.entity.QueryOrder;
import com.amaker.entity.QueryOrderDetail;

/**
 * @author 郭耀华 根据订单编号，查询订单详细信息
 */
public class PayServlet extends HttpServlet {

	// 用于暂时存放点菜信息 打印用
	public static List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();

	// 构造方法
	public PayServlet() {
		super();
	}

	// 销毁方法
	public void destroy() {
		super.destroy();
	}

	// 响应Get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		// 实例化DAO
		PayDao dao = new PayDaoImpl();
		// 获得订单ID
		String result = request.getParameter("id");
		String[] res = result.split("aaa");
		String shortid = res[0];
		String longid = res[1];
		String isPrint = res[2];
		// 查询订单信息
		QueryOrder qo = dao.getOrderById(Integer.parseInt(shortid));
		// 查询订单详细列表
		List list = dao.getOrderDetailList(Integer.parseInt(shortid));

		// 拼XML格式数据
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		// 根节点
		System.out.println(list.size());
		out.println("<disheslist>");
		for (int i = 0; i < list.size(); i++) {
			QueryOrderDetail qod = (QueryOrderDetail) list.get(i);
			String name = qod.getName();
			float price = qod.getPrice();
			int num = qod.getNum();
			float total = qod.getTotal();
			String remark = qod.getRemark();

			// 将每条菜品都加入到list中
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("price", price);
			map.put("name", name);
			map.put("num", num);
			orderList.add(map);

			// 菜品节点
			out.println("<dishes>");
			// 菜品名称
			out.print("<name>");
			out.print(name);
			System.out.println(name);
			out.println("</name>");
			// 单价
			out.print("<price>");
			out.print(price == 0 ? "" : "" + price);
			out.println("</price>");
			// 数量
			out.print("<num>");
			out.print(num == 0 ? "" : num + "");
			out.println("</num>");
			// 总价
			out.print("<total>");
			out.print(total);
			out.println("</total>");

			// 备注
			out.print("<remark>");
			out.print(remark);
			out.println("</remark>");

			out.println("</dishes>");

		}

		// 订单信息节点
		out.println("<information>");
		// 订单编号
		out.print("<id>");
		out.print(" " + longid);
		out.println("</id>");
		// 下单时间
		out.print("<time>");
		out.print(qo.getOrderTime());
		out.println("</time>");
		// 服务员
		out.print("<personname>");
		out.print(qo.getName());
		out.println("</personname>");
		// 人数
		out.print("<personnum>");
		out.print(qo.getPersonNum());
		out.println("</personnum>");
		// 桌号
		out.print("<tableid>");
		out.print(qo.getTableId());
		out.println("</tableid>");

		out.println("</information>");

		out.println("</disheslist>");

		out.flush();
		out.close();
// 打印
		if (isPrint.equals("true")) {
			Print.payPrint(orderList, "" + longid, qo.getOrderTime(), qo.getName(),
					qo.getPersonNum() + "", qo.getTableId() + "");
		}
		orderList.clear();
	}

	// 响应Post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 初始化方法
	public void init() throws ServletException {
	}

}
