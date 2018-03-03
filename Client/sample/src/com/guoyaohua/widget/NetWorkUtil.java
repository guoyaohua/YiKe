package com.guoyaohua.widget;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtil {
    /**
     * 判断有无网络 true有网 反正 返回false
     * @return
     */
	public static boolean netWorkConnection(Context context)
	{
    	ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		if (networkinfo == null || (null != networkinfo && !networkinfo.isAvailable())) 
		{
			return false;
		}else{
			return true;
		}
    }
	
	/**
	 * 网络类型
	 * @param context
	 * @return
	 */
	public static boolean isWifi(Context context)
	{
		boolean isWifi = false;
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		if (networkinfo != null && networkinfo.isAvailable()) 
		{
			if(networkinfo.getType() == ConnectivityManager.TYPE_WIFI)
			{
				isWifi = true;
			}
		}
		return isWifi;
	}
}
