package com.wxhao.responsemsg;

import com.wxhao.bean.BaseMsg;

/**
 * @ClassName: ResPonseTextMsg
 * @Description: 响应文本消息
 * @version V1.0
 */
public class ResPonseTextMsg extends BaseMsg
{
	  private String content;

	  public String getContent()
	  {
	    return this.content;
	  }

	  public void setContent(String content) {
	    this.content = content;
	  }
	  
	  public ResPonseTextMsg(){
		  
	  }

	  public ResPonseTextMsg(String toUserName, String fromUserName, String createTime, String msgType, String msgId, String content)
	  {
	    this.toUserName = toUserName;
	    this.fromUserName = fromUserName;
	    this.createTime = createTime;
	    this.msgType = msgType;
	    this.msgId = msgId;
	    this.content = content;
	  }
}