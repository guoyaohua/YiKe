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

//import com.amaker.wlo.OrderActivity;

import com.guoyaohua.activity.R;
import com.guoyaohua.util.HttpUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @类名:LoadMorePullToreshView
 * @描述:TODO(加载更多和下拉刷新的View)
 * @作者:guoyaohua
 * 
 * orderId全局变量
 */
public class LoadMoreGridViewPullToreshView extends LinearLayout {
	private PullToRefreshView pullToRefreshView = null;
	private GridView gridView = null;
	//public static String orderId = null;//订单号

	public GridView getGridView() {
		return gridView;
	}

	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}

	public PullToRefreshView getPullToRefreshView() {
		return pullToRefreshView;
	}

	public void setPullToRefreshView(PullToRefreshView pullToRefreshView) {
		this.pullToRefreshView = pullToRefreshView;
	}

	Context mContext = null;

	public LoadMoreGridViewPullToreshView(Context context) {
		super(context);
		mContext = context;
	}

	public LoadMoreGridViewPullToreshView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		mContext = context;
		initWidget(context);
	}

	/**
	 * 初始化布局
	 * 
	 * @param mcontext
	 */
	public void initWidget(Context mcontext) {
		LayoutInflater inflater = (LayoutInflater) mcontext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.grid_loadmore_pulltorefresh,
				null);
		// 添加布局参数
		this.addView(layout, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		pullToRefreshView = (PullToRefreshView) findViewById(R.id.main_pull_refresh_view);
		gridView = (GridView) findViewById(R.id.gvIndexData);
		gridView.setOnItemClickListener(new MyOnItemClickListener());
		// 设置周围没有
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
	}
}
