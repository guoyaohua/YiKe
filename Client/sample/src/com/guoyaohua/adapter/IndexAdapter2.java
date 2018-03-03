package com.guoyaohua.adapter;

import java.util.List;

import com.guoyaohua.activity.R;
import com.guoyaohua.app.App;
import com.guoyaohua.entity.NewsListEntity;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

/**
* @类名:IndexAdapter 
* @描述:TODO(菜单适配器) 
* @作者:guoyaohua
* @时间 2013-3-4 下午4:06:18
 */
public class IndexAdapter2 extends MyBaseAdapter {
	
	private LayoutInflater inflater = null;
	public IndexAdapter2(Context context){
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
			convertView = inflater.inflate(R.layout.index_grid2_item_layout, parent, false);
			viewHolder = new ViewHolder();
			//得到组件
			viewHolder.tv_Name = (TextView)convertView.findViewById(R.id.tv_index_item_name);
			viewHolder.tv_Info = (TextView)convertView.findViewById(R.id.tv_index_item_info);
			viewHolder.tv_price = (TextView)convertView.findViewById(R.id.tv_index_item_price);
			viewHolder.tv_url = (TextView)convertView.findViewById(R.id.tv_index_item_url);
			viewHolder.tv_foodId = (TextView)convertView.findViewById(R.id.tv_index_item_foodid);
			viewHolder.ivindexloadimg=(ImageView) convertView.findViewById(R.id.iv_index_item_pic);

			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		//此处更改   用另一个实体传递
		NewsListEntity newsListEntity =(NewsListEntity) alObjects.get(position);
		viewHolder.tv_Name.setText(newsListEntity.getNEWS_TITLE());
		viewHolder.tv_Info.setText(newsListEntity.getNEWS_MEMO());
		viewHolder.tv_price.setText(newsListEntity.getNEWS_COMMENT_COUNT());
		viewHolder.tv_url.setText(newsListEntity.getSERVER_DOMAIN());
		viewHolder.tv_foodId.setText(newsListEntity.getNEWS_ID());
		if(!TextUtils.isEmpty(newsListEntity.getSERVER_DOMAIN())){
			App.getIns().display(newsListEntity.getSERVER_DOMAIN(),  viewHolder.ivindexloadimg, R.drawable.index_groupitem_default_bg);
		}
		return convertView;
	}
	
	static class ViewHolder{
		
			public TextView tv_Name,tv_Info,tv_price,tv_url,tv_foodId;
			public ImageView ivindexloadimg;
		
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
