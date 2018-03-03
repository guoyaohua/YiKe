package com.guoyaohua.adapter;

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
import android.widget.ListView;
import android.widget.TextView;

/**
* @类名:NewsIndexAdapter 
* @描述:TODO(新闻适配器) 
* @作者:guoyaohua 
* @时间 2013-3-4 下午4:06:18
 */
public class NewsIndexAdapter extends MyBaseAdapter {
	private LayoutInflater inflater = null;
	public NewsIndexAdapter(Context context){
		//absListView.setOnClickListener(this);
		inflater = LayoutInflater.from(context);
		this.mContext = context;
	}
	public void setAbsListView(AbsListView absListView) {
		
		this.absListView = absListView;
	}
	public void clear()
	{
		this.alObjects.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.news_list_item_layout, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.tvNewsTitle = (TextView)convertView.findViewById(R.id.tvNewsTitle);
			viewHolder.tvNewsIntro = (TextView)convertView.findViewById(R.id.tvNewsIntro);
			viewHolder.ivindexloadimg=(ImageView) convertView.findViewById(R.id.ivindexloadimg);
			viewHolder.tvCommentCount=(TextView) convertView.findViewById(R.id.tvCommentCount);
			viewHolder.tvurl=(TextView) convertView.findViewById(R.id.picurl);
			//foodid用于存储菜品在menutbl中的id   用于最后提交订单时使用
			viewHolder.tvFoodId=(TextView) convertView.findViewById(R.id.foodID);
			convertView.setTag(viewHolder);
			
		}
		else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		NewsListEntity indexEntity =(NewsListEntity) alObjects.get(position);
		viewHolder.tvNewsTitle.setText(indexEntity.getNEWS_TITLE());
		viewHolder.tvNewsIntro.setText(indexEntity.getNEWS_MEMO());
		//设置foodid的text
		viewHolder.tvFoodId.setText(indexEntity.getNEWS_ID());
		String img=indexEntity.getSERVER_DOMAIN();
		if(!TextUtils.isEmpty(indexEntity.getSERVER_DOMAIN())){
			viewHolder.ivindexloadimg.setVisibility(View.VISIBLE);
			//将图片url信息存储到viewHolder.tvurl的text里
			viewHolder.tvurl.setText(indexEntity.getSERVER_DOMAIN());
			App.getIns().display(img, viewHolder.ivindexloadimg, R.drawable.icon);
		}else{
			viewHolder.ivindexloadimg.setVisibility(View.GONE);
		}
		if(!TextUtils.isEmpty(indexEntity.getNEWS_COMMENT_COUNT())&&Integer.valueOf(indexEntity.getNEWS_COMMENT_COUNT())>0){
			viewHolder.tvCommentCount.setVisibility(View.VISIBLE);
			viewHolder.tvCommentCount.setText(indexEntity.getNEWS_COMMENT_COUNT());
		}else{
			viewHolder.tvCommentCount.setVisibility(View.INVISIBLE);
		}
		
		return convertView;
	}
	
	public static class ViewHolder{
		public TextView tvNewsTitle,tvNewsIntro,tvCommentCount,tvurl,tvFoodId;
		public ImageView ivindexloadimg;
	}
 
	@Override
	public int getCount() {
		return alObjects.size();
	}

	@Override
	public Object getItem(int arg0) {
		return  alObjects.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

}
