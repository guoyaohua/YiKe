package com.guoyaohua.widget;

import com.guoyaohua.activity.ViewPagerListViewActivity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
public class TouchViewPager extends ViewPager {
	private float downX = 0;
	public TouchViewPager(Context context) {
		super(context);
	}
	
	public TouchViewPager(Context context,AttributeSet attributeSet) 
	{
		super(context,attributeSet);
//		ViewPagerListViewActivity.pageTitlePosition=this.getCurrentItem();
	}



	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			if(ev.getAction() == MotionEvent.ACTION_DOWN)
			{
				downX = ev.getX();
			}
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	/**
	 * 悲剧啊，由于进行了事件拦截，子View没有将Touch事件传到ViewPage，所以ViewPage.onTouch()中Action_down的时候拿不到坐标
	 * 只能这么做了
	 * @return
	 */
	public float getDownX() {
		return downX;
	}
	
}
