package com.guoyaohua.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import com.amaker.wlo.MainMenuActivity;
//import com.amaker.wlo.OrderActivity;
//import com.amaker.wlo.UpdateActivity;

import com.guoyaohua.activity.ContentActivity;
import com.guoyaohua.activity.Order;
import com.guoyaohua.activity.R;
import com.guoyaohua.activity.ViewPagerListViewActivity;
import com.guoyaohua.adapter.NewsIndexAdapter;
import com.guoyaohua.adapter.NewsIndexAdapter.ViewHolder;
import com.guoyaohua.app.App;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @类名:LoadMoreListView
 * @描述:TODO(加载更多listview)
 * @作者:guoyaohua
 * 
 */
public class LoadMoreListView extends ListView implements OnClickListener {

	private Context context;
	// 获得LayoutInflater实例
	LayoutInflater inflater = LayoutInflater.from(getContext());
	// 实例化在弹出对话框中添加的视图
	final View v = inflater.inflate(R.layout.order_details_layout, null);

	// @Override
	// public boolean onTouchEvent(MotionEvent ev) {
	// Toast.makeText(getContext(), "onTouchEvent", 1).show();
	// return super.onTouchEvent(ev);
	// }
	/**
	 * 此方法为响应listview中点击事件\ 用findviewbyid获取view对象中的数据 verygood！
	 */
	@Override
	public boolean performItemClick(View view, int position, long id) {
		// 获取菜品名称
		TextView tv_Name = (TextView) view.findViewById(R.id.tvNewsTitle);
		String name = (String) tv_Name.getText();
		// 获取菜品详细说明
		TextView tv_details = (TextView) view.findViewById(R.id.tvNewsIntro);
		String details = (String) tv_details.getText();
		// 获取菜品价格
		TextView tv_Price = (TextView) view.findViewById(R.id.tvCommentCount);
		String price = (String) tv_Price.getText();
		// 获取菜品图片url
		TextView picurl = (TextView) view.findViewById(R.id.picurl);
		String url = (String) picurl.getText();
		// 获取该菜品的id
        TextView tv_FoodId = (TextView) view.findViewById(R.id.foodID);
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
		intent.setClass(context, OrderDetails.class);
		context.startActivity(intent);

		return super.performItemClick(view, position, id);
	}

	public LoadMoreListView(Context context) {
		super(context);
		this.context = context;
	}

	public LoadMoreListView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initWidget();
		this.context = context;
	}

	public void initWidget() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// 加载更多
		View loadMoreView = inflater.inflate(R.layout.load_more_layout, null);
		this.addFooterView(loadMoreView, null, true);
		btnLoadMore = (Button) findViewById(R.id.btnLoadMore);
		btnLoadMore.setOnClickListener(this);
		// this.setFooterDividersEnabled(false);
	}

	private Button btnLoadMore;
	private IClickloadMoreBtnCallBack clickloadMoreBtnCallBack;

	@Override
	public void onClick(View v) {

		if (null != clickloadMoreBtnCallBack) {
			clickloadMoreBtnCallBack.loadMore();
		} else {
			Toast.makeText(getContext(), "请实现点击加载更多回调函数", 3).show();
		}
	}

	public interface IClickloadMoreBtnCallBack {
		public void loadMore();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}

	public void setClickloadMoreBtnCallBack(
			IClickloadMoreBtnCallBack clickloadMoreBtnCallBack) {
		this.clickloadMoreBtnCallBack = clickloadMoreBtnCallBack;
	}

	public void leftMargin(int leftMargin, int rightMargin) {
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParams.leftMargin = leftMargin;
		layoutParams.rightMargin = rightMargin;
		if (null != btnLoadMore) {
			btnLoadMore.setLayoutParams(layoutParams);
		}
	}

	public Button getBtnLoadMore() {
		return btnLoadMore;
	}

	public void showLoadMore() {
		if (null != btnLoadMore) {
			this.setFooterDividersEnabled(true);
			// btnLoadMore.setEnabled(true);
			btnLoadMore.setEnabled(false);
			btnLoadMore.setText("上拉加载更多");
			// 加载完成，更换加载更多按钮
			btnLoadMore.setVisibility(View.VISIBLE);
			((View) (btnLoadMore.getParent())).setVisibility(View.VISIBLE);
		}
	}

	public void hideLoadMore() {
		if (null != btnLoadMore) {
			btnLoadMore.setVisibility(View.GONE);
			// ((View) btnLoadMore.getParent()).setVisibility(View.GONE);
			// 禁用最后listView项的最后一条线
			this.setFooterDividersEnabled(false);

		}
	}

	public void enableLoadMore() {
		if (null != btnLoadMore) {
			// btnLoadMore.setEnabled(true);
			btnLoadMore.setEnabled(false);
		}
	}

	public void fillLoadMore(int currentPage) {
		if (null != btnLoadMore) {
			btnLoadMore.setEnabled(false);
			if (currentPage == 1)
				btnLoadMore.setVisibility(View.GONE);
			else
				btnLoadMore.setText(getResources().getString(
						R.string.hasloading_moreValues));
		}
	}
}
