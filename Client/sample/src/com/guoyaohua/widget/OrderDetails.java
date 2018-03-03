package com.guoyaohua.widget;

import java.util.HashMap;
import java.util.Map;

import com.guoyaohua.activity.Order;
import com.guoyaohua.activity.R;
import com.guoyaohua.activity.ViewPagerListViewActivity;
import com.guoyaohua.app.App;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderDetails extends Activity implements OnClickListener {
	private int num = 1;
	private TextView tv_detail;
	private TextView tv_price;
	private TextView tv_num;
	private ImageView iv_pic;
	private ImageButton ib_increase;
	private ImageButton ib_decrease;
	private Button bt_order;
	private Button bt_cancel;
	private String price;
	private String detail;
	private String name;
	private String url;
	private String foodId;
	private TextView tv_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_details_layout);
		// 取得从上一个Activity当中传递过来的Intent对象
		Intent intent = getIntent();
		// 从Intent当中根据key取得value
		price = intent.getStringExtra("价格");
		detail = intent.getStringExtra("介绍");
		name = intent.getStringExtra("菜名");
		url = intent.getStringExtra("图片");
		foodId = intent.getStringExtra("id");
		// 设置标题为菜品的名字
		setTitle(name);
		// 得到对象
		tv_name = (TextView) findViewById(R.id.tv_name_order_details_layout);
		tv_detail = (TextView) findViewById(R.id.tv_details);
		tv_price = (TextView) findViewById(R.id.price);
		tv_num = (TextView) findViewById(R.id.tv_num);
		iv_pic = (ImageView) findViewById(R.id.iv_pic);
		ib_increase = (ImageButton) findViewById(R.id.increase);
		ib_decrease = (ImageButton) findViewById(R.id.decrease);
		bt_order = (Button) findViewById(R.id.order);
		bt_cancel = (Button) findViewById(R.id.cancel);

		// 给按钮绑定监听器
		bt_order.setOnClickListener(this);
		bt_cancel.setOnClickListener(this);
		ib_increase.setOnClickListener(this);
		ib_decrease.setOnClickListener(this);
		// 设定值
		tv_name.setText(name);
		tv_detail.setText(detail);
		tv_price.setText("价格：￥ " + price);
		tv_num.setText(num + "");
		App.getIns().display(url, iv_pic, R.drawable.icon);

	}

	@Override
	public void onClick(View v) {
		if (v == bt_cancel) {
			// 取消，返回点菜界面 ，结束该activity
			finish();
		} else if (v == bt_order) {
			// 加入购物车，吧信息加入到购物车静态成员list集合中，用于构建listview，提示已加入购物车，关闭该界面，返回点菜界面
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("image", url);
			map.put("price", price);
		//	map.put("number", num);
			map.put("id", foodId);
			//判断购物车里是否含有同菜名的菜品
			boolean isExist = true;
			//此处设置为遍历list  判断其中是否有重名的菜品，如果有则数量加1 如果没有  则加入map
			for(int i = 0 ;i<Order.list.size();i++){
				String foodName = (String) Order.list.get(i).get("name");
				if(name.equals(foodName)){
					int foodNum = (Integer) Order.list.get(i).get("number");
				
					foodNum++;
					map.put("number", foodNum);
					Order.list.remove(i);	
					isExist = false;
					break;
				}
			}
			if(isExist == true){
				map.put("number", num);
			}
			
			Order.list.add(map);
			//为什么一加上toast就报错？
//			Toast.makeText(this, "已经加入订单", Toast.LENGTH_SHORT).show();
//			Intent intent = new Intent(this, ViewPagerListViewActivity.class);
//			startActivity(intent);
			finish();
		} else if (v == ib_increase) {
			// 点击增加按钮响应的事件
			num++;
			tv_num.setText(num + "");
		} else if (v == ib_decrease) {
			// 点击减少按钮响应的事件
			if (num > 1)
				{num--;
			tv_num.setText(num + "");}
		}

	}



}
