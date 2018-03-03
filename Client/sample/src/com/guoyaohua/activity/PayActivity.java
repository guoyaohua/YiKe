package com.guoyaohua.activity;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.guoyaohua.activity.R;
import com.guoyaohua.adapter.OrderIndexAdapter;
import com.guoyaohua.entity.GlobalVariable;
import com.guoyaohua.util.HttpUtil;
import com.guoyaohua.widget.LoadMoreGridViewPullToreshView;
import com.wanpu.pay.PayConnect;
import com.wanpu.pay.PayResultListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class PayActivity extends Activity {
	// 启动activity的请求码
	private static final int SHOW_PAYDAILOG = 1;
	private Button query;
	private Button pay;
	private ListView listView;
	private Button back;
	private static final int SUCCESS = 0;
	private static final int CONNECT_ERRO = 8;
	public static final int UPDATE_LISTVIEW = 1;
	protected static final int ONLY_QUERY = 1;
	protected static final int QUERY_AND_PAY = 2;
	private SimpleAdapter spAdapter;

	// 连接服务器用
	private Document doc;
	private NodeList nl;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == SUCCESS) {
				// 显示结算结果
				Toast.makeText(PayActivity.this, msg.obj + "",
						Toast.LENGTH_SHORT).show();
				// 使结算按钮失效
				pay.setEnabled(false);
			} else if (msg.what == UPDATE_LISTVIEW) {
				spAdapter.notifyDataSetChanged();
			} else if (msg.what == QUERY_AND_PAY) {
				spAdapter.notifyDataSetChanged();
				Intent intent = new Intent(PayActivity.this, PayDailog.class);

				startActivityForResult(intent, SHOW_PAYDAILOG);
			} else if (msg.what == CONNECT_ERRO) {
				Toast.makeText(PayActivity.this, "对不起，服务器连接错误，请重试",
						Toast.LENGTH_SHORT).show();
			}
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.pay_layout);
		super.onCreate(savedInstanceState);
		// 得到组件
		query = (Button) findViewById(R.id.bt_pay_query);
		pay = (Button) findViewById(R.id.bt_pay_pay);
		listView = (ListView) findViewById(R.id.lv_pay_listview);
		back = (Button) findViewById(R.id.bt_back);
		// 获取对象
		list = (List<Map<String, Object>>) getLastNonConfigurationInstance();
		if (list == null) {
			list = new ArrayList<Map<String, Object>>();
			// 初始化listview布局
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item1", "订单号");
			map.put("item2", "下单时间");
			map.put("item3", "服务员");
			map.put("item4", "人数");
			map.put("item5", "桌位号");
			list.add(map);
		}
		spAdapter = new SimpleAdapter(this, list,
				R.layout.pay_details_item_layout, new String[] { "item1",
						"item2", "item3", "item4", "item5" }, new int[] {
						R.id.pay_details_item1, R.id.pay_details_item2,
						R.id.pay_details_item3, R.id.pay_details_item4,
						R.id.pay_details_item5 });
		listView.setAdapter(spAdapter);
		// 添加监听器
		query.setOnClickListener(queryClickListener);
		pay.setOnClickListener(payClickListener);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}

		});
	}

	/**
	 * 用于查询按钮的 点击监听器
	 */
	OnClickListener queryClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					PayActivity.this);
			// 获得AlertDialog.Builder实例
			builder
			// 设置标题
			.setMessage("是否打印菜品清单？")

					// 设置确定按钮
					.setPositiveButton("是",
							new DialogInterface.OnClickListener() {
								// 确定按钮事件
								public void onClick(DialogInterface dialog,
										int id) {
									new QueryThread(ONLY_QUERY, true).start();
								}

							})
					.setNegativeButton("否",
							new DialogInterface.OnClickListener() {
								// 确定按钮事件
								public void onClick(DialogInterface dialog,
										int id) {
									new QueryThread(ONLY_QUERY, false).start();
								}

							});
			AlertDialog alert = builder.create();
			alert.show();

			/*
			 * // 请求服务器url String url = HttpUtil.BASE_URL +
			 * "servlet/PayServlet?id=" +
			 * LoadMoreGridViewPullToreshView.orderId; // 将返回信息在WebView中显示
			 * webView.loadUrl(url);
			 */
		}

	};
	/**
	 * 用于结算按钮的 点击监听器
	 */
	OnClickListener payClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			new QueryThread(QUERY_AND_PAY, true).start();

		}
	};
/**
 * 支付线程
 * @author John Kwok
 *
 */
	class PayThread extends Thread {

		private static final int SUCCESS = 0;

		// private static final int CONNECT_ERRO = 8;

		@Override
		public void run() {
			// 请求服务器url
			String url = HttpUtil.BASE_URL + "servlet/PayMoneyServlet?id="
					+ GlobalVariable.short_OrderId;
			// 获得查询结果
			String result = HttpUtil.queryStringForPost(url);
			if (result.equals("网络异常！")) {
				Message message = new Message();
				message.what = CONNECT_ERRO;

				handler.sendMessage(message);
			} else {
				Message message = new Message();
				message.what = SUCCESS;
				message.obj = result;
				handler.sendMessage(message);
			}
			// 显示结算结果
			// Toast.makeText(PayActivity.this, result,
			// Toast.LENGTH_LONG).show();
			// 使结算按钮失效
			// pay.setEnabled(false);
			super.run();
		}

	}

	/**
	 * 查询订单线程
	 * 
	 * @author John Kwok
	 * 
	 */
	class QueryThread extends Thread {
		private int type;
		private boolean isPrint;

		QueryThread(int type, boolean isPrint) {
			this.type = type;
			this.isPrint = isPrint;
		}

		@Override
		public void run() {
			// 清除list中的数据
			list.clear();
			// 将总金额 置零
			GlobalVariable.cost = 0.0f;
			// 访问服务器url
			String urlStr = HttpUtil.BASE_URL + "servlet/PayServlet?id="
					+ GlobalVariable.short_OrderId+"aaa"+GlobalVariable.long_OrderId+ "aaa" + isPrint;
			try {
				// 实例化URL对象
				URL url = new URL(urlStr);
				// 打开连接
				URLConnection conn = url.openConnection();
				// 获得输入流
				InputStream in = conn.getInputStream();
				// 实例化DocumentBuilderFactory
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				// 实例化DocumentBuilder
				DocumentBuilder builder = factory.newDocumentBuilder();
				// 获得Document
				doc = builder.parse(in);
				// 获得订单信息节点列表
				nl = doc.getElementsByTagName("information");
				// 在list中添加桌位信息条目
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("item1", "订单号");
				map.put("item2", "下单时间");
				map.put("item3", "服务员");
				map.put("item4", "人数");
				map.put("item5", "桌位号");
				list.add(map);

				for (int i = 0; i < nl.getLength(); i++) {
					map = new HashMap<String, Object>();
					// 订单号
					String id = doc.getElementsByTagName("id").item(i)
							.getFirstChild().getNodeValue();
					map.put("item1", id);
					// 下单时间
					String time = doc.getElementsByTagName("time").item(i)
							.getFirstChild().getNodeValue();
					map.put("item2", time);
					// 服务员
					String personname = doc.getElementsByTagName("personname")
							.item(i).getFirstChild().getNodeValue();
					map.put("item3", personname);
					// 人数
					String personnum = doc.getElementsByTagName("personnum")
							.item(i).getFirstChild().getNodeValue();
					map.put("item4", personnum);
					// 桌位id
					String tableid = doc.getElementsByTagName("tableid")
							.item(i).getFirstChild().getNodeValue();
					map.put("item5", tableid);
					// 将map加入到list中
					list.add(map);
				}
				// 在list中添加桌位信息条目
				map = new HashMap<String, Object>();
				map.put("item1", "菜名");
				map.put("item2", "单价");
				map.put("item3", "数量");
				map.put("item4", "总价");
				map.put("item5", "备注");
				list.add(map);

				// 获得菜品节点列表
				nl = doc.getElementsByTagName("dishes");
				int length = nl.getLength();
				for (int i = 0; i < nl.getLength(); i++) {
					map = new HashMap<String, Object>();
					// 菜名
					String name = doc.getElementsByTagName("name").item(i)
							.getFirstChild().getNodeValue();
					map.put("item1", name);
					// 单价
					String price = doc.getElementsByTagName("price").item(i)
							.getFirstChild().getNodeValue();
					map.put("item2", "￥" + price);
					// 数量
					String num = doc.getElementsByTagName("num").item(i)
							.getFirstChild().getNodeValue();
					map.put("item3", num);
					// 总价
					String total = doc.getElementsByTagName("total").item(i)
							.getFirstChild().getNodeValue();
					// 将菜单总计加到总金额里
					GlobalVariable.cost += Float.parseFloat(total);
					map.put("item4", "￥" + total);
					// 备注
					String remark = doc.getElementsByTagName("remark").item(i)
							.getFirstChild().getNodeValue();
					map.put("item5", remark);

					// 将map加入到list中
					list.add(map);
				}
				map = new HashMap<String, Object>();
				map.put("item1", "");
				map.put("item2", "");
				map.put("item3", "");
				map.put("item4", "总计：");
				map.put("item5", "￥" + GlobalVariable.cost);
				list.add(map);
				if (type == ONLY_QUERY) {
					Message msg = new Message();
					msg.what = UPDATE_LISTVIEW;
					handler.sendMessage(msg);
				} else if (type == QUERY_AND_PAY) {
					Message msg = new Message();
					msg.what = QUERY_AND_PAY;
					handler.sendMessage(msg);
				}

			} catch (Exception e) {
				e.printStackTrace();
				Message message = new Message();
				message.what = CONNECT_ERRO;

				handler.sendMessage(message);
			}

			super.run();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		// 判断 如果返回OK 表示支付成功 实例化PayThread线程 更改数据库
		if (resultCode == Activity.RESULT_OK) {
			new PayThread().start();
		}

	}

	/**
	 * 保存对象
	 */
	@Override
	public Object onRetainNonConfigurationInstance() {

		return list;
	}
	@Override
	protected void onDestroy() {
		System.gc();
		super.onDestroy();
	}
}
