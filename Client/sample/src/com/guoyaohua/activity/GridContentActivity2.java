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
import com.guoyaohua.adapter.IndexAdapter2;
import com.guoyaohua.entity.NewsListEntity;
import com.guoyaohua.util.HttpUtil;
import com.guoyaohua.widget.TotiPotentGridView;
import com.guoyaohua.widget.TotiPotentGridView2;
import com.guoyaohua.widget.TotiPotentGridView.ICommViewListener;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
/**
 * 该类用于加载gridview中的item
 * @author John Kwok
 *
 */
public class GridContentActivity2 extends Fragment implements com.guoyaohua.widget.TotiPotentGridView2.ICommViewListener {
	View mView = null;
	TotiPotentGridView2 loadDataView2 = null;
	IndexAdapter2 indexAdapter2 = null;
	GridView gridView = null;
	String defaultTitle = "";
	private final int numberPerPage = 12;
	// 服务器返回的信息
	private String[] strs;
	private Document doc;
	private NodeList nl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public static GridContentActivity2 newInstance(String title) {
		GridContentActivity2 fragment = new GridContentActivity2(title);
		return fragment;
	}

	public GridContentActivity2(String defaultTitle) {
		this.defaultTitle = defaultTitle;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(
				R.layout.gridcontent_layout2, null);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadDataView2 = (TotiPotentGridView2) mView
				.findViewById(R.id.loaddatagridview2);
		loadDataView2.setCommViewListener(this);
		gridView = loadDataView2.getGridView();
		indexAdapter2 = new IndexAdapter2(getActivity());
		gridView.setAdapter(indexAdapter2);
		loadDataView2.initData();
	}

	@Override
	public List<Object> doInBackGround(int CurrentPage) {
		// 访问服务器url
//		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateServlet";
		//百度云修改
		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateServlet";
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
			nl = doc.getElementsByTagName("menu");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects(CurrentPage);
	}

	@Override
	public void callBackListData(List<Object> list) {
		indexAdapter2.setList(list, true);
	}

	@Override
	public void onHeadRefresh() {
		indexAdapter2.clear();
	}

	/**
	 * 此函数是用于构建列表对象 可以将其功能改变为从服务器获得菜单对象，然后加入列表中
	 * 
	 * @param currentpage
	 * @return
	 */
	public List<Object> objects(int currentpage) {
		// 声明集合
		ArrayList<Object> arrayList = new ArrayList<Object>();
		// 获得当前已加入的菜品数目
		int number = (currentpage - 1) * numberPerPage;
		// 当前遍历到的指定种类的菜品数量
		int currentNum = 0;
		for (int i = 0; i < nl.getLength(); i++) {
			// 获取分类编号
			int typeId = Integer.parseInt(doc.getElementsByTagName("typeId")
					.item(i).getFirstChild().getNodeValue());
			// 判断是否为该种类的菜品
			if (ViewPagerListViewActivity.CONTENT[typeId - 1]
					.equals(defaultTitle)) {
				currentNum++;
				if (currentNum > number && currentNum <= number + numberPerPage) {
					// 把菜品加入集合arrayList
					// 实例化ContentValues
					ContentValues values = new ContentValues();
					// 解析XML文件获得菜单id
					int id = Integer.parseInt(doc.getElementsByTagName("id")
							.item(i).getFirstChild().getNodeValue());
					// 名称
					String name = doc.getElementsByTagName("name").item(i)
							.getFirstChild().getNodeValue();
					// 图片路径
					String pic = doc.getElementsByTagName("pic").item(i)
							.getFirstChild().getNodeValue();

					// 价格
					int price = Integer.parseInt(doc
							.getElementsByTagName("price").item(i)
							.getFirstChild().getNodeValue());

					// 备注
					String remark = doc.getElementsByTagName("remark").item(i)
							.getFirstChild().getNodeValue();
					// 判断是哪个种类 加入响应的listview中

					NewsListEntity newsListEntity = new NewsListEntity();
					newsListEntity.setNEWS_COMMENT_COUNT(price + "");
					newsListEntity.setNEWS_TITLE(name);
					newsListEntity.setSERVER_DOMAIN("http://" + Login.ip + ":6666/"
					// + Login.HttpName
							+ pic);
					newsListEntity.setNEWS_MEMO(remark);
					newsListEntity.setNEWS_ID(id + "");
					arrayList.add(newsListEntity);
				} else if (currentNum > number + numberPerPage) {
					break;
				}
			}
		}

		return arrayList;

	}
}
