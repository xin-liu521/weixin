package com.wxhao.saxparsetest;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @ClassName: SaxParserTestHandler
 * @Description: sax解析xml示例
 * @author zhutulang
 * @date 2016年1月2日
 * @version V1.0
 */
public class SaxParserTestHandler extends DefaultHandler {
	
	 
	 private List<Student> studentList;
	 private Student student;
	 //用来存放每次遍历后的元素名称(节点名称)  
	 private String tagName;
	 
	 @Override  
	 public void startDocument() throws SAXException {  
		 studentList = new ArrayList<Student>();
	 }
	 
	 @Override  
	 public void startElement(String uri, String localName, String qName,  
	            Attributes attributes) throws SAXException {  
		 if(qName.equals("student")){
			 student = new Student();
		 }
		 this.tagName = qName;
	 }
	 
	 @Override  
	 public void endElement(String uri, String localName, String qName)  
	 throws SAXException { 
		 if(qName.equals("student")){
			 studentList.add(student);
		 }
		 this.tagName = null;
	 }
	 
	 @Override  
	 public void endDocument() throws SAXException {
		 
	 }
	 
	 @Override  
	 public void characters(char[] ch, int start, int length) throws SAXException {  
		 if(tagName != null){
			 String data = new String(ch,start,length);
			 if(tagName.equals("name")){
				 student.setName(data);
			 }else if(tagName.equals("age")){
				 student.setAge(data);
			 } 
		 }
	 }
	 
	 public List<Student> getStudentList() {
			return studentList;
	 }
}
