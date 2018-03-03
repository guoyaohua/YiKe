package com.guoyaohua.activity;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.guoyaohua.activity.R;
import com.guoyaohua.adapter.IndexAdapter;
import com.guoyaohua.entity.IndexGridItemEntity;
import com.guoyaohua.entity.NewsListEntity;
import com.guoyaohua.provider.Tables;
import com.guoyaohua.units.Constants;
import com.guoyaohua.util.CheckTable;
import com.guoyaohua.util.HttpUtil;
import com.guoyaohua.widget.TotiPotentGridView;
import com.guoyaohua.widget.TotiPotentGridView.ICommViewListener;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

public class GridContentActivity extends Fragment implements ICommViewListener {
	View mView = null;
	TotiPotentGridView loadDataView = null;
	IndexAdapter indexAdapter = null;
	GridView gridView = null;
	String defaultTitle = "";
	// 服务器返回的信息
	private String[] strs;
	private Document doc;
	private NodeList nl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public static GridContentActivity newInstance(String title) {
		GridContentActivity fragment = new GridContentActivity(title);
		return fragment;
	}

	public GridContentActivity(String defaultTitle) {
		this.defaultTitle = defaultTitle;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(
				R.layout.gridcontent_layout, null);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadDataView = (TotiPotentGridView) mView
				.findViewById(R.id.loaddatagridview);
		loadDataView.setCommViewListener(this);
		gridView = loadDataView.getGridView();
		indexAdapter = new IndexAdapter(getActivity());
		gridView.setAdapter(indexAdapter);
		loadDataView.initData();
	}

	@Override
	public List<Object> doInBackGround(int CurrentPage) {
		// // 访问服务器url
		// String url = HttpUtil.BASE_URL+"servlet/CheckTableServlet";
		// // 查询返回结果
		// String result = HttpUtil.queryStringForPost(url);
		// // 拆分字符串，转换成对象，添加到列表
		// strs = result.split(";");
		//

		// 访问服务器url
//		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateTableTblServlet";
		//百度云修改
		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateTableTblServlet";
		try {
			// 实例化URL对象
			URL url = new URL(urlStr);
			// 打开连接
			URLConnection conn = url.openConnection();
			// 获得输入流
			InputStream in = conn.getInputStream();
			// 实例化DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// 实例化DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 获得Document
			doc = builder.parse(in);
			// 获得节点列表
			nl = doc.getElementsByTagName("table");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects(CurrentPage);
	}

	@Override
	public void callBackListData(List<Object> list) {
		indexAdapter.setList(list, true);
	}

	@Override
	public void onHeadRefresh() {
		indexAdapter.clear();
	}

	/**
	 * 此函数是用于构建列表对象 可以将其功能改变为从服务器获得菜单对象，然后加入列表中
	 * 
	 * @param currentpage
	 * @return
	 */
	public List<Object> objects(int currentpage) {

		ArrayList<Object> arrayList = new ArrayList<Object>();
		if (currentpage == 2) {
			return arrayList;
		}
		// for (int i = 0; i < strs.length; i++) {
		// int idx = strs[i].indexOf(",");
		// //拆分字符串 由“，”拆成左右两部分
		// int num = Integer.parseInt(strs[i].substring(0, idx));
		// int flag = Integer.parseInt(strs[i].substring(idx+1));
		// IndexGridItemEntity newsListEntity=new IndexGridItemEntity();
		// if(flag==1){
		// // 设置ImageView图片为 有人
		//
		// newsListEntity.setSERVER_DOMAIN("http://pic12.nipic.com/20110104/5679289_235608041110_2.jpg");
		//
		// }else{
		// // 设置ImageView图片为 空位
		// newsListEntity.setSERVER_DOMAIN("http://www.zlcool.com/d/file/2010/03/08/be66e4a5e4541700cb737357204811eb.jpg");
		// }
		// newsListEntity.setLINKED_TITLE("有"+num+"人就餐");
		// arrayList.add(newsListEntity);
		//
		//
		// }
		// ///////////////////////////////////////
		// /for (int i = 0; i < nl.getLength(); i++) {
		// // 实例化ContentValues
		// ContentValues values = new ContentValues();
		// // 解析XML文件获得菜单id
		// int id = Integer.parseInt(doc.getElementsByTagName("id").item(i)
		// .getFirstChild().getNodeValue());
		// // 人数
		// int num = Integer.parseInt(doc.getElementsByTagName("num").item(i)
		// .getFirstChild().getNodeValue());
		// // 状态
		// float flag = Float.parseFloat(doc.getElementsByTagName("flag")
		// .item(i).getFirstChild().getNodeValue());
		// // 描述
		// String description = doc.getElementsByTagName("description")
		// .item(i).getFirstChild().getNodeValue();
		//
		// IndexGridItemEntity newsListEntity = new IndexGridItemEntity();
		// if (flag == 1) {
		// // 设置ImageView图片为 有人
		//
		// newsListEntity
		// .setSERVER_DOMAIN("http://pic12.nipic.com/20110104/5679289_235608041110_2.jpg");
		//
		// } else {
		// // 设置ImageView图片为 空位
		// newsListEntity
		// .setSERVER_DOMAIN("http://www.zlcool.com/d/file/2010/03/08/be66e4a5e4541700cb737357204811eb.jpg");
		// }
		// newsListEntity.setLINKED_TITLE(description + "/n有" + num + "人就餐");
		// arrayList.add(newsListEntity);
		//
		// }
		// ////////////////////////////////////////////////////////////////////////////
		// 循环将数据保存到餐桌表
		for (int i = 0; i < nl.getLength(); i++) {
			// 实例化ContentValues
			ContentValues values = new ContentValues();
			// 解析XML文件获得餐桌id
			int id = Integer.parseInt(doc.getElementsByTagName("id").item(i)
					.getFirstChild().getNodeValue());
			// 人数
			int num = Integer.parseInt(doc.getElementsByTagName("num").item(i)
					.getFirstChild().getNodeValue());
			// 状态
			int flag = Integer.parseInt(doc.getElementsByTagName("flag")
					.item(i).getFirstChild().getNodeValue());
			// 备注
			String description = doc.getElementsByTagName("description")
					.item(i).getFirstChild().getNodeValue();
			// 创建一个餐桌对象
			IndexGridItemEntity newsListEntity = new IndexGridItemEntity();
			if (flag == 1) {
				// 设置ImageView图片为 有人

				newsListEntity
						.setSERVER_DOMAIN("http://"+Login.ip+":6666/HTTP/youren.png");
				
				newsListEntity.setNUM("有"+num+"人就餐");
				
			} else {
				// 设置ImageView图片为 空位
				newsListEntity
					.setSERVER_DOMAIN("http://"+Login.ip+":6666/HTTP/kongwei.png");
			//	.setSERVER_DOMAIN("http://pic1.win4000.com/wallpaper/6/53981f503d1af.jpg");
				newsListEntity.setNUM("空位");
			
			}
			newsListEntity.setLINKED_TITLE(description);
			arrayList.add(newsListEntity);
		}
		return arrayList;
	}
}
