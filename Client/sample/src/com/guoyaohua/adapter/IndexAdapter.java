package com.guoyaohua.adapter;

import java.util.List;

import com.guoyaohua.activity.R;
import com.guoyaohua.app.App;
import com.guoyaohua.entity.IndexGridItemEntity;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
* @类名:IndexAdapter 
* @描述:TODO(首页适配器) 
* @作者:guoyaohua
* @时间 2013-3-4 下午4:06:18
 */
public class IndexAdapter extends MyBaseAdapter {
	
	private LayoutInflater inflater = null;
	public IndexAdapter(Context context){
		////
//		absListView.setOnClickListener(this);
		inflater = LayoutInflater.from(context);
		this.mContext = context;
	}
	public void setAbsListView(AbsListView absListView) {
		this.absListView = absListView;
	}
	
	public void setList(List<Object> alObjects,boolean boo)
	{
		this.alObjects.addAll(alObjects);
		if(boo)
		{
			notifyDataSetChanged();
		}
	}
	
	public void clear()
	{
		this.alObjects.clear();
		notifyDataSetChanged();
	}
	/**
	 *  设置Gridview的view对象
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.index_grid_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.tvIndexItemValue = (TextView)convertView.findViewById(R.id.tvIndexItemValue);
			viewHolder.ivIndexItemimg=(ImageView) convertView.findViewById(R.id.ivIndexItemimg);
//			viewHolder.tv_orderId=(TextView) convertView.findViewById(R.id.tv_orderId);
			viewHolder.tv_personNum=(TextView) convertView.findViewById(R.id.tv_personNum);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		IndexGridItemEntity indexEntity =(IndexGridItemEntity) alObjects.get(position);
		viewHolder.tvIndexItemValue.setText(indexEntity.getLINKED_TITLE());
		viewHolder.tv_personNum.setText(indexEntity.getNUM());
		if(!TextUtils.isEmpty(indexEntity.getSERVER_DOMAIN())){
			App.getIns().display(indexEntity.getSERVER_DOMAIN(),  viewHolder.ivIndexItemimg, R.drawable.index_groupitem_default_bg);
		}
		return convertView;
	}
	
	static class ViewHolder{
		TextView tvIndexItemValue;
		TextView tv_orderId;
		TextView tv_personNum;
		ImageView ivIndexItemimg;
	}

	@Override
	public int getCount() {
		return alObjects.size();
	}

	@Override
	public Object getItem(int arg0) {
		return   alObjects.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

}
