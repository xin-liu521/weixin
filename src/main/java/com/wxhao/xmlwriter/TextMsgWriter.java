package com.wxhao.xmlwriter;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.helpers.AttributesImpl;

import com.wxhao.responsemsg.ResPonseTextMsg;

/**
 * @ClassName: TextMsgWriter
 * @Description: 文本消息xml 生成器
 * @version V1.0
 */
public class TextMsgWriter {

	/**
	 * <p>Title: getXmlString<／p>
	 * <p>Description: 将文本消息转换成xml格式字符串<／p>
	 * @param textMsg 文本消息
	 * @return
	 * @throws Exception
	 * @version 1.0
	 */
	public static  String getXmlString(ResPonseTextMsg textMsg) throws Exception{
		
		StringWriter writerStr = new StringWriter();  
        Result resultXml = new StreamResult(writerStr);
        
		SAXTransformerFactory sff = (SAXTransformerFactory)SAXTransformerFactory.newInstance();  
        TransformerHandler th = sff.newTransformerHandler();  
        th.setResult(resultXml);  
          
        Transformer transformer = th.getTransformer();  
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //编码格式是UTF-8  
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        th.startDocument(); //开始xml文档  
        AttributesImpl attr = new AttributesImpl();  
        th.startElement("", "", "xml", attr); //定义xml节点  
        
        if(textMsg.getToUserName() != null){
	        th.startElement("", "", "ToUserName", attr); //定义ToUserName节点  
	        th.startCDATA();
	        th.characters(textMsg.getToUserName().toCharArray(), 0, textMsg.getToUserName().length());  
	        th.endCDATA();
	        th.endElement("", "", "ToUserName"); //结束ToUserName节点     
        }
        
        if(textMsg.getFromUserName() != null){
	        th.startElement("", "", "FromUserName", attr); //定义FromUserName节点  
	        th.startCDATA();
	        th.characters(textMsg.getFromUserName().toCharArray(), 0, textMsg.getFromUserName().length());  
	        th.endCDATA();
	        th.endElement("", "", "FromUserName"); //结束FromUserName节点      
        }
        
        if(textMsg.getCreateTime() != null){
	        th.startElement("", "", "CreateTime", attr); //定义CreateTime节点  
	        th.startCDATA();
	        th.characters(textMsg.getCreateTime().toCharArray(), 0, textMsg.getCreateTime().length());  
	        th.endCDATA();
	        th.endElement("", "", "CreateTime"); //结束CreateTime节点         
        }
        
        if(textMsg.getMsgType() != null){
	        th.startElement("", "", "MsgType", attr); //定义MsgType节点  
	        th.startCDATA();
	        th.characters(textMsg.getMsgType().toCharArray(), 0, textMsg.getMsgType().length()); 
	        th.endCDATA();
	        th.endElement("", "", "MsgType"); //结束MsgType节点      
        }
        
        if(textMsg.getContent() != null){
	        th.startElement("", "", "Content", attr); //定义Content节点  
	        th.startCDATA();
	        th.characters(textMsg.getContent().toCharArray(), 0, textMsg.getContent().length());
	        th.endCDATA();
	        th.endElement("", "", "Content"); //结束Content节点      
        }
        
        if(textMsg.getMsgId() != null){
	        th.startElement("", "", "MsgId", attr); //定义MsgId节点  
	        th.startCDATA();
	        th.characters(textMsg.getMsgId().toCharArray(), 0, textMsg.getMsgId().length());  
	        th.endCDATA();
	        th.endElement("", "", "MsgId"); //结束MsgId节点      
        }
        
        th.endElement("", "", "xml"); //结束xml节点  
        th.endDocument(); //结束xml文档  

        return writerStr.getBuffer().toString();
	}
}
