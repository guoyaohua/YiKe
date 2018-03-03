package com.guoyaohua.activity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.guoyaohua.activity.R;
import com.guoyaohua.adapter.OrderIndexAdapter;
import com.guoyaohua.entity.GlobalVariable;
import com.guoyaohua.util.HttpUtil;
import com.guoyaohua.widget.LoadMoreGridViewPullToreshView;
//import com.amaker.wlo.OrderActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 购物车activity 显示以点菜单。
 * 
 * @author John Kwok
 * 
 */
public class Order extends Activity implements OnClickListener {
	private static final int SUCCESS = 0;
	private static final int WRONG = 1;
	private TextView tv_num;
	private TextView tv_cost;
	private Button bt_submitOrder;
	private Button bt_backToOrder;
	private ImageButton ib_decrease;
	private ImageButton ib_increase;
	private Button bt_deleteItem;
	public static OrderIndexAdapter adapter;
	private ListView lv_orderList;
	private double allprice = 0;
	private int allnum = 0;
	private boolean isClear = false;
	// 静态变量list 存储以点菜的信息 用于构造lv_orderList ListView
	public static List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// 声明Handler
	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SUCCESS:
//				Toast.makeText(Order.this, "已经成功下单", Toast.LENGTH_SHORT).show();
				isClear = true;
				OrderIndexAdapter.isSubmit = true;
				bt_submitOrder.setEnabled(false);
				Order.adapter.notifyDataSetChanged();
				//删除表中数据
				list.clear();
				Order.adapter.notifyDataSetChanged();
				Toast.makeText(Order.this, "已经成功下单", Toast.LENGTH_SHORT).show();
				break;
			case WRONG:
				Toast.makeText(Order.this, "对不起，服务器连接错误，请重试",
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_layout);
		// 得到控件
		tv_num = (TextView) findViewById(R.id.tv_num);
		tv_cost = (TextView) findViewById(R.id.tv_cost);
		bt_submitOrder = (Button) findViewById(R.id.bt_submitOrder);
		bt_backToOrder = (Button) findViewById(R.id.bt_backToOrder);
		lv_orderList = (ListView) findViewById(R.id.lv_orderList);
		ib_decrease = (ImageButton) findViewById(R.id.ib_decrease);
		ib_increase = (ImageButton) findViewById(R.id.ib_increase);
		bt_deleteItem = (Button) findViewById(R.id.bt_deleteItem);

		// 给按钮绑定监听器
		bt_submitOrder.setOnClickListener(this);
		bt_backToOrder.setOnClickListener(this);
		// 设置listitem 测试用
		// Map<String, Object> map1 = new HashMap<String, Object>();
		// map1.put("name", "土豆丝");
		// map1.put("image", R.drawable.youren);
		// map1.put("price", "单价:100￥");
		// map1.put("number", (int)6);
		//
		// Map<String, Object> map2 = new HashMap<String, Object>();
		// map2.put("name", "土豆丝");
		// map2.put("image", R.drawable.youren);
		// map2.put("price", "单价:100￥");
		// map2.put("number", 5);
		//
		// Map<String, Object> map3 = new HashMap<String, Object>();
		// map3.put("name", "土豆丝");
		// map3.put("image", R.drawable.youren);
		// map3.put("price", "单价:100￥");
		// map3.put("number", 4);
		//
		// Map<String, Object> map4 = new HashMap<String, Object>();
		// map4.put("name", "土豆丝");
		// map4.put("image", R.drawable.youren);
		// map4.put("price", "单价:100￥");
		// map4.put("number", 3);
		//
		// Map<String, Object> map5 = new HashMap<String, Object>();
		// map5.put("name", "土豆丝");
		// map5.put("image", R.drawable.youren);
		// map5.put("price", "单价:100￥");
		// map5.put("number", 2);
		//
		// Map<String, Object> map6 = new HashMap<String, Object>();
		// map6.put("name", "土豆丝");
		// map6.put("image", R.drawable.youren);
		// map6.put("price", "单价:100￥");
		// map6.put("number", 1);
		//
		// list.add(map1);
		// list.add(map2);
		// list.add(map3);
		// list.add(map4);
		// list.add(map5);
		// list.add(map6);
		//
		//
		// 为listview设置适配器
		lv_orderList.setAdapter(adapter = new OrderIndexAdapter(this));

		setNumAndCost();

		// View view = lv_orderList.inflate(this, R.layout.oder_layout_item,
		// null);
		// lv_orderList.setAdapter(new SimpleAdapter(Order.this, list,
		// R.layout.oder_layout_item, new String[] { "image", "name",
		// "price", "number" }, new int[] {
		// net.xinhua.activity.R.id.iv_icon,
		// net.xinhua.activity.R.id.tv_name,
		// net.xinhua.activity.R.id.tv_price,
		// net.xinhua.activity.R.id.tv_number }));
		// 为控件添加点击监听器
		// myOnClickListener listener = new myOnClickListener();
		// bt_submitOrder.setOnClickListener(listener);
		// bt_backToOrder.setOnClickListener(listener);
		// ib_decrease.setOnClickListener(listener);
		// ib_increase.setOnClickListener(listener);
		// bt_deleteItem.setOnClickListener(listener);
	}

	/**
	 * 遍历list集合 得到其中所有菜的价格 和数量 显示在购物车右端
	 */

	public void setNumAndCost() {

		// 确保遍历前总数与cost都为零 否则容易加重
		allnum = 0;
		allprice = 0;

		for (int i = 0; i < list.size(); i++) {
			int num = (Integer) list.get(i).get("number");
			double price = Double
					.parseDouble((String) list.get(i).get("price"));
			allnum += num;
			allprice += (num * price);
		}
		tv_num.setText("本次共点菜：" + allnum + "个");
		tv_cost.setText("本次共消费：￥" + allprice);

	}

	@Override
	public void onClick(View v) {
		if (v == bt_submitOrder) {
			// 提交数据到服务器
			new SubmitThread().start();

		} else if (v == bt_backToOrder) {
			// 返回点菜
			Intent intent = new Intent(this, MainMenu.class);
			startActivity(intent);
			finish();
		}

	}

	class SubmitThread extends Thread {

		@Override
		public void run() {
			// 遍历点菜列表
			for (int i = 0; i < list.size(); i++) {

				// 获得其中点菜map
				Map map = (Map) list.get(i);
				// 获得点菜项
				/**
				 * 此处需要修改下数据库 删除remark标签，修改menuid为菜名 新增tableid标签，新增时间标签
				 */
				String menuId = map.get("id") + "";
				String num = map.get("number") + "";
				String remark = GlobalVariable.tableName;
				String myOrderId = GlobalVariable.short_OrderId;
                String longOrderId = GlobalVariable.long_OrderId;
				// 封装到请求参数中
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// 添加到请求参数中
				params.add(new BasicNameValuePair("menuId", menuId));
				params.add(new BasicNameValuePair("orderId", myOrderId));
				params.add(new BasicNameValuePair("num", num));
				params.add(new BasicNameValuePair("remark", remark));
				params.add(new BasicNameValuePair("longOrderId", longOrderId));
				// 判断是否是最后一道菜
				if (i == list.size() - 1) {
					params.add(new BasicNameValuePair("isLast", "yes"));
				} else {
					params.add(new BasicNameValuePair("isLast", "no"));
				}

				UrlEncodedFormEntity entity1 = null;
				try {
					entity1 = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = WRONG;
					handler.sendMessage(msg);
				}

				// 请求服务器Servlet的url
				String url = HttpUtil.BASE_URL + "servlet/OrderDetailServlet";
				// 获得HttpPost对象
				HttpPost request = HttpUtil.getHttpPost(url);
				// 为请求设置参数
				request.setEntity(entity1);
				// 获得返回结果
				String result = HttpUtil.queryStringForPost(request);
				// 当提交完最后一道菜时才发送message给handler

				if (i == list.size() - 1) {
					if (result.equals("网络异常！")) {
						Message msg = new Message();
						msg.what = WRONG;
						handler.sendMessage(msg);
					} else {
						Message msg = new Message();
						msg.what = SUCCESS;
						msg.obj = result;
						handler.sendMessage(msg);
					}
				}
				super.run();
			}
		}
	}

	@Override
	protected void onDestroy() {
		if (isClear == true) {
			list.clear();
		}
		OrderIndexAdapter.isSubmit = false;
		System.gc();
		super.onDestroy();
	}

}

// class myOnClickListener implements OnClickListener{
// @Override
// public void onClick(View v) {
// Toast.makeText(v.getContext(), "submitOrder", 1).show();
//
// switch (v.getId()) {
// case R.id.bt_submitOrder:
// System.out.println("bt_submitOrder");
// break;
//
// case R.id.bt_backToOrder:
// System.out.println("bt_backToOrder");
// //Toast.makeText(this, "bt_backToOrder", 1).show();
// break;
// case R.id.ib_decrease:
// System.out.println("bt_backToOrder");
// //Toast.makeText(this, "-", 1).show();
// break;
// case R.id.ib_increase:
// System.out.println("bt_backToOrder");
// //Toast.makeText(this, "+", 1).show();
// break;
// case R.id.bt_deleteItem:
// System.out.println("bt_backToOrder");
// //Toast.makeText(this, "删除", 1).show();
// break;
// default:
// break;
// }
// }
// }