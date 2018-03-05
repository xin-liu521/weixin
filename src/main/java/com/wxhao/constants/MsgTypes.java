package com.wxhao.constants;

import com.wxhao.responsemsg.ResPonseTextMsg;
import com.wxhao.xmlwriter.TextMsgWriter;

import java.util.Date;

/**
 * @ClassName: MsgTypes
 * @Description: 消息类型枚举
 * @version V1.0
 */
public class MsgTypes {

	/**
	 * 文本消息
	 */
	public final static String TEXT = "text";
	
	/**
	 * 图片消息
	 */
	public final static String IMAGE = "image";
	
	/**
	 * 语音消息
	 */
	public final static String VOICE = "voice";
	
	/**
	 * 视频消息
	 */
	public final static String VIDEO = "video";
	
	/**
	 * 小视频消息
	 */
	public final static String SHORTVIDEO = "shortvideo";
	
	/**
	 * 地理消息
	 */
	public final static String LOCATION = "location";
	
	/**
	 * 链接消息
	 */
	public final static String LINK = "link";

	/**
	 * 关注事件
	 */
	public final static String EVENT = "event";

	// 事件类型：subscribe(订阅)
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// 事件类型：unsubscribe(取消订阅)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// 事件类型：scan(用户已关注时的扫描带参数二维码)
	public static final String EVENT_TYPE_SCAN = "scan";
	// 事件类型：LOCATION(上报地理位置)
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	// 事件类型：CLICK(自定义菜单)
	public static final String EVENT_TYPE_CLICK = "CLICK";

	// 事件类型：VIEW(自定义菜单)
	public static final String EVENT_TYPE_VIEW = "VIEW";

	/**

	 * 初始化回复消息

	 */

	public static String initText(String toUSerName,String fromUserName,String content) throws Exception {

		ResPonseTextMsg text = new ResPonseTextMsg();

		text.setFromUserName(toUSerName);

		text.setToUserName(fromUserName);

		text.setMsgType(TEXT);

		text.setCreateTime(new Date().getTime()+"");

		text.setContent(content);

		//text.setMsgId(MsgId);
		return TextMsgWriter.getXmlString(text);

	}

}

