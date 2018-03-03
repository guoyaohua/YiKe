package com.guoyaohua.adapter;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.guoyaohua.activity.Login;
import com.guoyaohua.activity.Order;
import com.guoyaohua.activity.R;
import com.guoyaohua.adapter.NewsIndexAdapter.ViewHolder;
import com.guoyaohua.app.App;
import com.guoyaohua.entity.GlobalVariable;
import com.guoyaohua.entity.NewsListEntity;
import com.guoyaohua.util.HttpUtil;
import com.guoyaohua.widget.MyButton;
import com.guoyaohua.widget.MyImageButton;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Message;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderIndexAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
	private int position = -1;// 用于确定该条目是否已经回收
	protected static Context mContext;
	// 用于判断是否需要回收图片
	public static boolean isSubmit = false;

	// public ArrayList<Object> alObjects = new ArrayList<Object>();

	public OrderIndexAdapter(Context context) {
		// absListView.setOnClickListener(this);
		inflater = LayoutInflater.from(context);
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Order.list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Order.list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 内部类 用于保存listitem中的信息
	public static class ViewHolder {
		public TextView tv_name, tv_price, tv_number;
		public ImageView iv_icon;
		public MyImageButton ib_decrease;
		public MyImageButton ib_increase;
		public MyButton bt_deleteItem;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.oder_layout_item, parent,
					false);
			// 新建个viewHolder类
			viewHolder = new ViewHolder();
			// 找到组件
			viewHolder.tv_name = (TextView) convertView
					.findViewById(R.id.tv_name);
			viewHolder.tv_price = (TextView) convertView
					.findViewById(R.id.tv_price);
			viewHolder.iv_icon = (ImageView) convertView
					.findViewById(R.id.iv_icon);
			viewHolder.tv_number = (TextView) convertView
					.findViewById(R.id.tv_number);
			viewHolder.ib_decrease = (MyImageButton) convertView
					.findViewById(R.id.ib_decrease);
			viewHolder.ib_increase = (MyImageButton) convertView
					.findViewById(R.id.ib_increase);
			viewHolder.bt_deleteItem = (MyButton) convertView
					.findViewById(R.id.bt_deleteItem);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// NewsListEntity indexEntity = (NewsListEntity)
		// alObjects.get(position);
		// 给组件赋值

		// 菜名
		viewHolder.tv_name.setText((String) Order.list.get(position)
				.get("name"));

		// 价格
		viewHolder.tv_price.setText("单价：￥"
				+ (String) Order.list.get(position).get("price"));
		// 数量
		viewHolder.tv_number.setText((String) Order.list.get(position)
				.get("number").toString());
		// //////////////////////////////////////////////////////////////
		// 2014年10月30日14:26:54 修改
		// //////////////////////////////////////////////////////////////
		if (isSubmit) {
			if (this.position != position) {
				this.position = position;
				// 回收bitmap
				// if(viewHolder.iv_icon.getTag()!=null)

				BitmapDrawable bitmapDrawable = (BitmapDrawable) viewHolder.iv_icon
						.getDrawable();

				if (bitmapDrawable != null) {

					Bitmap hisBitmap = bitmapDrawable.getBitmap();

					if (hisBitmap.isRecycled() == false)

					{
						viewHolder.iv_icon.setImageResource(R.drawable.icon);
						viewHolder.iv_icon.setTag(" ");
						hisBitmap.recycle();

					}

				}

				System.gc();
			}else{//相等时证明是重复执行getview 所以不用进行回收
				viewHolder.iv_icon.setImageResource(R.drawable.icon);
			}

		} else {

			// 此处加入判断 当没点击提交按钮时正常载入图片，如果点击提交按钮就不执行该语句
			// 设置listitem中的菜品图片
			String pic = (String) Order.list.get(position).get("image");
			if (!TextUtils.isEmpty(pic)) {
				viewHolder.iv_icon.setVisibility(View.VISIBLE);
				App.getIns().display(pic, viewHolder.iv_icon, R.drawable.icon);
			} else {
				// 此处为没有菜品图片时加载的默认图片
				viewHolder.iv_icon.setImageResource(R.drawable.youren);
			}
		}
		// 删除按钮
		// viewHolder.bt_deleteItem.setText("删除");
		// String img = indexEntity.getSERVER_DOMAIN();
		// if (!TextUtils.isEmpty(indexEntity.getSERVER_DOMAIN())) {
		// viewHolder.ivindexloadimg.setVisibility(View.VISIBLE);
		// App.getIns().display(img, viewHolder.ivindexloadimg,
		// R.drawable.icon);
		// } else {
		// viewHolder.ivindexloadimg.setVisibility(View.GONE);
		// }
		// if (!TextUtils.isEmpty(indexEntity.getNEWS_COMMENT_COUNT())
		// && Integer.valueOf(indexEntity.getNEWS_COMMENT_COUNT()) > 0) {
		// viewHolder.tvCommentCount.setVisibility(View.VISIBLE);
		// viewHolder.tvCommentCount.setText(indexEntity
		// .getNEWS_COMMENT_COUNT());
		// } else {
		// viewHolder.tvCommentCount.setVisibility(View.INVISIBLE);
		// }
		// 给按钮赋值position 并绑定监听器
		viewHolder.bt_deleteItem.setIndex(position);
		viewHolder.bt_deleteItem.setOnClickListener(MyOnClickListener
				.getInstance());
		viewHolder.ib_decrease.setIndex(position);
		viewHolder.ib_decrease.setOnClickListener(MyOnClickListener
				.getInstance());
		viewHolder.ib_increase.setIndex(position);
		viewHolder.ib_increase.setOnClickListener(MyOnClickListener
				.getInstance());

		return convertView;
	}

}

class MyOnClickListener implements OnClickListener {

	public static MyOnClickListener instance = null;

	private MyOnClickListener() {
	}

	public static MyOnClickListener getInstance() {
		if (instance == null)
			instance = new MyOnClickListener();
		return instance;
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == (int) R.id.ib_decrease) {
			// 减小
			int index = ((MyImageButton) v).getIndex();
			int num = (Integer) Order.list.get(index).get("number");
			if (num > 1) {

				Order.list.get(index).remove("number");
				Order.list.get(index).put("number", num - 1);
				// 更新listview
				Order.adapter.notifyDataSetChanged();
				// 改变listview后还需重新调用setNumAndCost();
				((Order) OrderIndexAdapter.mContext).setNumAndCost();

			} else
				return;
		} else if (v.getId() == (int) R.id.ib_increase) {
			// 增大
			int index = ((MyImageButton) v).getIndex();
			int num = (Integer) Order.list.get(index).get("number");

			Order.list.get(index).remove("number");
			Order.list.get(index).put("number", num + 1);
			// 更新listview
			Order.adapter.notifyDataSetChanged();
			// 改变listview后还需重新调用setNumAndCost();
			((Order) OrderIndexAdapter.mContext).setNumAndCost();
		} else if (v.getId() == (int) R.id.bt_deleteItem) {
			// 删除listview 的view 在此处添加对话框
			final int index = ((MyButton) v).getIndex();
			final View view = v;
			AlertDialog.Builder builder = new AlertDialog.Builder(
					OrderIndexAdapter.mContext);
			// 获得AlertDialog.Builder实例
			builder
			// 设置标题
			.setMessage("是否要删除该菜品？")
			// .setView(v)
			// 设置确定按钮
					.setPositiveButton("删除",
							new DialogInterface.OnClickListener() {
								// 确定按钮事件
								public void onClick(DialogInterface dialog,
										int id) {
									// 得到图片
									ImageView pic = (ImageView) ((Activity) OrderIndexAdapter.mContext)
											.findViewById(R.id.iv_icon);
									// 回收bitmap
									BitmapDrawable bitmapDrawable = (BitmapDrawable) pic
											.getDrawable();

									if (bitmapDrawable != null) {

										Bitmap hisBitmap = bitmapDrawable
												.getBitmap();

										if (hisBitmap.isRecycled() == false)

										{

											hisBitmap.recycle();

										}

									}
									Order.list.remove(index);
									// 更新listview
									Order.adapter.notifyDataSetChanged();
									// 改变listview后还需重新调用setNumAndCost();
									((Order) OrderIndexAdapter.mContext)
											.setNumAndCost();
								}

							}).setNegativeButton("取消", null);
			AlertDialog alert = builder.create();
			alert.show();

		}

	}

}