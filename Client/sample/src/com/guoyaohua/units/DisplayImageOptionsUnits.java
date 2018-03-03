package com.guoyaohua.units;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
/**
* @类名:DisplayImageOptionsUnits 
* @描述:TODO(获取请求图片的配置信息) 
* @作者:zhaohao 
* @时间 2013-6-16 下午3:23:53
 */
public class DisplayImageOptionsUnits {
  public static DisplayImageOptionsUnits displayImageOptionsUnits=new DisplayImageOptionsUnits();
  DisplayImageOptions options=null;
  int defaultid=0;
  public static DisplayImageOptionsUnits getIns(){
	  return displayImageOptionsUnits;
  }
 public DisplayImageOptions displayImageOptions(int resId){
	 	//当options对象不为空并且defaultid与resid一致时候 且返回options对象
		 if(null!=options&&defaultid==resId){
			 return options;
		 }
	     options = new DisplayImageOptions.Builder()
		.showStubImage(resId)
		.showImageForEmptyUri(resId)
		.showImageOnFail(resId)
		.cacheInMemory()
		.cacheOnDisc()
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
	    defaultid=resId;
	    return options;
 	}
}
