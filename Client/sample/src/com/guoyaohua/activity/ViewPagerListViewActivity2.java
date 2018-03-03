package com.guoyaohua.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.guoyaohua.activity.R;
import com.guoyaohua.adapter.MyBaseAdapter;
import com.guoyaohua.entity.GlobalVariable;
import com.guoyaohua.widget.LoadMoreGridViewPullToreshView;
import com.guoyaohua.widget.TouchViewPager;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @类名:ViewPagerListViewActivity
 * @描述:选桌界面
 * @作者:guoyaohua
 * @时间 2013-7-1 上午11:23:33
 */
public class ViewPagerListViewActivity2 extends FragmentActivity {

	PowerManager.WakeLock wakeLock;

	private static final int ORDER_LIST_IS_NOT_EMPTY = 0;
	public static final String[] CONTENT = new String[] { "餐桌管理" };
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
						| PowerManager.ON_AFTER_RELEASE, "WakeLockActivity2");

		adapter = new GoogleMusicAdapter(getSupportFragmentManager());
		pager = (TouchViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		onTouch();
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// pageTitlePosition = position;
			if ("餐桌管理".equals(CONTENT[position])) {
				return GridContentActivity.newInstance(CONTENT[position]);
			} else {
				return ContentActivity.newInstance(CONTENT[position]);
			}
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
		touchSlop = ViewConfiguration.get(ViewPagerListViewActivity2.this)
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
//						Toast.makeText(ViewPagerListViewActivity2.this,
//								"返回主菜单", Toast.LENGTH_SHORT).show();
//						Intent intent = new Intent();
//						intent.setClass(ViewPagerListViewActivity2.this,
//								MainMenu.class);
//						startActivity(intent);
						ViewPagerListViewActivity2.this.finish();
					} else if (touchSlop < disX && lastX < downX
							&& pager.getCurrentItem() == adapter.getCount() - 1) {
						// // 跳转到评论activity
						// Toast.makeText(ViewPagerListViewActivity2.this,
						// "右滑动进入点菜", Toast.LENGTH_SHORT).show();
						// Intent intent = new Intent();
						// intent.setClass(ViewPagerListViewActivity2.this,
						// ViewPagerListViewActivity.class);
						// startActivity(intent);
						// 进入点菜界面
						if (GlobalVariable.short_OrderId == null) {
							Toast.makeText(ViewPagerListViewActivity2.this,
									"您还没有选择桌位，请先选择桌位。", Toast.LENGTH_SHORT)
									.show();

						} else {
							Toast.makeText(ViewPagerListViewActivity2.this,
									 "右滑动进入点菜", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(
									ViewPagerListViewActivity2.this,
									ViewPagerListViewActivity.class);
							startActivity(intent);
							ViewPagerListViewActivity2.this.finish();
						}
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

}
