package com.guoyaohua.app;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.guoyaohua.units.DisplayImageOptionsUnits;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class App extends Application {
	ImageLoader imageLoader=null;
	static App app=null;
	public static App getIns(){
		return app;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		app=this;
		imageLoader=ImageLoader.getInstance();
		initImageLoader(getApplicationContext());
	}

	public void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.enableLogging() // Not necessary in common
				.build();
		ImageLoader.getInstance().init(config);
	}
	/**
	 * 加载图片
	 * @param imgurl
	 * @param imageView
	 * @param defaultPicId
	 */
	public void display(String imgurl,ImageView imageView,int defaultPicId){
		imageLoader.displayImage(imgurl, imageView, DisplayImageOptionsUnits.getIns().displayImageOptions(defaultPicId));
	}
}