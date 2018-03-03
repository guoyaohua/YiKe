package com.guoyaohua.activity;

import java.util.Date;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.guoyaohua.activity.R;
import com.guoyaohua.adapter.MyBaseAdapter;
import com.guoyaohua.widget.TouchViewPager;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @类名:ViewPagerListViewActivity
 * @描述:点菜界面
 * @作者:guoyaohua
 * @时间 2013-7-1 上午11:23:33
 */
public class ViewPagerListViewActivity extends FragmentActivity {

	private PopupWindow popupWindow;

	PowerManager.WakeLock wakeLock;

	public static final String[] CONTENT = new String[] { "今日推荐", "凉菜", "热菜",
			"主食", "甜品", "汤", "水果拼盘", "酒水饮料" };
	private int touchSlop;
	TouchViewPager pager = null;
	FragmentPagerAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_tabs);
		// 保持屏幕常亮
		wakeLock = ((PowerManager) getSystemService(POWER_SERVICE))
				.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
						| PowerManager.ON_AFTER_RELEASE, "WakeLockActivity");

		adapter = new GoogleMusicAdapter(getSupportFragmentManager());
		pager = (TouchViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		onTouch();

		Handler handler = new Handler();
		 handler.postDelayed(new Runnable(){

			@Override
			public void run() {
				startPopupwin();
			}
			 
		 }, 500);
		
		
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// // pageTitlePosition = position;
			// if ("美图".equals(CONTENT[position])) {
			// return GridContentActivity.newInstance(CONTENT[position]);
			// } else {
			return GridContentActivity2.newInstance(CONTENT[position]);
			// }
		}

		@Override
		public CharSequence getPageTitle(int position) {

			return CONTENT[position % CONTENT.length];
		}

		@Override
		public int getCount() {
			return CONTENT.length;
		}
	}

	public void onTouch() {
		touchSlop = ViewConfiguration.get(ViewPagerListViewActivity.this)
				.getScaledTouchSlop();
		pager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					float downX = pager.getDownX();
					float lastX = event.getX();
					float disX = Math.abs(lastX - downX);
					if (touchSlop < disX && lastX > downX
							&& pager.getCurrentItem() == 0) {

						// 跳转回mainmenuactivity
						// Toast.makeText(ViewPagerListViewActivity.this,
						// "返回主菜单",
						// Toast.LENGTH_SHORT).show();
						// Intent intent = new Intent();
						// intent.setClass(ViewPagerListViewActivity.this,
						// MainMenu.class);
						// startActivity(intent);
						ViewPagerListViewActivity.this.finish();
					} else if (touchSlop < disX && lastX < downX
							&& pager.getCurrentItem() == adapter.getCount() - 1) {
						// 跳转到评论activity
						Toast.makeText(ViewPagerListViewActivity.this,
								"已加载全部类别", Toast.LENGTH_SHORT).show();
					}

					break;

				}

				return false;
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (wakeLock != null) {
			wakeLock.release();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		wakeLock.acquire();
	}

	private void startPopupwin() {
		View v = View.inflate(this, R.layout.popwindow_layout, null);
		// 创建AlertDialog
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setView(v);

		Button button1 = (Button) v.findViewById(R.id.bt_popwindow);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.cancel();
			}
		});

		dialog.show();

	}

}
