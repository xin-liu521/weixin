package com.wxhao.saxparsetest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.SAXException;


public class TestMain {

	@Test
	public void parserTest(){
		SAXParser saxParser = null;
		try {
			//构建SAXParser  
			saxParser = SAXParserFactory.newInstance().newSAXParser();
			//实例化  DefaultHandler对象  
			SaxParserTestHandler saxParserTestHandler = new SaxParserTestHandler();
			//加载资源文件 转化为一个输入流  
            InputStream stream = SaxParserTestHandler.class.getClassLoader().getResourceAsStream("Students.xml");
            //调用parse()方法  
            saxParser.parse(stream, saxParserTestHandler);
            List<Student> studentList = saxParserTestHandler.getStudentList();
            System.out.println(studentList);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
