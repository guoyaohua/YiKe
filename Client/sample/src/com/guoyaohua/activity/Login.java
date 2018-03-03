package com.guoyaohua.activity;

import com.guoyaohua.activity.R;
import com.guoyaohua.util.HttpUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	/**
	 * 进度对话框
	 */
	private ProgressDialog progressDialog;
	// 全局变量用于保存服务人员id
	public static String userId = null;
	private static final int NAME_OR_PSW_EMPTY = 1;
	private static final int NAME_OR_PSW_WRONG = 2;
	// HTTP服务器根目录的文件夹名称
	public static final String HttpName = "HTTP";

	private EditText userName;
	private EditText password;
	private Button login;
	// /////////////测试用：
	// private EditText ip;
	// private EditText serverName;
	// private EditText duankou;
	// ////////
	// 声明setting对话框中的editText
	private EditText et_setting_ip;
	private EditText et_setting_serverName;
	private EditText et_setting_port;
	private CheckBox cb_setting_saveName;
	public static String ip = "guoyaohua.vicp.net";
	// 定义menu菜单ID
	private final int MENU_1 = 0; // 设置
	private final int MENU_2 = 1; // 退出
	private static final int MENU_3 = 2;// 帮助
	private static final int MENU_4 = 3;// 关于
	private static final int CONNECT_ERRO = 0;
	// 创建handler
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			//取消进度框
			dismissProgressDialog();
			switch (msg.what) {
			case CONNECT_ERRO:
				Toast.makeText(Login.this, "登录失败，请检查网络", Toast.LENGTH_SHORT)
						.show();
				break;
			case NAME_OR_PSW_EMPTY:
				Toast.makeText(Login.this, (String) msg.obj, Toast.LENGTH_SHORT)
						.show();
				break;

			case NAME_OR_PSW_WRONG:
				Toast.makeText(Login.this, (String) msg.obj, Toast.LENGTH_SHORT)
						.show();
				break;
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		/**
		 * 在该activity创建的时候 自动检查是否本地有配置文件 如果有就自动拼接服务器地址
		 */
		// 调取本地配置文件中的用户信息
		SharedPreferences pre = getSharedPreferences("user_msg",
				MODE_WORLD_WRITEABLE);
		// 得到配置文件中的用户名
		pre.getString("ip", null);
		pre.getString("serverName", null);
		pre.getString("port", null);
		// 判断本地是否有记录 如果有 则在textview中显示该记录
		if (pre.getString("ip", null) != null
				|| pre.getString("serverName", null) != null
				|| pre.getString("port", null) != null) {
			Login.ip = pre.getString("ip", null);
			HttpUtil.BASE_URL = "http://" + pre.getString("ip", null) + ":"
					+ pre.getString("port", null) + "/"
					+ pre.getString("serverName", null) + "/";
		}

		// 设置标题
		// setTitle("Smile点餐系统-用户登录");
		// 通过findViewById方法实例化组件
		userName = (EditText) findViewById(R.id.et_userId);
		// 通过findViewById方法实例化组件
		password = (EditText) findViewById(R.id.et_password);
		// // //////////
		// ip = (EditText) findViewById(net.xinhua.activity.R.id.et_IP);
		// serverName = (EditText)
		// findViewById(net.xinhua.activity.R.id.et_ServerName);
		// duankou = (EditText)
		// findViewById(net.xinhua.activity.R.id.et_duankou);
		// // ////////////
		login = (Button) findViewById(R.id.bt_login);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showProgressDialog("正在登陆，请稍后......");
				new LoginThread().start();
			}
		});

		// 调取本地配置文件中的用户信息
		SharedPreferences pre1 = getSharedPreferences("user_msg",
				MODE_WORLD_WRITEABLE);
		// 得到配置文件中的用户名
		pre1.getString("name", null);
		// 判断本地是否有记录 如果有 则在textview中显示该记录
		if (pre1.getString("name", null) != null) {
			userName.setText(pre1.getString("name", null));
		}

	}

	// 登录方法
	public int login() {
		// 获得用户名称
		String username = userName.getText().toString();
		// 获得密码
		String pwd = password.getText().toString();
		// 获得登录结果
		String result = query(username, pwd);
		if (result != null && result.equals("0")) { // 网络连通服务器连通但是用户名密码不匹配

			return 1;
		} else if (result==null||result.equals("网络异常！")) { // 网络异常

			Message msg = new Message();
			msg.what = CONNECT_ERRO;
			handler.sendMessage(msg);
			return 2;
		} else { // 登录成功
			saveUserMsg(result);
			return 0;
		}
	}

	// 将用户信息保存到配置文件
	public void saveUserMsg(String msg) {
		// 用户编号
		String id = "";
		// 获得信息数组
		String[] msgs = msg.split(";");
		int idx = msgs[0].indexOf("=");
		id = msgs[0].substring(idx + 1);
		idx = msgs[1].indexOf("=");
		msgs[1].substring(idx + 1);
		// 保存用户id到全局变量userId中
		userId = id;
		// 共享信息 是下次自动填写表单
		SharedPreferences pre = getSharedPreferences("user_msg",
				MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = pre.edit();
		editor.putString("id", id);
		//
		// editor.putString("name", name);
		//
		editor.putString("name", userName.getText().toString());
		editor.commit();
	}

	// 验证方法
	public boolean validate() {
		String username = userName.getText().toString();
		password.getText().toString();
		if (username.equals("")) {
			Message msg = new Message();
			msg.what = NAME_OR_PSW_EMPTY;
			msg.obj = "请输入用户名或密码！";
			handler.sendMessage(msg);
			return false;
		}
		return true;
	}

	// public void showDialog(String msg) {
	// AlertDialog.Builder builder = new AlertDialog.Builder(this);
	// builder.setMessage(msg).setCancelable(false)
	// .setPositiveButton("确定", new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// }
	// });
	// AlertDialog alert = builder.create();
	// alert.show();
	// }

	// 根据用户名称密码查询
	public String query(String account, String password) {
		// 查询参数
		String queryString = "account=" + account + "&password=" + password;
		// url HttpUtil.BASE_URL为自己服务器的地址
		String url = HttpUtil.BASE_URL + "servlet/LoginServlet?" + queryString;
//		String url = "http://guoyaohua567.duapp.com/" + "servlet/LoginServlet?" + queryString;
		// 查询返回结果
		return HttpUtil.queryStringForPost(url);

	}

	class LoginThread extends Thread {

		@Override
		public void run() {
			// 得到ip和server拼接
			// HttpUtil.BASE_URL="http://"+ip.getText().toString()+":"+duankou.getText().toString()+"/"+serverName.getText().toString()+"/";

			if (validate()) {
				int result = login(); //得到返回结果   登录成功返回0  密码错误返回1  网络连接错误返回2
				if (result == 0) { //登录成功
					
					Intent intent = new Intent(Login.this, MainMenu.class);
					
					startActivity(intent);
					//取消进度框
					dismissProgressDialog();
					System.out.println("登陆成功");
					Login.this.finish();
				} else if (result == 1) {
					// System.out.println("用户名或密码错误");
					Message msg = new Message();
					msg.what = NAME_OR_PSW_WRONG;
					msg.obj = "用户名或密码错误，请重新输入！";
					handler.sendMessage(msg);
				}
			}
			super.run();
		}

	}

	/**
	 * 复写onKeyDown方法 监听back点击事件，当点击一次back时不会退出，点击两次时才会退出
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 获得AlertDialog.Builder实例
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder
			// 设置标题
			.setMessage("您确定要退出？")

			// 设置确定按钮
					.setPositiveButton("是",
							new DialogInterface.OnClickListener() {
								// 确定按钮事件
								public void onClick(DialogInterface dialog,
										int id) {
									finish();
									System.exit(0);
								}
							}).setNegativeButton("否", null);
			AlertDialog alert = builder.create();
			alert.show();
			return true;
		} 
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 对于菜单id的要求，必须是一个常量。故在定义菜单id时需要用到final关键字

		menu.add(0, MENU_1, 0, "设置").setIcon(R.drawable.icon);
		menu.add(0, MENU_3, 1, "帮助");

		menu.add(0, MENU_4, 2, "关于");

		menu.add(0, MENU_2, 3, "退出");

		// //可用setIcon方法来设置菜单图标

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case MENU_1:
			// setting();
			startActivity(new Intent(getApplication(), SettingActivity.class));
			break;
		case MENU_2:
	//		finish();
			System.exit(0);
			break;
		case MENU_3:
			startActivity(new Intent(getApplication(), GuideActivity.class));
			break;
		case MENU_4:
			startActivity(new Intent(getApplication(), AboutUsActivity.class));
			break;
		default:
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	// 设置 点击事件
	private void setting() {
		// 获得LayoutInflater实例
		LayoutInflater inflater = LayoutInflater.from(this);
		// 实例化在弹出对话框中添加的视图
		final View v = inflater.inflate(R.layout.setting_layout, null);
		// 得到组件
		et_setting_ip = (EditText) v.findViewById(R.id.et_setting_ip);
		et_setting_serverName = (EditText) v
				.findViewById(R.id.et_setting_serverName);
		et_setting_port = (EditText) v.findViewById(R.id.et_setting_port);

		// 调取本地配置文件中的用户信息
		SharedPreferences pre = getSharedPreferences("user_msg",
				MODE_WORLD_WRITEABLE);
		// 得到配置文件中的用户名
		pre.getString("ip", null);
		pre.getString("serverName", null);
		pre.getString("port", null);
		// 判断本地是否有记录 如果有 则在textview中显示该记录
		if (pre.getString("ip", null) != null) {
			et_setting_ip.setText(pre.getString("ip", null));
		}
		if (pre.getString("serverName", null) != null) {
			et_setting_serverName.setText(pre.getString("serverName", null));
		}
		if (pre.getString("port", null) != null) {
			et_setting_port.setText(pre.getString("port", null));
		}
		// 获得AlertDialog.Builder实例
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder
		// 设置标题
		.setMessage("设置")
		// 设置自定义视图
				.setView(v)
				// 设置确定按钮
				.setPositiveButton("保存", new DialogInterface.OnClickListener() {
					// 确定按钮事件
					public void onClick(DialogInterface dialog, int id) {
						// 得到组件
						et_setting_ip = (EditText) v
								.findViewById(R.id.et_setting_ip);
						et_setting_serverName = (EditText) v
								.findViewById(R.id.et_setting_serverName);
						et_setting_port = (EditText) v
								.findViewById(R.id.et_setting_port);
						cb_setting_saveName = (CheckBox) v
								.findViewById(R.id.cb_setting_saveName);

						// 得到值
						String ip = et_setting_ip.getText().toString();
						String serverName = et_setting_serverName.getText()
								.toString();
						String port = et_setting_port.getText().toString();

						// 共享信息 是下次自动填写表单
						SharedPreferences pre = getSharedPreferences(
								"user_msg", MODE_WORLD_WRITEABLE);
						SharedPreferences.Editor editor = pre.edit();
						editor.putString("ip", ip);
						editor.putString("serverName", serverName);
						editor.putString("port", port);
						editor.commit();
						// 判断是否勾选checkbox
						boolean saveName = cb_setting_saveName.isSelected();
						if (!ip.equals("") && !serverName.equals("")
								&& !port.equals("")) {
							// 得到ip和server拼接
							Login.ip = ip;
							HttpUtil.BASE_URL = "http://" + ip + ":" + port
									+ "/" + serverName + "/";
						}
					}
				}).setNegativeButton("返回", null);
		AlertDialog alert = builder.create();
		alert.show();
	}
	/**
	 * 显示进度框
	 * 
	 * @param message
	 *            提示消息
	 */
	public void showProgressDialog(String message) {
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage(message);
		// 设置点击外部窗口不消失
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setCancelable(true);
		progressDialog.show();
	}

	/**
	 * 取消进度框
	 */
	public void dismissProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	@Override
	protected void onDestroy() {
		
		View view = findViewById(R.id.bt_login);
		view.setBackgroundResource(0);
		view = findViewById(R.id.login_layout_linearLayout);
		view.setBackgroundResource(0);
		System.gc();
		super.onDestroy();
	}
	
}
