package com.guoyaohua.activity;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.guoyaohua.activity.R;
import com.guoyaohua.entity.GlobalVariable;
import com.guoyaohua.util.HttpUtil;
import com.guoyaohua.widget.LoadMoreGridViewPullToreshView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class FeedBackActivity extends Activity {
	private Button submit;
	private Button cancel;
	public RatingBar ratingBar1;
	public RatingBar ratingBar2;
	public RatingBar ratingBar3;
	public RatingBar ratingBar4;
	public EditText et_FeedBack;
	int SUCSESS = 0;
	int CONNECT_ERRO = 1;
	// public String mark = null;
	public String feedBack = null;
	String[] mark = { "很差", "很差", "一般", "满意", "较满意", "非常满意" };
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == SUCSESS) {

				Toast.makeText(FeedBackActivity.this, "您的评价已提交，祝您用餐愉快！",
						Toast.LENGTH_LONG).show();
			}else if(msg.what == CONNECT_ERRO){
				Toast.makeText(FeedBackActivity.this, "对不起，服务器连接失败，请重试。",
						Toast.LENGTH_LONG).show();
			}
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.feedback_layout);
		super.onCreate(savedInstanceState);
		// 得到组件
		submit = (Button) findViewById(R.id.bt_feedback_submit);
		cancel = (Button) findViewById(R.id.bt_feedback_cancel);
		ratingBar1 = (RatingBar) findViewById(R.id.rb_feedback_1);
		ratingBar2 = (RatingBar) findViewById(R.id.rb_feedback_2);
		ratingBar3 = (RatingBar) findViewById(R.id.rb_feedback_3);
		ratingBar4 = (RatingBar) findViewById(R.id.rb_feedback_4);
		et_FeedBack = (EditText) findViewById(R.id.et_feedback);

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final int mark1 = (int) ratingBar1.getRating() + 1;
				final int mark2 = (int) ratingBar2.getRating() + 1;
				final int mark3 = (int) ratingBar3.getRating() + 1;
				final int mark4 = (int) ratingBar4.getRating() + 1;

				feedBack = et_FeedBack.getText().toString().trim();
				// 下面执行post语句 将评分内容和orderid传送至服务器
				// Toast.makeText(
				// FeedBackActivity.this,
				// LoadMoreGridViewPullToreshView.orderId + "    "
				// + mark[mark1 - 1] + "    " + mark[mark2 - 1]
				// + "    " + mark[mark3 - 1] + "    "
				// + mark[mark4 - 1] + "    " + feedBack, 0)
				// .show();
				// 向服务器提交评价信息
				new Thread() {

					@Override
					public void run() {
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yy-MM-dd hh:mm");
						// 评价时间
						String Time = sdf.format(date);
						// 请求参数列表
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						// 添加请求参数

						params.add(new BasicNameValuePair("time", Time));
						// 此处应该的到当前登录用户名的id
						// 由登录时赋值给全局变量serverid
						params.add(new BasicNameValuePair("userId",
								Login.userId));

						params.add(new BasicNameValuePair("mark1",
								mark[mark1 - 1]));
						params.add(new BasicNameValuePair("mark2",
								mark[mark2 - 1]));
						params.add(new BasicNameValuePair("mark3",
								mark[mark3 - 1]));
						params.add(new BasicNameValuePair("mark4",
								mark[mark4 - 1]));
						params.add(new BasicNameValuePair("feedback", feedBack));
						params.add(new BasicNameValuePair("orderId",
								GlobalVariable.short_OrderId));
						UrlEncodedFormEntity entity1 = null;
						try {
							entity1 = new UrlEncodedFormEntity(params,
									HTTP.UTF_8);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
							Message msg = new Message();

							msg.what = CONNECT_ERRO;
							handler.sendMessage(msg);
						}
						// 请求服务器url
						String url = HttpUtil.BASE_URL
								+ "servlet/FeedBackServlet";
						// 获得请求对象HttpPost
						HttpPost request = HttpUtil.getHttpPost(url);
						// 设置查询参数
						request.setEntity(entity1);
						// 获得响应结果
						String result = HttpUtil.queryStringForPost(request);
						if (result.equals("网络异常！")) {
							Message msg = new Message();

							msg.what = CONNECT_ERRO;
							handler.sendMessage(msg);
						} else {
							Message msg = new Message();

							msg.what = SUCSESS;
							handler.sendMessage(msg);

						}
						super.run();
					}

				}.start();

			}

		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}

		});

	}
	@Override
	protected void onDestroy() {
		System.gc();
		super.onDestroy();
	}
}
