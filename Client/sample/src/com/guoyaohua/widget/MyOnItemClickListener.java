package com.guoyaohua.widget;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.guoyaohua.activity.Login;
import com.guoyaohua.activity.MainMenu;
import com.guoyaohua.activity.Order;
import com.guoyaohua.activity.R;
import com.guoyaohua.activity.ViewPagerListViewActivity2;
import com.guoyaohua.entity.GlobalVariable;
import com.guoyaohua.util.HttpUtil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;

/**
 * 绑定在选桌activity的OnItemClickListener
 * 
 * @author John Kwok
 * 
 */
@SuppressLint("NewApi")
public class MyOnItemClickListener implements OnItemClickListener {
	// 管理其他桌位
	int CHANGE = 0;
	// 开桌
	int START = 1;

	@SuppressLint("NewApi")
	@Override
	public void onItemClick(AdapterView<?> parent, final View view,
			int position, long id) {
		// 响应点击事件
		final int tableId = position + 1;
		// 创建静态handler用于接收桌位改变信息
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				// change
				case 0:
					String result = (String) msg.obj;
					String[] res = result.split(" "); // res[0]为长订单号   res[1]为短订单号    res[2]为桌位描述
					Toast.makeText(view.getContext(),
							"已成功更换为管理" + res[2] + "餐桌", Toast.LENGTH_SHORT)
							.show();
					MainMenu.state.setText("您当前管理的餐桌为:" + res[2] + "  订单号为:"
							+ res[0]);
					GlobalVariable.tableName = res[2];
					break;
				// start table
				case 1:
					String result2 = (String) msg.obj;
					String[] res2 = result2.split(" "); // res[0]为长订单号   res[1]为短订单号    res[2]为桌位描述
					Toast.makeText(view.getContext(),
							"开桌成功！ 订单号为:" + res2[0] + "  右滑开始点菜",
							Toast.LENGTH_SHORT).show();
					MainMenu.state.setText("您当前管理的餐桌为：" + res2[2] + "  订单号为"
							+ res2[0]);
					GlobalVariable.tableName = res2[2];
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}

		};
		// 判断购物车中是否有菜品没提交
		if (!Order.list.isEmpty()) {
			//
			Toast.makeText(view.getContext(), "您购物车中还有未下单的菜品，请先处理。",
					Toast.LENGTH_SHORT).show();
			return;
		}

		// 判断是否有人
		TextView tv_num = (TextView) view.findViewById(R.id.tv_personNum);
		final TextView tv_description = (TextView) view
				.findViewById(R.id.tvIndexItemValue);
		if (tv_num.getText().equals("空位")) {
			/**
			 * 桌位为空时
			 */
			// 获得LayoutInflater实例
			LayoutInflater inflater = LayoutInflater.from(view.getContext());
			// 实例化在弹出对话框中添加的视图
			final View v = inflater.inflate(
					R.layout.table_click_alertdialog_layout, null);
			// 获得视图中的NumberPicker对象
			final NumberPicker personNum = (NumberPicker) v
					.findViewById(R.id.np_personnum);
			// 设定最大最小值
			personNum.setMinValue(0);
			personNum.setMaxValue(30);

			AlertDialog.Builder builder = new AlertDialog.Builder(
					new ContextThemeWrapper(view.getContext(), R.style.dialog));
			// 获得AlertDialog.Builder实例
			// AlertDialog.Builder builder = new AlertDialog.Builder(
			// view.getContext());
			builder
			// 设置标题
			.setMessage("您是否确定开一个新桌？")
					.setView(v)
					// 设置确定按钮
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								// 确定按钮事件
								public void onClick(DialogInterface dialog,
										int id) {
									// 此处应该加入一个判断语句，获取textview中的数据 如果为无人状态 则执行开桌
									// 如果为有人状态则提示是否加菜或者退桌，
									// 若选择加菜功能 则更改全局变量 tableId 然后清空购物车集合
									// 从新点菜加入购物车，，，
									// 当用户在执行付款时
									// 程序会从服务器调取订单信息，吧该订单的信息加载到listviev中并显示总价，
									// 当用户付款结束后跳转到 评价栏目。提交评价，更改该桌位的状态即可
									/**
									 * 经验证 在布局中加入隐藏textview存储该桌位此时的订单号行不通
									 * 以为每次刷新界面时都会重新构造item 存储的OrderId会丢失 所以
									 * 应该修改数据库 在tablebl单中加入一个OrderId标签，
									 * 在选择桌位时自动保存ID到该标签 当服务员管理餐桌时
									 * 点击已有人的餐桌会自动获取与该桌位绑定的OrderId 并存储到静态变量中
									 * 用于管理该订单
									 */
									// String personNumber =
									// personNum.getValue()+"";
									new Thread() {

										@Override
										public void run() {
											Date date = new Date();
											SimpleDateFormat sdf = new SimpleDateFormat(
													"yy-MM-dd hh:mm");
											// 开桌时间
											String orderTime = sdf.format(date);
											// 请求参数列表
											List<NameValuePair> params = new ArrayList<NameValuePair>();
											// 添加请求参数
											params.add(new BasicNameValuePair(
													"type", "1"));
											params.add(new BasicNameValuePair(
													"orderTime", orderTime));
											// 此处应该的到当前登录用户名的id
											// 由登录时赋值给全局变量serverid
											params.add(new BasicNameValuePair(
													"userId", Login.userId));
											params.add(new BasicNameValuePair(
													"tableId", tableId + ""));
											params.add(new BasicNameValuePair(
													"personNum", personNum
															.getValue() + ""));
											UrlEncodedFormEntity entity1 = null;
											try {
												entity1 = new UrlEncodedFormEntity(
														params, HTTP.UTF_8);
											} catch (UnsupportedEncodingException e) {
												e.printStackTrace();
											}
											// 请求服务器url
											String url = HttpUtil.BASE_URL
													+ "servlet/StartTableServlet";
											// 获得请求对象HttpPost
											HttpPost request = HttpUtil
													.getHttpPost(url);
											// 设置查询参数
											request.setEntity(entity1);
											// 获得响应结果
											String result = HttpUtil
													.queryStringForPost(request);
											//orderid[0]为长订单号    orderID【1】为短订单号
											String[] orderId = result.split(" ");
											
											// 将开桌生成的订单Id，赋值给全局orderId
											GlobalVariable.long_OrderId = orderId[0];
											GlobalVariable.short_OrderId = orderId[1];

											Message msg = new Message();
											msg.obj = result + " "
													+ tv_description.getText();

											msg.what = START;
											handler.sendMessage(msg);
											// 此处应该更改桌位状态，变为有人，应该在服务端增加该功能

											super.run();
										}

									}.start();

								}
							}).setNegativeButton("取消", null);
			AlertDialog alert = builder.create();
			alert.show();
		} else {
			/**
			 * 桌位不为空时
			 */
			// 此处设置为将orderID变更为 该桌位的订单id，需要在GridItem中加入个隐藏的TextView
			// 用于存储orderID信息
			AlertDialog.Builder builder = new AlertDialog.Builder(
					view.getContext());
			builder.setMessage("您是否要切换管理该桌位？")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									new Thread() {

										@Override
										public void run() {

											// 请求参数列表
											List<NameValuePair> params = new ArrayList<NameValuePair>();
											// 添加请求参数,获取orderid
											params.add(new BasicNameValuePair(
													"type", "2"));

											params.add(new BasicNameValuePair(
													"tableId", tableId + ""));

											UrlEncodedFormEntity entity1 = null;
											try {
												entity1 = new UrlEncodedFormEntity(
														params, HTTP.UTF_8);
											} catch (UnsupportedEncodingException e) {
												e.printStackTrace();
											}
											// 请求服务器url
											String url = HttpUtil.BASE_URL
													+ "servlet/StartTableServlet";
											// 获得请求对象HttpPost
											HttpPost request = HttpUtil
													.getHttpPost(url);
											// 设置查询参数
											request.setEntity(entity1);
											// 获得响应结果
											String result = HttpUtil
													.queryStringForPost(request);
											//order[0] longid   order[1] orderid
											String[] orderId = result.split(" ");
											// 将开桌生成的订单Id，赋值给全局orderId
											GlobalVariable.long_OrderId = orderId[0];
											GlobalVariable.short_OrderId = orderId[1];

											Message msg = new Message();
											msg.obj = result + " "
													+ tv_description.getText();
											msg.what = CHANGE;
											handler.sendMessage(msg);
											// 此处应该更改桌位状态，变为有人，应该在服务端增加该功能
											System.out.println("以切换至orderId"
													+ result);
											super.run();
										}

									}.start();
								}
							}).setNegativeButton("取消", null);
			AlertDialog alert = builder.create();
			alert.show();

		}
	}

	// class OrderTableThread extends Thread {
	//
	// @Override
	// public void run() {
	// super.run();
	// Date date = new Date();
	// SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm");
	// // 开桌时间
	// String orderTime = sdf.format(date);
	// // 请求参数列表
	// List<NameValuePair> params = new ArrayList<NameValuePair>();
	// // 添加请求参数
	// params.add(new BasicNameValuePair("orderTime", orderTime));
	// params.add(new BasicNameValuePair("userId", "1"));
	// params.add(new BasicNameValuePair("tableId", "23"));
	// params.add(new BasicNameValuePair("personNum", "33"));
	// UrlEncodedFormEntity entity1 = null;
	// try {
	// entity1 = new UrlEncodedFormEntity(params, HTTP.UTF_8);
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// // 请求服务器url
	// String url = HttpUtil.BASE_URL + "servlet/StartTableServlet";
	// // 获得请求对象HttpPost
	// HttpPost request = HttpUtil.getHttpPost(url);
	// // 设置查询参数
	// request.setEntity(entity1);
	// // 获得响应结果
	// String result = HttpUtil.queryStringForPost(request);
	// // 将开桌生成的订单Id，赋值给全局orderId
	// LoadMoreGridViewPullToreshView.orderId = result;
	//
	// Message msg = new Message();
	// msg.obj = result;
	// MyOnItemClickListener.handler.sendMessage(msg);
	// //此处应该更改桌位状态，变为有人，应该在服务端增加该功能
	//
	//
	//
	//
	// }
	//
	// }

}
