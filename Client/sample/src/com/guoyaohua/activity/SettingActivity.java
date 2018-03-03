package com.guoyaohua.activity;

import com.guoyaohua.entity.GlobalVariable;
import com.guoyaohua.util.HttpUtil;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends Activity {
	private EditText foodIp;
	private EditText foodPort;
	private EditText foodName;
	private EditText videoIp;
	private EditText videoPort;
	private EditText videoPassword;
	private Button save;
	private Button back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.setting_layout_new);

		// 得到组件
		foodIp = (EditText) findViewById(R.id.et_foodip_setting_layout_new);
		foodPort = (EditText) findViewById(R.id.et_foodport_setting_layout_new);
		foodName = (EditText) findViewById(R.id.et_foodname_setting_layout_new);
		videoIp = (EditText) findViewById(R.id.et_videoip_setting_layout_new);
		videoPort = (EditText) findViewById(R.id.et_videoport_setting_layout_new);
		videoPassword = (EditText) findViewById(R.id.et_videopassword_setting_layout_new);
		save = (Button) findViewById(R.id.bt_setting_save);
		back = (Button) findViewById(R.id.bt_setting_back);

		// 调取本地配置文件中的用户信息
		SharedPreferences pre = getSharedPreferences("user_msg",
				MODE_WORLD_WRITEABLE);
		// 得到配置文件中的用户名
		pre.getString("ip", null);
		pre.getString("serverName", null);
		pre.getString("port", null);
		// 判断本地是否有记录 如果有 则在textview中显示该记录
		if (pre.getString("ip", null) != null) {
			foodIp.setText(pre.getString("ip", null));
		}
		if (pre.getString("serverName", null) != null) {
			foodName.setText(pre.getString("serverName", null));
		}
		if (pre.getString("port", null) != null) {
			foodPort.setText(pre.getString("port", null));
		}

		// 调取本地配置文件中的用户信息
		// SharedPreferences pre = getSharedPreferences("user_msg",
		// MODE_WORLD_WRITEABLE);
		// 得到配置文件中的用户名
		pre.getString("RV_ip", null);
		pre.getString("RV_password", null);
		pre.getString("RV_port", null);
		// 判断本地是否有记录 如果有 则在textview中显示该记录
		if (pre.getString("RV_ip", null) != null) {
			videoIp.setText(pre.getString("RV_ip", null));
		}
		if (pre.getString("RV_password", null) != null) {
			videoPassword.setText(pre.getString("RV_password", null));
		}
		if (pre.getString("RV_port", null) != null) {
			videoPort.setText(pre.getString("RV_port", null));
		}

		// 绑定监听器
		// 保存
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 点菜服务器信息保存
				// 得到值
				String ip = foodIp.getText().toString();
				String serverName = foodName.getText().toString();
				String port = foodPort.getText().toString();

				// 共享信息 是下次自动填写表单
				SharedPreferences pre = getSharedPreferences("user_msg",
						MODE_WORLD_WRITEABLE);
				SharedPreferences.Editor editor = pre.edit();
				editor.putString("ip", ip);
				editor.putString("serverName", serverName);
				editor.putString("port", port);
				editor.commit();

				if (!ip.equals("") && !serverName.equals("")
						&& !port.equals("")) {
					// 得到ip和server拼接
					Login.ip = ip;
					HttpUtil.BASE_URL = "http://" + ip + ":" + port + "/"
							+ serverName + "/";
				}
				// 视频传输服务端配置信息保存
				// 得到值
				String videoip = videoIp.getText().toString();
				String passWord = videoPassword.getText().toString();
				String videoport = videoPort.getText().toString();

				// 共享信息 是下次自动填写表单
				// SharedPreferences pre = getSharedPreferences(
				// "user_msg", MODE_WORLD_WRITEABLE);
				// SharedPreferences.Editor editor = pre.edit();
				editor.putString("RV_ip", videoip);
				editor.putString("RV_password", passWord);
				editor.putString("RV_port", videoport);
				editor.commit();

				if (!ip.equals("") && !passWord.equals("") && !port.equals("")) {
					GlobalVariable.ip = videoip;
					GlobalVariable.passWord = passWord;
					GlobalVariable.prot = Integer.parseInt(videoport);
				}
		//		Toast.makeText(getApplicationContext(), "信息已保存", 0).show();
				finish();
			}
		});
		// 返回
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		System.gc();
		super.onDestroy();
	}
}
