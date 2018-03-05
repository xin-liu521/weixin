package com.wxhao.util;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wxhao.bean.TextMsg;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/27.
 */
public class WechatMessageUtil {
    /**
     *
     * 解析微信发来的请求（XML）
     *
     *
     *
     * @param request
     *
     * @return
     *
     * @throws Exception
     *
     */

    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {

        // 将解析结果存储在HashMap中

        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流

        InputStream inputStream = request.getInputStream();

        // 读取输入流

        SAXReader reader = new SAXReader();

        Document document = reader.read(inputStream);

        String requestXml = document.asXML();

        String subXml = requestXml.split(">")[0] + ">";

        requestXml = requestXml.substring(subXml.length());

        // 得到xml根元素

        Element root = document.getRootElement();

        // 得到根元素的全部子节点

        List<Element> elementList = root.elements();

        // 遍历全部子节点

        for (Element e : elementList) {

            map.put(e.getName(), e.getText());

        }

        map.put("requestXml", requestXml);

        // 释放资源

        inputStream.close();

        inputStream = null;

        return map;

    }

    /**

     * 将文本消息对象转成XML

     * @param textMessage

     * @return

     */

    public static String textMessageToXml(TextMsg textMessage){
        //将xml的根节点替换成<xml>  默认为TextMessage的包名

        xstream.alias("xml", textMessage.getClass());

        return xstream.toXML(textMessage);

    }

    /**

     * 拼接关注主菜单

     */

    public static String menuText(){

        StringBuffer sb = new StringBuffer();

        sb.append("欢迎关注店小二公众号，回复数字:\n\n");

        sb.append("1、查看XXXXX。\n");

        sb.append("2、查看XXXXX。\n\n");

        sb.append("回复？查看XXXXX。\n\n");

        return sb.toString();

    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver(){
        public HierarchicalStreamWriter createWriter(Writer out) {

            return new PrettyPrintWriter(out){
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
