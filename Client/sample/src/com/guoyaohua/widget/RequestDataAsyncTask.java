package com.guoyaohua.widget;


import java.util.List;

import com.guoyaohua.activity.R;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
* @类名:RequestDataAsyncTask 
* @描述:TODO(数据异步加载和无数据时候界面展示等) 
* @作者:guoyaohua 
* 
 */
public class RequestDataAsyncTask {
	  //每页显示的条数
	  public static final int PAGESIZE = 20;
	  private int currentPage = 0;			  //加载当前页面
	  private PullToRefreshView pullToRefreshView;//下拉刷新的数据
	  private Context mContext;
	  private ICallBackAsyncTaskLister callBackAsyncTaskLister;
	  private RequestWebDataHelpr requestWebDataHelpr=new RequestWebDataHelpr();
	  private  LoadMoreListView loadMoreListView;
	  private boolean isShowNoDataLayout=false;
	  private String notDataMsg = "抱歉!暂无数据!";
	  private int notDataImgResId = R.drawable.nodata_default;
	  private  boolean isLoadMoreStatus=true;
	  private boolean isdefaultShowAll=true;
	  public boolean isIsdefaultShowAll() {
		return isdefaultShowAll;
	}
	public void setIsdefaultShowAll(boolean isdefaultShowAll) {
		this.isdefaultShowAll = isdefaultShowAll;
	}
	public boolean isLoadMoreStatus() {
		return isLoadMoreStatus;
	}
		public void setLoadMoreStatus(boolean isLoadMoreStatus) {
			this.isLoadMoreStatus = isLoadMoreStatus;
		}
	public int getNotDataImgResId() {
		return notDataImgResId;
	}
	public void setNotDataImgResId(int notDataImgResId) {
		this.notDataImgResId = notDataImgResId;
	}
	/**
	   * 设置在没有数据的时显示 No data UI 
	   * @param isShowNoDataLayout true展示，false不展示 默认false
	   */
	public void setShowNoDataLayout(boolean isShowNoDataLayout) {
		this.isShowNoDataLayout = isShowNoDataLayout;
	}
	private View notDataView;
	
	public RequestDataAsyncTask(Context context,PullToRefreshView pullToRefreshView,AbsListView listView)
	{
		this.pullToRefreshView = pullToRefreshView;
		this.mContext = context;
		if(null != listView)
		{
			if(listView instanceof ListView)
			{
				try {
					loadMoreListView = (LoadMoreListView) listView;
				} catch (Exception e) {
				}
			}
		}
	}
	public RequestDataAsyncTask(Context context)
	{
		this.mContext = context;
	}
   /**
    * 执行加载数据操作
    **/
	public void executeLoadData()
	{
		if(NetWorkUtil.netWorkConnection(mContext))
		{
			currentPage = 0;
			requestWebDataHelpr.onCancelled();
			requestWebDataHelpr=new RequestWebDataHelpr();
			requestWebDataHelpr.execute();
		}
		else
		{
			Toast.makeText(mContext, R.string.network_error, 3).show();
			if(null != pullToRefreshView)
			{
				pullToRefreshView.onHeaderRefreshComplete();
			}
		}
	}
	/**
	 * 不取消上一次执行的任务允许多任务执行
	 */
	public void executeSubScribe(){

		if(NetWorkUtil.netWorkConnection(mContext))
		{
			currentPage = 0;
			requestWebDataHelpr=new RequestWebDataHelpr();
			requestWebDataHelpr.execute();
		}
		else
		{
			Toast.makeText(mContext, R.string.network_error, 3).show();
		}
	}
	
	/**
	 * 执行加载更多操作
	 */
	public void executeLoadMore()
	{
		if(NetWorkUtil.netWorkConnection(mContext))
		{
			new RequestWebDataHelpr().execute();
		}
		else
		{
			Toast.makeText(mContext, R.string.network_error, 3).show();
		}
	}
	
	/**
	 * 加载数据的异步任务
	 * @author 赵豪
	 * 浙江新华移动传媒有限公司版权所有
	 */
	public class RequestWebDataHelpr extends AsyncTask<Void, Void, List<Object>>
	{
		//是否继续执行标志
		 private boolean executedata=true;
		 /**
		  * 判断异步类是否在执行
		  * 当返回为false的时候则表示正在执行 反之没执行
		  */
		 private boolean isrunning=false;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			setExecutedata(true);
	    	setIsrunning(true);
	    	currentPage+=1;
	    	if(currentPage>1){
	    		hideLoadMore();
	    	}
	    	callBackAsyncTaskLister.onPreExecute(currentPage);
			if(null != loadMoreListView)
			{
				loadMoreListView.fillLoadMore(currentPage);
				loadMoreListView.setFooterDividersEnabled(false);
			}
		}
		
		
		@Override
		protected void onCancelled() {
			super.onCancelled();
			setExecutedata(false);
	    	setIsrunning(false);
		}
		
		@Override
		protected List<Object> doInBackground(Void... params)
		{
	   	      List<Object> objects = callBackAsyncTaskLister.doInBackground(currentPage);
	   	      if(isExecutedata()){
	   	    	  return  objects;
	   	      }else{
	   	    	  return null;
	   	      }
		}
		
		@Override
		protected void onPostExecute(List<Object> result) 
		{
			super.onPostExecute(result);
			int size = 0;
			if(null != result)
			{
				size = result.size();
			}
			if(null!=pullToRefreshView)
			{
				pullToRefreshView.onFooterRefreshComplete(size);
			}
			if(size>0)
			{
				
				if(size>= 5)
				{
					showLoadMore();
					if(isLoadMoreStatus){
						if(null!=pullToRefreshView)
						pullToRefreshView.setEnablePullLoadMoreDataStatus(true);
					}
				}
				else
				{
					hideLoadMore();
					if(null!=pullToRefreshView)
						pullToRefreshView.setEnablePullLoadMoreDataStatus(false);
					
				}
				//隐藏没有数据提示View
				if(null != notDataView)
				{
					notDataView.setVisibility(View.GONE);
				}
				if(null != loadMoreListView)
				{
					loadMoreListView.setVisibility(View.VISIBLE);
				}
			}
			else
			{
				//当获取数据为空的时候也禁用上拉加载更多
				if(null!=pullToRefreshView) pullToRefreshView.setEnablePullLoadMoreDataStatus(false);
				if(currentPage!=1&&isIsdefaultShowAll())
				{
					Toast.makeText(mContext, R.string.nomoredate, 3).show();
				}
				hideLoadMore();
			}
			
			//取消刷新
			if(currentPage==1)
			{
				initDataView(size);
				if(null!=pullToRefreshView)
				{
					pullToRefreshView.onHeaderRefreshComplete();
				}
			} 
			//回调结果到界面
			callBackAsyncTaskLister.onPostExecute(result,currentPage);
			
		}
		public boolean isExecutedata() {
			return executedata;
		}

		public void setExecutedata(boolean executedata) {
			this.executedata = executedata;
		}

		public boolean isIsrunning() {
			return isrunning;
		}

		public void setIsrunning(boolean isrunning) {
			this.isrunning = isrunning;
		}
	}
	
	public interface ICallBackAsyncTaskLister{
		
		/**
		 *	预准备的回调方法用于初始化基本信息
		 ***/
		public void onPreExecute(int currentPage);
		
		/**
		 * 执行网络状态并返回
		 * @param ishasNetwork
		 * @param currentPage
		 * @return
		 */
		
		public List<Object> doInBackground(int currentPage);
		
		/**
		 * 返回数据并回调到界面
		 * @param listDatas
		 * @param currentPage
		 */
		public void onPostExecute(List<Object> listDatas,int currentPage);
	}
	
	public void setCallBackAsyncTaskLister(
			ICallBackAsyncTaskLister callBackAsyncTaskLister) {
		this.callBackAsyncTaskLister = callBackAsyncTaskLister;
	}
	//我把loadMoreListView和isLoadMoreStatus 生成为static了
	public  void hideLoadMore()
	{
		if(null != loadMoreListView&&isLoadMoreStatus)
		{
			loadMoreListView.hideLoadMore();
		}
	}
	
	public void showLoadMore()
	{
		if(null != loadMoreListView&&isLoadMoreStatus)
		{
			loadMoreListView.showLoadMore();
		}
	}
	
	public void setNotDataView(View notDataView) {
		this.notDataView = notDataView;
	}
	
	public boolean isShowNoDataLayout() {
		return isShowNoDataLayout;
	}
	public void setNotDataMsg(String notDataMsg) {
		this.notDataMsg = notDataMsg;
	}
	/**
	 * 控制是否显示无数据时候的布局
	 * @param size
	 */
	public void initDataView(int size){
		if(isShowNoDataLayout)
		{
			if(null==notDataView)
			{
				//初始化没有数据时候显示的UI
				notDataView = LayoutInflater.from(mContext).inflate(R.layout.comment_no_data_layout, null);
				TextView tvNotDataTxt = (TextView) notDataView.findViewById(R.id.tvNotDataTxt);
				tvNotDataTxt.setText(notDataMsg);
				ImageView ivNotDataImg = (ImageView) notDataView.findViewById(R.id.ivNotDataDefaultImg);
				ivNotDataImg.setImageResource(notDataImgResId);
				pullToRefreshView.addView(notDataView,new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
			}
			if(size>0){
				notDataView.setVisibility(View.GONE);
				loadMoreListView.setVisibility(View.VISIBLE);
			}else{
				notDataView.setVisibility(View.VISIBLE);
				loadMoreListView.setVisibility(View.GONE);
			}
			pullToRefreshView.onFooterRefreshComplete(size);
		}
	}
}