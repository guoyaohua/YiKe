package com.guoyaohua.widget;

import com.guoyaohua.activity.R;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 用于点菜事件
 * @author John Kwok
 *
 */
public class MyOnItemClickListener2 implements OnItemClickListener  {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 获取菜品名称
				TextView tv_Name = (TextView) view.findViewById(R.id.tv_index_item_name);
				String name = (String) tv_Name.getText();
				// 获取菜品详细说明
				TextView tv_details = (TextView) view.findViewById(R.id.tv_index_item_info);
				String details = (String) tv_details.getText();
				// 获取菜品价格
				TextView tv_Price = (TextView) view.findViewById(R.id.tv_index_item_price);
				String price = (String) tv_Price.getText();
				// 获取菜品图片url
				TextView picurl = (TextView) view.findViewById(R.id.tv_index_item_url);
				String url = (String) picurl.getText();
				// 获取该菜品的id
		        TextView tv_FoodId = (TextView) view.findViewById(R.id.tv_index_item_foodid);
		        String foodId = (String) tv_FoodId.getText();
				// 测试
			//	Toast.makeText(getContext(), url, 1).show();

				// Map<String, Object> map1 = new HashMap<String, Object>();
				// map1.put("name", name);
				// map1.put("image", R.drawable.youren);
				// map1.put("price", "单价: ￥" + price);
				// map1.put("number", (int) 1);

				/*
				 * // 此处为给imageview加载网络地址图片 if
				 * (!TextUtils.isEmpty(indexEntity.getSERVER_DOMAIN())) {
				 * viewHolder.ivindexloadimg.setVisibility(View.VISIBLE);
				 * App.getIns().display(img, viewHolder.ivindexloadimg,
				 * R.drawable.icon); } else {
				 * viewHolder.ivindexloadimg.setVisibility(View.GONE); }
				 */

				// 此处启动购物清单activity 之前还要先修改order中的list

				Intent intent = new Intent();
				// 启动更新Activity
				intent.putExtra("菜名", tv_Name.getText());
				intent.putExtra("介绍", tv_details.getText());
				intent.putExtra("价格", tv_Price.getText());
				intent.putExtra("图片", url);
				intent.putExtra("id", foodId);
				intent.setClass(view.getContext(), OrderDetails.class);
				view.getContext().startActivity(intent);
		
	}

}
