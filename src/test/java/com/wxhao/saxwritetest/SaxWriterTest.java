package com.wxhao.saxwritetest;

import org.junit.Test;

import com.wxhao.responsemsg.ResPonseTextMsg;
import com.wxhao.xmlwriter.TextMsgWriter;

public class SaxWriterTest {

	@Test
	public void test1() throws Exception{
		ResPonseTextMsg textMsg = new ResPonseTextMsg();
		textMsg.setContent("god");
		textMsg.setCreateTime("1234567890");
		textMsg.setFromUserName("mike");
		textMsg.setMsgId("1234");
		textMsg.setMsgType("text");
		textMsg.setToUserName("lucy");
		System.out.println(TextMsgWriter.getXmlString(textMsg));
	}
}
