package com.guoyaohua.activity;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.Socket;
import java.net.UnknownHostException;

import android.R.drawable;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.guoyaohua.activity.R;
import com.guoyaohua.entity.GlobalVariable;

public class RealVedioActivity extends Activity {
	PowerManager.WakeLock wakeLock;
	private ImageView realImage;
	private Button start;
	private Button back;
	private Boolean flag;
	// 用于判断是否是第一张图
	boolean isFirst = true;
	// 定义menu菜单ID
	private final int MENU_1 = 0; // 设置
	private final int MENU_2 = 1; // 退出
	/**
	 * 进度对话框
	 */
	private ProgressDialog progressDialog;
	// 用于显示
	private Bitmap show;
	private String[] buttonstate = new String[4];
	/**
	 * Handler用来处理UI更新事件
	 */
	protected Handler handler = null;
	// 监控服务器信息
	private EditText et_setting_passWord;
	private EditText et_setting_port;
	private EditText et_setting_ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.realvideo_layout);
		// 保持屏幕常亮
		wakeLock = ((PowerManager) getSystemService(POWER_SERVICE))
				.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
						| PowerManager.ON_AFTER_RELEASE, "WakeLockActivity3");
		// 得到组件

		handler = new MyHandler();
		realImage = (ImageView) findViewById(R.id.iv_realvideo_realImage);
		start = (Button) findViewById(R.id.bt_realvideo_start);
		back = (Button) findViewById(R.id.bt_realvideo_return);
		// 调取本地配置文件中的用户信息
		SharedPreferences pre = getSharedPreferences("user_msg",
				MODE_WORLD_WRITEABLE);
		// 得到配置文件中的用户名
		pre.getString("RV_ip", null);
		pre.getString("RV_password", null);
		pre.getString("RV_port", null);
		// 判断本地是否有记录 如果有 则在textview中显示该记录
		if (pre.getString("RV_ip", null) != null) {
			GlobalVariable.ip = pre.getString("RV_ip", null);
		}
		if (pre.getString("RV_password", null) != null) {
			GlobalVariable.passWord = pre.getString("RV_password", null);
		}
		if (pre.getString("RV_port", null) != null) {
			GlobalVariable.prot = Integer.parseInt(pre.getString("RV_port",
					null));
		}

		// 绑定监听器
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (start.getText().equals(" ")) {// 一个空格为开始接收
					// 检测网络状态
					if (!checkNetworkInfo()) {
						setNetworkDialog();
						return;
					}
					// // 配置中没有输入IP或者端口
					// if (configService.getIp().equals("")
					// || configService.getRealPort().equals("")
					// || configService.getConfigPort().equals("")) {
					// showConfigDialog();
					// return;
					// }
					showProgressDialog("正在连接\n请稍候。。。");
					flag = true;
					// 创建一个线程处理网络传输
					new Thread(new RealThread()).start();
					start.setText("  ");
					// 此处改为“停止接收”
					start.setBackgroundResource(R.drawable.tingzhijieshou_selector);
					// start.setEnabled(true);
				} else if (start.getText().equals("  ")) {// 两个空格为停止接收
					// 当前没有录制 直接结束线程
					flag = false;
					start.setText(" ");
					// realImage.setImageResource(R.drawable.chuyixiu2);
					start.setBackgroundResource(R.drawable.kaishijieshou_selector);
					// realImage.setImageResource(R.drawable.chuyixiu2);
				}
				// }

				// else {
				// // 当前正在录制，提示是否结束录制
				// show();
				// }
			}

		});
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				flag = false;
				finish();

			}
		});
		// 点击imageview时可以全屏
		realImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (start.getText().equals(" ")) {// 一个空格为开始接收
					// 检测网络状态 //此处先不检测网络状态。2014年11月4日18:50:58
					// if (!checkNetworkInfo()) {
					// setNetworkDialog();
					// return;
					// }
					// // 配置中没有输入IP或者端口
					// if (configService.getIp().equals("")
					// || configService.getRealPort().equals("")
					// || configService.getConfigPort().equals("")) {
					// showConfigDialog();
					// return;
					// }
					showProgressDialog("正在连接\n请稍候。。。");
					flag = true;
					// 创建一个线程处理网络传输
					new Thread(new RealThread()).start();
					start.setText("  ");
					// 此处改为“停止接收”
					start.setBackgroundResource(R.drawable.tingzhijieshou_selector);
					// start.setEnabled(true);
				} else if (start.getText().equals("  ")) {// 两个空格为停止接收
					// 当前没有录制 直接结束线程
					flag = false;
					start.setText(" ");
					start.setBackgroundResource(R.drawable.kaishijieshou_selector);
					// realImage.setImageResource(R.drawable.chuyixiu2);
				}
			}
		});

		// /////////////////////////////////////
		// 面包兄给的
		ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		System.out.println("可用堆大小:" + activityManager.getMemoryClass());

		// ////////////////////////////////////////
	}

	/**
	 * 检测网络状态
	 * 
	 * @return
	 */
	public boolean checkNetworkInfo() {
		ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		// mobile 3G Data Network
		State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		// wifi
		State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();

		// 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
		if (mobile == State.CONNECTED || mobile == State.CONNECTING)
			return true;
		if (wifi == State.CONNECTED || wifi == State.CONNECTING)
			return true;
		return false;
	}

	/**
	 * 设置网络对话框
	 */
	public void setNetworkDialog() {
		Dialog dialog = new AlertDialog.Builder(this)
				.setTitle("设置网络")
				.setMessage("您要设置网络吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// startActivity(new
						// Intent(Settings.ACTION_WIRELESS_SETTINGS));
						// 进入无线网络配置界面
						startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
						// //进入手机中的wifi网络设置界面
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).create();
		dialog.show();
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
	 * 实时传输进程
	 * 
	 * @author John Kwok
	 * 
	 */
	class RealThread implements Runnable {

		@Override
		public void run() {
			int j = 0;
			InputStream in = null;
			SoftReference<Bitmap> bitmap;// 声明弱引用
			Socket socket = null;
			try {
				socket = new Socket(GlobalVariable.ip, GlobalVariable.prot);
				DataInputStream din = new DataInputStream(
						socket.getInputStream());
				DataOutputStream dout = new DataOutputStream(
						socket.getOutputStream());
				dout.writeUTF(GlobalVariable.passWord);
				if (!din.readUTF().equals("true")) {
					handler.sendEmptyMessage(8);
					return;
				}
				in = socket.getInputStream();
				// new Thread(new get()).start();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// socket连接异常或者输入流获取异常
				handler.sendEmptyMessage(2);
				e.printStackTrace();
			}
			// 使用参数获取合适的图片
			// 设置options 可以降低内存
			// BitmapFactory.Options options = new BitmapFactory.Options();
			// options.inJustDecodeBounds = false;
			// options.inSampleSize = 4;
			// bitmap = BitmapFactory.decodeStream(in, null, options);

			while (true) {

				// 从输入流创建bitmap
				// 该方法居然没有异常抛出
				// bitmap = BitmapFactory.decodeStream(in);
				
//				try {
//					byte[] data = readStream(in);
//					System.out.println("输入流数据大小------"+data.length);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
				
				bitmap = new SoftReference<Bitmap>(
						BitmapFactory.decodeStream(in));
				if (bitmap.get() != null) {

					// // 回收bitmap
					// BitmapDrawable bitmapDrawable = (BitmapDrawable)
					// realImage
					// .getBackground();
					//
					// if (bitmapDrawable != null) {
					//
					// Bitmap hisBitmap = bitmapDrawable.getBitmap();
					//
					// if (hisBitmap.isRecycled() == false)
					//
					// {
					//
					// hisBitmap.recycle();
					//
					// }
					//
					// }

					// 从流中创建了一个bitmap,将引用赋给show

					show = bitmap.get();
					handler.sendEmptyMessage(0);
					j = 0;

				} else {
					if (j++ > 50) {
						// 连续50次没有图片，认为服务端断开
						try {
							if (in != null && in.read() == -1) {
								// 服务端没有数据传入，服务端断开
								handler.sendEmptyMessage(3);
								flag = false;
								// break;
							}
						} catch (IOException e) {
							handler.sendEmptyMessage(3);
							flag = false;
							// break;
						}
					}
				}
				// flag为true不执行，false时关闭连接
				if (!flag) {
					try {
						if (in != null) {
							in.close();
						}
						if (socket != null) {
							socket.close();
						}
						// 回收bitmap

						if (show.isRecycled() == false)

						{
							if (!isFirst) {
								show.recycle();
							} else {
								isFirst = false;
							}
						}

					} catch (IOException e) {
						e.printStackTrace();
						break;
					}
					in = null;
					socket = null;
					handler.sendEmptyMessage(1);
					break;
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 从服务端获取配置
	 * 
	 */
	class get implements Runnable {

		@Override
		public void run() {
			try {
				Socket socket = new Socket(GlobalVariable.ip,
						GlobalVariable.prot);
				DataOutputStream dout = new DataOutputStream(
						socket.getOutputStream());
				DataInputStream din = new DataInputStream(
						socket.getInputStream());
				dout.writeUTF(GlobalVariable.passWord);
				if (!din.readUTF().equals("true")) {
					handler.sendEmptyMessage(8);
					return;
				}
				dout.writeUTF("get:");
				buttonstate[1] = din.readUTF().split("/")[5];
				if (buttonstate != null) {
					handler.sendEmptyMessage(4);
				}
				dout.close();
				din.close();
				socket.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				handler.sendEmptyMessage(1);
				e.printStackTrace();
			}
		}
	}

	protected void dealMessage(Message msg) {
		dismissProgressDialog();
		switch (msg.what) {
		case 0:
			// 回收bitmap
			BitmapDrawable bitmapDrawable = (BitmapDrawable) realImage
					.getDrawable();

			if (bitmapDrawable != null) {

				Bitmap hisBitmap = bitmapDrawable.getBitmap();

				if (hisBitmap.isRecycled() == false)

				{
					if (!isFirst) {
						hisBitmap.recycle();
					} else {
						isFirst = false;
					}
				}

			}
			System.gc();
			// 显示bitmap
			realImage.setImageBitmap(show);

			break;
		case 1:
			// 点击关闭按钮
			realImage.setImageResource(R.drawable.chuyixiu2);
			break;
		case 2:
			// 初次连接异常
			showToast("无法连接服务端，请确保服务端已开启");
			break;
		case 3:
			// 连接中服务端关闭
			showToast("服务端已经关闭，请打开服务端重试");
			realImage.setImageResource(R.drawable.chuyixiu2);
			// start.setText("接收画面");
			start.setText(" ");
			start.setBackgroundResource(R.drawable.kaishijieshou_selector);
			break;
		case 4:
			// 服务端连接成功，改变按钮状态
			start.setText("停止接收");
			// changeButton(buttonstate);
			break;
		case 5:
			showToast("操作成功");
			// changeButton(buttonstate);
			break;
		case 6:
			showToast("操作失败");
			break;
		case 7:
			start.setText("接收画面");
			break;
		case 8:
			showToast("连接密码错误");
			flag = false;
			start.setText("接收画面");

			break;
		}
	}

	/**
	 * 自定义handler
	 * 
	 */
	@SuppressLint("HandlerLeak")
	class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			dealMessage(msg);
		}
	}

	/**
	 * 获取Handler
	 * 
	 * @return
	 */
	public Handler getHandler() {
		return this.handler;
	}

	/**
	 * 取消进度框
	 */
	public void dismissProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	/**
	 * 显示Toast
	 */
	public void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 对于菜单id的要求，必须是一个常量。故在定义菜单id时需要用到final关键字

		menu.add(0, MENU_1, 0, "设置");
		menu.add(0, MENU_2, 1, "退出");
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
			flag = false;
			finish();
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
		final View v = inflater
				.inflate(R.layout.realvedio_setting_layout, null);
		// 得到组件
		et_setting_ip = (EditText) v.findViewById(R.id.et_realvedio_setting_ip);
		et_setting_passWord = (EditText) v
				.findViewById(R.id.et_realvedio_setting_password);
		et_setting_port = (EditText) v
				.findViewById(R.id.et_realvedio_setting_port);

		// 调取本地配置文件中的用户信息
		SharedPreferences pre = getSharedPreferences("user_msg",
				MODE_WORLD_WRITEABLE);
		// 得到配置文件中的用户名
		pre.getString("RV_ip", null);
		pre.getString("RV_password", null);
		pre.getString("RV_port", null);
		// 判断本地是否有记录 如果有 则在textview中显示该记录
		if (pre.getString("RV_ip", null) != null) {
			et_setting_ip.setText(pre.getString("RV_ip", null));
		}
		if (pre.getString("RV_password", null) != null) {
			et_setting_passWord.setText(pre.getString("RV_password", null));
		}
		if (pre.getString("RV_port", null) != null) {
			et_setting_port.setText(pre.getString("RV_port", null));
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
								.findViewById(R.id.et_realvedio_setting_ip);
						et_setting_passWord = (EditText) v
								.findViewById(R.id.et_realvedio_setting_password);
						et_setting_port = (EditText) v
								.findViewById(R.id.et_realvedio_setting_port);
						// 得到值
						String ip = et_setting_ip.getText().toString();
						String passWord = et_setting_passWord.getText()
								.toString();
						String port = et_setting_port.getText().toString();

						// 共享信息 是下次自动填写表单
						SharedPreferences pre = getSharedPreferences(
								"user_msg", MODE_WORLD_WRITEABLE);
						SharedPreferences.Editor editor = pre.edit();
						editor.putString("RV_ip", ip);
						editor.putString("RV_password", passWord);
						editor.putString("RV_port", port);
						editor.commit();

						if (!ip.equals("") && !passWord.equals("")
								&& !port.equals("")) {
							GlobalVariable.ip = ip;
							GlobalVariable.passWord = passWord;
							GlobalVariable.prot = Integer.parseInt(port);
						}
					}
				}).setNegativeButton("返回", null);
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (wakeLock != null) {
			wakeLock.release();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		wakeLock.acquire();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			flag = false;
			finish();

			return true;
		}
		return false;
	}

	@Override
	protected void onDestroy() {
		System.gc();
		super.onDestroy();
	}

	/*
	 * 测试用 得到图片字节流 数组大小
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}
}
