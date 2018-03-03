package com.guoyaohua.activity;

import java.util.Date;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AboutUsActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.aboutus_layout);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		ImageView pic = (ImageView) findViewById(R.id.iv_us);
		// 回收bitmap
		BitmapDrawable bitmapDrawable = (BitmapDrawable) pic
				.getDrawable();

		if (bitmapDrawable != null) {

			Bitmap hisBitmap = bitmapDrawable.getBitmap();

			if (hisBitmap.isRecycled() == false)

			{
				
					hisBitmap.recycle();
				
			}

		}
		
		System.gc();
		super.onDestroy();
	}
	/**
	 * 复写onKeyDown方法 监听back点击事件，当点击一次back时不会退出，点击两次时才会退出
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
			
		return super.onKeyDown(keyCode, event);
	}
}
