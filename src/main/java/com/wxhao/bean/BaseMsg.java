package com.wxhao.bean;

/**
 * @ClassName: BaseMsg
 * @Description: 消息基类
 * @version V1.0
 */
public class BaseMsg {

	/**
	 * 开发者微信号
	 */
	protected String toUserName;
	/**
	 * 发送方帐号（一个OpenID）
	 */
	protected String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	protected String createTime;
	/**
	 * 消息类型
	 */
	protected String msgType;
	/**
	 * 消息id，64位整型
	 */
	protected String msgId;
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
