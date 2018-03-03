package com.guoyaohua.entity;


 
public class NewsListEntity {
	/**
	 * 　　　 Element名称	Element类型	备注
			RETURN_CODE	String	即消息响应码
			RETURN_DESC	String	结果返回描述，必须填写客户能阅读的失败原因
			LIST	list	
			SERVER_DOMAIN	String	url   //猜测应该是图片的url
			NEWS_TITLE	String	标题
			NEWS_COMMENT_COUNT	String	评论数
			NEWS_MODILAR_ID	int	栏目编号
			NEWS_ID	Int	新闻编号
			NEWS_MEMO	String	简介
			PAGE_URL	String	页面URL
	 */
    private String NEWS_COMMENT_COUNT="";
    private String PAGE_URL="";
	private String SERVER_DOMAIN="";
	public String getNEWS_COMMENT_COUNT() {
		return NEWS_COMMENT_COUNT;
	}
	public void setNEWS_COMMENT_COUNT(String nEWS_COMMENT_COUNT) {
		NEWS_COMMENT_COUNT = nEWS_COMMENT_COUNT;
	}
	public String getPAGE_URL() {
		return PAGE_URL;
	}
	public void setPAGE_URL(String pAGE_URL) {
		PAGE_URL = pAGE_URL;
	}
	public String getSERVER_DOMAIN() {
		return SERVER_DOMAIN;
	}
	public void setSERVER_DOMAIN(String sERVER_DOMAIN) {
		SERVER_DOMAIN = sERVER_DOMAIN;
	}
	public String getNEWS_TITLE() {
		return NEWS_TITLE;
	}
	public void setNEWS_TITLE(String nEWS_TITLE) {
		NEWS_TITLE = nEWS_TITLE;
	}
	public String getNEWS_MODILAR_ID() {
		return NEWS_MODILAR_ID;
	}
	public void setNEWS_MODILAR_ID(String nEWS_MODILAR_ID) {
		NEWS_MODILAR_ID = nEWS_MODILAR_ID;
	}
	public String getNEWS_ID() {
		return NEWS_ID;
	}
	public void setNEWS_ID(String nEWS_ID) {
		NEWS_ID = nEWS_ID;
	}
	public String getNEWS_MEMO() {
		return NEWS_MEMO;
	}
	public void setNEWS_MEMO(String nEWS_MEMO) {
		NEWS_MEMO = nEWS_MEMO;
	}
	private String NEWS_TITLE="";
	private String NEWS_MODILAR_ID="";
	private String NEWS_ID="";
	private String NEWS_MEMO="";
}
