package com.guoyaohua.widget;


import com.guoyaohua.activity.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
/**
* @类名:LoadMorePullToreshView 
* @描述:TODO(加载更多和下拉刷新的View) 
* @作者:guoyaohua 
* 
 */
public class LoadMorePullToreshView  extends LinearLayout{
	private PullToRefreshView pullToRefreshView=null;
	private LoadMoreListView loadMoreListView=null;
	public PullToRefreshView getPullToRefreshView() {
		return pullToRefreshView;
	}

	public void setPullToRefreshView(PullToRefreshView pullToRefreshView) {
		this.pullToRefreshView = pullToRefreshView;
	}
	/**
	 * 获取加载更多的空间信息
	 * @return
	 */
	public LoadMoreListView getLoadMoreListView() {
		return loadMoreListView;
	}

	public void setLoadMoreListView(LoadMoreListView loadMoreListView) {
		this.loadMoreListView = loadMoreListView;
	}
	Context mContext=null;
		public LoadMorePullToreshView(Context context) {
			super(context);
			mContext=context;
		}
		
	public LoadMorePullToreshView(Context context,AttributeSet attributeSet) {
		super(context,attributeSet);
		mContext=context;
		initWidget(context);
	}
	/**
	 * 初始化布局
	 * @param mcontext
	 */
	public void initWidget(Context mcontext) {
	    LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.list_loadmore_pulltorefresh, null);
		//添加布局参数
		this.addView(layout,new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		pullToRefreshView=(PullToRefreshView) findViewById(R.id.main_pull_refresh_view);
		loadMoreListView=(LoadMoreListView) findViewById(R.id.lvnewsdata);
	}
	/**
	 * 设置listView分割线资源
	 * @param ResId 为0的情况下取消分割线 
	 */
	public void setListViewDriverLine(int ResId){
		if(ResId==0){
			loadMoreListView.setDivider(null);
		}else{
			Bitmap srcName= BitmapFactory.decodeResource(getResources(),ResId);  
			BitmapDrawable is=new BitmapDrawable(srcName);
			loadMoreListView.setDivider(is);
		}
	}
}
