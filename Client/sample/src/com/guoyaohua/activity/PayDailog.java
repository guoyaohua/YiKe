package com.guoyaohua.activity;

import com.guoyaohua.activity.R;
import com.guoyaohua.entity.GlobalVariable;
import com.guoyaohua.widget.LoadMoreGridViewPullToreshView;
import com.wanpu.pay.PayConnect;
import com.wanpu.pay.PayResultListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PayDailog extends Activity {
	Button crashPay;
	Button cardPay;
	// 应用或游戏商自定义的支付订单(每条支付订单数据不可相同)
	String orderId = "";
	// 用户标识
	String userId = "";
	// 支付商品名称
	String goodsName = GlobalVariable.long_OrderId + "号订单结账";
	// 支付金额
	float price = 1000.0f;
	// 支付时间
	String time = "";
	// 支付描述
	String goodsDesc = "结账";
	// 应用或游戏商服务器端回调接口（无服务器可不填写）
	String notifyUrl = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.paydailog_layout);
		super.onCreate(savedInstanceState);

		crashPay = (Button) findViewById(R.id.bt_paydailog_crash);
		cardPay = (Button) findViewById(R.id.bt_paydailog_card);

		crashPay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 当使用现金付款时 直接返回ok
				setResult(RESULT_OK, null);
				finish();
			}

		});
		cardPay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					// // 游戏商自定义支付订单号，保证该订单号的唯一性，建议在执行支付操作时才进行该订单号的生成
					orderId = System.currentTimeMillis() + "";

					// String amountStr = amountView.getText().toString();
					// if (!"".equals(amountStr)) {
					// price = Float.valueOf(amountStr);
					// } else {
					// price = 0.0f;
					// }
					// 此处应该将钱数赋值给price
					price = GlobalVariable.cost;
					PayConnect.getInstance(PayDailog.this).pay(PayDailog.this,
							GlobalVariable.short_OrderId, Login.userId, price,
							goodsName, goodsDesc, notifyUrl,
							new MyPayResultListener());

				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

			}

		});

	}

	/**
	 * 自定义Listener实现PaySuccessListener，用于监听支付成功
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPayResultListener implements PayResultListener {

		@Override
		public void onPayFinish(Context payViewContext, String orderId,
				int resultCode, String resultString, int payType, float amount,
				String goodsName) {
			// 可根据resultCode自行判断
			if (resultCode == 0) {
				Toast.makeText(getApplicationContext(),
						resultString + "：" + amount + "元", Toast.LENGTH_LONG)
						.show();
				// 支付成功时关闭当前支付界面
				PayConnect.getInstance(PayDailog.this).closePayView(
						payViewContext);

				// TODO 在客户端处理支付成功的操作
				setResult(RESULT_OK, null);
				finish();
 
				// 未指定notifyUrl的情况下，交易成功后，必须发送回执
				PayConnect.getInstance(PayDailog.this)
						.confirm(orderId, payType);
			} else {
				Toast.makeText(getApplicationContext(), resultString,
						Toast.LENGTH_LONG).show();
			}
		}
	}
/**
 * 复写ondestroy方法  回收bitmap   此处演示平板先不用此方法 2014年11月4日18:53:01
 */
	
/*	
	@Override
	protected void onDestroy() {
		// 回收bitmap
		LinearLayout background = (LinearLayout) findViewById(R.id.paydialog_LinearLayout);
		BitmapDrawable bitmapDrawable = (BitmapDrawable) background
				.getBackground();

		if (bitmapDrawable != null) {

			Bitmap hisBitmap = bitmapDrawable.getBitmap();

			if (hisBitmap.isRecycled() == false)

			{

				hisBitmap.recycle();

			}

		}

		bitmapDrawable = (BitmapDrawable) crashPay.getBackground();

		if (bitmapDrawable != null) {

			Bitmap hisBitmap = bitmapDrawable.getBitmap();

			if (hisBitmap.isRecycled() == false)

			{

				hisBitmap.recycle();

			}

		}
		bitmapDrawable = (BitmapDrawable) cardPay.getBackground();

		if (bitmapDrawable != null) {

			Bitmap hisBitmap = bitmapDrawable.getBitmap();

			if (hisBitmap.isRecycled() == false)

			{

				hisBitmap.recycle();

			}

		}
		super.onDestroy();
	}
*/
}
