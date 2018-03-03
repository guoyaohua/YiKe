package com.guoyaohua.activity;

import java.util.Date;

import com.guoyaohua.activity.R;
import com.guoyaohua.entity.GlobalVariable;
import com.guoyaohua.widget.LoadMoreGridViewPullToreshView;
import com.wanpu.pay.PayConnect;

//import com.amaker.wlo.LoginActivity;
//import com.amaker.wlo.LogoutActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends Activity implements OnClickListener {

	private static final int MENU_1 = 1;
	private static final int MENU_2 = 2;
	private static final int MENU_3 = 3;
	private static final int MENU_4 = 4;
	// 声明变量 用于实现双击back按键退出Activity
	// 上一次啊点击back的时间
	private long mLastBackTime = 0;
	// 两次点击的事件间隔
	private long TIME_DELAY = 2 * 1000;
	public static TextView state;
	private Button table;
	private Button order;
	private Button orderdetail;
	private Button feedback;
	private Button pay;
	private Button logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu_layout);
		// 找到组件
		state = (TextView) findViewById(R.id.tv_mm_state);

		table = (Button) findViewById(R.id.bt_mm_table);
		order = (Button) findViewById(R.id.bt_mm_order);
		orderdetail = (Button) findViewById(R.id.bt_mm_orderdetail);
		feedback = (Button) findViewById(R.id.bt_mm_feedback);
		pay = (Button) findViewById(R.id.bt_mm_pay);
		logout = (Button) findViewById(R.id.bt_mm_logout);
		// 绑定点击监听器
		table.setOnClickListener(this);
		order.setOnClickListener(this);
		orderdetail.setOnClickListener(this);
		feedback.setOnClickListener(this);
		pay.setOnClickListener(this);
		logout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		if (v == table) {
			// 进入选桌界面
			Intent intent = new Intent(this, ViewPagerListViewActivity2.class);
			startActivity(intent);
		} else if (v == order) {
			// 进入点菜界面
			if (GlobalVariable.short_OrderId == null) {
				Toast.makeText(this, "您还没有选择桌位，请先选择桌位。", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			Intent intent = new Intent(this, ViewPagerListViewActivity.class);
			startActivity(intent);
		} else if (v == orderdetail) {
			// 进入购物车界面
			if (Order.list.isEmpty()) {
				Toast.makeText(this, "您还没有点菜，请先点菜。", Toast.LENGTH_SHORT).show();
			} else {
				Intent intent = new Intent(this, Order.class);
				startActivity(intent);
			}

		} else if (v == feedback) {
			// 进入反馈界面
			if (GlobalVariable.short_OrderId == null) {
				Toast.makeText(this, "您还没有选择桌位，请先选择桌位。", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			Intent intent = new Intent(this, FeedBackActivity.class);
			startActivity(intent);
		} else if (v == pay) {
			// 进入结账界面
			if (GlobalVariable.short_OrderId == null) {
				Toast.makeText(this, "您还没有选择桌位，请先选择桌位。", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			Intent intent = new Intent(this, PayActivity.class);
			startActivity(intent);
		} else if (v == logout) {
			// // 注销登录 返回登录界面
			// Intent intent = new Intent();
			// intent.setClass(this, Login.class);
			// startActivity(intent);
			// // 关闭自身activity
			// finish();
			Intent intent = new Intent();
			intent.setClass(this, RealVedioActivity.class);
			startActivity(intent);

		}
	}

	/**
	 * 复写onKeyDown方法 监听back点击事件，当点击一次back时不会退出，点击两次时才会退出
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long now = new Date().getTime();
			if (now - mLastBackTime < TIME_DELAY) {
				System.exit(0);
				return super.onKeyDown(keyCode, event);

			} else {
				// 将现在时刻设置为最后一次点击时间
				mLastBackTime = now;
				Toast.makeText(this, "再次点击将退出", Toast.LENGTH_LONG).show();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 对于菜单id的要求，必须是一个常量。故在定义菜单id时需要用到final关键字

		menu.add(0, MENU_1, 2, "注销");
		menu.add(0, MENU_3, 0, "帮助");
		menu.add(0, MENU_4, 1, "关于");
		menu.add(0, MENU_2, 3, "退出");

		// //可用setIcon方法来设置菜单图标

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case MENU_1:
			logOut();
			break;
		case MENU_2:
			// finish();
			System.exit(0);
			break;
		case MENU_3:
			startActivity(new Intent(getApplication(), GuideActivity.class));
			break;
		case MENU_4:
			startActivity(new Intent(getApplication(), AboutUsActivity.class));
			break;
		default:
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	private void logOut() {
		// 注销登录 返回登录界面
		Intent intent = new Intent();
		intent.setClass(this, Login.class);
		startActivity(intent);
		// 关闭自身activity
		finish();

	}

	@Override
	protected void onDestroy() {
		// 以前版本的finalize()方法作废
		PayConnect.getInstance(this).close();

		// 垃圾回收
		View view = findViewById(R.id.mainmenu_layout_linearLayout);
		view.setBackgroundResource(0);
		view = findViewById(R.id.bt_mm_table);
		view.setBackgroundResource(0);
		view = findViewById(R.id.bt_mm_feedback);
		view.setBackgroundResource(0);
		view = findViewById(R.id.bt_mm_logout);
		view.setBackgroundResource(0);
		view = findViewById(R.id.bt_mm_order);
		view.setBackgroundResource(0);
		view = findViewById(R.id.bt_mm_orderdetail);
		view.setBackgroundResource(0);
		view = findViewById(R.id.bt_mm_pay);
		view.setBackgroundResource(0);
		System.gc();

		super.onDestroy();
	}

	// 怎样解决在使用时 锁屏 对软件造成的影响 有的activity会刷新，
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// Nothing need to be done here

		} else {
			// Nothing need to be done here
		}
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		state.setText(savedInstanceState.getString("state"));
		super.onRestoreInstanceState(savedInstanceState);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("state", state.getText().toString());
		super.onSaveInstanceState(outState);

	}

}