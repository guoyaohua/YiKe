package com.guoyaohua.activity;

import com.guoyaohua.activity.R;
import com.wanpu.pay.PayConnect;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

public class Welcome extends Activity{
	boolean isFirstUse;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.welcom_layout);
		super.onCreate(savedInstanceState);
		PayConnect.getInstance("f7190357e6fd2130b635706e503b7b34", "APP_PID", this);
		
		 Handler handler = new Handler();
		 handler.postDelayed(new Runnable(){

			@Override
			public void run() {
//				startActivity(new Intent(getApplication(),Login.class));
//				Welcome.this.finish();
				
				
				
				
				//读取SharedPreferences中需要的数据  
	            SharedPreferences preferences = getSharedPreferences("isFirstUse",MODE_WORLD_READABLE);  
	  
	           isFirstUse = preferences.getBoolean("isFirstUse", true);  
	  
	            /** 
	             *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面 
	             */  
	            if (isFirstUse) {  
	            	startActivity(new Intent(getApplication(),GuideActivity.class));
					Welcome.this.finish();  
	            } else {  
	            	startActivity(new Intent(getApplication(),Login.class));
					Welcome.this.finish(); 
	            }  
	            finish();  
//	              
//	            //实例化Editor对象  
//	            Editor editor = preferences.edit();  
//	            //存入数据  
//	            editor.putBoolean("isFirstUse", false);  
//	            //提交修改  
//	            editor.commit();  
			}
			 
		 }, 3000);
		
		
	}
	@Override
	protected void onDestroy() {
		LinearLayout background = (LinearLayout) findViewById(R.id.welcom_layout_linearLayout);
		// 回收bitmap
		BitmapDrawable bitmapDrawable = (BitmapDrawable) background
				.getBackground();

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
}
