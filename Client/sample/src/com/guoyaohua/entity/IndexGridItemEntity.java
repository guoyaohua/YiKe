package com.guoyaohua.entity;
/**
* @类名:IndexGridItemEntity 
* @描述:TODO(首界面实体) 
* @作者:guoyaohua 
* @时间 2013-5-17 上午10:15:43
 */
public class IndexGridItemEntity {
	/**
	 * Element名称	Element类型	备注
		RETURN_CODE	String	即消息响应码
		RETURN_DESC	String	结果返回描述，必须填写客户能阅读的失败原因
		LIST	list	
		SERVER_DOMAIN	String	url地址
		LINKED_DATA_ID	int	数据编号
		LINKED_DATA_TYPE	String	列表接口类型
		LINKED_TITLE	String	标题
		SHOW_MODULE_TYPE	String	枚举类型
		OUT_LINK	String	内链地址url
		ORDERID             存储与该桌位绑定的订单号
		NUM                 存储人数
	 */
	private String ORDERID="";
	
	private String NUM="";
	
	private String SERVER_DOMAIN="";
	private String LINKED_DATA_ID="";
	private String LINKED_DATA_TYPE="";
	private String LINKED_TITLE="";
	private String SHOW_MODULE_TYPE="";
	private String OUT_LINK="";
	public String getOUT_LINK() {
		return OUT_LINK;
	}
	public void setOUT_LINK(String oUT_LINK) {
		OUT_LINK = oUT_LINK;
	}
	public String getSERVER_DOMAIN() {
		return SERVER_DOMAIN;
	}
	public void setSERVER_DOMAIN(String sERVER_DOMAIN) {
		SERVER_DOMAIN = sERVER_DOMAIN;
	}
	public String getLINKED_DATA_ID() {
		return LINKED_DATA_ID;
	}
	public void setLINKED_DATA_ID(String lINKED_DATA_ID) {
		LINKED_DATA_ID = lINKED_DATA_ID;
	}
	public String getLINKED_DATA_TYPE() {
		return LINKED_DATA_TYPE;
	}
	public void setLINKED_DATA_TYPE(String lINKED_DATA_TYPE) {
		LINKED_DATA_TYPE = lINKED_DATA_TYPE;
	}
	public String getLINKED_TITLE() {
		return LINKED_TITLE;
	}
	public void setLINKED_TITLE(String lINKED_TITLE) {
		LINKED_TITLE = lINKED_TITLE;
	}
	public String getSHOW_MODULE_TYPE() {
		return SHOW_MODULE_TYPE;
	}
	public void setSHOW_MODULE_TYPE(String sHOW_MODULE_TYPE) {
		SHOW_MODULE_TYPE = sHOW_MODULE_TYPE;
	}
 
	public String getORDERID() {
		return ORDERID;
	}
	public void setORDERID(String oRDERID) {
		ORDERID = oRDERID;
	}
	public String getNUM() {
		return NUM;
	}
	public void setNUM(String nUM) {
		NUM = nUM;
	}
}
