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
import com.guoyaohua.adapter.NewsIndexAdapter;
import com.guoyaohua.entity.NewsListEntity;
import com.guoyaohua.provider.Menus;
import com.guoyaohua.units.Constants;
import com.guoyaohua.util.HttpUtil;
import com.guoyaohua.widget.LoadMoreListView;
import com.guoyaohua.widget.RequestDataAsyncTask;
import com.guoyaohua.widget.TotiPotentListView;
import com.guoyaohua.widget.TotiPotentListView.ICommViewListener;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * @类名:ContentActivity
 * @描述:TODO(内容页面，异步加载数据，下拉刷新，上拉加载更多)
 * @作者:郭耀华
 * 
 */
public class ContentActivity extends Fragment implements ICommViewListener {
	View mView = null;
	TotiPotentListView loadDataView = null;
	NewsIndexAdapter newsIndexAdapter = null;
	LoadMoreListView loadMoreListView = null;
	String defaultTitle = "";
	private Document doc;
	private NodeList nl;
	private final int numberPerPage = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

//	public ContentActivity() {
//		this.defaultTitle = "今日推荐";
//	}

	// 传入了标题名称
	public static ContentActivity newInstance(String title) {
		ContentActivity fragment = new ContentActivity(title);
		return fragment;
	}

	public ContentActivity(String defaultTitle) {
		this.defaultTitle = defaultTitle;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.content_layout, null);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadDataView = (TotiPotentListView) mView
				.findViewById(R.id.loaddataview);
		loadDataView.setCommViewListener(this);

		loadMoreListView = loadDataView.getLoadMoreListView();
		loadDataView.setListViewDriver(0);
		newsIndexAdapter = new NewsIndexAdapter(getActivity());
		loadMoreListView.setAdapter(newsIndexAdapter);
		loadDataView.initData();
	}

	/**
	 * doInBackGround 最终实现的位置 此处可以用于获取服务器数据 然后传递给 objects方法 加入到列表中
	 * 此处通过判断defaultTitle的值来进行不同的任务
	 */
	@Override
	public List<Object> doInBackGround(int CurrentPage) {
		// System.out.println("do in back ground");
		// 访问服务器url
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
		newsIndexAdapter.setList(list, true);
	}

	@Override
	public void onHeadRefresh() {
		newsIndexAdapter.clear();
	}

	/**
	 * 此处为获得列表对象并返回 可以修改为服务器调用
	 * 
	 * @param currentpage
	 * @return
	 */
	/**
	 * 　　　 Element名称 Element类型 备注 RETURN_CODE String 即消息响应码 RETURN_DESC String
	 * 结果返回描述，必须填写客户能阅读的失败原因 LIST list SERVER_DOMAIN String url NEWS_TITLE
	 * String 标题 NEWS_COMMENT_COUNT String 评论数 NEWS_MODILAR_ID int 栏目编号 NEWS_ID
	 * Int 新闻编号 NEWS_MEMO String 简介 PAGE_URL String 页面URL
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
					newsListEntity.setSERVER_DOMAIN("http://" + Login.ip + "/"
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
