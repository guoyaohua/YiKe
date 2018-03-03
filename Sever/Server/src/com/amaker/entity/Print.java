package com.amaker.entity;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class Print {

	// web调用打印
	public static void doPrint(String bf) {
		try {
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			// 使用默认打印机，如果默认打印机不是POS打印机，请通过名称查找。
			PrintService printer = PrintServiceLookup
					.lookupDefaultPrintService();
			// job
			DocPrintJob job = printer.createPrintJob();
			byte[] buf = bf.getBytes();
			InputStream stream = new ByteArrayInputStream(buf);
			Doc doc = new SimpleDoc(stream, flavor, null);

			// print
			job.print(doc, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点菜打印
	 * 
	 * @param list
	 *            菜品信息
	 * @param orderId
	 *            订单号
	 * @param tableName
	 *            桌位名称
	 */
	public static void orderPrint(List<Map<String, Object>> list,
			String orderId, String tableName) {
		StringBuffer bf = new StringBuffer();
		bf.append("          易点无线点餐 \n");
		bf.append("            点餐小票 \n");
		bf.append("--------------------------------\n");
		bf.append("桌位:" + tableName + "     订单号:" + orderId + "\n");
		bf.append("--------------------------------\n");
		bf.append("菜品名称            数量\n");
		bf.append("--------------------------------\n");
		for (int i = 0; i < list.size(); i++) {
			// 获得其中点菜map
			Map listItem = (Map) list.get(i);
			String listItem_num = listItem.get("num") + "";
			String listItem_name = listItem.get("name") + "";
			// 一个汉字大小占据两个空格 此处需要判断菜名长度
			if (listItem_name.length() == 4) {
				bf.append(listItem_name + "             " + listItem_num
						+ "\n");
			} else if (listItem_name.length() == 3) {
				bf.append(listItem_name + "               "
						+ listItem_num + "\n");
			} else if (listItem_name.length() == 2) {
				bf.append(listItem_name + "                 "
						+ listItem_num + "\n");
			} else if (listItem_name.length() == 1) {
				bf.append(listItem_name + "                   "
						+ listItem_num + "\n");
			} else if (listItem_name.length() == 5) {
				bf.append(listItem_name + "           " + listItem_num
						+ "\n");
			} else if (listItem_name.length() == 6) {
				bf.append(listItem_name + "         " + listItem_num
						+ "\n");
			}
		}
		bf.append("--------------------------------\n");
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bf.append("点餐时间：" + df.format(new Date()) + "\n");
		bf.append("\n\n\n\n\n\n\n");
		doPrint(bf.toString());
	}

	/**
	 * 支付打印
	 * 
	 * @param list
	 *            菜品清单
	 * @param orderId
	 *            订单号
	 * @param orderTime
	 *            订餐时间
	 * @param serverName
	 *            服务员姓名
	 * @param personNum
	 *            人数
	 * @param tableId
	 *            桌位id
	 */
	public static void payPrint(List<Map<String, Object>> list, String orderId,
			String orderTime, String serverName, String personNum,
			String tableId) {
		int totalNum = 0;
		float totalPrice = 0;
		StringBuffer bf = new StringBuffer();
		bf.append("          易点无线点餐 \n");
		bf.append("            菜品清单 \n");
		bf.append("--------------------------------\n");
		bf.append("订单号:" + orderId + "  服务员:" + serverName + "\n");
		bf.append("桌位号:" + tableId + "          人数:" + personNum + "\n");
		bf.append("--------------------------------\n");
		bf.append("菜品名称       数量       单价\n");// 11
		bf.append("--------------------------------\n");
		for (int i = 0; i < list.size(); i++) {
			// 获得其中点菜map
			Map listItem = (Map) list.get(i);
			String listItem_num = listItem.get("num") + "";
			String listItem_name = listItem.get("name") + "";
			String listItem_price = listItem.get("price") + "";
			String listItem_total = listItem.get("total") + "";
			totalNum += Integer.parseInt(listItem_num);
			totalPrice += Float.parseFloat(listItem_total);
			// 一个汉字大小占据两个空格 此处需要判断菜名长度
			if (listItem_name.length() == 4) {
				bf.append(listItem_name + "        " + listItem_num
						+ "       ￥" + listItem_price + "\n");
			} else if (listItem_name.length() == 3) {
				bf.append(listItem_name + "          " + listItem_num
						+ "       ￥" + listItem_price + "\n");
			} else if (listItem_name.length() == 2) {
				bf.append(listItem_name + "            " + listItem_num
						+ "       ￥" + listItem_price + "\n");
			} else if (listItem_name.length() == 1) {
				bf.append(listItem_name + "              " + listItem_num
						+ "       ￥" + listItem_price + "\n");
			} else if (listItem_name.length() == 5) {
				bf.append(listItem_name + "      " + listItem_num + "       ￥"
						+ listItem_price + "\n");
			} else if (listItem_name.length() == 6) {
				bf.append(listItem_name + "    " + listItem_num + "       ￥"
						+ listItem_price + "\n");
			}
		}
		bf.append("--------------------------------\n");
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bf.append("结算时间：" + df.format(new Date()) + "\n");
		bf.append("菜品总数：" + totalNum + "\n");
		bf.append("总计：￥" + totalPrice + "\n");
		bf.append("\n\n\n\n\n\n\n");
		doPrint(bf.toString());

	}
	// public static void main(String[] args) {
	// czPrint(1, "0000", "8888", "12.25", "100", "10", "90");
	// }
}
