package com.guoyaohua.adapter;

import java.util.ArrayList;
import java.util.List;

import com.guoyaohua.activity.ViewPagerListViewActivity;
import com.guoyaohua.widget.PullToRefreshView;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyBaseAdapter extends BaseAdapter{
   protected PullToRefreshView pullToRefreshView=null;
   public ArrayList<Object> alObjects = new ArrayList<Object>();
   
   public AbsListView absListView;
   
   protected  Context mContext;
   
  
   
	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	/**
	 * 设置集合
	 * @param alObjects
	 * @param boo
	 */
	public void setList(List<Object> alObjects,boolean boo)
	{
		if(null!=alObjects&&alObjects.size()>0){
			this.alObjects.addAll(alObjects);
			if(boo)
			{
				notifyDataSetChanged();
			}
		}
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		return null;
	}
	public PullToRefreshView getPullToRefreshView() {
		return pullToRefreshView;
	}
	public void setPullToRefreshView(PullToRefreshView pullToRefreshView) {
		this.pullToRefreshView = pullToRefreshView;
	}
	public ArrayList<Object> getAlObjects() {
		return alObjects;
	}	

	public void setAlObjects(ArrayList<Object> alObjects) {
		this.alObjects = alObjects;
	}
	public AbsListView getAbsListView() {
	return absListView;
}

	public void setAbsListView(AbsListView absListView) {
		this.absListView = absListView;
	}

	

	
}
